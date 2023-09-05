package src.level2;

import java.util.Scanner;

public class NQueen { // N-Queen
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("체스판의 모서리의 길이를 입력하세요 (12 이하의 자연수로 입력) >>> ");
        String length = scan.next();
        int changeTypeLength = 0;

        try {
            changeTypeLength = Integer.parseInt(length);
            if(changeTypeLength < 1 || changeTypeLength > 12) {
                System.out.println("범위를 초과하였습니다. 처음부터 다시 입력하세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("문자를 포함해서 입력하였거나 자연수가 아닌 실수를 입력하였습니다. 처음부터 다시 입력하세요.");
            System.exit(0);
        }

        System.out.println(changeTypeLength + " × " + changeTypeLength + " 체스판의 경우의 수를 구합니다.");
        System.out.println("해당 경우의 수 : " + cases(changeTypeLength));
    }

    public static long cases(int n) {
        long caseCount = 0;
        for(int i = 1; i <= factorial(n); i++) {
            boolean able = true;
            int[] indexes = findLineIndex(n, i);
            for(int x = 0; x < indexes.length; x++) {
                for(int y = 0; y < indexes.length; y++) {
                    if (x < y) {
                        double decline = (double) (indexes[y] - indexes[x]) / (y - x);
                        if(Math.abs(decline) == 1) able = false;
                    }
                }
            }

            if(able) caseCount++;
        }

        return caseCount;
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