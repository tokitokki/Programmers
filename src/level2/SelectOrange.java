package src.level2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class SelectOrange { //귤 고르기
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int boxOrangeCount = 0;

        System.out.println("한 상자에 담으려는 귤의 개수를 입력하세요 (1이상 100000이하의 자연수 중 하나 입력) >>> ");
        try {
            boxOrangeCount = scan.nextInt();
            if(boxOrangeCount < 1 || boxOrangeCount > 100000) {
                System.out.println("범위가 잘못되었습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("잘못 입력하였습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }

        int arrayOrangeCount = 0;
        System.out.println("수확한 귤의 개수를 입력하세요 (상자에 담으려는 귤의 개수 이상 100000 이하의 자연수 중 하나 입력) >>> ");
        try {
            arrayOrangeCount = scan.nextInt();
            if(arrayOrangeCount < boxOrangeCount || arrayOrangeCount > 100000) {
                System.out.println("범위가 잘못되었습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("잘못 입력하였습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }

        int[] oranges = new int[arrayOrangeCount];
        for(int x = 0; x < arrayOrangeCount; x++) {
            System.out.println((x + 1) + "번째 칸에 있는 오렌지의 크기를 입력하세요 (1 이상 10000000 이하의 자연수 중 하나 입력) >>>");
            String orangeWeight = scan.next();
            int parseOrangeWeight;
            try {
                parseOrangeWeight = Integer.parseInt(orangeWeight);
                if(parseOrangeWeight < 1 || parseOrangeWeight > 10000000) {
                    System.out.println("범위가 잘못되었습니다. 다시 입력해주세요.");
                    x--;
                    continue;
                }
            } catch(Exception e) {
                System.out.println("잘못 입력하였습니다. 다시 입력해주세요.");
                x--;
                continue;
            }

            System.out.println((x + 1) + "번째 칸에 있는 오렌지의 크기 : " + parseOrangeWeight);
            oranges[x] = parseOrangeWeight;
        }

        System.out.println("각 귤의 크기를 나열한 배열 : " + Arrays.toString(oranges));
        System.out.println("결과값 : " + result(boxOrangeCount, oranges));
    }

    public static int result(int k, int[] oranges) {
        int answer = 1;
        if(k == 1) return 1;
        HashMap<Integer, Integer> putOrangeRules = new HashMap<>();
        HashMap<Integer, Integer> orangeWeightCounts = new HashMap<>();
        putOrangeRules.put(1, k);
        for(int i = 2; i <= k; i++) {
            if(k % i == 0) putOrangeRules.put(i, k / i);
        }

        for(int i = 0; i < oranges.length; i++) {
            if(!orangeWeightCounts.containsKey(oranges[i])) {
                int count = 1;
                if(i != oranges.length - 1) {
                    for(int j = i + 1; j < oranges.length; j++) {
                        if(oranges[i] == oranges[j]) count++;
                    }
                }

                orangeWeightCounts.put(oranges[i], count);
            }
        }

        for(int i : putOrangeRules.keySet()) {
            int orangeCount = 0;
            for(int j : orangeWeightCounts.keySet()) {
                if(i != k && orangeWeightCounts.get(j) >= putOrangeRules.get(i)) orangeCount++;
            }
            if(orangeCount >= i) answer = i;
        }

        return answer;
    }
}
