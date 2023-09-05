package src.level2;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PrimeNumberCount { //소수 개수 구하기
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("1000000 이하의 자연수 n의 값을 입력하세요 >>> ");
        String answer = scan.next();
        int changeTypeAnswer;
        try {
            changeTypeAnswer = Integer.parseInt(answer);
            if(changeTypeAnswer <= 0 || changeTypeAnswer > 1000000) {
                System.out.println("잘못 입력하였습니다. 처음부터 다시 시도하세요.");
                System.exit(0);
            }

            System.out.println("3진법부터 10진법까지의 진법 중에서 몇 진법으로 수를 나타낼까요? >>> ");
            int notation = 0;
            try {
                notation = scan.nextInt();
                if(notation <= 2 || notation > 10) {
                    System.out.println("잘못 입력하였습니다. 처음부터 다시 시도하세요.");
                    System.exit(0);
                }

            } catch(Exception e) {
                System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 시도하세요.");
                System.exit(0);
            }

            boolean flag = true;
            StringBuilder stringNumber = new StringBuilder();
            long changeTypeNumber;
            int value = changeTypeAnswer;
            while(flag) {
                if(value == 0) {
                    flag = false;
                } else {
                    stringNumber.insert(0, (value % notation));
                    value = value / notation;
                }
            }

            changeTypeNumber = Long.parseLong(String.valueOf(stringNumber));
            System.out.println(notation + "진법으로 변환한 수 : " + changeTypeNumber);

            String[] numbers = String.valueOf(changeTypeNumber).split("0");
            ArrayList<Long> checkNumbers = new ArrayList<>();
            for(String number : numbers) {
                if(!number.equals("")) {
                    checkNumbers.add(Long.parseLong(number));
                }
            }
            System.out.println("0을 기점으로 분리된 배열 : " + checkNumbers);

            int primeNumberCount = 0;
            for(Long checkNumber : checkNumbers) {
                if(checkNumber == 1) continue;
                int count = 0;

                for(int x = 2; x <= checkNumber / 2; x++) {
                    if(checkNumber % x == 0) count++;
                }

                if(count == 0) {
                    primeNumberCount++;
                }
            }

            System.out.println("해당 배열 중 소수의 개수 : " + primeNumberCount + "개");

        } catch(InputMismatchException e) {
            System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 시도하세요.");
            System.exit(0);
        }
    }
}
