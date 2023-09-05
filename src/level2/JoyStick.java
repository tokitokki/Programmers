package src.level2;
import java.util.Scanner;

public class JoyStick { //조이스틱
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("등록하려는 대문자 알파벳으로 이루어진 영어 이름을 입력하세요 (최대 20글자) >>> ");
        String answer = scan.next();
        if(answer.length() > 20) {
            System.out.println("잘못 입력하였습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }

        for(int x = 0; x < answer.length(); x++) {
            if(!(answer.charAt(x) >= 'A' && answer.charAt(x) <= 'Z')) {
                System.out.println("대문자가 아닌 문자를 포함해서 입력하였거나 숫자를 포함해서 입력하였습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        }

        System.out.println("입력한 문자 : " + answer);
        System.out.println("조이스틱 조작 횟수의 최솟값 : " + useJoyStickCount(answer));
    }

    public static int useJoyStickCount(String string) {
        int count = 0;
        for(int x = 0; x < string.length(); x++) {
            if(string.charAt(x) >= 'A' && string.charAt(x) <= 'N') {
                count += string.charAt(x) - 65;
            } else {
                count += 91 - string.charAt(x);
            }
        }

        StringBuilder a = new StringBuilder();
        int containA = 0;
        boolean flag = true;
        while(flag) {
            a.append("A");
            if(string.contains(a)) containA++;
            else flag = false;
        }

        count += string.length() - 1 - containA;

        return count;
    }
}
