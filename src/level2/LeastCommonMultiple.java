package src.level2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class LeastCommonMultiple { //여러 숫자의 최소공배수
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("최소공배수를 구하려는 수들의 개수를 입력하세요 (1 이상 15 이하) >>> ");
        int arrayLength;
        try {
            arrayLength = scan.nextInt();
            if(arrayLength <= 0 || arrayLength > 15) {
                System.out.println("1부터 15까지의 자연수 중 하나를 입력하지 않았습니다. 처음부터 다시 시도하세요.");
                System.exit(0);
            }

            int[] numbers = new int[arrayLength];
            for(int x = 0; x < numbers.length; x++) {
                System.out.println((x + 1) + "번째 배열에 들어갈 숫자를 입력하세요 >>> ");
                String answer = scan.next();
                int changeTypeAnswer = 0;
                try {
                    changeTypeAnswer = Integer.parseInt(answer);
                    if(changeTypeAnswer <= 0 || changeTypeAnswer > 100) {
                        System.out.println("1부터 100까지의 자연수 중 하나를 입력하지 않았습니다. 다시 시도하세요.");
                        x--;
                        continue;
                    }
                } catch(Exception e) {
                    System.out.println("1부터 100까지의 자연수 중 하나를 입력하지 않았습니다. 다시 시도하세요.");
                    x--;
                }

                System.out.println("입력한 " + (x + 1) + "번째 배열의 숫자 : " + changeTypeAnswer);
                numbers[x] = changeTypeAnswer;
            }

            System.out.println("입력한 배열 : " + Arrays.toString(numbers));
            System.out.println("해당 수들의 최소공배수 : " + leastCommonMultipleNumber(numbers));

        } catch(Exception e) {
            System.out.println("1부터 15까지의 자연수 중 하나를 입력하지 않았습니다. 처음부터 다시 시도하세요.");
            System.exit(0);
        }
    }

    public static int leastCommonMultipleNumber(int[] array) {
        int prod = 1;
        HashMap<Integer, Integer> primeFactors = new HashMap<>();
        for(int number : array) {
            ArrayList<Integer> primeNumbers = new ArrayList<>();
            if(number != 1) {
                for(int x = 2; x <= number; x++) {
                    if(x == 2 || x == 3) primeNumbers.add(x);
                    else {
                        int count = 0;
                        for(int y = 2; y <= x / 2; y++) {
                            if(x % y == 0) count++;
                        }

                        if(count == 0) primeNumbers.add(x);
                    }
                }
            }

            for(int primeNumber : primeNumbers) {
                boolean flag = true;
                int value = number;
                int times = 0;
                while(flag) {
                    times++;
                    if(value % primeNumber == 0) {
                        value = value / primeNumber;
                    } else if(value % primeNumber != 0 && times == 1) {
                        flag = false;
                    } else {
                        if(!primeFactors.containsKey(primeNumber) || primeFactors.get(primeNumber) <= times - 1) {
                            primeFactors.put(primeNumber, times - 1);
                        }
                        flag = false;
                    }
                }
            }
        }

        for(int primeNumber : primeFactors.keySet()) {
            prod *= Math.pow(primeNumber, primeFactors.get(primeNumber));
        }

        return prod;
    }
}
