import java.util.Arrays;
import java.util.Scanner;

public class BOJ_22988 {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		long x = sc.nextLong();
		
		long[] arr = new long[n];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextLong();
		}
		
		sc.close();
		
		Arrays.sort(arr); // 오름차순 정렬
		
		int s = 0, e = n - 1, cnt = 0, remain = 0; // 시작, 끝, 절반이상 개수, 짝을 이루지 못한 개수
		long half = (x + 1) / 2;
		
		while (s <= e) { // 두 포인터가 만나거나 교차할 경우 종료
			
			if (arr[e] >= x) { // 단독으로 목표 기준값 이상일 경우
				cnt++;
				e--;
				continue;
			}
			
			if (s == e) { // 두 포인터가 만날 경우
				remain++;
				break;
			}
			
			if (arr[s] + arr[e] >= half) { // 합이 절반 이상인 경우
				cnt++;
				s++;
				e--;
			} else { // 절반에 못미치는 경우
				s++;
				remain++;
			}
		}
		
		System.out.println(cnt + remain / 3); // 짝을 이루지 못한 병이 3개인 경우 1개로 교환이 가능
	}
}

