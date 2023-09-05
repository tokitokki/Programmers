package src.level3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class InstallBaseStation { // 기지국 설치
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("아파트의 개수를 입력하세요 (1 이상 200000000 이하의 자연수 중 하나 입력) >>> ");
        String apartCount = scan.nextLine();
        int apartmentCount = 0;

        try {
            apartmentCount = Integer.parseInt(apartCount);
            if(apartmentCount < 1 || apartmentCount > 200000000) {
                System.out.println("잘못된 범위입니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("잘못 입력하셨습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }
        System.out.println("아파트 " + apartmentCount + "채가 나란히 있습니다.");
        System.out.println();

        ArrayList<Integer> setupStations = new ArrayList<>();
        while(true) {
            System.out.println("몇 번 아파트에 4g 기지국을 설치하였나요? >>> ");
            System.out.println("[1 이상 해당 아파트 길이 이하의 자연수 중 중복 없이 하나 입력 (종료키 : x)]");
            String stationNumber = scan.nextLine();
            if(stationNumber.equalsIgnoreCase("x")) {
                System.out.println("입력을 종료합니다.");
                break;
            }

            int stationOrder;
            try {
                stationOrder = Integer.parseInt(stationNumber);
                if(stationOrder < 1 || stationOrder > apartmentCount)  {
                    System.out.println("잘못된 범위입니다. 다시 입력해주세요.");
                    System.out.println();
                    continue;
                }
            } catch(Exception e) {
                System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
                System.out.println();
                continue;
            }

            if(setupStations.isEmpty()) {
                System.out.println(stationOrder + "번째 아파트에 기지국이 있습니다.");
                System.out.println();
                setupStations.add(stationOrder);
                continue;
            }

            if(setupStations.contains(stationOrder)) {
                System.out.println("아파트 순서가 중복되었습니다. 다시 입력해주세요.");
                System.out.println();
                continue;
            }

            System.out.println(stationOrder + "번째 아파트에 기지국이 있습니다.");
            System.out.println();
            setupStations.add(stationOrder);
        }

        if(setupStations.isEmpty()) {
            System.out.println("기지국이 있는 아파트를 하나 이상 입력해주세요.");
            System.exit(0);
        }

        Collections.sort(setupStations);
        System.out.println("기지국이 있는 아파트 순서를 오름차순으로 나열한 배열 : " + setupStations);
        System.out.println();
        System.out.println("기지국의 전파의 도달 거리를 입력하세요 >>> ");
        String arrivalDistance = scan.nextLine();
        int distance = 0;

        try {
            distance = Integer.parseInt(arrivalDistance);
            if(distance < 1 || distance > 10000) {
                System.out.println("잘못된 범위입니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("잘못 입력하셨습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }

        System.out.println("설치 가능한 최소 기지국 수 : " + minStations(apartmentCount, setupStations, distance));
    }

    public static int minStations(int apartments, ArrayList<Integer> selectApart, int stationDistance) {
        int count = 0;
        boolean[] ableWifi = new boolean[apartments];
        for(int apartNumber : selectApart) {
            int startPoint = apartNumber - 1 - stationDistance;
            int endPoint = apartNumber - 1 + stationDistance;
            if(startPoint < 0) startPoint = 0;
            if(endPoint > apartments - 1) endPoint = apartments - 1;

            for(int x = startPoint; x <= endPoint; x++) {
                ableWifi[x] = true;
            }
        }

        ArrayList<Integer> falseSectionLengths = new ArrayList<>();
        int length = 0;
        for(int y = 0; y < ableWifi.length; y++) {
            if(!ableWifi[y]) {
                if(y == ableWifi.length - 1) {
                    length++;
                    falseSectionLengths.add(length);
                } else {
                    length++;
                }
            } else {
                if(length > 0) falseSectionLengths.add(length);
                length = 0;
            }
        }

        for(int falseSectionLength : falseSectionLengths) {
            if(falseSectionLength % (2 * stationDistance + 1) == 0) {
                count += falseSectionLength / (2 * stationDistance + 1);
            } else {
                count += falseSectionLength / (2 * stationDistance + 1) + 1;
            }
        }

        return count;
    }
}