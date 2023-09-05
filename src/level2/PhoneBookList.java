package src.level2;
import java.util.Arrays;
import java.util.Scanner;

public class PhoneBookList { //전화번호 목록
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("전화번호부에 몇 개의 전화번호를 등록하시겠습니까? (최대 1000000개 까지 등록 가능) >>> ");
        int answer = 0;
        try {
            answer = scan.nextInt();
            if(answer <= 0 || answer > 1000000) {
                System.out.println("잘못 입력하였습니다. 처음부터 다시 시도하세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 시도하세요");
            System.exit(0);
        }

        String[] phoneBookList = new String[answer];
        for(int x = 0; x < phoneBookList.length; x++) {
            System.out.println("전화번호를 '-'를 생략하고 입력하세요 (최대 20자) >>> ");
            String phoneNumber = scan.next();
            if(phoneNumber.length() > 20) {
                System.out.println("잘못 입력하였습니다. 다시 입력하세요.");
                x--;
                continue;
            }

            int check = 0;
            for(int y = 0; y < phoneNumber.length(); y++) {
                if(phoneNumber.charAt(y) >= '0' && phoneNumber.charAt(y) <= '9') check++;
            }

            if(check != phoneNumber.length()) {
                System.out.println("문자를 포함해서 입력하였습니다. 다시 입력하세요.");
                x--;
                continue;
            }

            int duplicate = 0;
            if(x != 0) {
                for(int y = 0; y < x; y++) {
                    if(phoneBookList[y].equals(phoneNumber)) duplicate++;
                }

                if(duplicate > 0) {
                    System.out.println("연락처가 중복되었습니다. 다시 입력하세요.");
                    x--;
                    continue;
                }
            }

            System.out.println((x + 1) + "번째 목록에 입력한 전화번호 : " + phoneNumber);
            phoneBookList[x] = phoneNumber;
        }

        System.out.println("등록한 전화번호들을 나열한 배열 : " + Arrays.toString(phoneBookList));
        System.out.println("반환되는 논리값 : " + isNotContained(phoneBookList));
    }

    public static boolean isNotContained(String[] phoneNumbers) {
        int containCount = 0;
        for(int x = 0; x < phoneNumbers.length; x++) {
            if(x != 0) {
                for(int y = 0; y < x; y++) {
                    if(phoneNumbers[y].contains(phoneNumbers[x]) || phoneNumbers[x].contains(phoneNumbers[y])) {
                        containCount++;
                    }
                }
            }
        }

        return containCount == 0;
    }
}
