package src.level2;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TrianglePyramid { //삼각 달팽이
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("매개변수인 자연수 n의 값을 입력하세요 (1이상 1000이하) >>> ");
        int value;
        try {
            value = scan.nextInt();
            if(value <= 0 || value > 1000) {
                System.out.println("1이상 1000이하의 자연수 외의 값을 입력하였습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }

            HashMap<Integer, int[]> pyramids = new HashMap<>();
            for(int x = 1; x <= value; x++) {
                int[] thisValues = new int[x];
                pyramids.put(x, thisValues);
            }

            int count = value;
            int number = 1;
            for(int x = 1; x <= value; x++) {
                for(int y = 1; y <= count; y++) {
                    if (x % 3 == 1) {
                        pyramids.get(y + (x / 3) * 2)[x / 3] = number;
                        number++;
                    } else if (x % 3 == 2) {
                        pyramids.get(value - x / 3)[y + x / 3] = number;
                        number++;
                    } else {
                        pyramids.get(value - (x / 3) - y + 1)[pyramids.get(value - (x / 3) - y).length + 1 - x / 3] = number;
                        number++;
                    }
                }
                count--;
            }

            int[] arrayAdd = new int[add(value)];
            int hap = 0;
            for(int x = 1; x <= value; x++) {
                System.arraycopy(pyramids.get(x), 0, arrayAdd, hap, pyramids.get(x).length);
                hap += pyramids.get(x).length;
            }

            System.out.println("출력되는 배열 : " + Arrays.toString(arrayAdd));

        } catch(InputMismatchException e) {
            System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }
    }

    public static int add(int n) {
        int hap = 0;
        for(int x = 1; x <= n; x++) {
            hap += x;
        }

        return hap;
    }
}
