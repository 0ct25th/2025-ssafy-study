import java.io.*;
import java.util.*;

public class Main {
	static int N, L, R, result;
	static int[][] map;
	static boolean[][] isVisited;
	static List<List<Coord>> association;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++)
				map[r][c] = Integer.parseInt(st.nextToken());
		}

		while (ready() > 0) {
			result++;
			for (List<Coord> a : association)
				move(a);
		}

		System.out.println(result);
	}

	static void move(List<Coord> lst) {
		int sum = 0;
		for (Coord c : lst)
			sum += map[c.r][c.c];
		
		int population = sum / lst.size();
		for (Coord c : lst)
			map[c.r][c.c] = population;
	}

	static int ready() {
		association = new ArrayList<>();
		isVisited = new boolean[N][N];
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (isVisited[r][c])
					continue;
				
				List<Coord> lst = research(r, c);
				if (lst.size() > 0)
					association.add(lst);
			}
		}
		
		return association.size();
	}

	static List<Coord> research(int sr, int sc) {
		Queue<Coord> dq = new ArrayDeque<>();
		List<Coord> lst = new ArrayList<>();
		
		isVisited[sr][sc] = true;
		dq.offer(new Coord(sr, sc));
		
		while (!dq.isEmpty()) {
			Coord cur = dq.poll();
			int r = cur.r;
			int c = cur.c;
			
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if (!isValidCoord(nr, nc) || isVisited[nr][nc])
					continue;
				
				if (!isDifference(r, c, nr, nc))
					continue;
				
				isVisited[nr][nc] = true;
				dq.offer(new Coord(nr, nc));
				lst.add(new Coord(nr, nc));
			}
		}
		
		if (lst.size() != 0)
			lst.add(new Coord(sr, sc));
		
		return lst;
	}

	static boolean isDifference(int r, int c, int nr, int nc) {
		int minus = Math.abs(map[nr][nc] - map[r][c]);
		
		return L <= minus && minus <= R;
	}

	static boolean isValidCoord(int r, int c) {
		return -1 < r && r < N && -1 < c && c < N;
	}

	static class Coord {
		int r, c;

		Coord(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
