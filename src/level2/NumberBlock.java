package src.level2;
import java.util.Scanner;

public class NumberBlock { //숫자 블록
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("시작 순서를 입력하세요 (1번째부터 999999999번째까지 순서 중 하나의 번호 입력) >>> ");
        int begin = 0;
        try {
            begin = scan.nextInt();
            if(begin <= 0 || begin >= 1000000000) {
                System.out.println("범위에 해당되지 않는 정수를 입력하였습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 시도하세요");
            System.exit(0);
        }

        System.out.println("끝 순서를 입력하세요 (2번째부터 100000000번째까지 순서 중 하나의 번호 입력) >>> ");
        int end = 0;
        try {
            end = scan.nextInt();
            if(end <= 1 || end > 1000000000) {
                System.out.println("범위에 해당되지 않는 정수를 입력하였습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }

            if(begin >= end) {
                System.out.println("끝값이 시작값보다 작거나 같을 수 없습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }

            if(end - begin > 10000) {
                System.out.println("출력하려는 배열의 길이는 10000을 초과할 수 없습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }

        } catch(Exception e) {
            System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 시도하세요");
            System.exit(0);
        }

        int[] blocks = new int[end];
        for(int i = 1; i <= end / 2; i++) {
            for(int j = i * 2; j <= end; j += i) {
                blocks[j - 1] = i;
            }
        }

        for(int x = begin - 1; x < end; x++) {
            if(x == begin - 1) {
                System.out.print("반환된 배열 : [" + blocks[x]);
            } else if(x != end - 1) {
                System.out.print(", " + blocks[x]);
            } else {
                System.out.println(", " + blocks[x] + "]");
            }
        }
    }
}
