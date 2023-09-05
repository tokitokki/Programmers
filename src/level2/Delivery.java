package src.level2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Delivery { // 배달

    public static final int INFINITE = 500001; // (배달 시간 최대 범위) + 1 로 값을 무한대로 만듬

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("마을의 개수를 입력하세요 (1 이상 50 이하의 자연수 중 하나 입력) >>> ");
        String village = scan.nextLine();
        int villageCount = 0;
        int putCount = 0;

        try {
            villageCount = Integer.parseInt(village);
            if (villageCount < 1 || villageCount > 50) {
                System.out.println("범위가 잘못되었습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch (Exception e) {
            System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }

        System.out.println("마을의 개수 : " + villageCount + "개");
        System.out.println();
        HashMap<Integer, int[]> deliveryTimes = new HashMap<>();
        while (true) {
            System.out.println("도로의 시작 지점을 입력하거나 종료키를 입력하세요 >>> ");
            System.out.println("[도로 시작 지점 : 1부터 해당 마을의 번호까지의 자연수 중 하나 입력]");
            System.out.println("[종료키 : X]");
            String inputString = scan.nextLine();
            if (inputString.equalsIgnoreCase("x")) {
                System.out.println("종료키를 입력하였습니다. 도로 입력을 종료합니다.");
                System.out.println();
                break;
            }

            int[] deliverInfo = new int[3];
            int startNumber;

            try {
                startNumber = Integer.parseInt(inputString);
                if (startNumber > villageCount) {
                    System.out.println("마을의 시작 지점 번호를 잘못 입력하였습니다. 다시 입력해주세요.");
                    System.out.println();
                    continue;
                }
            } catch (Exception e) {
                System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 다시 입력해주세요.");
                System.out.println();
                continue;
            }

            System.out.println("시작 지점 번호 : " + startNumber);
            deliverInfo[0] = startNumber;

            System.out.println();
            System.out.println("도로의 종료 지점을 입력하세요 (시작 마을 번호와 다른 번호로 마을의 마지막 번호까지의 자연수 중 하나 입력) >>> ");
            String endVillage = scan.nextLine();
            int endNumber;

            try {
                endNumber = Integer.parseInt(endVillage);
                if (startNumber == endNumber || endNumber > villageCount) {
                    System.out.println("마을의 종료 지점 번호를 잘못 입력하였습니다. 시작 지점 번호부터 다시 입력해주세요.");
                    System.out.println();
                    continue;
                }
            } catch (Exception e) {
                System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 시작 지점 번호부터 다시 입력해주세요.");
                System.out.println();
                continue;
            }

            System.out.println("종료 지점 번호 : " + endNumber);
            deliverInfo[1] = endNumber;

            System.out.println();
            System.out.println("도로를 지나는데 걸리는 시간은 몇 시간입니까? (1이상 10000이하의 자연수 중 하나 입력) >> ");
            String loadTime = scan.nextLine();
            int time;

            try {
                time = Integer.parseInt(loadTime);
                if (time < 1 || time > 10000) {
                    System.out.println("걸리는 시간을 잘못 입력하였습니다. 시작 지점 번호부터 다시 입력해주세요.");
                    System.out.println();
                    continue;
                }
            } catch (Exception e) {
                System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 시작 지점 번호부터 다시 입력해주세요.");
                System.out.println();
                continue;
            }

            System.out.println("도로의 시작 구간과 종료 구간 : [" + deliverInfo[0] + ", " + deliverInfo[1] + "]");
            System.out.println("배달하는데 걸리는 시간 : " + time + "시간");
            deliverInfo[2] = time;

            if (!deliveryTimes.isEmpty()) {
                boolean duplicate = false;
                for (int[] deliveryTime : deliveryTimes.values()) {
                    if (deliverInfo[0] == deliveryTime[0] && deliverInfo[1] == deliveryTime[1]) {
                        duplicate = true;
                        break;
                    } else if (deliverInfo[0] == deliveryTime[1] && deliverInfo[1] == deliveryTime[0]) {
                        duplicate = true;
                        break;
                    }
                }

                if (duplicate) {
                    System.out.println("도로가 중복되었습니다. 시작 지점 번호부터 다시 입력해주세요.");
                    System.out.println();
                } else {
                    putCount++;
                    deliveryTimes.put(putCount, deliverInfo);
                    System.out.println("도로를 추가하였습니다.");
                    System.out.println();
                }
            } else {
                putCount++;
                deliveryTimes.put(putCount, deliverInfo);
                System.out.println("도로를 추가하였습니다.");
                System.out.println();
            }
        }

        if(deliveryTimes.size() > 0 && deliveryTimes.size() <= 2000) {
            System.out.println("-----[각 도로 정보]-----");
            for(int[] deliveryTime : deliveryTimes.values()) {
                System.out.println(Arrays.toString(deliveryTime));
            }
        } else if(deliveryTimes.size() > 2000) {
            System.out.println("도로는 2000개를 초과할 수 없습니다.");
            System.exit(0);
        } else {
            System.out.println("도로를 하나 이상 입력해 주세요.");
            System.exit(0);
        }
        System.out.println();

        System.out.println("몇 시간 안에 배달이 가능한 마을의 수를 구할까요? (1부터 500000이하의 자연수 중 하나 입력) >>> ");
        String deliverTime = scan.nextLine();
        int parseDeliverTime = 0;

        try {
            parseDeliverTime = Integer.parseInt(deliverTime);
            if(parseDeliverTime < 1 || parseDeliverTime > 500000) {
                System.out.println("범위가 잘못되었습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }
        System.out.println(parseDeliverTime + "시간 안에 배달이 가능한 마을의 수를 구합니다.");

        System.out.println();
        System.out.println("해당되는 마을의 개수 : " + ableDeliveryVillages(villageCount, deliveryTimes, parseDeliverTime));
    }

    public static int ableDeliveryVillages(int village, HashMap<Integer, int[]> roads, int timeLimit) {
        int villageCount = 0;
        int[][] plate = new int[village][village];

        for(int x = 0; x < plate.length; x++) {
            for(int y = 0; y < plate.length; y++) {
                if(x == y) continue;
                plate[x][y] = INFINITE;
            }
        }

        for(int[] road : roads.values()) {
            if(plate[road[0] - 1][road[1] - 1] < road[2]) continue;
            plate[road[0] - 1][road[1] - 1] = road[2];
            plate[road[1] - 1][road[0] - 1] = road[2];
        }

        for(int x = 0; x < plate.length; x++) {
            for(int y = 0; y < plate.length; y++) {
                for(int z = 0; z < plate.length; z++) {
                    if(plate[y][z] > plate[y][x] + plate[x][z]) plate[y][z] = plate[y][x] + plate[x][z];
                }
            }
        }

        for(int i = 0; i < plate[0].length; i++) {
            if(plate[0][i] <= timeLimit) villageCount++;
        }

        return villageCount;
    }
}