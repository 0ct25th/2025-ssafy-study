import sys

n = int(sys.stdin.readline())
stack = []
res = []
i = 1

for _ in range(n):
    num = int(sys.stdin.readline())
    while i <= num:
        stack.append(i)
        res.append('+')
        i += 1
    if stack and stack[-1] == num:
        stack.pop()
        res.append('-')
    else:
        print('NO')
        sys.exit(0)

print('\n'.join(res))
