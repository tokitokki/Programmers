package src.level3;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class CrackdownCamera { // 단속 카메라

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("고속도로를 지나는 차는 몇 대입니까? (1 이상 10000 이하의 자연수 중 하나 입력) >>> ");
        String cars = scan.nextLine();
        int carCount = 0;

        try {
            carCount = Integer.parseInt(cars);
            if(carCount < 1 || carCount > 20000) {
                System.out.println("범위가 잘못되었습니다. 처음부터 다시 입력하세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("잘못 입력하였습니다. 처음부터 다시 입력하세요.");
            System.exit(0);
        }
        System.out.println("고속도로에 " + carCount + "대의 차가 지나갔습니다.");
        int[][] terms = new int[carCount][2];
        System.out.println();

        for(int a = 0; a < terms.length; a++) {
            System.out.println((a + 1) + "번째 차량이 고속도로를 통과한 시작 지점을 입력하세요 >>> ");
            System.out.println("[시작 지점의 좌표는 -30000 부터 29999 까지 입니다.]");
            String startCoord = scan.nextLine();
            int changeTypeStartCoord;

            try {
                changeTypeStartCoord = Integer.parseInt(startCoord);
                if(changeTypeStartCoord < -30000 || changeTypeStartCoord >= 30000) {
                    System.out.println("범위가 잘못되었습니다. 다시 입력하세요.");
                    System.out.println();
                    a--;
                    continue;
                }
            } catch(Exception e) {
                System.out.println("잘못 입력하였습니다. 다시 입력하세요.");
                System.out.println();
                a--;
                continue;
            }
            System.out.println();

            System.out.println((a + 1) + "번째 차량이 고속도로를 통과한 종료 지점을 입력하세요 >>> ");
            System.out.println("[종료 지점의 좌표는 -29999 부터 30000 까지이고, 시작 지점의 좌표보다 더 큰 값입니다.]");
            String endCoord = scan.nextLine();
            int changeTypeEndCoord;

            try {
                changeTypeEndCoord = Integer.parseInt(endCoord);
                if(changeTypeEndCoord < changeTypeStartCoord || changeTypeEndCoord > 30000) {
                    System.out.println("범위가 잘못되었습니다. 시작 지점 입력부터 다시 입력하세요.");
                    System.out.println();
                    a--;
                    continue;
                }
            } catch(Exception e) {
                System.out.println("잘못 입력하였습니다. 시작 지점 입력부터 다시 입력하세요.");
                System.out.println();
                a--;
                continue;
            }

            System.out.println("시작 지점과 종료 지점의 좌표 : [" + changeTypeStartCoord + ", " + changeTypeEndCoord + "]");
            System.out.println();
            terms[a][0] = changeTypeStartCoord;
            terms[a][1] = changeTypeEndCoord;
        }

        System.out.println("시작 지점과 종료 지점의 구간을 나타낸 배열 : " + Arrays.deepToString(terms));
        System.out.println("설치할 수 있는 단속 카메라의 최소 개수 : " + cameras(terms));
    }

    public static int cameras(int[][] termArray) {
        int cameraCount = 0;
        int minValue = Integer.MAX_VALUE;
        int maxValue = Integer.MIN_VALUE;
        for(int[] term : termArray) {
            minValue = Math.min(minValue, term[0]);
            maxValue = Math.max(maxValue, term[1]);
        }

        HashMap<Integer, Integer> passCount = new HashMap<>();
        for(int a = minValue; a <= maxValue; a++) {
            passCount.put(a, 0);
        }

        for(int[] term : termArray) {
            for(int x = term[0]; x <= term[1]; x++) {
                int trueCount = passCount.get(x);
                passCount.put(x, trueCount + 1);
            }
        }

        while(true) {
            boolean complete = true;
            for(int key : passCount.keySet()) {
                if(passCount.get(key) > 0) {
                    complete = false;
                    break;
                }
            }

            if(complete) break;
            int maxTrueCount = Collections.max(passCount.values());

            int maxIndex = 0;
            for(int x = minValue; x <= maxValue; x++) {
                if(passCount.get(x) == maxTrueCount) {
                    maxIndex = x;
                    break;
                }
            }

            for(int[] term : termArray) {
                if(term[0] <= maxIndex && maxIndex <= term[1]) {
                    for(int x = term[0]; x <= term[1]; x++) {
                        int trueCount = passCount.get(x);
                        passCount.put(x, trueCount - 1);
                    }
                }
            }

            cameraCount++;
        }

        return cameraCount;
    }
}