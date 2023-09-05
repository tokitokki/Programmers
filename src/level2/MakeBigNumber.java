package src.level2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MakeBigNumber { //큰 수 만들기
    public static void main(String[] args) { //최대 18자리 수까지 입력할 수 있다고 범위를 변경하였다.
        Scanner scan = new Scanner(System.in);
        System.out.println("자연수를 입력하세요 (2자리 이상 18자리 이하로 입력) >>> ");
        String number = scan.next();

        if(number.length() <= 1 || number.length() > 19) {
            System.out.println("잘못 입력하였습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }

        for(int x = 0; x < number.length(); x++) {
            if(!(number.charAt(x) >= '0' && number.charAt(x) <= '9')) {
                System.out.println("문자를 포함해서 입력하였습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        }

        if(number.charAt(0) == '0') {
            System.out.println("맨 처음의 수는 0이 올 수 없습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }

        System.out.println("입력한 자연수 : " + number);

        System.out.println("몇 개의 숫자를 제거하시겠습니까? >>> ");
        int answer = 0;
        try {
            answer = scan.nextInt();
            if(answer <= 0 || answer >= number.length()) {
                System.out.println("잘못 입력하였습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }

        System.out.println("제거할 숫자 개수 : " + answer);
        ArrayList<Long> values = new ArrayList<>();
        for(int x = 1; x <= permutation(number.length(), answer); x++) {
            String changeValue = "";
            int[] numberArray = permutationArray(number.length(), answer, x);

            for(int y = 0; y < number.length(); y++) {
                int count = 0;
                for(int i : numberArray) {
                    if(i - 1 == y) count++;
                }
                if(count == 0) changeValue = changeValue.concat(String.valueOf(number.charAt(y)));
            }

            if(changeValue.charAt(0) != '0') values.add(Long.parseLong(changeValue));
        }

        System.out.println("최댓값 : " + Collections.max(values));
    }

    public static int[] permutationArray(int n, int r, int k) {
        int[] number = new int[n];
        for(int x = 0; x < n; x++) {
            number[x] = x + 1;
        }

        int[] array = new int[r];
        int count = n;
        int time = r;
        int index = 1;
        for(int x = 0; x < array.length; x++) {
            for(int y = 0; y < number.length; y++) {
                if(k >= index + permutation(count - 1, time - 1) * y && k <= index + permutation(count - 1, time - 1) * (y + 1) - 1) {
                    array[x] = number[y];
                    int[] changeNumber = new int[count - 1];
                    for(int z = 0; z < number.length; z++) {
                        if(z < y) changeNumber[z] = number[z];
                        else if(z > y) changeNumber[z - 1] = number[z];
                    }

                    number = changeNumber;
                    index += permutation(count - 1, time - 1) * y;
                    count -= 1;
                    time -= 1;
                    break;
                }
            }
        }

        return array;
    }

    public static int permutation(int n, int r) {
        if(r <= 0) return 1;
        if(r == 1) return n;
        return n * permutation(n - 1, r - 1);
    }
}
