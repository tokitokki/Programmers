package src.level2;

import java.util.HashMap;
import java.util.Scanner;

public class MagicalElevator { //마법의 엘리베이터
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("마법의 엘리베이터가 있는 층을 입력하세요 (1부터 100000000 까지의 자연수 중 하나 입력) >>> ");
        int floor = 0;
        try {
            floor = scan.nextInt();
            if (floor < 1 || floor > 100000000) {
                System.out.println("범위를 잘못 입력하였습니다. 처음부터 다시 입력하세요.");
                System.exit(0);
            }
        } catch (Exception e) {
            System.out.println("잘못 입력하였습니다. 처음부터 다시 입력하세요.");
            System.exit(0);
        }

        System.out.println("사용할 수 있는 최소 스톤의 개수 : " + minSummonStoneCounts(floor));
    }

    public static int minSummonStoneCounts(int floor) {
        int count = 0;

        HashMap<Integer, int[]> cases = new HashMap<>();
        for(int i = 0; i < String.valueOf(floor).length(); i++) {
            int index = 0;
            int[] caseValue = new int[2];
            if(i == 0) {
                int rests = floor % 10;
                if(rests != 0) {
                    index++;
                    caseValue[0] = floor + 10 - rests;
                    caseValue[1] = 10 - rests;
                    cases.put(index, caseValue);

                    index++;
                    caseValue = new int[2];
                    caseValue[0] = floor - rests;
                    caseValue[1] = rests;
                    cases.put(index, caseValue);
                } else {
                    caseValue[0] = floor;
                    cases.put(index, caseValue);
                }
            } else {
                HashMap<Integer, int[]> nextCases = new HashMap<>();
                for(int value : cases.keySet()) {
                    int rests = cases.get(value)[0] % (int) Math.pow(10, i + 1);
                    int presentStones = cases.get(value)[1];
                    if(rests != 0) {
                        if(i != String.valueOf(floor).length() - 1) {
                            int plusValue = cases.get(value)[0] + (int) Math.pow(10, i + 1) - rests;
                            int plusValueStones = ((int) Math.pow(10, i + 1) - rests) / (int) Math.pow(10, i);

                            index++;
                            caseValue[0] = plusValue;
                            caseValue[1] = presentStones + plusValueStones;
                            nextCases.put(index, caseValue);
                        }

                        caseValue = new int[2];
                        int minusValue = cases.get(value)[0] - rests;
                        int minusValueStones = (rests / (int) Math.pow(10, i));
                        index++;
                        caseValue[0] = minusValue;
                        caseValue[1] = presentStones + minusValueStones;
                        nextCases.put(index, caseValue);
                    } else {
                        index++;
                        caseValue[0] = cases.get(value)[0];
                        caseValue[1] = presentStones;
                        nextCases.put(index, caseValue);
                    }
                }

                cases = nextCases;
            }
        }

        for(int stones : cases.keySet()) {
            if (count == 0) count = cases.get(stones)[1];
            else if (count > cases.get(stones)[1]) count = cases.get(stones)[1];
        }

        return count;
    }
}
