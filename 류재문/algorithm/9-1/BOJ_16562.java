import java.util.Scanner;

public class BOJ_16562 {
	
static int parent[], cost[];
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner (System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		int k = sc.nextInt();
		
		parent = new int[n + 1]; // 부모 배열
		cost = new int[n + 1]; // 친구비 배열
		for (int i = 1; i < n + 1; i++) {
			parent[i] = i;
			cost[i] = sc.nextInt();
		}
		
		for (int i = 0; i < m; i++) {
			int v = sc.nextInt();
			int w = sc.nextInt();
			
			union(v, w);
		}
		
		sc.close();
		
		int result = 0;
		for (int i = 1; i < n + 1; i++) {
			if (parent[i] == i) { // 부모가 본인인 경우 친구비 계산
				result += cost[i];
			}
		}
		
		if (result > k) {
			System.out.println("Oh no");
			return;
		}
		
		System.out.println(result);
	}
	
	static void union(int num1, int num2) {
		int a = find(num1);
		int b = find(num2);
		
		if (cost[a] > cost[b]) {
			parent[a] = b; // 친구비를 최소한으로 내기 위해 가장 작은 친구비가 부모가 되도록 설정
			return;
		}
		
		parent[b] = a;
	}
	
	static int find(int num) {
		if (parent[num] == num) {
			return num;
		}
		
		return find(parent[num]);
	}

}

