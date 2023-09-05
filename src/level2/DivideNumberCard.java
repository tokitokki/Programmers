package src.level2;

import java.util.Scanner;

public class DivideNumberCard { //숫자 카드 나누기
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int arrayLength = 0;
        System.out.println("지정할 두 배열의 길이를 입력하세요 (1부터 500000 까지 자연수 중 하나 입력) >>> ");
        try {
            arrayLength = scan.nextInt();
            if(arrayLength < 1 || arrayLength > 500000) {
                System.out.println("해당되지 않는 범위입니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("잘못 입력하셨습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }

        int[] arrayA = new int[arrayLength];
        int[] arrayB = new int[arrayLength];

        for(int x = 0; x <= 1; x++) {
            for (int i = 0; i < arrayLength; i++) {
                System.out.println((x + 1) + "번째 배열에 들어갈 " + (i + 1) + "번째 숫자를 입력하세요 >>> ");
                int number;
                String words = scan.next();
                try {
                    number = Integer.parseInt(words);
                    if (number < 1 || number > 100000000) {
                        System.out.println("잘못 입력하였습니다. 다시 입력해주세요.");
                        i--;
                        continue;
                    }
                } catch (Exception e) {
                    System.out.println("잘못 입력하였습니다. 다시 입력해주세요.");
                    i--;
                    continue;
                }

                System.out.println((x + 1) + "번째 배열에 입력한 " + (i + 1) + "번째 숫자 : " + number);
                if(x == 0) arrayA[i] = number;
                else arrayB[i] = number;
            }
        }

        System.out.println("결과값 (없으면 0으로 도출) : " + divideMostBigNumber(arrayA, arrayB));
    }

    public static int divideMostBigNumber(int[] arrayA, int[] arrayB) {
        int divideValue = 0;
        int number = 2;
        boolean flag = true;
        while(flag) {
            int countA = 0;
            int minCountA = 0;
            int countB = 0;
            int minCountB = 0;
            for(int value : arrayA) {
                if(value % number == 0) countA++;

                if(minCountA == 0) minCountA = value;
                else if(minCountA > value) minCountA = value;
            }

            for(int value : arrayB) {
                if(value % number == 0) countB++;

                if(minCountB == 0) minCountB = value;
                else if(minCountB > value) minCountB = value;
            }

            if((countA == arrayA.length && countB == 0) || (countA == 0 && countB == arrayB.length)) {
                divideValue = number;
            }

            if(minCountA < number && minCountB < number) flag = false;
            else number++;
        }

        return divideValue;
    }
}