import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BOJ_2108 {
    
public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		int sum = 0;
		int[] nums = new int[n];
		
		Map<Integer, Integer> maps = new HashMap<>(); // 빈도 저장 맵
		for (int i = 0; i < n; i++) {
			nums[i] = sc.nextInt();
			
			sum += nums[i];
			
			if (!maps.containsKey(nums[i])) { // 빈도 계산
				maps.put(nums[i], 1);
			} else {
				maps.put(nums[i], maps.get(nums[i]) + 1);
			}
			
		}
		
		sc.close();
		
		Arrays.sort(nums); // 오름차순 정렬
		
		int check = 0; // 빈도 확인 변수
		int cnt = 0; // 빈도
		int value = 0; // 실제 숫자 값
		for (int num : nums) {
			if (value == num && maps.get(num) == cnt) {
				continue; // 앞서 갱신했던 동일한 값이면 무시
			}
			
			if (maps.get(num) > cnt) { // 빈도가 더 높은 경우
				cnt = maps.get(num); // 빈도 갱신
				check = 1; // 빈도 확인 체크
				value = num; // 실제 값 갱신
				continue;
			}
			
			if (check == 1 && maps.get(num) == cnt) { // 두 번째로 작은 값 조건
				check = 0; // 빈도 확인 원복
				value = num; // 실제 값 갱신
			}
		}
		
		System.out.println((int) Math.round((double) sum / n));
		System.out.println(nums[n / 2]);
		System.out.println(value);
		System.out.println(nums[n - 1] - nums[0]);
		
	}

}
