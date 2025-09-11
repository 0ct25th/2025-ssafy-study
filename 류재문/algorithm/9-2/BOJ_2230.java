import java.util.Arrays;
import java.util.Scanner;

public class BOJ_2230 {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		
		sc.close();
	
		Arrays.sort(arr);
		
		int result = Integer.MAX_VALUE;
		int s = 0, e = 0;
		
		while (s < n && e < n) {
			int diff = arr[e] - arr[s];
			
			if (diff >= m) {
				result = Math.min(result, diff); // 최소값 갱신
				s++;
			} else {
				e++;
			}
		}
		
		System.out.println(result);
		
	}

}

