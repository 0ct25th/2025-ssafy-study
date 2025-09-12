import java.util.Arrays;
import java.util.Scanner;

public class BOJ_1253 {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		
		sc.close();
		
		// 정렬
		Arrays.sort(arr);
		
		int result = 0;
		for (int i = 0; i < n; i++) {
			
			int s = 0, e = n - 1;
			
			while (s < e) {
				
				if (s == i) { // 본인을 포함하면 안됨
					s++;
					continue;
				}
				
				if (e == i) { // 본인을 포함하면 안됨
					e--;
					continue;
				}
				
				if (arr[s] + arr[e] == arr[i]) { // 두 수의 합이 같은 경우
					result++;
					break;
				}
				
				if (arr[s] + arr[e] < arr[i]) { // 두 수의 합이 작은 경우
					s++;
				}
				
				if (arr[s] + arr[e] > arr[i]) { // 두 수의 합이 큰 경우
					e--;
				}
			}
		}
		
		System.out.println(result);
	}
}

