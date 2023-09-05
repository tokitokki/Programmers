package src.level2;
import java.util.Arrays;
import java.util.Scanner;

public class CutArray { //배열 자르기
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("설정할 2차원 배열 n × n 배열의 길이 n을 입력하세요 (1 부터 10000000 까지 자연수로 입력) >>> ");
        int length = 0;
        try {
            length = scan.nextInt();
            if(length <= 0 || length > 10000000) {
                System.out.println("1부터 10000000까지의 자연수를 입력해 주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }

        System.out.println("구하고자 하는 일부 배열의 시작 index를 입력하세요 >>> ");
        int left = 0;
        try {
            left = scan.nextInt();
            if(left < 0 || left >= Math.pow(10000000, 2)) {
                System.out.println("배열의 길이를 잘못 입력하였습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }

        System.out.println("구하고자 하는 일부 배열의 끝 index를 입력하세요 (배열의 시작 index보다 100000 미만으로 차이나도록 입력) >>> ");
        int right = 0;
        try {
            right = scan.nextInt();
            if(right < 0 || right >= Math.pow(10000000, 2)) {
                System.out.println("배열의 길이를 잘못 입력하였습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }

            if(left > right) {
                System.out.println("배열의 끝 index 값을 잘못 입력하였습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }

        } catch(Exception e) {
            System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }

        if(right - left >= Math.pow(10, 5)) {
            System.out.println("배열의 길이가 너무 깁니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }

        int[][] matrix = new int[length][length];
        for(int x = 0; x < length; x++) {
            matrix[x][x] = x + 1;
            for(int y = 0; y <= x; y++) {
                if(matrix[x][y] == 0 && x != y) {
                    matrix[x][y] = x + 1;
                    matrix[y][x] = x + 1;
                }
            }
        }

        int[] stretchArray = new int[(int) Math.pow(length, 2)];
        for(int x = 0; x < length; x++) {
            System.arraycopy(matrix[x], 0, stretchArray, length * x, length);
        }

        int[] copyStretchArray = Arrays.copyOfRange(stretchArray, left, right + 1);
        System.out.println("반환된 배열 : " + Arrays.toString(copyStretchArray));
    }
}
