package src.level2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class IslandTravel { //무인도 여행
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int mapWidthLength = 0;
        int mapHeightLength = 0;

        System.out.println("지도의 가로 길이를 입력하세요 (3에서 100까지 자연수 중 하나 입력) >>> ");
        try {
            mapWidthLength = scan.nextInt();
            if(mapWidthLength < 3 || mapWidthLength > 100) {
                System.out.println("해당되지 않는 범위입니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("잘못 입력하셨습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }

        System.out.println("지도의 세로 길이를 입력하세요 (3에서 100까지 자연수 중 하나 입력) >>> ");
        try {
            mapHeightLength = scan.nextInt();
            if(mapHeightLength < 3 || mapHeightLength > 100) {
                System.out.println("해당되지 않는 범위입니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("잘못 입력하셨습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }

        String[] maps = new String[mapHeightLength];
        for(int i = 0; i < mapHeightLength; i++) {
            String mapText = "";
            for(int j = 0; j < mapWidthLength; j++) {
                String words;
                System.out.println("지도에 " + (i + 1) + "행 " + (j + 1) + "열에 표시할 X 문자 또는 1부터 9까지의 자연수 중 하나를 입력해주세요 >>> ");
                try {
                    words = scan.next();
                    if(words.length() != 1) {
                        System.out.println("문자를 한 글자만 입력하세요.");
                        j--;
                        continue;
                    }

                    if(!(words.charAt(0) == 88 || (words.charAt(0) >= 48 && words.charAt(0) <= 57))) {
                        System.out.println("문자를 잘못 입력했습니다.");
                        j--;
                        continue;
                    }

                } catch(Exception e) {
                    System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
                    j--;
                    continue;
                }

                System.out.println("지도에 " + (i + 1) + "행 " + (j + 1) + "열에 입력한 값 : " + words);
                mapText = mapText.concat(words);
            }

            maps[i] = mapText;
        }

        System.out.println("출력 결과 : " + islandTravelDays(connects(maps), maps));
    }

    public static int[][] connects(String[] maps) {
        int[][] connectNumbers = new int[maps.length][maps[0].length()];
        int number = 1;
        for(int i = 0; i < maps.length; i++) {
            for(int j = 0; j < maps[i].length(); j++) {
                int count = 0;
                if (maps[i].charAt(j) != 'X' && connectNumbers[i][j] == 0) {
                    connectNumbers[i][j] = number;
                    if (i < maps.length - 1 && maps[i + 1].charAt(j) != 'X') {
                        connectNumbers[i + 1][j] = number;
                        count++;
                    }
                    if (j < maps[i].length() - 1 && maps[i].charAt(j + 1) != 'X') {
                        connectNumbers[i][j + 1] = number;
                        count++;
                    }
                    if (count == 0) number++;
                } else if (maps[i].charAt(j) != 'X' && connectNumbers[i][j] != 0) {
                    if (i < maps.length - 1 && maps[i + 1].charAt(j) != 'X') {
                        connectNumbers[i + 1][j] = connectNumbers[i][j];
                        count++;
                    }
                    if (j < maps[i].length() - 1 && maps[i].charAt(j + 1) != 'X') {
                        connectNumbers[i][j + 1] = connectNumbers[i][j];
                        count++;
                    }
                    if (count == 0) number++;
                } else connectNumbers[i][j] = 0;
            }
        }

        return connectNumbers;
    }

    public static ArrayList<Integer> islandTravelDays(int[][] connectNumbers, String[] maps) {
        ArrayList<Integer> islands = new ArrayList<>();
        boolean flag = true;
        int number = 1;
        while(flag) {
            int hap = 0;
            int zeroCount = 0;
            for(int x = 0; x < maps.length; x++) {
                for(int y = 0; y < maps[x].length(); y++) {
                    if(connectNumbers[x][y] == number) {
                        hap += Integer.parseInt(String.valueOf(maps[x].charAt(y)));
                        connectNumbers[x][y] = 0;
                    } else zeroCount++;
                }
            }

            if(hap > 0) islands.add(hap);
            if(zeroCount == maps.length * maps[0].length()) flag = false;
            else number++;
        }

        if(islands.isEmpty()) islands.add(-1);
        Collections.sort(islands);

        return islands;
    }
}