import java.util.Scanner;

public class BOJ_5582 {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		String[] str1 = sc.nextLine().split("");
		String[] str2 = sc.nextLine().split("");
		
		sc.close();
		
		int[][] dp = new int[str1.length + 1][str2.length + 1];
		
		int len = 0;
		for (int i = 1; i < str1.length + 1; i++) {
			for (int j = 1; j < str2.length + 1; j++) {
				if (str1[i - 1].equals(str2[j - 1])) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
					len = Math.max(len, dp[i][j]);
				} else {
					dp[i][j] = 0;
				}
			}
		}
		
		System.out.println(len);
	}

}

