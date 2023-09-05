package src.level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class MakeMinValue { //최솟값 만들기
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("두 배열에 설정할 길이를 입력하세요 (1 이상 1000 이하의 자연수) >>> ");
        int length = 0;
        try {
            length = scan.nextInt();
            if (length <= 0 || length > 1000) {
                System.out.println("잘못 입력하였습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch (Exception e) {
            System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }

        int[] A = new int[length];
        int[] B = new int[length];
        for (int x = 0; x < A.length; x++) {
            System.out.println("A 배열에 " + (x + 1) + "번째 칸에 대입할 1000 이하의 자연수를 입력하세요 >>> ");
            int answer;
            try {
                answer = scan.nextInt();
                if (answer <= 0 || answer > 1000) {
                    System.out.println("잘못 입력하였습니다. 다시 입력해주세요.");
                    x--;
                    continue;
                }
            } catch (Exception e) {
                System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 다시 입력해주세요.");
                x--;
                continue;
            }

            System.out.println("대입한 A 배열의 " + (x + 1) + "번째 값 : " + answer);
            A[x] = answer;
        }

        for (int x = 0; x < B.length; x++) {
            System.out.println("B 배열에 " + (x + 1) + "번째 칸에 대입할 1000 이하의 자연수를 입력하세요 >>> ");
            int answer;
            try {
                answer = scan.nextInt();
                if (answer <= 0 || answer > 1000) {
                    System.out.println("잘못 입력하였습니다. 다시 입력해주세요.");
                    x--;
                    continue;
                }
            } catch (Exception e) {
                System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 다시 입력해주세요.");
                x--;
                continue;
            }

            System.out.println("대입한 B 배열의 " + (x + 1) + "번째 값 : " + answer);
            B[x] = answer;
        }

        System.out.println("A 배열의 원소 : " + Arrays.toString(A));
        System.out.println("B 배열의 원소 : " + Arrays.toString(B));
        ArrayList<Integer> answers = new ArrayList<>();

        for (int x = 1; x <= factorial(length); x++) {
            int[] lineNumber = findLineIndex(length, x);
            int answer = 0;
            for(int k = 0; k < A.length; k++) {
                answer += A[k] * B[lineNumber[k] - 1];
            }

            answers.add(answer);
        }

        System.out.println("계산한 값들 중 최솟값 : " + Collections.min(answers));
    }

    public static int factorial(int n) {
        if (n == 0 || n == 1) return 1;
        else return n * factorial(n - 1);
    }

    public static int[] findLineIndex(int n, int k) {
        int[] number = new int[n];
        for(int x = 0; x < n; x++) {
            number[x] = x + 1;
        }

        int[] lineIndex = new int[n];
        int index = 1;
        for (int x = 1; x <= lineIndex.length; x++) {
            int[] changeLineIndex = new int[0];
            for (int y = 0; y < number.length; y++) {
                if (k >= index + factorial(n - x) * y && k <= index + factorial(n - x) * (y + 1) - 1) {
                    lineIndex[x - 1] = number[y];
                    index += factorial(n - x) * y;
                    changeLineIndex = new int[n - 1];
                    for (int z = 0; z < number.length; z++) {
                        if (z < y) changeLineIndex[z] = number[z];
                        else if (z > y) changeLineIndex[z - 1] = number[z];
                    }
                    break;
                }
            }

            number = changeLineIndex;
        }

        return lineIndex;
    }
}
