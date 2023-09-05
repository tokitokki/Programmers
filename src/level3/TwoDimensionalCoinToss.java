package src.level3;

import java.util.Arrays;
import java.util.Scanner;

public class TwoDimensionalCoinToss { // 2차원 동전 뒤집기
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("동전을 담는 판의 가로 길이를 입력하세요 (1 이상 10 이하의 자연수 중 하나 입력) >>> ");
        String length = scan.nextLine();
        int changeTypeWidth = 0;
        try {
            changeTypeWidth = Integer.parseInt(length);
            if(changeTypeWidth < 1 || changeTypeWidth > 10) {
                System.out.println("범위가 잘못되었습니다. 처음부터 다시 입력하세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("잘못 입력하셨습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }
        System.out.println();

        System.out.println("동전을 담는 판의 세로 길이를 입력하세요 (1 이상 10 이하의 자연수 중 하나 입력) >>> ");
        length = scan.nextLine();
        int changeTypeHeight = 0;

        try {
            changeTypeHeight = Integer.parseInt(length);
            if(changeTypeHeight < 1 || changeTypeHeight > 10) {
                System.out.println("범위가 잘못되었습니다. 처음부터 다시 입력하세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("잘못 입력하셨습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }
        System.out.println("동전을 담는 판의 가로 길이 : " + changeTypeWidth + ", 세로 길이 : " + changeTypeHeight);
        System.out.println();

        int[][] coinsBegin = new int[changeTypeHeight][changeTypeWidth];
        int[][] coinsEnd = new int[changeTypeHeight][changeTypeWidth];

        System.out.println("-------[초기 상태의 동전의 앞면, 뒷면을 입력합니다]-------");
        for(int x = 0; x < coinsBegin.length; x++) {
            for(int y = 0; y < coinsBegin[x].length; y++) {
                System.out.println("초기 상태의 " + (x + 1) + "행 " + (y + 1) + "열 동전의 면을 입력하세요 >>> ");
                System.out.println("[앞면은 0 또는 '앞면'으로, 뒷면은 1 또는 '뒷면'으로 입력하세요.]");
                String coin = scan.nextLine();
                if(coin.equals("앞면")) coin = "0";
                else if(coin.equals("뒷면")) coin = "1";
                int changeTypeCoin;

                try {
                    changeTypeCoin = Integer.parseInt(coin);
                    if(!(changeTypeCoin == 0 || changeTypeCoin == 1)) {
                        System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
                        System.out.println();
                        y--;
                        continue;
                    }
                } catch(Exception e) {
                    System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
                    System.out.println();
                    y--;
                    continue;
                }

                String coinTossResult;
                if(changeTypeCoin == 0) coinTossResult = "앞면";
                else coinTossResult = "뒷면";

                System.out.println("초기 상태의 " + (x + 1) + "행 " + (y + 1) + "열의 동전은 " + coinTossResult + "입니다.");
                System.out.println();
                coinsBegin[x][y] = changeTypeCoin;
            }
        }

        System.out.println("-------[초기 상태의 동전 앞면, 뒷면을 나타낸 배열]-------");
        for(int[] coinsLine : coinsBegin) {
            System.out.println(Arrays.toString(coinsLine));
        }
        System.out.println();

        System.out.println("-------[목표 상태의 동전의 앞면, 뒷면을 입력합니다]-------");
        for(int x = 0; x < coinsEnd.length; x++) {
            for(int y = 0; y < coinsEnd[x].length; y++) {
                System.out.println("목표 상태의 " + (x + 1) + "행 " + (y + 1) + "열 동전의 면을 입력하세요 >>> ");
                System.out.println("[앞면은 0 또는 '앞면'으로, 뒷면은 1 또는 '뒷면'으로 입력하세요.]");
                String coin = scan.nextLine();
                if(coin.equals("앞면")) coin = "0";
                else if(coin.equals("뒷면")) coin = "1";
                int changeTypeCoin;

                try {
                    changeTypeCoin = Integer.parseInt(coin);
                    if(!(changeTypeCoin == 0 || changeTypeCoin == 1)) {
                        System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
                        System.out.println();
                        y--;
                        continue;
                    }
                } catch(Exception e) {
                    System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
                    System.out.println();
                    y--;
                    continue;
                }

                String coinTossResult;
                if(changeTypeCoin == 0) coinTossResult = "앞면";
                else coinTossResult = "뒷면";

                System.out.println("목표 상태의 " + (x + 1) + "행 " + (y + 1) + "열의 동전은 " + coinTossResult + "입니다.");
                System.out.println();
                coinsEnd[x][y] = changeTypeCoin;
            }
        }

        System.out.println("-------[목표 상태의 동전 앞면, 뒷면을 나타낸 배열]-------");
        for(int[] coinsLine : coinsEnd) {
            System.out.println(Arrays.toString(coinsLine));
        }
        System.out.println();

        System.out.println("결과값 (목표 상태로 안 되면 -1) : " + minValue(coinsBegin, coinsEnd));
    }

    public static int minValue(int[][] coinsBegin, int[][] coinsEnd) {
        int count = 0;
        for(int a = 0; a < coinsBegin.length; a++) {
            if(coinsBegin[a][0] != coinsEnd[a][0]) {
                widthToss(coinsBegin, a);
                count++;
            }
        }

        for(int a = 0; a < coinsBegin[0].length; a++) {
            int correct = 0;
            int different = 0;

            for(int b = 0; b < coinsBegin.length; b++) {
                if(coinsBegin[b][a] == coinsEnd[b][a]) correct++;
                else different++;
            }

            if(different == coinsBegin.length) {
                heightToss(coinsBegin, a);
                count++;
            } else if(correct != coinsBegin.length) {
                count = -1;
                break;
            }
        }

        return count;
    }

    public static void widthToss(int[][] coins, int order) {
        for(int x = 0; x < coins[order].length; x++) {
            if(coins[order][x] == 0) coins[order][x] = 1;
            else coins[order][x] = 0;
        }
    }

    public static void heightToss(int[][] coins, int order) {
        for(int x = 0; x < coins.length; x++) {
            if(coins[x][order] == 0) coins[x][order] = 1;
            else coins[x][order] = 0;
        }
    }
}