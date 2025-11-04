# Bài tập 1: Thực hiện lệnh WHOIS lookup với tên miền indriver.com.
1. Id của IANA của tên miền	trên là	gì? -  299
2. Tên miền trên được đăng ký khi nào? -2004-09-21T21:01:04Z
3. registar của tên	miền trên? - CSC Corporate Domains, Inc.
4. Công	ty nào được sử dụng cho dịch vụ name server? 
	Name Server: NS-1336.AWSDNS-39.ORG
    Name Server: NS-1696.AWSDNS-20.CO.UK
    Name Server: NS-294.AWSDNS-36.COM
    Name Server: NS-621.AWSDNS-13.NET
5. Địa chỉ admin contact email cho tên miền	trên? - domainabuse@cscglobal.com
# Bài tập 2: So sánh kết quả khi thực hiện nslookup và dig với loại query là mx với tên	miền indriver.com, thông tin nào được cung cấp thêm bởi lệnh DIG, ý nghĩa các thông tin đó như thế nào?
## So sánh 
- Nslookup: 
    - Chỉ gồm các thông tin cơ bản: DNS Server, Non-auth.. : phần trả lời lấy từ cache,mail exchanger : các bản ghi MX của indriver.com (tất cả đều trỏ về máy chủ google)
    - Không bao gồm các thông tin kĩ thuật chi tiết 
- Dig: 
    - Chi tiết kĩ thuật hơn nslookup:
    - Header:
    ```
    ;; ->>HEADER<<- opcode: QUERY, status: NOERROR, id: 35296
    ;; flags: qr rd ra; QUERY: 1, ANSWER: 5, AUTHORITY: 4, ADDITIONAL: 1
    ```
    opcode: loại truy vấn, trạng thái, flags, số bản ghi (counts)
    - Session answer tương tự nslookup nhưng có thêm TTL
    - Có thêm thông tin bổ sung về các truy vấn
        - Querry time
        - Server DNS được hỏi
        - THời điểm querry
        - size gói tin nhận


# Bài tập 4:
- Các  nguồn có thể tìm kiếm dữ liệu công khai tên miền phụ ở đâu?
    - Trong log cấp cert TLS/SSL,..
    - Dùng các công cụ truy vấn như gogoduck, google dork
    - Các công cụ pentest chuyên nghiệp để cralw các subdomain như subfinder, ZAP,...
    - Các công cụ phân tích DNS như dig,...
    - ..

- Script tải các link tìm kiếm được bằng gg dork vào csv
```
const subdomains = new Set();

document.querySelectorAll('a').forEach(link => {
    try {
        if (link.href && link.href.startsWith('http')) {
            const url = new URL(link.href);
            const hostname = url.hostname;
          
            if (hostname.endsWith('.indriver.com') && hostname !== 'indriver.com') {
                subdomains.add(hostname);
            }
        }
    } catch (e) 
});

let csvContent = "subdomain\n"; 
subdomains.forEach(sub => {
    csvContent += sub + "\n";
});

console.log(csvContent);
console.log("--- DONE ---");
```

# Bài tập 5:
- Tập   các danh    sách    tên miền    phụ có  thể tìm kiếm    ở   đâu và  
cách    nào để  đưa tên miền    phụ và  burpsuite   để  tìm kiếm?
    - Wordlist trên Internet

- worlist kia không có các subdomain ứng vs indriver.com subdomain --> dùng rockyou ( 1 wordlist nổi tiếng và có sẵn trên )


# Bài tập thực hành
## Bài 4
