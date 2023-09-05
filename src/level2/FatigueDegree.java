package src.level2;

import java.util.Arrays;
import java.util.Scanner;

public class FatigueDegree { //피로도
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("접속한 유저가 갖고 있는 피로도를 입력하세요 (1 이상 5000 이하의 자연수 중 하나 입력) >>> ");
        int fatigueDegree = 0;
        try {
            fatigueDegree = scan.nextInt();
            if(fatigueDegree < 1 || fatigueDegree > 5000) {
                System.out.println("해당되지 않는 범위입니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("잘못 입력하셨습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }
        System.out.println("접속한 유저의 피로도 : " + fatigueDegree);
        System.out.println();

        System.out.println("던전의 개수를 입력하세요 (1 이상 8 이하의 자연수 중 하나 입력) >>> ");
        int dungeons = 0;
        try {
            dungeons = scan.nextInt();
            if(dungeons < 1 || dungeons > 8) {
                System.out.println("해당되지 않는 범위입니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("잘못 입력하셨습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }
        System.out.println();

        int[][] dungeonsFatigue = new int[dungeons][2];
        for(int x = 0; x < dungeonsFatigue.length; x++) {
            System.out.println((x + 1) + "번째 던전에 입장 가능한 최소 필요 피로도를 입력하세요 (1 이상 1000 이하의 자연수로 입력 >>> )");
            String dungeonFatigue = scan.next();
            int changeTypeFatigue;
            try {
                changeTypeFatigue = Integer.parseInt(dungeonFatigue);
                if(changeTypeFatigue < 1 || changeTypeFatigue > 1000) {
                    System.out.println("해당되지 않는 범위입니다. 다시 입력해주세요.");
                    x--;
                    continue;
                }
            } catch(Exception e) {
                System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
                x--;
                continue;
            }

            System.out.println((x + 1) + "번째 던전의 최소 필요 피로도 : " + changeTypeFatigue);
            dungeonsFatigue[x][0] = changeTypeFatigue;
            System.out.println();

            System.out.println((x + 1) + "번째 던전에 입장하면 얼마의 피로도를 소모합니까 (1 이상 최소 필요 피로도 이하의 자연수로 입력) >>> ");
            String consumeFatigue = scan.next();
            int changeTypeConsume;
            try {
                changeTypeConsume = Integer.parseInt(consumeFatigue);
                if(changeTypeConsume < 1 || changeTypeConsume > changeTypeFatigue) {
                    System.out.println("해당되지 않는 범위입니다. " + (x + 1) + "번째 최소 필요 피로도부터 다시 입력해주세요.");
                    x--;
                    continue;
                }
            } catch(Exception e) {
                System.out.println("잘못 입력하셨습니다. " + (x + 1) + "번째 최소 필요 피로도부터 다시 입력해주세요.");
                x--;
                continue;
            }

            System.out.println((x + 1) + "번째 던전을 입장하면 소모되는 피로도 : " + changeTypeConsume);
            dungeonsFatigue[x][1] = changeTypeConsume;
            System.out.println();
        }

        System.out.println("각 던전의 최소 필요 피로도와 소모 피로도를 나타낸 배열 : " + Arrays.deepToString(dungeonsFatigue));
        System.out.println("결과값 : " + maxDungeons(fatigueDegree, dungeonsFatigue));
    }

    public static int maxDungeons(int fatigue, int[][] dungeons) {
        int dungeonCount = 0;
        for(int x = 1; x <= factorial(dungeons.length); x++) {
            int value = fatigue;
            int[] orders = dungeonOrders(dungeons.length, x);
            int[][] settingDungeons = new int[dungeons.length][2];
            for(int y = 0; y < orders.length; y++) {
                for(int z = 0; z < settingDungeons.length; z++) {
                    if(orders[y] == z + 1) {
                        settingDungeons[z][0] = dungeons[y][0];
                        settingDungeons[z][1] = dungeons[y][1];
                    }
                }
            }

            int entranceDungeons = 0;
            for(int[] settingDungeon : settingDungeons) {
                if(value >= settingDungeon[0]) {
                    entranceDungeons++;
                    value -= settingDungeon[1];
                }
            }

            if(dungeonCount <= entranceDungeons) dungeonCount = entranceDungeons;
        }

        return dungeonCount;
    }

    public static int factorial(int n) {
        if(n <= 1) return 1;
        return n * factorial(n - 1);
    }

    public static int[] dungeonOrders(int n, int order) {
        int[] orders = new int[n];
        if(n == 1) {
            orders[0] = 1;
            return orders;
        }

        for(int x = 0; x < orders.length; x++) {
            orders[x] = x + 1;
        }

        int[] thisOrder = new int[orders.length];
        int value = order;
        int count = n - 1;
        for(int x = 0; x < thisOrder.length; x++) {
            for(int y = 0; y < orders.length; y++) {
                if(y * factorial(count) + 1 <= value && value <= (y + 1) * factorial(count)) {
                    thisOrder[x] = orders[y];
                    int[] nextOrders = new int[count];
                    for(int z = 0; z < orders.length; z++) {
                        if(z < y) nextOrders[z] = orders[z];
                        if(z > y) nextOrders[z - 1] = orders[z];
                    }

                    orders = nextOrders;
                    value -= y * factorial(count);
                    count -= 1;
                    break;
                }
            }
        }

        return thisOrder;
    }
}