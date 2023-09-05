package src.level2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class DefenseGame { //디펜스 게임
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int armyCount = 0;

        System.out.println("보유하고 있는 병사의 수를 입력하세요 (1 이상 1000000000 이하의 자연수 중 하나 입력) >>> ");
        try {
            armyCount = scan.nextInt();
            if(armyCount < 1 || armyCount > 1000000000) {
                System.out.println("범위를 잘못 입력하였습니다. 처음부터 다시 입력하세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("잘못 입력하였습니다. 처음부터 다시 입력하세요.");
            System.exit(0);
        }

        int freePassCount = 0;
        System.out.println("보유하고 있는 무적권의 개수를 입력하세요 (1 이상 500000 이하의 자연수 중 하나 입력) >>> ");
        try {
            freePassCount = scan.nextInt();
            if(freePassCount < 1 || freePassCount > 500000) {
                System.out.println("범위를 잘못 입력하였습니다. 처음부터 다시 입력하세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("잘못 입력하였습니다. 처음부터 다시 입력하세요.");
            System.exit(0);
        }

        int rounds = 0;
        System.out.println("최대 몇 라운드까지 스테이지가 있나요? (1 이상 1000000 이하의 자연수 중 하나 입력) >>> ");
        try {
            rounds = scan.nextInt();
            if(rounds < 1 || rounds > 1000000) {
                System.out.println("범위를 잘못 입력하였습니다. 처음부터 다시 입력하세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("잘못 입력하였습니다. 처음부터 다시 입력하세요.");
            System.exit(0);
        }

        int[] enemyCounts = new int[rounds];
        for(int x = 0; x < enemyCounts.length; x++) {
            System.out.println((x + 1) + "번째 라운드에 출현하는 적의 수를 입력하세요 (1 이상 1000000 이하의 자연수 중 하나 입력) >>>");
            String enemies = scan.next();
            int enemyCount;
            try {
                enemyCount = Integer.parseInt(enemies);
                if (enemyCount < 1 || enemyCount > 1000000) {
                    System.out.println("잘못 입력하였습니다. 다시 입력하세요.");
                    x--;
                    continue;
                }
            } catch (Exception e) {
                System.out.println("잘못 입력하였습니다. 다시 입력하세요.");
                x--;
                continue;
            }

            System.out.println((x + 1) + "번째 라운드에 출현하는 적의 수 : " + enemyCount + "명");
            enemyCounts[x] = enemyCount;
        }

        System.out.println("스테이지별 적의 수 : " + Arrays.toString(enemyCounts));

        if(freePassCount >= rounds) {
            System.out.println("결과값 : " + rounds);
            System.exit(0);
        }

        int[] playRounds = new int[enemyCounts.length];

        int maxRounds = 0;
        for(int i : ordersSort(rounds, freePassCount).keySet()) {
            System.arraycopy(enemyCounts, 0, playRounds, 0, playRounds.length);
            int[] order = ordersSort(rounds, freePassCount).get(i);
            for(int j : order) {
                for(int z = 0; z < playRounds.length; z++) {
                    if(j == z + 1) playRounds[z] = 0;
                }
            }

            int armies = armyCount;
            int clearStage = 0;
            for(int j = 0; j < playRounds.length; j++) {
                if(armies >= playRounds[j]) {
                    clearStage++;
                    armies -= playRounds[j];
                } else {
                    if(maxRounds < clearStage) maxRounds = clearStage;
                    break;
                }

                if(j == playRounds.length - 1) maxRounds = clearStage;
            }
        }

        System.out.println("결과값 : " + maxRounds);
    }

    public static int permutation(int n, int k) {
        if(k == 0) return 1;
        if(k == 1) return n;
        else return n * permutation(n - 1, k - 1);
    }

    public static int[] freePassOrders(int n, int k, int count) {
        int[] numbers = new int[n];
        for(int x = 0; x < numbers.length; x++) {
            numbers[x] = x + 1;
        }

        int[] orders = new int[k];
        int[] changeNumbers = numbers;
            for(int x = 0; x < orders.length; x++) {
                for(int y = 0; y < numbers.length; y++) {
                    if(y * permutation(n - x - 1, k - x - 1) + 1 <= count && count <= (y + 1) * permutation(n - x - 1, k - x - 1)) {
                        orders[x] = changeNumbers[y];
                        count -= y * permutation(n - x - 1, k - x - 1);
                        int[] changeOtherNumbers = new int[n - x - 1];
                        for(int z = 0; z < changeNumbers.length; z++) {
                            if(z < y) changeOtherNumbers[z] = changeNumbers[z];
                            if(z > y) changeOtherNumbers[z - 1] = changeNumbers[z];
                        }
                        changeNumbers = changeOtherNumbers;
                    }
                }
            }
        return orders;
    }

    public static HashMap<Integer, int[]> ordersSort(int n, int k) {
        HashMap<Integer, int[]> differentOrders = new HashMap<>();

        for(int i = 1; i <= permutation(n, k); i++) {
            int[] orders = freePassOrders(n, k, i);
            for (int x = 0; x < orders.length; x++) {
                for (int y = 0; y < orders.length; y++) {
                    if (x < y && orders[x] > orders[y]) {
                        int xValue = orders[x];
                        int yValue = orders[y];
                        orders[x] = yValue;
                        orders[y] = xValue;
                    }
                }
            }

            boolean duplicate;
            if(!differentOrders.isEmpty()) {
                int count;
                duplicate = false;
                for(int[] turn : differentOrders.values()) {
                    count = 0;
                    for(int z = 0; z < turn.length; z++) {
                        if(orders[z] == turn[z]) count++;
                    }

                    if(count == turn.length) {
                        duplicate = true;
                        break;
                    }
                }

                if(!duplicate) {
                    differentOrders.put(i, orders);
                }
            } else differentOrders.put(i, orders);
        }

        return differentOrders;
    }
}