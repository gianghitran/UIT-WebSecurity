#!/bin/bash

# Kiểm tra file input
INPUT_FILE="subdomains_indriver.txt"
OUTPUT_FILE="indriver_subdomains.csv"

if [ ! -f "$INPUT_FILE" ]; then
    echo "notfound file  $INPUT_FILE"
    exit 1
fi

# Ghi tiêu đề CSV
echo "subdomain,ip" > "$OUTPUT_FILE"

# Duyệt qua từng subdomain
while read -r subdomain; do
    if [[ -n "$subdomain" ]]; then
        nslookup "$subdomain" | grep "^Address" | awk '{print $2}' | while read -r ip; do
        echo "$subdomain,$ip" >> "$OUTPUT_FILE"
        echo "==> $subdomain => $ip"
   done
   fi
done < "$INPUT_FILE"

echo "Saved $OUTPUT_FILE"

