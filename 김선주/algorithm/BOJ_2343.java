// BOJ_2343 기타 레슨

import java.io.*;
import java.util.*;

public class Main {
    static int N, M, right, left;
    static int[] lec;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        lec = new int[N];

        int sum = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            lec[i] = Integer.parseInt(st.nextToken());
            sum += lec[i];
            left = Math.max(left, lec[i]);
        }

        right = sum;

        System.out.println(search());
    }

    public static int search() {
        while (left <= right) {
            int sum = 0;
            int mid = (left + right) / 2;
            int count = 1;

            for (int i = 0; i < N; i++) {
                sum += lec[i];
                if (sum > mid) {
                    sum = lec[i];
                    count++;
                }
            }

            if (count <= M) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}