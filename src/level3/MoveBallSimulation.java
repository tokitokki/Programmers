package src.level3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MoveBallSimulation { // 공 이동 시뮬레이션

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("격자의 행(세로) 길이를 입력하세요 (1부터 1000000000까지 자연수 중 하나 입력) >>> ");
        String row = scan.nextLine();
        int changeTypeRow = 0;

        try {
            changeTypeRow = Integer.parseInt(row);
            if(changeTypeRow < 1 || changeTypeRow > 1000000000) {
                System.out.println("잘못된 범위입니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("잘못 입력하셨습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }
        System.out.println();

        System.out.println("격자의 열(가로) 길이를 입력하세요 (1부터 1000000000까지 자연수 중 하나 입력) >>> ");
        String column = scan.nextLine();
        int changeTypeColumn = 0;

        try {
            changeTypeColumn = Integer.parseInt(column);
            if(changeTypeColumn < 1 || changeTypeColumn > 1000000000) {
                System.out.println("잘못된 범위입니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }

        } catch(Exception e) {
            System.out.println("잘못 입력하셨습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }
        System.out.println(changeTypeColumn + " × " + changeTypeRow + " 격자를 활용합니다.");
        System.out.println("[격자의 x 좌표는 0부터 " + (changeTypeRow - 1) + "까지입니다.]");
        System.out.println("[격자의 y 좌표는 0부터 " + (changeTypeColumn - 1) + "까지입니다.]");
        System.out.println();

        System.out.println("목표 위치의 x 좌표를 입력하세요 >>> ");
        String coordX = scan.nextLine();
        int changeTypeCoordX = 0;

        try {
            changeTypeCoordX = Integer.parseInt(coordX);
            if(changeTypeCoordX < 0 || changeTypeCoordX > changeTypeColumn - 1) {
                System.out.println("잘못된 범위입니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("잘못 입력하셨습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }
        System.out.println();

        System.out.println("목표 위치의 y 좌표를 입력하세요 >>> ");
        String coordY = scan.nextLine();
        int changeTypeCoordY = 0;

        try {
            changeTypeCoordY = Integer.parseInt(coordY);
            if(changeTypeCoordY < 0 || changeTypeCoordY > changeTypeRow - 1) {
                System.out.println("잘못된 범위입니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("잘못 입력하셨습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }
        System.out.println("목표 지점의 좌표 : (" + changeTypeCoordX + ", " + changeTypeCoordY + ")");
        System.out.println();

        ArrayList<int[]> queries = new ArrayList<>();
        while(true) {
            System.out.println("생성할 화살표 번호를 입력하세요 (0번 ~ 3번, x 중에 하나 입력) >>> ");
            System.out.println("[0번 : ←, 1번 : →, 2번 : ↑, 3번 : ↓]");
            System.out.println("[종료는 'x'를 입력하세요]");
            String answer = scan.next();

            if(answer.equalsIgnoreCase("x")) {
                System.out.println("입력을 종료합니다.");
                System.out.println();
                break;
            }

            int changeTypeAnswer;
            try {
                changeTypeAnswer = Integer.parseInt(answer);
                if(changeTypeAnswer < 0 || changeTypeAnswer > 3) {
                    System.out.println("범위가 잘못되었습니다. 다시 입력해주세요.");
                    System.out.println();
                    continue;
                }
            } catch(Exception e) {
                System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
                System.out.println();
                continue;
            }

            System.out.println("해당 화살표를 몇 개 생성하시겠습니까? (1부터 1000000000까지 자연수 중 하나 입력) >>> ");
            String arrowCount = scan.next();
            int changeTypeArrowCount;
            try {
                changeTypeArrowCount = Integer.parseInt(arrowCount);
                if(changeTypeArrowCount < 1 || changeTypeArrowCount > 1000000000) {
                    System.out.println("범위가 잘못되었습니다. 화살표 번호부터 다시 입력해주세요.");
                    System.out.println();
                    continue;
                }
            } catch(Exception e) {
                System.out.println("잘못 입력하셨습니다. 화살표 번호부터 다시 입력해주세요.");
                System.out.println();
                continue;
            }

            switch (changeTypeAnswer) {
                case 0 -> System.out.println("← 모양의 화살표 " + changeTypeArrowCount + "개를 생성합니다.");
                case 1 -> System.out.println("→ 모양의 화살표 " + changeTypeArrowCount + "개를 생성합니다.");
                case 2 -> System.out.println("↑ 모양의 화살표 " + changeTypeArrowCount + "개를 생성합니다.");
                default -> System.out.println("↓ 모양의 화살표 " + changeTypeArrowCount + "개를 생성합니다.");
            }
            System.out.println();

            int[] query = new int[2];
            query[0] = changeTypeAnswer;
            query[1] = changeTypeArrowCount;
            queries.add(query);
        }

        System.out.print("나열한 쿼리 : ");
        for(int[] query : queries) {
            System.out.print(Arrays.toString(query) + " ");
        }
        System.out.println();

        System.out.print("x행 y열에 도착하는 시작점의 개수 : ");
        System.out.println(moveAbleCount(changeTypeColumn, changeTypeRow, changeTypeCoordY, changeTypeCoordX, queries));
    }

    public static int moveAbleCount(int m, int n, int a, int b, ArrayList<int[]> arrays) {
        int ableCount = 0;
        String arrows = expressArrows(arrays);
        int[] endPoint = new int[2];
        endPoint[0] = a;
        endPoint[1] = b;
        for(int p = 0; p < m; p++) {
            for(int q = 0; q < n; q++) {
                int startPointX = p;
                int startPointY = q;
                for(int r = 0; r < arrows.length(); r++) {
                    if(arrows.charAt(r) == '←') {
                        if(startPointX > 0) startPointX--;
                    } else if(arrows.charAt(r) == '→') {
                        if(startPointX < m - 1) startPointX++;
                    } else if(arrows.charAt(r) == '↑') {
                        if(startPointY > 0) startPointY--;
                    } else {
                        if(startPointY < n - 1) startPointY++;
                    }
                }

                if(startPointX == endPoint[0] && startPointY == endPoint[1]) ableCount++;
            }
        }

        return ableCount;
    }

    public static String expressArrows(ArrayList<int[]> arrays) {
        String result = "";
        for(int[] array : arrays) {
            String arrow = switch(array[0]) {
                case 0 -> "←";
                case 1 -> "→";
                case 2 -> "↑";
                default -> "↓";
            };

            for(int a = 1; a <= array[1]; a++) {
                result = result.concat(arrow);
            }
        }
        return result;
    }
}
