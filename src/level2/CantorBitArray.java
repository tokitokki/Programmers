package src.level2;

import java.util.Scanner;

public class CantorBitArray { //유사 칸토어 비트열
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("몇 번째 유사 칸토어 비트열에서 1을 검색할까요? (1 이상 20 이하의 자연수 중 하나 입력) >>> ");
        String order = scan.next();
        int cantorBitOrder = 0;
        try {
            cantorBitOrder = Integer.parseInt(order);
            if(cantorBitOrder < 1 || cantorBitOrder > 20) {
                System.out.println("해당되지 않는 범위입니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("잘못 입력하셨습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }

        System.out.println(cantorBitOrder + "번째 유사 칸토어 비트열에서 1을 검색합니다.");
        System.out.println();

        System.out.println("몇 번째 순서부터 1을 검색할까요? (1 이상 5의 " + cantorBitOrder + "제곱 까지의 자연수 중 하나 입력) >>> ");
        String firstIndex = scan.next();
        int changeTypeFirstIndex = 0;

        try {
            changeTypeFirstIndex = Integer.parseInt(firstIndex);
            if(changeTypeFirstIndex < 1 || changeTypeFirstIndex > Math.pow(5, cantorBitOrder)) {
                System.out.println("해당되지 않는 범위입니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("잘못 입력하셨습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }

        System.out.println();
        System.out.println("몇 번째 순서까지 1을 검색할까요? (시작 순서보다 크고 5의 "+ cantorBitOrder + "제곱 까지의 자연수 중 하나 입력)");
        String lastIndex = scan.next();
        int changeTypeLastIndex = 0;

        try {
            changeTypeLastIndex = Integer.parseInt(lastIndex);
            if(changeTypeLastIndex < changeTypeFirstIndex) {
                System.out.println("시작 순서가 종료 순서보다 더 크거나 같습니다. 처음부터 다시 입력하세요.");
                System.exit(0);
            }

            if(changeTypeLastIndex > Math.pow(5, cantorBitOrder)) {
                System.out.println("해당되지 않는 범위입니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("잘못 입력하셨습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }

        System.out.println();
        System.out.println(changeTypeFirstIndex + "번째 순서부터 " + changeTypeLastIndex + "번째 순서까지 1의 개수를 검색합니다.");

        int searchCount = 0;
        for(int x = changeTypeFirstIndex - 1; x < changeTypeLastIndex; x++) {
            if(cantorBit(cantorBitOrder).charAt(x) == '1') searchCount++;
        }

        System.out.println("검색된 1의 개수 : " + searchCount);
    }

    public static String cantorBit(int n) {
        String beat = "1";
        for(int x = 1; x <= n; x++) {
            String nextBeat = "";
            for(int i = 0; i < beat.length(); i++) {
                if(beat.charAt(i) == '0') nextBeat = nextBeat.concat("00000");
                if(beat.charAt(i) == '1') nextBeat = nextBeat.concat("11011");
            }

            beat = nextBeat;
        }

        return beat;
    }
}
