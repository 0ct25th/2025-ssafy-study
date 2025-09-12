n = int(input())
lst = list(map(int, input().split()))

dp = [0]*n
dp[0] = lst[0]
res = dp[0]

for i in range(1, n):
    dp[i] = max(lst[i], dp[i-1]+lst[i])
    if dp[i] > res:
        res = dp[i]

print(res)
