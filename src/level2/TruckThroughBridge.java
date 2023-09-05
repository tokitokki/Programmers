package src.level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TruckThroughBridge { //다리를 지나는 트럭
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("다리의 길이를 입력하세요 (1 이상 10000 이하) >>> ");
        int bridgeLength = 0;
        try {
            bridgeLength = scan.nextInt();
            if(bridgeLength <= 0 || bridgeLength > 1000) {
                System.out.println("범위에 해당되지 않는 정수를 입력하였습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("문자를 포함하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }

        System.out.println("트럭이 견딜 수 있는 최대 무게를 입력하세요 (1 이상 10000 이하) >>> ");
        int maxWeight = 0;
        try {
            maxWeight = scan.nextInt();
            if(maxWeight <= 0 || maxWeight > 10000) {
                System.out.println("범위에 해당되지 않는 정수를 입력하였습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("문자를 포함하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }

        System.out.println("트럭의 수를 입력하세요 (1 이상 10000 이하) >>> ");
        int truckCount = 0;
        try {
            truckCount = scan.nextInt();
            if(truckCount <= 0 || truckCount > 10000) {
                System.out.println("범위에 해당되지 않는 정수를 입력하였습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("문자를 포함하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }

        int[] truckWeights = new int[truckCount];
        for(int x = 0; x < truckWeights.length; x++) {
            System.out.println((x + 1) + "번째 트럭의 무게를 입력하세요 >>> ");
            int truckWeight = 0;
            try {
                truckWeight = scan.nextInt();
                if(truckWeight <= 0) {
                    System.out.println("자연수가 아닌 정수를 입력하였습니다. 처음부터 다시 입력해주세요.");
                    x--;
                    continue;
                } else if(truckWeight > maxWeight) {
                    System.out.println("트럭의 무게는 다리가 견디는 최대 무게를 초과할 수 없습니다. 처음부터 다시 입력해주세요.");
                    x--;
                    continue;
                }

            } catch(Exception e) {
                System.out.println("문자를 포함하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 입력해주세요.");
                x--;
                System.exit(0);
            }

            System.out.println((x + 1) + "번째 트럭의 무게 : " + truckWeight);
            truckWeights[x] = truckWeight;
        }

        System.out.println("각 트럭의 무게를 나열한 배열 : " + Arrays.toString(truckWeights));
        System.out.println("다리를 모두 통과하는데 걸리는 최소 시간 : " + minTime(bridgeLength, maxWeight, truckWeights));
    }

    public static int minTime(int bridgeLength, int maxWeight, int[] truckWeights) {
        int time = 0;
        String[] truckPositions = new String[truckWeights.length];
        Arrays.fill(truckPositions, "ready");
        int[] arriveTruckTime = new int[truckWeights.length];
        ArrayList<Integer> movingTrucks = new ArrayList<>();
        boolean flag = true;
        while(flag) {
            time++;
            int totalWeight = 0;
            for(int movingTruck : movingTrucks) {
                totalWeight += movingTruck;
            }

            for(int x = 0; x < truckWeights.length; x++) {
                if(truckPositions[x].equals("moving") && arriveTruckTime[x] == time) {
                    truckPositions[x] = "finish";
                    totalWeight -= truckWeights[x];
                    movingTrucks.remove(0);
                    break;
                }
            }

            for(int x = 0; x < truckWeights.length; x++) {
                if(truckPositions[x].equals("ready")) {
                    if(totalWeight + truckWeights[x] <= maxWeight) {
                        movingTrucks.add(truckWeights[x]);
                        arriveTruckTime[x] = time + bridgeLength;
                        truckPositions[x] = "moving";
                    }
                    break;
                }
            }

            int finishCount = 0;
            for(int x = 0; x < truckWeights.length; x++) {
                if(truckPositions[x].equals("finish")) {
                    finishCount++;
                }
            }

            if(finishCount == truckWeights.length) flag = false;
        }

        return time;
    }
}
