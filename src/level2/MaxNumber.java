package src.level2;
import java.util.Arrays;
import java.util.Scanner;

public class MaxNumber { //가장 큰 수
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("배열의 크기를 입력하세요 (1 이상 100000 이하) >>> ");
        String arrayLength = scan.next();
        int changeTypeArrayLength;
        try {
            changeTypeArrayLength = Integer.parseInt(arrayLength);
            if(changeTypeArrayLength <= 0 || changeTypeArrayLength > 100000) {
                System.out.println("배열의 범위에 해당되지 않습니다. 처음부터 다시 시도하세요.");
                System.exit(0);
            }

            int[] numbers = new int[changeTypeArrayLength];
            for(int x = 0; x < numbers.length; x++) {
                System.out.println("배열의 " + (x + 1) + "번째 칸에 들어갈 음이 아닌 1000 이하의 정수를 입력하세요 >>> ");
                String value = scan.next();
                int changeTypeValue;
                try {
                    changeTypeValue = Integer.parseInt(value);
                    if(changeTypeValue < 0 || changeTypeValue > 1000) {
                        System.out.println("입력하는 숫자의 범위에 해당하지 않습니다. 다시 입력하세요.");
                        x--;
                        continue;
                    }

                    numbers[x] = changeTypeValue;
                    System.out.println("입력한 " + (x + 1) + "번째 칸의 값은 " + changeTypeValue + "입니다.");

                } catch(Exception e) {
                    System.out.println("문자를 포함해서 입력하였습니다. 다시 입력하세요.");
                    x--;
                }
            }

            System.out.println("입력한 배열 : " + Arrays.toString(numbers));
            System.out.println("이어붙인 가장 큰 값 : " + findMaxNumber(numbers));

        } catch(Exception e) {
            System.out.println("문자를 포함해서 입력하였거나 자연수가 아닌 실수를 입력하였습니다. 처음부터 다시 시도하세요.");
            System.exit(0);
        }
    }

    public static String findMaxNumber(int[] numbers) {
        String[] changeTypeNumbers = new String[numbers.length];
        for(int x = 0; x < numbers.length; x++) {
            changeTypeNumbers[x] = String.valueOf(numbers[x]);
        }

        Arrays.sort(changeTypeNumbers, (x1, x2) -> (x2 + x1).compareTo(x1 + x2));
        return changeTypeNumbers[0].equals("0") ? "0" : String.join("", changeTypeNumbers);
    }
}
