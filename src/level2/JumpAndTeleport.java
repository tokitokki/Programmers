package src.level2;
import java.util.Scanner;

public class JumpAndTeleport { //점프와 순간이동
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("아이언 슈트를 착용하고 움직이려는 거리를 입력하세요 (자연수 단위로 입력, 1000000000 이하) >>> ");
        int answer = 0;
        try {
            answer = scan.nextInt();
            if(answer <= 0 || answer > 1000000000) {
                System.out.println("잘못 입력하였습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }

        System.out.println("움직이려는 거리 : " + answer);
        System.out.println("건전지 사용량의 최솟값 : " + useLowerBatteryAmount(answer));
    }

    public static int useLowerBatteryAmount(int n) {
        int batteryUseAmount = 0;
        int value = n;
        boolean flag = true;
        while(flag) {
            if(value % 2 == 0) {
                value = value / 2;
            } else {
                value = value - 1;
                batteryUseAmount++;
            }

            if(value == 0) flag = false;
        }

        return batteryUseAmount;
    }
}
