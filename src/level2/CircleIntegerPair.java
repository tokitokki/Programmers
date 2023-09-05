package src.level2;

import java.util.Scanner;

public class CircleIntegerPair { //두 원 사이의 정수 쌍
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("첫 번째 원의 반지름을 입력하세요 (1 이상 999999 이하의 자연수 중 하나로 입력) >>> ");
        String radius1 = scan.next();
        int changeTypeRadius1 = 0;
        try {
            changeTypeRadius1 = Integer.parseInt(radius1);
            if(changeTypeRadius1 < 1 || changeTypeRadius1 > 999999) {
                System.out.println("해당되지 않는 범위입니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("잘못 입력하셨습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }

        System.out.println("첫 번째 원의 반지름의 값 : " + changeTypeRadius1);
        System.out.println();

        System.out.println("두 번째 원의 반지름을 입력하세요 (첫 번째 원의 반지름보다 크고 1000000 이하의 자연수 중 하나로 입력) >>> ");
        String radius2 = scan.next();
        int changeTypeRadius2 = 0;
        try {
            changeTypeRadius2 = Integer.parseInt(radius2);
            if(changeTypeRadius2 < changeTypeRadius1 || changeTypeRadius2 > 1000000) {
                System.out.println("해당되지 않는 범위입니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("잘못 입력하셨습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }

        System.out.println("두 번째 원의 반지름의 값 : " + changeTypeRadius2);
        System.out.println();

        int cases = 0;
        int[] integerArrays = new int[2 * changeTypeRadius2 + 1];
        for(int x = 0; x < integerArrays.length; x++) {
            integerArrays[x] = (-1) * changeTypeRadius2 + x;
        }

        for(int i : integerArrays) {
            for(int j : integerArrays) {
                if(Math.pow(i, 2) + Math.pow(j, 2) >= Math.pow(changeTypeRadius1, 2) && Math.pow(i, 2) + Math.pow(j, 2) <= Math.pow(changeTypeRadius2, 2)) {
                    cases++;
                }
            }
        }

        System.out.println("총 점의 개수 : " + cases);
    }
}
