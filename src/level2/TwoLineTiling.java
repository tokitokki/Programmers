package src.level2;
import java.util.Scanner;

public class TwoLineTiling { //두줄 타일링

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("타일로 만들 바닥의 가로 길이를 입력하세요 (60000 이하의 자연수로 입력하세요) >>> ");
        long value;
        try {
            value = scan.nextLong();
            if(value <= 0 || value > 60000) {
                System.out.println("범위에 해당되지 않는 정수를 입력하였습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }

            System.out.println("반환된 값 : " + caseTileValue(value));

        } catch(Exception e) {
            System.out.println("문자를 포함하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }
    }

    //해당 문제에서 타일을 정리하는 방법의 수는 피보나치 수열이다. (가로 길이 1일 때 1개, 2일 때 2개, 3일 때 3개, 4일 때 5개, ... )
    public static long caseTileValue(long n) {
        if(n == 1) return 1;
        if(n == 2) return 2;
        int[] answers = new int[(int) n];
        answers[0] = 1;
        answers[1] = 2;
        for(int x = 2; x < n; x++) {
            answers[x] = ((answers[x - 1] + (answers[x - 2])) % 1000000007);
        }

        return answers[(int) (n - 1)];
    }
}
