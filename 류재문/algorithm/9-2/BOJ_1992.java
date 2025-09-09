import java.util.Scanner;

public class BOJ_1992 {

    static int[][] arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            String line = sc.next();
            for (int j = 0; j < n; j++) {
                arr[i][j] = line.charAt(j) - '0';
            }
        }
        sc.close();

        compress(0, 0, n);
        System.out.print(sb.toString());
    }

    // (x, y)부터 size×size 구간을 쿼드트리 규칙으로 압축
    static void compress(int x, int y, int size) {
        if (isUniform(x, y, size)) {
            sb.append(arr[x][y]); // 전부 0이거나 전부 1이면 그대로 기록
            return;
        }

        int half = size / 2;
        sb.append('(');
        // 왼쪽 위, 오른쪽 위, 왼쪽 아래, 오른쪽 아래 순서
        compress(x, y, half);
        compress(x, y + half, half);
        compress(x + half, y, half);
        compress(x + half, y + half, half);
        sb.append(')');
    }

    // 해당 구간이 모두 같은 값(0 또는 1)인지 검사
    static boolean isUniform(int x, int y, int size) {
        int first = arr[x][y];
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (arr[i][j] != first) return false;
            }
        }
        return true;
    }
}
