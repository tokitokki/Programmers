package src.level2;
import java.util.Arrays;
import java.util.Scanner;

public class StandingInLineMethod { //줄 서는 방법
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("줄을 서는 사람의 수를 입력하세요 (최대 20까지 입력) >>> ");
        int answer = 0;
        try {
            answer = scan.nextInt();
            if(answer <= 0 || answer > 20) {
                System.out.println("잘못 입력하였습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }

        System.out.println("사전 순으로 나열한 방법 중 몇 번째 방법을 출력할까요? >>> ");
        int number = 0;
        try {
            number = scan.nextInt();
            if(number <= 0 || number > factorial(answer)) {
                System.out.println("해당되지 않는 순서입니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }

        System.out.println(number + "번째로 나열한 배열 : " + Arrays.toString(findLineIndex(answer, number)));
    }

    public static int factorial(int n) {
                if(n == 0 || n == 1) return 1;
                else return n * factorial(n - 1);
            }

            public static int[] findLineIndex(int n, int k) {
                int[] number = new int[n];
                for(int x = 0; x < n; x++) {
                    number[x] = x + 1;
        }

        int[] people = new int[n];
        int index = 1;
        for(int x = 1; x <= people.length; x++) {
            int[] changeNumberIndex = new int[0];
            for(int y = 0; y < number.length; y++) {
                if(k >= index + factorial(n - x) * y && k <= index + factorial(n - x) * (y + 1) - 1) {
                    people[x - 1] = number[y];
                    index += factorial(n - x) * y;
                    changeNumberIndex = new int[n - 1];
                    for(int z = 0; z < number.length; z++) {
                        if(z < y) changeNumberIndex[z] = number[z];
                        else if(z > y) changeNumberIndex[z - 1] = number[z];
                    }
                    break;
                }
            }
            
            number = changeNumberIndex;
        }

        return people;
    }
}
