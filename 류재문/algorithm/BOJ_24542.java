import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_24542 {
	
	static int[] parent;
	static final int MOD = 1_000_000_007;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
		
		parent = new int[n + 1]; // 초기화: 각 노드는 자기 자신이 부모
        for (int i = 1; i < n + 1; i++) {
        	parent[i] = i;
        }

        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            union(u, v);
        }

        int[] count = new int[n + 1]; // 해당 부모에 속한 자식 개수를 count 배열에 저장
        for (int i = 1; i < n + 1; ++i) {
        	count[find(i)]++;
        }

        long result = 1; // 결과 계산: 각 자식의 수를 모두 곱한 값
        for (int i = 1; i < n + 1; ++i) {
        	if (parent[i] == i) {
            	result = (result * count[i]) % MOD;
            }
        }

        System.out.println(result);
    }

    static void union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);
        
        if (parentA == parentB) {
        	return; // 이미 같은 집합이면 무시
        }

        if (parentA < parentB) { // 더 작은 자식을 가진 부모를 부모로 설정
        	parent[parentB] = parentA;
        	return;
        }
        
        parent[parentA] = parentB;
    }

    static int find(int x) {
        if (parent[x] != x) {
        	parent[x] = find(parent[x]); // 부모를 최상위 부모로 갱신
        }
        
        return parent[x];
    }

}

