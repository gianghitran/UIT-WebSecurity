#!/bin/bash

PORT_LIST_CSV="top-1000-most-popular-tcp-ports-nmap-sorted.csv"

if grep -q "," "$PORT_LIST_CSV"; then
    PORTS=$(cat "$PORT_LIST_CSV" | tr -d '\n')
else
    PORTS=$(cat "$PORT_LIST_CSV" | tr '\n' ',' | sed 's/,$//')
fi
echo "Ports to scan (first 100 chars): ${PORTS:0:100}..."

IP_FILE_RAW="ips_subdomain_indriver.txt"
IP_FILE="ips_clean.txt"
OUTPUT_CSV="indriver_port_scan_results.csv"

if [[ ! -f "$IP_FILE_RAW" ]]; then
    echo "Error: IP file $IP_FILE_RAW not found!"
    exit 1
fi

echo "Cleaning IP addresses from $IP_FILE_RAW..."
cat "$IP_FILE_RAW" | \
    sed 's/[^0-9\.\n:]//g' | \
    grep -E '^[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}$' | \
    sort -u > "$IP_FILE"

if [[ ! -s "$IP_FILE" ]]; then
    echo "Error: No valid IP addresses found after cleaning!"
    echo "Please check the format of $IP_FILE_RAW"
    exit 1
fi

echo "Found $(wc -l < "$IP_FILE") valid IP addresses after cleaning"
echo "First 5 IPs to scan:"
head -5 "$IP_FILE"
echo ""

echo "ip,open_ports" > "$OUTPUT_CSV"

echo "Starting nmap scan..."
while read -r ip; do
    ip=$(echo "$ip" | tr -d '\r\n' | xargs)
    [[ -z "$ip" ]] && continue
    
    # Validate IP format (IPv4)
    if ! [[ "$ip" =~ ^[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}$ ]]; then
        echo "Skipping invalid IP: $ip"
        continue
    fi
    
    echo "Scanning $ip ..."
    TMP_RESULT=$(mktemp)
    
    nmap -Pn -n -p "$PORTS" "$ip" -oG "$TMP_RESULT" 2>/dev/null
    
    open_ports=""
    if grep -q "open" "$TMP_RESULT"; then
        open_ports=$(grep "open" "$TMP_RESULT" | grep -oE '[0-9]+/open' | cut -d'/' -f1 | sort -n | tr '\n' ',' | sed 's/,$//')
    fi
    
 
    if [[ -z "$open_ports" ]]; then
        open_ports=$(awk '/Ports:/ {for(i=1;i<=NF;i++) if($i ~ /\/open\//) {split($i,a,"/"); print a[1]}}' "$TMP_RESULT" | sort -n | tr '\n' ',' | sed 's/,$//')
    fi
    
    if [[ -n "$open_ports" ]]; then
        echo "$ip,$open_ports" >> "$OUTPUT_CSV"
        echo "Found open ports: $open_ports"
    else
        echo "$ip,no_open_ports" >> "$OUTPUT_CSV"
        echo "No open ports found"
    fi
    
    rm -f "$TMP_RESULT"
    
    sleep 0.5
done < "$IP_FILE"

echo "Ressaved to: $OUTPUT_CSV"


echo "SUM ================="
raw_ips=$(wc -l < "$IP_FILE_RAW" 2>/dev/null || echo "0")
clean_ips=$(wc -l < "$IP_FILE")
successful_scans=$(grep -v "no_open_ports" "$OUTPUT_CSV" | grep -v "ip,open_ports" | wc -l)
failed_scans=$(grep "no_open_ports" "$OUTPUT_CSV" | wc -l)

echo "Raw IPs in original file: $raw_ips"
echo "Results file: $OUTPUT_CSV"

head -10 "$OUTPUT_CSV"
echo "..."
