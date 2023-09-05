package src.level2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MinValueAndMaxValue { //최댓값과 최솟값
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("숫자와 공백으로만 포함되도록 입력하세요 >>> ");
        String answer = scan.nextLine();
        for(int x = 0; x < answer.length(); x++) {
            if(!((answer.charAt(x) >= '0' && answer.charAt(x) <= '9') || answer.charAt(x) == ' ' || answer.charAt(x) == '-')){
                System.out.println("숫자 또는 공백이 포함되지 않는 부분이 있습니다. 처음부터 다시 입력하세요.");
                System.exit(0);
            }
        }

        String[] answerSplit = answer.split(" ");
        ArrayList<Integer> numbers = new ArrayList<>();
        for(String word : answerSplit) {
            if(!word.equals("")) {
                try {
                    numbers.add(Integer.parseInt(word));
                } catch(Exception e) {
                    System.out.println("음의 정수로 변환할 수 없는 부분이 있습니다. 처음부터 다시 시도하세요.");
                    System.exit(0);
                }
            }
        }

        if(numbers.isEmpty()) {
            System.out.println("숫자를 입력하지 않았습니다. 처음부터 다시 입력하세요.");
            System.exit(0);
        }

        System.out.println("숫자를 따로 분리시켜 만든 배열 : " + numbers);
        System.out.println("반환값 : " + minValueAndMaxValue(numbers)[0] + " " + minValueAndMaxValue(numbers)[1]);
    }

    public static int[] minValueAndMaxValue(ArrayList<Integer> numbers) {
        int[] values = new int[2];
        values[0] = Collections.min(numbers);
        values[1] = Collections.max(numbers);

        return values;
    }
}
