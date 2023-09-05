package src.level2;

import java.util.ArrayList;
import java.util.Scanner;

public class ChangeNumber { //숫자 변환하기
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int x = 0;
        int y = 0;
        int n = 0;

        System.out.println("연산을 시도하려고 하는 자연수를 입력하세요 (1부터 1000000 사이의 자연수로 입력) >>> ");
        try {
            x = scan.nextInt();
            if(x < 1 || x > 1000000) {
                System.out.println("해당되지 않는 범위입니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }

        } catch(Exception e) {
            System.out.println("잘못 입력하셨습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }

        System.out.println("결과값을 얼마의 자연수로 도출하시겠습니까? (1부터 1000000 사이의 자연수로 첫 번째 값보다 더 큰 수로 입력) >>> ");
        try {
            y = scan.nextInt();
            if(y < 1 || y > 1000000 || x > y) {
                System.out.println("해당되지 않는 범위입니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("잘못 입력하셨습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }

        System.out.println("덧셈을 시도할 때, 얼마의 자연수만큼 더하시겠습니까? (1 이상이고 두 번째 값보다 더 작은 자연수로 입력) >>> ");
        try {
            n = scan.nextInt();
            if(n >= y) {
                System.out.println("해당되지 않는 범위입니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("잘못 입력하셨습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }

        System.out.println("자연수 " + x + "를(을) 3가지 연산을 적용해서 값을 " + y + "로(으로) 만듭니다.");
        System.out.println("가능한 최대 연산 수 (방법이 없으면 -1로 결과 도출) : " + minMethods(x, y, n));
    }

    public static int minMethods(int x, int y, int n) {
        int results = 0;
        ArrayList<Integer> calculateNumbers = new ArrayList<>();
        ArrayList<Integer> resultNumbers = new ArrayList<>();
        boolean flag = true;
        int plus;
        int twoTimes;
        int threeTimes;
        while(flag) {
            if(results == 0) calculateNumbers.add(x);
            else {
                calculateNumbers = resultNumbers;
                resultNumbers = new ArrayList<>();
            }

            for(int value : calculateNumbers) {
                plus = value + n;
                twoTimes = 2 * value;
                threeTimes = 3 * value;

                if(!resultNumbers.contains(plus) && plus <= y) resultNumbers.add(plus);
                if(!resultNumbers.contains(twoTimes) && twoTimes <= y) resultNumbers.add(twoTimes);
                if(!resultNumbers.contains(threeTimes) && threeTimes <= y) resultNumbers.add(threeTimes);
            }

            results++;
            if(resultNumbers.contains(y) || resultNumbers.isEmpty()) flag = false;
        }

        if(resultNumbers.isEmpty()) return -1;
        return results;
    }
}