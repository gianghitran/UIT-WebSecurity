import itertools
import string


template = "baomatweb-{}86{}5{}{}{}.studywithme.info"


charset = string.ascii_lowercase

with open("vhost_wordlist.txt", "w") as f:
    for p in itertools.product(charset, repeat=5):
        subdomain = template.format(p[0], p[1], p[2], p[3], p[4])
        f.write(subdomain + "\n")

print("Đã tạo xong file vhost_wordlist.txt!")
