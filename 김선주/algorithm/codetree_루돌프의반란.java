import java.io.*;
import java.util.*;

public class Main {
    static int N, M, P, C, D, rudolphR, rudolphC;
    static int[][] map;     // 게임판
    static int[] scores;    // 산타 점수
    static int[] dx = {-1, 0, 1, 0, 1, -1, 1, -1}; // 상우하좌 대각선
    static int[] dy = {0, 1, 0, -1, 1, -1, -1, 1};
    static int dir; // 루돌프가 이동하는 방향
    static int[][] santas;   // 산타 위치
    static int[] shocked;  // 기절한 산타
    static boolean[] isAlive;   // 살아있는 산타


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // 게임판 크기
        M = Integer.parseInt(st.nextToken());   // 턴 수
        P = Integer.parseInt(st.nextToken());   // 산타 수
        C = Integer.parseInt(st.nextToken());   // 루돌프 힘
        D = Integer.parseInt(st.nextToken());   // 산타 힘

        map = new int[N + 1][N + 1];
        scores = new int[P + 1];
        santas = new int[P + 1][2];
        shocked = new int[P + 1];
        isAlive = new boolean[P + 1];
        Arrays.fill(isAlive, true);

        st = new StringTokenizer(br.readLine());
        rudolphR = Integer.parseInt(st.nextToken());
        rudolphC = Integer.parseInt(st.nextToken());

        map[rudolphR][rudolphC] = -1;     // 루돌프 위치
        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            santas[num] = new int[]{r, c};

