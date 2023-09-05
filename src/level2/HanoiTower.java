package src.level2;

import java.util.Arrays;
import java.util.Scanner;

public class HanoiTower { // 하노이의 탑
    public static final int[] MOVE_A = {1, 2};
    public static final int[] MOVE_B = {1, 3};
    public static final int[] MOVE_C = {2, 3};


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("하노이의 탑에서 사용하는 원판의 개수를 입력하세요. (15이하의 자연수로 입력) >>> ");
        String answer = scan.nextLine();
        int changeTypeAnswer = 0;

        try {
            changeTypeAnswer = Integer.parseInt(answer);
            if(changeTypeAnswer < 1 || changeTypeAnswer > 15) {
                System.out.println("범위가 잘못되었습니다. 처음부터 다시 입력하세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("잘못 입력하셨습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }

        System.out.println("원판을 " + changeTypeAnswer + "개 넣고 실행합니다.");
        System.out.println("원판을 움직이는 방법 : " + Arrays.deepToString(moveMethod(changeTypeAnswer)));
    }

    public static int[][] moveMethod(int n) {
        int[][] moving = new int[(int)(Math.pow(2, n)) - 1][2];
        if(n == 1) {
            moving[0] = MOVE_B;
        } else if(n == 2) {
            moving[0] = MOVE_A;
            moving[1] = moveMethod(1)[0];
            moving[2] = MOVE_C;
        } else {
            if(n % 2 == 1) {
                moving[0] = moveMethod(1)[0];
                moving[moving.length - 1] = moveMethod(1)[0];
            } else {
                moving[0] = moveMethod(2)[0];
                moving[moving.length - 1] = moveMethod(2)[2];
            }

            for(int x = 1; x < moving.length - 1; x++) {
                if(x % 2 == 1) moving[x] = moveMethod(n - 1)[(x - 1) / 2];
            }

            for(int x = 1; x < moving.length - 1; x++) {
                if(x % 2 == 0) {
                    int commonNumber = 0;
                    int leftNumber = 0;
                    int rightNumber = 0;
                    boolean commonFindYn = false;
                    boolean differenceFindYn = false;
                    for(int i = 0; i < 2; i++) {
                        for(int j = 0; j < 2; j++) {
                            if(moving[x - 1][i] == moving[x + 1][j]) {
                                commonNumber = moving[x - 1][i];
                                commonFindYn = true;
                                break;
                            }
                        }

                        if(commonFindYn) break;
                    }

                    for(int i = 0; i < 2; i++) {
                        for(int j = 0; j < 2; j++) {
                            if(moving[x - 1][i] != commonNumber && moving[x + 1][j] != commonNumber && moving[x - 1][i] != moving[x + 1][j]) {
                                leftNumber = moving[x - 1][i];
                                rightNumber = moving[x + 1][j];
                                differenceFindYn = true;
                                break;
                            }
                        }

                        if(differenceFindYn) break;
                    }

                    moving[x][0] = rightNumber;
                    moving[x][1] = leftNumber;
                }
            }
        }
        return moving;
    }
}