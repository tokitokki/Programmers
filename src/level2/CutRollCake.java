package src.level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CutRollCake { //롤케이크 자르기
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("롤케이크의 길이를 입력하세요 (1부터 1000000까지의 자연수 중 하나 입력) >>> ");
        int breadLength = 0;
        try {
            breadLength = scan.nextInt();
            if(breadLength <= 0 || breadLength > 1000000) {
                System.out.println("잘못 입력하였습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("문자를 포함해서 입력하였습니다. 처음부터 다시 입력하세요.");
            System.exit(0);
        }

        System.out.println("롤케이크에 만들 수 있는 토핑의 종류 수를 입력하세요 (1부터 10000까지의 자연수 중 하나 입력) >>> ");
        int toppingTypes = 0;
        try {
            toppingTypes = scan.nextInt();
            if(toppingTypes <= 0 || toppingTypes > 10000) {
                System.out.println("잘못 입력하였습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("문자를 포함해서 입력하였습니다. 처음부터 다시 입력하세요.");
            System.exit(0);
        }

        int[] breadToppingNumbers = new int[breadLength];
        for(int x = 0; x < breadToppingNumbers.length; x++) {
            System.out.println("해당 빵의 " + (x + 1) + "번째 길이에 올려진 토핑 번호를 입력하세요 >>> ");
            int toppingNumber = 0;
            try {
                toppingNumber = scan.nextInt();
                if(toppingNumber <= 0 || toppingNumber > toppingTypes) {
                    System.out.println("잘못 입력하였습니다. 다시 입력해주세요.");
                    x--;
                    continue;
                }
            } catch(Exception e) {
                System.out.println("문자를 포함해서 입력하였습니다. 처음부터 다시 입력하세요.");
                System.exit(0);
            }

            System.out.println((x + 1) + "번째 길이의 토핑 번호 : " + toppingNumber);
            breadToppingNumbers[x] = toppingNumber;
        }

        System.out.println("빵의 토핑의 번호를 나열한 배열 : " + Arrays.toString(breadToppingNumbers));
        System.out.println("빵을 나눌 수 있는 방법의 개수 : " + caseResult(breadToppingNumbers));
    }

    public static int caseResult(int[] breadToppingArray) {
        int result = 0;
        if(breadToppingArray.length == 1) return 0;
        int[] leftToppingArray;
        ArrayList<Integer> leftToppings;

        int[] rightToppingArray;
        ArrayList<Integer> rightToppings;
        for(int x = 0; x < breadToppingArray.length - 1; x++) {
            leftToppingArray = new int[x + 1];
            leftToppings = new ArrayList<>();
            System.arraycopy(breadToppingArray, 0, leftToppingArray, 0, leftToppingArray.length);

            for (int toppingValue : leftToppingArray) {
                if (leftToppings.isEmpty()) leftToppings.add(toppingValue);
                else {
                    int duplicate = 0;
                    for (int topping : leftToppings) {
                        if (toppingValue == topping) duplicate++;
                    }

                    if (duplicate == 0) leftToppings.add(toppingValue);
                }
            }

            rightToppingArray = new int[breadToppingArray.length - x - 1];
            for(int y = 0; y < rightToppingArray.length; y++) {
                rightToppingArray[y] = breadToppingArray[y + x + 1];
            }
            rightToppings = new ArrayList<>();
            for (int toppingValue : rightToppingArray) {
                if (rightToppings.isEmpty()) rightToppings.add(toppingValue);
                else {
                    int duplicate = 0;
                    for (int topping : rightToppings) {
                        if (toppingValue == topping) duplicate++;
                    }

                    if (duplicate == 0) rightToppings.add(toppingValue);
                }
            }

            if(leftToppings.size() == rightToppings.size()) result++;
        }

        return result;
    }
}
