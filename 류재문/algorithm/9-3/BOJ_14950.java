import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ_14950_Prim {
		
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		int t = sc.nextInt();
				
		List<int[]>[] graph = new ArrayList[n + 1];
		int[] v = new int[n + 1];
		
		for (int i = 0; i <= n; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < m; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int weight = sc.nextInt();
			
			graph[a].add(new int[] {b, weight});
			graph[b].add(new int[] {a, weight});
			
		}
		
		sc.close();
		
		PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
		pq.add(new int[] {1, 0});
		
		int result = 0,cnt = 0;
		while (!pq.isEmpty()) {
			
			if (cnt == n) {
				break;
			}
			
			int[] poll = pq.poll();
			int node = poll[0];
			int weight = poll[1];
			
			if (v[node] == 1) {
				continue;
			}
			
			for (int[] nxt : graph[node]) {
				pq.add(nxt);
			}
			
			if (cnt > 0) {
				result += weight + t * (cnt - 1);
			} else {
				result += weight;				
			}
			
			v[node] = 1;
			cnt++;
		}
		
		System.out.println(result);
	}

}

