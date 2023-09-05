package src.level2;

import java.util.*;

public class InterceptionMissile { // 요격 미사일
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("폭격 미사일의 개수를 입력하세요 (1부터 500000까지의 자연수 중 하나 입력) >>> ");
        String attackMissile = scan.nextLine();
        int attackMissiles = 0;

        try {
            attackMissiles = Integer.parseInt(attackMissile);
            if(attackMissiles < 1 || attackMissiles > 500000) {
                System.out.println("범위가 잘못되었습니다. 처음부터 다시 입력하세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 입력하세요.");
            System.exit(0);
        }

        System.out.println(attackMissiles + "개의 미사일을 막기 위하여 요격 미사일을 설치합니다.");

        int[][] sections = new int[attackMissiles][2];
        ArrayList<Integer> minPoints = new ArrayList<>();
        ArrayList<Integer> maxPoints = new ArrayList<>();
        for(int x = 0; x < sections.length; x++) {
            System.out.println((x + 1) + "번째 미사일을 발사할 때 어느 구간부터 폭격이 되나요? >>> ");
            String startPoint = scan.nextLine();
            int changeTypeStartPoint;

            try {
                changeTypeStartPoint = Integer.parseInt(startPoint);
            } catch(Exception e) {
                System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 다시 입력하세요.");
                System.out.println();
                x--;
                continue;
            }
            System.out.println();

            System.out.println((x + 1) + "번째 미사일을 발사할 때 어느 구간까지 폭격이 되나요? >>> ");
            String endPoint = scan.nextLine();
            int changeTypeEndPoint;

            try {
                changeTypeEndPoint = Integer.parseInt(endPoint);
                if(changeTypeStartPoint >= changeTypeEndPoint) {
                    System.out.println("시작 지점이 종료 지점보다 더 크거나 같습니다. 시작 지점부터 다시 입력하세요.");
                    System.out.println();
                    x--;
                    continue;
                }
            } catch(Exception e) {
                System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 시작 지점부터 다시 입력하세요.");
                System.out.println();
                x--;
                continue;
            }

            System.out.println((x + 1) + "번째 폭격 미사일의 구간 : [" + changeTypeStartPoint + ", " + changeTypeEndPoint + "]");
            System.out.println();
            sections[x][0] = changeTypeStartPoint;
            sections[x][1] = changeTypeEndPoint;
            if(!minPoints.isEmpty()) {
                boolean duplicate = false;
                for(int minPoint : minPoints) {
                    if(minPoint == changeTypeStartPoint) {
                        duplicate = true;
                        break;
                    }
                }

                if(!duplicate) minPoints.add(changeTypeStartPoint);
            } else minPoints.add(changeTypeStartPoint);

            if(!maxPoints.isEmpty()) {
                boolean duplicate = false;
                for(int maxPoint : maxPoints) {
                    if(maxPoint == changeTypeEndPoint) {
                        duplicate = true;
                        break;
                    }
                }

                if(!duplicate) maxPoints.add(changeTypeEndPoint);
            } else maxPoints.add(changeTypeEndPoint);
        }

        System.out.println("모든 미사일의 구간을 나타낸 배열 : " + Arrays.deepToString(sections));
        System.out.println("최소 요격 미사일의 개수 : " + minDefenseMissiles(sections, minPoints, maxPoints));
    }

    public static int minDefenseMissiles(int[][] coord, ArrayList<Integer> minValues, ArrayList<Integer> maxValues) {
        int missileCount = 0;
        int minValue = Collections.min(minValues);
        int maxValue = Collections.max(maxValues);
        HashMap<Double, Integer> defenseMissilePoints = new HashMap<>();
        for(double x = minValue + 0.5; x <= maxValue - 0.5; x += 1.0) {
            defenseMissilePoints.put(x, 0);
        }

        for(int[] missile : coord) {
            for(double key : defenseMissilePoints.keySet()) {
                int count = defenseMissilePoints.get(key);
                if(missile[0] < key && key < missile[1]) count++;
                defenseMissilePoints.put(key, count);
            }
        }

        int maxIncludeMissiles;
        while(true) {
            maxIncludeMissiles = Collections.max(defenseMissilePoints.values());
            if(maxIncludeMissiles == 0) break;
            missileCount++;
            double selectKey = 0;
            for(double i = minValue + 0.5; i <= maxValue - 0.5; i += 1.0) {
                if(defenseMissilePoints.get(i) == maxIncludeMissiles) {
                    selectKey = i;
                    break;
                }
            }

            for(int[] missile : coord) {
                if(missile[0] < selectKey && selectKey < missile[1]) {
                    for(double j = missile[0] + 0.5; j <= missile[1] - 0.5; j += 1.0) {
                        int count = defenseMissilePoints.get(j);
                        count--;
                        defenseMissilePoints.put(j, count);
                    }
                }
            }
        }

        return missileCount;
    }
}