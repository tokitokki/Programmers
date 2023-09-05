package src.level2;

import java.util.Scanner;

public class Number124 { //124 나라의 숫자

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("자연수 n의 값을 입력하세요 >> ");
        String input = scan.next(); //입력한 값을 String 타입 변수에 대입하고
        int n;
        try {
            n = Integer.parseInt(input); //int 타입으로 변환해서 n 변수에 대입한다.

            if(n <= 0 || n > 500000000) { //0 이하거나 5억을 초과할 경우
                System.out.println("허용이 안 된 범위입니다. 처음부터 다시 시도하세요."); //범위 밖이라는 의미의 글을 출력해주고
                System.exit(0); //출력을 여기서 종료한다.
            }

            int charCounts = 1; //124 나라의 숫자로 진법을 바꿀 때 자리 수

            int plusValue = 3; //밑의 if 타입에서 break가 안될 때 최소 범위와 최대 범위에서 더해지는 수
            int minValue = 1; //조건문의 최소 범위
            int maxValue = 3; //조건문의 최대 범위

            //반복문을 활용해서 124 나라의 진법으로 나타낼 때 입력한 수의 자리 수부터 구한다.
            for(int x = 1; x <= 18; x++) { //5억은 124 나라의 진법으로 나타낼 때 18자리 수이므로 최대 18까지 자리수 범위 설정
                if(minValue <= n && n <= maxValue) { //n 값이 124 자리수로 나타낼 때 진법으로 바꿀 때 해당 자리 수 범위 내에 속하면
                    charCounts = x; //해당 자리수 값을 반환하고
                    break; //반복문을 해당 시점에서 종료한다. (예) n 값으로 9를 입력했을 때 반환되는 x 값이 2
                }

                minValue += plusValue; //종료되지 않으면 최소 범위에 plusValue 변수값을 더하고
                maxValue += plusValue * 3; //최대 범위에 plusValue 변수값에 3을 곱한 값을 더한다.

                plusValue *= 3;
                //그리고 더해지는 수는 3을 계속 곱해나간다. (밑의 방법대로 자리 수 구하는 범위를 맞춰주기 위해 3을 곱해나간다.)
            /*
                예) (minValue) <= n <= (maxValue)
                    1 <= n <= 3 ==> x = 1
                    4 <= n <= 12 ==> x = 2
                    13 <= n <= 39 ==> x = 3
             */
            }

            int[] rests = new int[charCounts];

            int divideValue = n - minValue;

            for(int x = 0; x < rests.length; x++) {
                if(divideValue == 0) {
                    rests[rests.length - 1 - x] = 0;
                } else {
                    rests[rests.length - 1 - x] = divideValue % 3;
                    divideValue = divideValue / 3;
                }
            }

            String result = "";

            for(int x : rests) {
                if(x == 0) {
                    result = result.concat("1");
                } else if(x == 1) {
                    result = result.concat("2");
                } else {
                    result = result.concat("4");
                }
            }

            int changeTypeResult = Integer.parseInt(result);

            System.out.println("124 나라의 진법으로 변환한 값 : " + changeTypeResult);
        } catch(Exception e) { //정수를 입력하지 않은 경우
            System.out.println("잘못 입력하였습니다. 처음부터 다시 시도하세요."); //잘못 입력했다고 출력해주고
            System.exit(0); //출력을 여기서 종료한다.
        }
    }
}
