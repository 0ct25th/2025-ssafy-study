import sys
from collections import Counter

name = sys.stdin.readline().strip()
cnt = Counter(name)
odd_char = ''
odd_count = 0
res = ''

for char in sorted(cnt.keys()):
    if cnt[char]%2 != 0:
        odd_char += char
        odd_count += 1
    res += char*(cnt[char]//2)

if odd_count > 1:
    print("I'm Sorry Hansoo")
else:
    print(res+odd_char+res[::-1])
