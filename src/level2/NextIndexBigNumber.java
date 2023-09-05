package src.level2;

import java.util.Arrays;
import java.util.Scanner;

public class NextIndexBigNumber { //뒤에 있는 큰 수 찾기
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int arrayLength = 0;
        System.out.println("배열의 길이를 입력하세요 (4 이상 1000000 이하의 자연수 중 하나로 입력) >>> ");

        try {
            arrayLength = scan.nextInt();
            if(arrayLength < 4 || arrayLength > 1000000) {
                System.out.println("범위에 해당되지 않는 정수를 입력하였습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("잘못 입력하였습니다. 처음부터 다시 입력하세요.");
            System.exit(0);
        }

        int[] numbers = new int[arrayLength];
        for(int i = 0; i < numbers.length; i++) {
            System.out.println((i + 1) + "번째 칸에 자연수를 입력하세요 (1 이상 1000000 이하의 자연수 중 하나로 입력) >>> ");
            String number = scan.next();
            int changeTypeNumber;
            try {
                changeTypeNumber = Integer.parseInt(number);
                if(changeTypeNumber < 1 || changeTypeNumber > 1000000) {
                    System.out.println("범위에 해당되지 않는 정수를 입력하였습니다. 다시 입력해주세요.");
                    i--;
                    continue;
                }
            } catch(Exception e) {
                System.out.println("잘못 입력하였습니다. 다시 입력하세요.");
                i--;
                continue;
            }

            System.out.println((i + 1) + "번째 배열의 숫자 : " + changeTypeNumber);
            numbers[i] = changeTypeNumber;
        }

        System.out.println("입력한 숫자를 순서대로 나타낸 배열 : " + Arrays.toString(numbers));
        System.out.println("결과값 : " + Arrays.toString(nextIndexBigNumber(numbers)));
    }

    public static int[] nextIndexBigNumber(int[] arrays) {
        int[] changeNumber = new int[arrays.length];
        for(int x = 0; x < changeNumber.length; x++) {
            if(x == changeNumber.length - 1) changeNumber[x] = -1;
            else {
                for(int y = x + 1; y < changeNumber.length; y++) {
                    if(changeNumber[x] == 0 && arrays[x] < arrays[y]) {
                        changeNumber[x] = arrays[y];
                        break;
                    }
                }

                if(changeNumber[x] == 0) changeNumber[x] = -1;
            }
        }

        return changeNumber;
    }
}