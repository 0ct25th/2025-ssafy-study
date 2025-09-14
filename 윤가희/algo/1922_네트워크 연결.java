import java.io.*;
import java.util.*;

public class Main {

	static int N, M, result;
	static int[] p;
	static List<Computer> computers;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		M = Integer.parseInt(br.readLine());

		computers = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			computers.add(new Computer(a, b, c));
		}

		Collections.sort(computers, (o1, o2) -> Integer.compare(o1.w, o2.w));

		kruskal();

		System.out.println(result);
	}

	static void kruskal() {
		init();

		for (int i = 0; i < M; i++) {
			Computer cur = computers.get(i);
			int a = cur.a;
			int b = cur.b;
			int w = cur.w;

			if (union(a, b))
				continue;

			result += w;
		}
	}

	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot == bRoot)
			return true;

		p[bRoot] = aRoot;
		return false;
	}

	static int find(int x) {
		if (x == p[x])
			return x;

		return p[x] = find(p[x]);
	}

	static void init() {
		p = new int[N + 1];

		for (int i = 1; i <= N; i++)
			p[i] = i;
	}

	static class Computer {
		int a, b;
		int w;

		Computer(int a, int b, int w) {
			this.a = a;
			this.b = b;
			this.w = w;
		}
	}
}
