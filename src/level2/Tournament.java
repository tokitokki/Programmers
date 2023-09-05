package src.level2;
import java.util.Scanner;

public class Tournament { //대진표
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("토너먼트 참가자 수의 거듭제곱을 입력하세요 (2의 거듭제곱, 1부터 20까지의 자연수 중 하나 입력) >>> ");
        String pow = scan.next();
        int changeTypePow = 0;
        try {
            changeTypePow = Integer.parseInt(pow);
            if(changeTypePow <= 0 || changeTypePow > 20) {
                System.out.println("범위에 해당되지 않는 정수를 입력하였습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }

        System.out.println("참가자 A의 참가 순번을 입력해주세요 >>> ");
        String a = scan.next();
        int a_number = 0;
        try {
            a_number = Integer.parseInt(a);
            if(a_number <= 0 || a_number > Math.pow(2, changeTypePow)) {
                System.out.println("참가 순번이 잘못되었습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }

        System.out.println("참가자 B의 참가 순번을 입력해주세요 >>> ");
        String b = scan.next();
        int b_number = 0;
        try {
            b_number = Integer.parseInt(b);
            if(b_number <= 0 || b_number > Math.pow(2, changeTypePow)) {
                System.out.println("참가 순번이 잘못되었습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
            if(a_number == b_number) {
                System.out.println("참가자는 참가 순번이 서로 다릅니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }

        System.out.println("전체 참가자 수 : " + (int) Math.pow(2, changeTypePow) + "명");
        System.out.println("참가자 A의 참가 순번 : " + a_number);
        System.out.println("참가자 B의 참가 순번 : " + b_number);
        System.out.println("반환값 : " + answer(changeTypePow, a_number, b_number));
    }

    public static int answer(int n, int a, int b) {
        int minValue;
        int maxValue;
        int repeatTimes = 0;
        for(int x = 1; x <= n; x++) {
            minValue = (int) Math.pow(2, 0);
            maxValue = (int) Math.pow(2, x);
            repeatTimes = (int) (Math.pow(2, n) / x);
            for(int y = 1; y <= repeatTimes; y++) {
                if(a >= minValue && a <= maxValue && b >= minValue && b <= maxValue) {
                    return x;
                } else {
                    minValue += (int) Math.pow(2, x);
                    maxValue += (int) Math.pow(2, x);
                }
            }
        }
        return repeatTimes;
    }
}
