package src.level2;
import java.util.Scanner;

public class NextBigNumber { //다음 큰 숫자
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("자연수를 입력하세요 >>> ");
        String inputValue = scan.next();
        int number;
        try {
            number = Integer.parseInt(inputValue);
            if(number <= 0 || number > 1000000) {
                System.out.println("1부터 1000000까지의 자연수를 입력해 주세요.");
                System.exit(0);
            }
            int nextNumber = number + 1;
            while(true) {
                int countThisNumber = 0;
                int countNextNumber = 0;
                String thisNumberBinary = Integer.toBinaryString(number);
                String nextNumberBinary = Integer.toBinaryString(nextNumber);
                for(int x = 0; x < thisNumberBinary.length(); x++) {
                    if(thisNumberBinary.charAt(x) == '1') {
                        countThisNumber++;
                    }
                }

                for(int x = 0; x < nextNumberBinary.length(); x++) {
                    if(nextNumberBinary.charAt(x) == '1') {
                        countNextNumber++;
                    }
                }

                if(countThisNumber == countNextNumber) {
                    break;
                } else {
                    nextNumber++;
                }
            }

            System.out.println("해당 문제의 조건에 맞는 다음으로 큰 숫자 : " + nextNumber);
        } catch(Exception e) {
            System.out.println("1부터 1000000까지의 자연수를 입력해 주세요.");
            System.exit(0);
        }
    }
}
