package src.level2;
import java.util.Scanner;

public class ContinuityNumberCount { //숫자의 표현
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("10000 이하의 자연수 n을 입력하세요 >>> ");
        String value = scan.next();
        int changeTypeValue;
        try {
            changeTypeValue = Integer.parseInt(value);
            if(changeTypeValue > 10000 || changeTypeValue <= 0) {
                System.out.println("1부터 10000까지의 자연수를 입력해 주세요.");
                System.exit(0);
            }

            int minValue = 0;
            int maxValue = 1;
            int number = 1;
            boolean flag = true;
            while(flag) {
                minValue += number;
                maxValue += number + 1;
                if(changeTypeValue >= minValue && changeTypeValue < maxValue) {
                    flag = false;
                } else {
                    number++;
                }
            }

            int count = 0;
            for(int x = 1; x <= number; x++) {
                if(x % 2 == 0) {
                    if(changeTypeValue % x == x / 2) count++;
                } else {
                    if(x == 1) count++;
                    else if(changeTypeValue % x == 0) count++;
                }
            }

            System.out.println("연속된 자연수로 나타낼 수 있는 방법의 수 : " + count);

        } catch(Exception e) {
            System.out.println("1부터 10000까지의 자연수를 입력해 주세요.");
            System.exit(0);
        }
    }
}
