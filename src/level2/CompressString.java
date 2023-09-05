package src.level2;
import java.util.Scanner;

public class CompressString { //문자열 압축
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("입력하고 싶은 알파벳 소문자로만 이루어진 문자를 입력하세요 (1000자 이하) >>> ");
        String word = scan.next();
        if(word.length() > 1000) {
            System.out.println("문자열의 길이는 1000 이하입니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }
        for(int x = 0; x < word.length(); x++) {
            if(!(word.charAt(x) >= 'a' && word.charAt(x) <= 'z')) {
                System.out.println("알파벳 소문자가 아닌 문자를 입력하였거나 숫자를 포함해서 입력하였습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        }

        System.out.println("압축된 문자열의 최소 크기 : " + compressString(word));
    }

    public static int compressString(String word) {
        if(word.length() == 1) return 1;
        int answer = Integer.MAX_VALUE;
        for(int i = 1; i <= word.length() / 2; i++) {
            String str = "";
            String temp = "";
            int count = 1;
            for(int j = 0; j < word.length() / i; j++) {
                if(temp.equals(word.substring(j * i, (j * i) + i))){
                    count++;
                    continue;
                }

                if(count > 1) {
                    str = str.concat(count + temp);
                    count = 1;
                } else {
                    str = str.concat(temp);
                }

                temp = word.substring(j * i, (j * i) + i);
            }

            if(count > 1) {
                str = str.concat(count + temp);
            } else {
                str = str.concat(temp);
            }

            if(word.length() % i != 0) {
                str = str.concat(word.substring(word.length() - word.length() % i));
            }

            answer = Math.min(answer, str.length());
        }

        return answer;
    }
}
