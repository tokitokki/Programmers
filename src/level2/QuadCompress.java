package src.level2;
import java.util.Arrays;
import java.util.Scanner;

public class QuadCompress { //쿼드압축 후 개수 세기
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("2의 거듭제곱으로 정할 정수를 입력하세요 (0부터 10까지 정수 중 하나 입력) >>> ");
        int answer = 0;
        try {
            answer = scan.nextInt();
            if(answer < 0 || answer > 10) {
                System.out.println("해당되지 않는 범위입니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }

        System.out.println((int) Math.pow(2, answer) + " × " + (int) Math.pow(2, answer) + " 2차원 배열을 생성합니다.");
        int[][] matrix = new int[(int) Math.pow(2, answer)][(int) Math.pow(2, answer)];
        for(int x = 0; x < (int) Math.pow(2, answer); x++) {
            for(int y = 0; y < (int) Math.pow(2, answer); y++) {
                System.out.println((x + 1) + "행 " + (y + 1) + "열에 대입할 값을 입력하세요 (0 또는 1 입력) >>> ");
                try {
                    matrix[x][y] = scan.nextInt();
                    if(!(matrix[x][y] == 0 || matrix[x][y] == 1)) {
                        System.out.println("다른 숫자를 입력하였거나 음의 정수를 입력하였습니다. 다시 입력하세요.");
                        y--;
                        continue;
                    }
                } catch(Exception e) {
                    System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 입력해주세요.");
                    System.exit(0);
                }

                System.out.println((x + 1) + "행 " + (y + 1) + "열에 대입한 값 : " + matrix[x][y]);
            }

            System.out.println((x + 1) + "행의 배열 : " + Arrays.toString(matrix[x]));
        }

        System.out.println("---------입력한 2차원 배열---------");
        for(int x = 0; x < (int) Math.pow(2, answer); x++) {
            System.out.println(Arrays.toString(matrix[x]));
        }

        System.out.println("압축 후 0과 1의 개수를 순서대로 나타낸 배열 : " + Arrays.toString(counts(matrix)));
    }

    static int[] zeroCountOrOneCount;
    public static boolean zip(int[][] array, int x, int y, int size, int value) {
        for(int i = x; i < x + size; i++) {
            for(int j = y; j < y + size; j++) {
                if(array[i][j] != value) return false;
            }
        }

        return true;
    }

    public static void quad(int[][] array, int x, int y, int size) {
        if(zip(array, x, y, size, array[x][y])) {
            if(array[x][y] == 1) {
                zeroCountOrOneCount[1]++;
            } else zeroCountOrOneCount[0]++;
            return;
        }

        quad(array, x, y, size / 2);
        quad(array, x, y + size / 2, size / 2);
        quad(array, x + size / 2, y, size / 2);
        quad(array, x + size / 2, y + size / 2, size / 2);
    }

    public static int[] counts(int[][] array) {
        zeroCountOrOneCount = new int[2];
        quad(array, 0, 0, array.length);
        return zeroCountOrOneCount;
    }
}
