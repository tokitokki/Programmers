package src.level2;
import java.util.Scanner;

public class JadenCase { //JadenCase 문자열 만들기

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("문자열을 입력하세요 >>> ");
        try {
            String s = scan.nextLine();
            System.out.println(jadenCase(s));
        } catch(IndexOutOfBoundsException e) {
            System.out.println("잘못 입력하였습니다. 다시 입력하세요");
            System.exit(0);
        }
    }

    public static String jadenCase(String word) {
        String result;
        if(word.length() < 1 || word.length() > 200) {
            return "문자열의 길이가 1이상 200이하가 되도록 입력해 주세요";
        } else if(word.contains("  ")) {
            return "공백은 한 칸 씩만 띄워서 사용할 수 있습니다.";
        } else {

            if(!((word.charAt(0) >= '0' && word.charAt(0) <= '9') || (word.charAt(0) >= 'A' && word.charAt(0) <= 'Z') ||
                    (word.charAt(0) >= 'a' && word.charAt(0) <= 'z'))) {
                return "첫 글자는 숫자 또는 알파벳으로 입력해 주세요";
            }

            for(int check = 1; check < word.length(); check++) {
                char checked = word.charAt(check);
                if(!((checked >= 'A' && checked <= 'Z') || (checked >= 'a' && checked <= 'z') || checked == ' ')) {
                    return "첫 글자 이후의 글자는 알파벳으로 입력해 주세요";
                }
            }
            
            String[] wordSplit = word.split(" ");
            for(int index = 0; index < wordSplit.length; index++) {
                String upperSplitWord = wordSplit[index].substring(0, 1).toUpperCase();
                String lowerSplitWord = wordSplit[index].substring(1).toLowerCase();
                wordSplit[index] = upperSplitWord + lowerSplitWord;
            }

            result = String.join(" ", wordSplit);
        }

        return result;
    }
}
