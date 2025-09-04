// BOJ4195 친구 네트워크

import java.io.*;
import java.util.*;

public class Main {
    static int[] parent, total;
    static HashMap<String, Integer> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int tc = Integer.parseInt(br.readLine());
        for (int T = 0; T < tc; T++) {
            int F = Integer.parseInt(br.readLine());
            int idx = 0;    // 친구 인덱스
            parent = new int[F * 2];
            total = new int[F * 2];   // 총 친구 수
            map = new HashMap<>();

            Arrays.fill(parent, -1);
            Arrays.fill(total, 1);

            for (int f = 0; f < F; f++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String f1 = st.nextToken();
                String f2 = st.nextToken();

                // 친구와 고유번호 함께 저장
                if (!map.containsKey(f1)) {
                    map.put(f1, idx++);
                }
                if (!map.containsKey(f2)) {
                    map.put(f2, idx++);
                }

                // 인덱스 가지고 union
                int a = map.get(f1);
                int b = map.get(f2);

                union(a, b);
                int root = findParent(a);
                int friends = total[root];

                sb.append(friends).append("\n");
            }
        }
        System.out.println(sb);
    }

    public static void union(int a, int b) {
        int pa = findParent(a);
        int pb = findParent(b);

        // 작은 수 기준 union
        if (pa != pb) {
            if (pa < pb) {
                parent[pb] = pa;
                total[pa] += total[pb]; // 친구 수 추가
            } else {
                parent[pa] = pb;
                total[pb] += total[pa]; // 친구 수 추가
            }
        }
    }

    public static int findParent(int a) {
        if (parent[a] == -1) return a;  // 자기 자신이 루트

        return parent[a] = findParent(parent[a]);
    }
}