            map[r][c] = num;    // 산타 위치
        }

        // M판 게임 시작
        for (int game = 0; game < M; game++) {

            // 루돌프에서 가장 가까운 산타
            int santaNum = findSanta();

            // 가장 가까운 산타에게 가는 방향
            dir = findDir(santaNum);

            // 루돌프 이동
            rudolphR += dx[dir];
            rudolphC += dy[dir];

            // 루돌프가 이동해서 산타와 충돌
            if (rudolphR == santas[santaNum][0] && rudolphC == santas[santaNum][1]) {

                // 산타 점수
                scores[santaNum] += C;

                santas[santaNum][0] += C * dx[dir];
                santas[santaNum][1] += C * dy[dir];

                //  산타가 범위 밖으로 나감
                if (santas[santaNum][0] <= 0 || santas[santaNum][1] <= 0 || santas[santaNum][0] >= N + 1 || santas[santaNum][1] >= N + 1) {
                    isAlive[santaNum] = false;
                }

                interact(santaNum, santas[santaNum][0], santas[santaNum][1], dir);

                // 산타 기절
                shocked[santaNum] = 2;
            }

            // 산타 이동
            for (int i = 1; i <= P; i++) {
                if (isAlive[i] && shocked[i] == 0) {   // 기절 안하고 살아있는 산타만
                    int santaDir = santaDir(i); // 산타 이동 방향
                    if (santaDir != -1) {   // 이동할 수 있을 때만
                        santas[i][0] += dx[santaDir];
                        santas[i][1] += dy[santaDir];

                        // 산타가 이동해서 루돌프와 충돌
                        if (santas[i][0] == rudolphR && santas[i][1] == rudolphC) {
                            scores[i] += D;

                            // 산타 이동한 반대로 D칸
                            santas[i][0] += D * dx[santaDir] * -1;
                            santas[i][1] += D * dy[santaDir] * -1;
                            int pushDir = (santaDir + 2) % 4; // 산타 이동할 방향

                            interact(i, santas[i][0], santas[i][1], pushDir);

                            //  범위에서 벗어남
                            if (santas[i][0] <= 0 || santas[i][1] <= 0 || santas[i][0] >= N + 1 || santas[i][1] >= N + 1) {
                                isAlive[i] = false;
                            }


                            // 산타 기절
                            shocked[i] = 2;
                        }
                    }
                }
            }

            for (int i = 1; i <= P; i++) {
                if (isAlive[i]) {   // 살아있는 산타만 점수 추가
                    scores[i]++;
                }

                // 기절 시간 단축
                if (shocked[i] != 0) {
                    shocked[i]--;
                }
            }
        }

        for (int i = 1; i <= P; i++) {
            System.out.print(scores[i] + " ");
        }
    }

    // 가장 가까운 산타
    public static int findSanta() {
        int min = Integer.MAX_VALUE;
        int santaNum = 0;
        for (int i = 1; i <= P; i++) {
            if (isAlive[i]) {   // 산타 살아있을 때
                int distance = (int) (Math.pow(rudolphR - santas[i][0], 2) + Math.pow(rudolphC - santas[i][1], 2));
                if (distance < min) {
                    min = distance;
                    santaNum = i;
                } else if (distance == min) {   // 최소 거리 같을 때
                    if (santas[santaNum][0] < santas[i][0]) {   // R좌표가 더 큰 산타
                        santaNum = i;
                    } else if (santas[santaNum][0] == santas[i][0]) {
                        if (santas[santaNum][1] < santas[i][1]) {    // C좌표가 더 큰 산타
                            santaNum = i;
                        }
                    }
                }
            }
        }
        return santaNum;
    }

    // 가장 가까운 산타에게로 가는 방향
    public static int findDir(int santaNum) {
        int dir = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 8; i++) {
            int nx = rudolphR + dx[i];
            int ny = rudolphC + dy[i];

            //   범위 벗어나면 패스
            if (nx <= 0 || ny <= 0 || nx >= N + 1 || ny >= N + 1) continue;

            // 거리 확인
            int distance = (int) (Math.pow(nx - santas[santaNum][0], 2) + Math.pow(ny - santas[santaNum][1], 2));

            if (distance < min) {
                min = distance;
                dir = i;    // i번째 방향
            }
        }

        return dir;
    }

    // 루돌프에게로 가는 방향
    public static int santaDir(int santa) {
        int min = Integer.MAX_VALUE;
        int dir = -1;
        int curDistance = (int) (Math.pow(rudolphR - santas[santa][0], 2) + Math.pow(rudolphC - santas[santa][1], 2));
        if (isAlive[santa] && shocked[santa] == 0) {    // 살아있거나 기절 상태가 아닐 때만
            for (int j = 0; j < 4; j++) {
                int nr = santas[santa][0] + dx[j];
                int nc = santas[santa][1] + dy[j];

                // 범위 확인
                if (nr <= 0 || nc <= 0 || nr >= N + 1 || nc >= N + 1 || isSanta(nr, nc)) continue;
                int distance = (int) (Math.pow(rudolphR - nr, 2) + Math.pow(rudolphC - nc, 2));

                // 거리가 가까워지고, 최소일 때만
                if (distance < min && distance < curDistance) {
                    min = distance;
                    dir = j;
                }
            }
        }
        return dir;
    }

    // 이동하려는 위치에 다른 산타가 있는지 확인
    public static boolean isSanta(int r, int c) {
        for (int i = 1; i <= P; i++) {
            if (santas[i][0] == r && santas[i][1] == c && isAlive[i]) return true;
        }
        return false;
    }

    // 상호작용
    public static void interact(int santa, int r, int c, int dir) {
        for (int i = 1; i <= P; i++) {
            // 충돌한 산타가 있을 때
            if (i != santa && santas[i][0] == r && santas[i][1] == c) {
                santas[i][0] += dx[dir];
                santas[i][1] += dy[dir];

                // 범위 벗어나면 죽음
                if (santas[i][0] <= 0 || santas[i][1] <= 0 || santas[i][0] >= N + 1 || santas[i][1] >= N + 1) {
                    isAlive[i] = false;
                }

                interact(i, santas[i][0], santas[i][1], dir);
            }
        }
    }
}

