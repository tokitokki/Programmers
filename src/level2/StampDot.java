package src.level2;

import java.util.Scanner;

public class StampDot { // 점 찍기
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("※ x 좌표와 y 좌표의 범위는 각각 0 이상입니다.");
        System.out.println("좌표평면 위의 점을 (0, 0) 부터 몇씩 증가시키면서 찍을까요? (1부터 1000000까지의 자연수 중 하나 입력) >> ");
        String gap = scan.nextLine();
        int distance = 0;

        try {
            distance = Integer.parseInt(gap);
            if(distance < 1 || distance > 1000000) {
                System.out.println("범위가 잘못되었습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }

        System.out.println(distance + "만큼의 간격으로 (0, 0)을 기준으로 좌표를 찍습니다.");
        System.out.println();

        System.out.println("(0, 0)부터 몇까지의 거리만큼 좌표를 찍을까요? (1부터 1000000까지의 자연수 중 하나 입력) >> ");
        String length = scan.nextLine();
        int changeTypeLength = 0;

        try {
            changeTypeLength = Integer.parseInt(length);
            if(changeTypeLength < 1 || changeTypeLength > 1000000) {
                System.out.println("범위가 잘못되었습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }

        System.out.println("(0, 0) 부터 거리가 " + changeTypeLength + "인 점들의 개수를 구합니다.");
        System.out.println();

        System.out.println("구하려는 점의 개수 : " + points(distance, changeTypeLength));
    }

    public static int points(int interval, int length) {
        int pointCount = 0;
        for(int x = 0; x <= length; x += interval) {
            for(int y = 0; y <= length; y += interval) {
                if(Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)) <= length) pointCount++;
            }
        }

        return pointCount;
    }
}