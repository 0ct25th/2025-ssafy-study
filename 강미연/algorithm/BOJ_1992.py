import sys

n = int(sys.stdin.readline())
arr = [sys.stdin.readline() for _ in range(n)]


def quadtree(x, y, size):
    first_pixel = arr[x][y]
    flag = True
    for i in range(x, x+size):
        for j in range(y, y+size):
            if arr[i][j] != first_pixel:
                flag = False
                break
        if not flag:
            break
    if flag:
        return first_pixel
    else:
        half_size = size//2
        top_left = quadtree(x, y, half_size)
        top_right = quadtree(x, y+half_size, half_size)
        bottom_left = quadtree(x+half_size, y, half_size)
        bottom_right = quadtree(x+half_size, y+half_size, half_size)
        return f"({top_left}{top_right}{bottom_left}{bottom_right})"


print(quadtree(0, 0, n))
