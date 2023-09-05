package src.level2;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ThreeLineTiling { //세줄 타일링

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("타일로 만들 바닥의 가로 길이를 입력하세요 (5000 이하의 자연수로 입력하세요) >>> ");
        String value = scan.next();
        long changeTypeValue;
        try {
            changeTypeValue = Long.parseLong(value);
            if(changeTypeValue <= 0 || changeTypeValue > 5000) {
                System.out.println("해당되지 않는 범위의 정수를 입력하였습니다. 처음부터 다시 입력하세요.");
                System.exit(0);
            }

            System.out.println("반환값 : " + caseTitleValue(changeTypeValue));
        } catch(InputMismatchException e) {
            System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 시도하세요.");
            System.exit(0);
        }
    }

    public static long caseTitleValue(long n) {
        if(n % 2 == 1) return 0;
        else {
            if (n == 2) return 3;
            if (n == 4) return 11;
            long[] answers = new long[(int) n / 2];
            answers[0] = 3;
            answers[1] = 11;
            for (int x = 2; x < answers.length; x++) {
                answers[x] = (answers[x - 1] * 4 - answers[x - 2]) % 1000000007;
            }
            return answers[answers.length - 1];
        }
    }
}
