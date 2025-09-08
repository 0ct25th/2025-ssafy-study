// BOJ20955 민서의 응급 수술
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];
        Arrays.fill(parent, -1);    // -1로 초기화

        count = 0;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            // 같은 집합에 속함 -> 사이클 발생
            if (!union(u, v)) {
                count++;
            }
        }

        // 총 집합 개수 확인 -> 연결
        for (int i = 1; i <= N; i++) {
            if (parent[i] < 0) {
                count++;
            }
        }

        System.out.println(count - 1);

    }

    // 루트 찾기
    public static int findParent(int a) {
        if (parent[a] < 0)  // 자기 자신이 루트
            return a;

        return parent[a] = findParent(parent[a]);
    }

    // 합치기
    public static boolean union(int a, int b) {
        int pa = findParent(a);
        int pb = findParent(b);

        if (pa == pb) return false;

        // 작은 숫자 기준으로 union
        if (pa < pb) {
            parent[pb] = pa;
        } else {
            parent[pa] = pb;
        }

        return true;
    }
}