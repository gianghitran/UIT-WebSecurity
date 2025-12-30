import base64
from Crypto.Cipher import AES

key_string = "This is the super secret key 123"
key = key_string.encode('utf-8') 
iv = bytes([0]*16)              
encrypted_b64 = "kV+Ddug3sfr6fNMRut+QQg=="
encrypted_bytes = base64.b64decode(encrypted_b64)

cipher = AES.new(key, AES.MODE_CBC, iv)
decrypted_bytes = cipher.decrypt(encrypted_bytes)

padding_len = decrypted_bytes[-1]
password = decrypted_bytes[:-padding_len].decode('utf-8')

print("Decrypt: " + password)