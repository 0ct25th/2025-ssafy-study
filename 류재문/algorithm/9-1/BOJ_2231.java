import java.util.Scanner;

public class BOJ_2231 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		sc.close();
		
		for (int i = 1; i < n; i++) {
			String str = i + "";
			
			int result = i;
			for (int j = 0; j < str.length(); j++) {
				result += str.charAt(j) - '0';
			}
			
			if (result == n) {
				System.out.println(i);
				return;
			}
		}
		
		System.out.println(0);
		
	}
}

