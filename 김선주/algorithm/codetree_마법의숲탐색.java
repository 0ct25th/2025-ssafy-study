import java.io.*;
import java.util.*;

public class Main {
    static int R, C, K, result, golem_num;
    static int[][] forest;

    // 4방향 이동
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    // 아래 이동
    static int[] down_dx = {1, 2, 1};
    static int[] down_dy = {-1, 0, 1};

    // 왼쪽 이동
    static int[] left_dx = {-1, 0, 1, 1, 2};
    static int[] left_dy = {-1, -2, -1, -2, -1};

    // 오른쪽 이동
    static int[] right_dx = {-1, 0, 1, 1, 2};
    static int[] right_dy = {1, 2, 1, 2, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());   // 행
        C = Integer.parseInt(st.nextToken());   // 열
        K = Integer.parseInt(st.nextToken());   // 정령 수

        // (1) 윗쪽 버퍼 3칸 포함
        forest = new int[R + 3][C + 1];
        result = 0;
        golem_num = 1;

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            Golem golem = new Golem(0, c, d);

            // 이동
            while (true) {
                // 아래로 이동
                if (moveDown(golem)) {
                    golem.x++;
                } else if (moveLeft(golem)) {   // 왼쪽 이동
                    golem.x++;
                    golem.y--;
                    golem.dir = golem.dir == 0 ? 3 : golem.dir - 1;
                } else if (moveRight(golem)) {  // 오른쪽 이동
                    golem.x++;
                    golem.y++;
                    golem.dir = golem.dir == 3 ? 0 : golem.dir + 1;
                } else {
                    if (!canSettle(golem)) {
                        forest = new int[R + 3][C + 1];
                        break; // 다음 골렘
                    }

                    forest[golem.x][golem.y] = golem_num;
                    golem_num += 2;

                    // 4방향 탐색
                    for (int j = 0; j < 4; j++) {
                        int nx = golem.x + dx[j];
                        int ny = golem.y + dy[j];

                        if (!isPossible(nx, ny)) continue;

                        // 출구일 때 -> 짝수로 표시
                        if (golem.dir == j) {
                            forest[nx][ny] = forest[golem.x][golem.y] + 1;
                        } else {
                            forest[nx][ny] = forest[golem.x][golem.y];
                        }
                    }
                    result += bfs(golem);
                    break;
                }
            }
        }
        System.out.println(result);
    }

    // 가장 남쪽으로 이동
    public static int bfs(Golem golem) {
        Queue<Golem> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[R + 3][C + 1];
        queue.add(golem);
        visited[golem.x][golem.y] = true;
        int distance = golem.x;

        while (!queue.isEmpty()) {
            Golem g = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = g.x + dx[i];
                int ny = g.y + dy[i];

                // 이동 불가
                if (!isPossible(nx, ny) || forest[nx][ny] == 0 || visited[nx][ny]) continue;

                if (// 같은 골렘 내부 이동
                        ((forest[g.x][g.y] % 2 == 0 ? forest[g.x][g.y] - 1 : forest[g.x][g.y]) ==
                                (forest[nx][ny] % 2 == 0 ? forest[nx][ny] - 1 : forest[nx][ny]))
                                ||
                                // 현재 칸이 출구이고, 이웃이 다른 골렘
                                ((forest[g.x][g.y] % 2 == 0) &&
                                        ((forest[g.x][g.y] % 2 == 0 ? forest[g.x][g.y] - 1 : forest[g.x][g.y]) !=
                                                (forest[nx][ny] % 2 == 0 ? forest[nx][ny] - 1 : forest[nx][ny])))) {
                    visited[nx][ny] = true;
                    queue.offer(new Golem(nx, ny, 5));

                    distance = Math.max(distance, nx);
                }
            }
        }
        return Math.max(0, distance - 2);
    }

    public static boolean moveDown(Golem golem) {
        for (int i = 0; i < 3; i++) {
            int nx = golem.x + down_dx[i];
            int ny = golem.y + down_dy[i];
            if (!fallAvailable(nx, ny)) return false;
        }
        return true;
    }

    public static boolean moveLeft(Golem golem) {
        for (int i = 0; i < 5; i++) {
            int nx = golem.x + left_dx[i];
            int ny = golem.y + left_dy[i];
            if (!fallAvailable(nx, ny)) return false;
        }
        return true;
    }

    public static boolean moveRight(Golem golem) {
        for (int i = 0; i < 5; i++) {
            int nx = golem.x + right_dx[i];
            int ny = golem.y + right_dy[i];
            if (!fallAvailable(nx, ny)) return false;
        }
        return true;
    }

    static boolean fallAvailable(int x, int y) {
        if (y <= 0 || y > C) return false;
        if (x <= 2) return true;
        if (x <= R + 2) return forest[x][y] == 0;
        return false;                           // R+2 아래는 불가
    }

    public static boolean isPossible(int x, int y) {
        return (3 <= x && x <= R + 2 && 1 <= y && y <= C);
    }

    // 골렘이 숲 내부에 있는지 체크
    static boolean isInside(int x, int y) {
        return (3 <= x && x <= R + 2 && 1 <= y && y <= C);
    }

    static boolean canSettle(Golem g) {
        if (!isInside(g.x, g.y)) return false;
        for (int j = 0; j < 4; j++) {
            int nx = g.x + dx[j];
            int ny = g.y + dy[j];
            if (!isInside(nx, ny)) return false;
        }
        return true;
    }

    static class Golem {
        int x;
        int y;
        int dir;    // 출구 방향

        public Golem(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
}