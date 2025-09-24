import java.util.Scanner;

public class BOJ_2775 {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		
		for (int tc = 0; tc < t; tc++) {
			int k = sc.nextInt();
			int n = sc.nextInt();
			
			int[][] dp = new int[k + 1][n + 1];
			
			for (int i = 1; i <= n; i++) {
				dp[0][i] = i;
			}
			
			for (int i = 1; i <= k; i++) {
				for (int j = 1; j <= n; j++) {
					dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
				}
			}
			
			System.out.println(dp[k][n]);
		}
		
		sc.close();
	}
}
