package src.level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TargetNumber { //타겟 넘버
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("배열의 길이를 입력하세요 (2 이상 20 이하의 자연수 중 하나를 입력) >>> ");
        int answer = 0;
        try {
            answer = scan.nextInt();
            if(answer <= 1 || answer > 20) {
                System.out.println("범위에 해당되지 않는 정수를 입력하였습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("문자를 포함하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }

        int[] numbers = new int[answer];
        for(int x = 0; x < numbers.length; x++) {
            System.out.println((x + 1) + "번째 배열의 자연수를 입력하세요 (1 이상 50 이하의 자연수 중 하나를 입력) >>> ");
            int number;
            try {
                number = scan.nextInt();
                if(number <= 0 || number > 50) {
                    System.out.println("범위에 해당되지 않는 정수를 입력하였습니다. 다시 입력해주세요.");
                    x--;
                    continue;
                }
            } catch(Exception e) {
                System.out.println("문자를 포함하였거나 정수가 아닌 실수를 입력하였습니다. 다시 입력해주세요.");
                x--;
                continue;
            }

            System.out.println("대입한 " + (x + 1) + "번째 배열의 자연수 : " + number);
            numbers[x] = number;
        }

        System.out.println("입력한 배열 : " + Arrays.toString(numbers));
        boolean flag = true;
        int result = 0;
        while(flag) {
            System.out.println("+, - 부호를 사용해서 도출할 값을 입력하세요 (1 이상 1000 이하의 자연수 중 하나를 입력) >>> ");
            try {
                result = scan.nextInt();
                if(result <= 0 || result > 1000) {
                    System.out.println("범위에 해당되지 않는 정수를 입력하였습니다. 다시 입력해주세요.");
                    continue;
                }
                
                flag = false;
            } catch(Exception e) {
                System.out.println("문자를 포함하였거나 정수가 아닌 실수를 입력하였습니다. 다시 입력해주세요.");
            }
        }

        System.out.println("도출할 값 : " + result);
        System.out.println("도출할 값이 나오는 경우의 수 : " + caseCount(numbers, result));
    }

    public static int caseCount(int[] array, int x) {
        int count = 0;
        String[] plusMinus = new String[]{"plus", "minus"};
        ArrayList<String[]> plusMinusCase = new ArrayList<>();
        for(int i = 0; i < (int) Math.pow(2, array.length); i++) {
            String binaryValue = Integer.toBinaryString(i);

            String[] buttons = new String[array.length];
            Arrays.fill(buttons, plusMinus[0]);

            String[] binaryValueButton = new String[binaryValue.length()];
            for(int j = 0; j < binaryValue.length(); j++) {
                if(binaryValue.charAt(j) == '0') binaryValueButton[j] = plusMinus[0];
                else binaryValueButton[j] = plusMinus[1];
            }

            System.arraycopy(binaryValueButton, 0, buttons, array.length - binaryValue.length(), binaryValue.length());
            plusMinusCase.add(buttons);
        }

        for(String[] plusAndMinus : plusMinusCase) {
            int calculation = 0;
            for(int j = 0; j < plusAndMinus.length; j++) {
                if(plusAndMinus[j].equals("plus")) calculation += array[j];
                else calculation -= array[j];
            }

            if(calculation == x) count++;
        }

        return count;
    }
}
