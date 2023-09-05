package src.level2;
import java.util.Scanner;
import java.util.Stack;

public class ParenthesisCharacter { //괄호 회전하기

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("소괄호, 중괄호, 대괄호 3개만 사용해서 문자를 작성하세요 >>> ");
        String sentence = scan.next();

        if(sentence.length() > 1000) {
            System.out.println("문자열의 길이는 1000을 넘을 수 없습니다.");
            System.exit(0);
        }

        for(int x = 0; x < sentence.length(); x++) {
            if(!(sentence.charAt(x) == '[' || sentence.charAt(x) == ']' || sentence.charAt(x) == '(' ||
                    sentence.charAt(x) == ')' || sentence.charAt(x) == '{' || sentence.charAt(x) == '}')) {
                System.out.println("소괄호, 중괄호, 대괄호 이외의 문자열을 포함해서 입력하였습니다. 처음부터 다시 시도하세요.");
                System.exit(0);
            }
        }

        System.out.println("입력한 문자 : " + sentence);
        int correct = 0;

        for(int s = 1; s < sentence.length(); s++) {
            String changeSentence = sentence.substring(sentence.length() - 1 - s) + sentence.substring(0, sentence.length() - 1 - s);
            if(isCorrect(changeSentence)) correct++;
        }

        System.out.println("반환되는 값 : " + correct);
    }

    public static boolean isCorrect(String sentence) {
        Stack<Character> chars = new Stack<>();
        char[] arr = sentence.toCharArray();

        for(char data : arr) {
            if(chars.isEmpty()) {
                chars.push(data);
            } else if(chars.peek() == '[' && data == ']') {
                chars.pop();
            } else if(chars.peek() == '(' && data == ')') {
                chars.pop();
            } else if(chars.peek() == '{' && data == '}') {
                chars.pop();
            } else {
                chars.push(data);
            }
        }

        return chars.isEmpty();
    }
}
