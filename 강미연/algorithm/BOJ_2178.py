import sys
from collections import deque

di = [1, -1, 0, 0]
dj = [0, 0, 1, -1]

n, m = map(int, sys.stdin.readline().split())
arr = [list(map(int, sys.stdin.readline().strip())) for _ in range(n)]

dq = deque([(0, 0)])

while dq:
    x, y = dq.popleft()
    if x == n-1 and y == m-1:
        print(arr[x][y])
        sys.exit(0)
    for d in range(4):
        nx, ny = x+di[d], y+dj[d]
        if 0 <= nx < n and 0 <= ny < m and arr[nx][ny] == 1:
            arr[nx][ny] = arr[x][y]+1
            dq.append((nx, ny))
