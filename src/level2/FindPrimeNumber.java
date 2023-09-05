package src.level2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class FindPrimeNumber { //소수 찾기
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("숫자들을 코드처럼 나열한 번호를 입력하세요. (숫자를 7개까지 입력할 수 있습니다.) >>> ");
        String code = scan.next();

        for(int x = 0; x < code.length(); x++) {
            if(!(code.charAt(x) >= '0' && code.charAt(x) <= '9')) {
                System.out.println("숫자가 아닌 다른 문자를 포함하여 입력하였습니다. 처음부터 다시 시도하세요.");
                System.exit(0);
            }
        }

        if(code.length() > 7) {
            System.out.println("8자리 이상 번호를 입력하였습니다. 처음부터 다시 시도하세요.");
            System.exit(0);
        }

        String[] splitNumbers = new String[code.length()];
        for(int x = 0; x < splitNumbers.length; x++) {
            splitNumbers[x] = String.valueOf(code.charAt(x));
        }

        Arrays.sort(splitNumbers);
        System.out.println("입력한 숫자들을 하나씩 담은 배열 : " + Arrays.toString(splitNumbers));

        boolean[] checked = new boolean[code.length()];
        Arrays.fill(checked, false);

        ArrayList<Integer> makeNumbers = new ArrayList<>();

        for(int x = 0; x < splitNumbers.length; x++) {
            permutation_makeNumber(makeNumbers, splitNumbers, checked, "", x + 1, 0);
        }

        System.out.println(makeNumbers);

        if(makeNumbers.isEmpty()) System.out.println("소수의 개수 : 0개");
        else System.out.println("소수의 개수 : " + makeNumbers.size() + "개");
    }



    public static void permutation_makeNumber(ArrayList<Integer> arrayList, String[] inputNumber, boolean[] check, String string, int r, int count) {
        if(count == r) {
            int number = Integer.parseInt(string);
            if (isPrimeNumber(number) && !arrayList.contains(number)) {
                arrayList.add(number);
            }
            return;
        }

        for(int i = 0; i < inputNumber.length; i++) {
            if(!check[i]) {
                check[i] = true;
                string += inputNumber[i];
                permutation_makeNumber(arrayList, inputNumber, check, string, r, count + 1);
                string = string.substring(0, string.length() - 1);
                check[i] = false;
            }
        }
    }

    public static boolean isPrimeNumber(int n) {
        if(n <= 1) return false;
        int count = 0;
        for(int x = 2; x <= n / 2; x++) {
            if(n % x == 0) count++;
        }

        return count == 0;
    }
}
