package src.level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Carpet { //카펫
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("카펫에서 본 갈색 격자의 수를 입력하세요 (8 이상 5000 이하인 자연수 중 하나로 입력) >>> ");
        int brownTile = 0;
        try {
            brownTile = scan.nextInt();
            if(brownTile <= 7 || brownTile > 5000) {
                System.out.println("잘못 입력하였습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("문자를 포함해서 입력하였습니다. 처음부터 다시 입력하세요.");
            System.exit(0);
        }

        System.out.println("카펫에서 본 노란색 격자의 수를 입력하세요 (1 이상 2000000 이하인 자연수 중 하나로 입력) >>> ");
        int yellowTile = 0;
        try {
            yellowTile = scan.nextInt();
            if(yellowTile <= 0 || yellowTile > 2000000) {
                System.out.println("잘못 입력하였습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("문자를 포함해서 입력하였습니다. 처음부터 다시 입력하세요.");
            System.exit(0);
        }

        ArrayList<int[]> yellowTileLengthCase = new ArrayList<>();
        for(int x = 1; x <= yellowTile; x++) {
            int[] lengths = new int[2];
            int number = yellowTile;
            if(number % x == 0) {
                lengths[0] = number / x;
                lengths[1] = x;
                if(lengths[0] >= lengths[1]) {
                    yellowTileLengthCase.add(lengths);
                }
            }
        }
        int[] carpetLengthsCase;
        int[] carpetLengths = new int[2];
        for(int x = 1; x <= brownTile + yellowTile; x++) {
            carpetLengthsCase = new int[2];
            int number = brownTile + yellowTile;
            if(number % x == 0) {
                carpetLengthsCase[0] = number / x;
                carpetLengthsCase[1] = x;
                if(carpetLengthsCase[0] >= carpetLengthsCase[1]) {
                    for(int[] lengthsCase : yellowTileLengthCase) {
                        int count = 0;
                        if(carpetLengthsCase[0] == lengthsCase[0] + 2) count++;
                        if(carpetLengthsCase[1] == lengthsCase[1] + 2) count++;
                        if(count == 2) {
                            carpetLengths[0] = carpetLengthsCase[0];
                            carpetLengths[1] = carpetLengthsCase[1];
                            break;
                        }
                    }
                }
            }
        }

        if(carpetLengths[0] != 0) {
            System.out.println("카펫의 가로 길이와 세로 길이를 나타낸 배열 : " + Arrays.toString(carpetLengths));
        } else {
            System.out.println("타일의 개수를 제대로 입력해주세요.");
        }
    }
}
