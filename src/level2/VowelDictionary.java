package src.level2;
import java.util.Scanner;

public class VowelDictionary { //모음 사전
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("대문자 모음으로 이루어진 문자를 입력하세요 (최대 5글자까지) >>> ");
        String word = scan.next();
        if(word.length() > 5) {
            System.out.println("문자의 길이는 5 이하입니다. 처음부터 다시 시도하세요.");
            System.exit(0);
        }

        for(int x = 0; x < word.length(); x++) {
            if(!(word.charAt(x) == 'A' || word.charAt(x) == 'E' || word.charAt(x) == 'I' ||
                word.charAt(x) == 'O' || word.charAt(x) == 'U')) {
                System.out.println("대문자 모음이 아닌 문자열을 포함해서 입력하였습니다. 처음부터 다시 시도하세요.");
                System.exit(0);
            }
        }

        int index = 0;

        switch (word.length()) {
            case 1 -> index = 1 + 781 * alphabetValue(word.charAt(0));
            case 2 -> {
                index = 2;
                for(int x = 0; x < 2; x++) {
                    if(x == 0) index += 781 * alphabetValue(word.charAt(0));
                    else index += 156 * alphabetValue(word.charAt(1));
                }
            }
            case 3 -> {
                index = 3;
                for(int x = 0; x < 3; x++) {
                    if(x == 0) index += 781 * alphabetValue(word.charAt(0));
                    else if(x == 1) index += 156 * alphabetValue(word.charAt(1));
                    else index += 31 * alphabetValue(word.charAt(2));
                }
            }
            case 4 -> {
                index = 4;
                for(int x = 0; x < 4; x++) {
                    if(x == 0) index += 781 * alphabetValue(word.charAt(0));
                    else if(x == 1) index += 156 * alphabetValue(word.charAt(1));
                    else if(x == 2) index += 31 * alphabetValue(word.charAt(2));
                    else index += 6 * alphabetValue(word.charAt(3));
                }
            }
            case 5 -> {
                index = 5;
                for(int x = 0; x < 5; x++) {
                    if(x == 0) index += 781 * alphabetValue(word.charAt(0));
                    else if(x == 1) index += 156 * alphabetValue(word.charAt(1));
                    else if(x == 2) index += 31 * alphabetValue(word.charAt(2));
                    else if(x == 3) index += 6 * alphabetValue(word.charAt(3));
                    else index += alphabetValue(word.charAt(4));
                }
            }
        }

        System.out.println("해당 문자는 " + index + "번째 순서에 있습니다.");
    }

    private static int alphabetValue(Character alphabet) {
        if(alphabet == 'A') return 0;
        else if(alphabet == 'E') return 1;
        else if(alphabet == 'I') return 2;
        else if(alphabet == 'O') return 3;
        else return 4;
    }
}
