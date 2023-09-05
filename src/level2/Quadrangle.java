package src.level2;
import java.util.Scanner;

public class Quadrangle { //멀쩡한 사각형
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int width = 0;
        System.out.println("직사각형의 가로의 길이를 입력하세요 (1억 이하의 정수) >>> ");
        try {
            width = scan.nextInt();
            if(width <= 0 || width > 100000000) {
                System.out.println("범위에 해당되지 않는 정수를 입력하였습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 시도하세요");
            System.exit(0);
        }

        int height = 0;
        System.out.println("직사각형의 세로의 길이를 입력하세요 (1억 이하의 정수) >>> ");
        try {
            height = scan.nextInt();
            if(height <= 0 || height > 100000000) {
                System.out.println("범위에 해당되지 않는 정수를 입력하였습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 시도하세요");
            System.exit(0);
        }

        System.out.println("해당 문제에 맞게 구한 정사각형 개수 : " + squareCount(width, height) + "개");
    }

    public static int squareCount(int width, int height) {
        int count = 0;
        double y;
        for(int x = 1; x <= width; x++) {
            y = height - (double) height / width * x;
            count += Math.floor(y);
        }

        return count * 2;
    }
}
