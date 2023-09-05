package src.level2;
import java.util.Scanner;
import java.util.Stack;

public class CorrectParentheses { //올바른 괄호
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("괄호들로만 이루어진 문자를 입력하세요 >>> ");
        String parentheses = scan.next();
        if(parentheses.length() > 100000) {
            System.out.println("해당 문자의 길이는 100000 이하입니다. 처음부터 다시 입력하세요.");
            System.exit(0);
        }

        for(int x = 0; x < parentheses.length(); x++) {
            if(!(parentheses.charAt(x) == 40 || parentheses.charAt(x) == 41)) {
                System.out.println("괄호 이외에 다른 숫자 또는 문자를 입력하였습니다. 처음부터 다시 시도하세요.");
                System.exit(0);
            }
        }

        System.out.println("입력한 문자 : " + parentheses);
        System.out.println("옳은 문자인지 아닌지 판단 여부 : " + isCorrect(parentheses));
    }

    public static boolean isCorrect(String sentence) {
        Stack<Character> chars = new Stack<>();
        char[] array = sentence.toCharArray();
        for(char data : array) {
            if(chars.isEmpty()) {
                chars.push(data);
            } else if(chars.peek() == '(' && data == ')') {
                chars.pop();
            } else {
                chars.push(data);
            }
        }

        return chars.isEmpty();
    }
}
