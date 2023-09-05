package src.level2;

import java.util.ArrayList;
import java.util.Scanner;

public class ModifyMaximize { // 수식 최대화
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("수식을 입력하세요 (숫자, +, -, *, 공백으로 올바른 중위표기법대로 입력) >>> ");
        String answer = scan.nextLine();
        String changeAnswer = "";
        for(int x = 0; x < answer.length(); x++) {
            if(answer.charAt(x) != ' ') changeAnswer = changeAnswer.concat(answer.substring(x, x + 1));
        }

        if(changeAnswer.length() == 0) {
            System.out.println("수식을 하나 이상의 숫자 또는 문자로 입력해주세요.");
            System.exit(0);
        }

        boolean able = true;
        boolean numberCountYn = true;
        for(int x = 0; x < changeAnswer.length(); x++) {
            if(!(changeAnswer.charAt(x) == 42 || changeAnswer.charAt(x) == 43 || changeAnswer.charAt(x) == 45 || (changeAnswer.charAt(x) >= 48 && changeAnswer.charAt(x) <= 57))) {
                able = false;
                break;
            }

            if(changeAnswer.length() >= 4 && x <= changeAnswer.length() - 4) {
                int count = 0;
                for(int i = x; i < x + 4; i++) {
                    if(changeAnswer.charAt(i) >= 48 && changeAnswer.charAt(i) <= 57) count++;
                }

                if(count == 4) numberCountYn = false;
            }
        }

        if(!able) {
            System.out.println("수식을 잘못 입력하였습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        } else if(changeAnswer.charAt(0) == '0') {
            System.out.println("첫 번째 자리에는 0이 올 수 없습니다.");
            System.exit(0);
        } else if(changeAnswer.charAt(0) == 42 || changeAnswer.charAt(0) == 43 || changeAnswer.charAt(0) == 45) {
            System.out.println("첫 번째 자리에는 연산 기호가 올 수 없습니다.");
            System.exit(0);
        } else if(!numberCountYn) {
            System.out.println("자연수는 세 자리까지만 입력해 주세요.");
            System.exit(0);
        }

        System.out.println("입력한 수식 : " + changeAnswer);
        System.out.println("결과값 : " + maxValue(changeAnswer));
    }

    public static long maxValue(String expression) {
        long answer = Long.MIN_VALUE;
        String[][] op = { { "+", "-", "*" }, { "+", "*", "-" }, { "-", "*", "+" },
                { "-", "+", "*" }, { "*", "-", "+" }, { "*", "+", "-" } };

        ArrayList<String> list = new ArrayList<>();
        int start = 0;
        for(int i = 0; i < expression.length(); i++) {
            if(expression.charAt(i) == '-' || expression.charAt(i) == '+' || expression.charAt(i) == '*') {
                list.add(expression.substring(start, i));
                list.add(expression.charAt(i) + "");
                start = i + 1;
            }
        }
        list.add(expression.substring(start));

        for(String[] strings : op) {
            ArrayList<String> sub_list = new ArrayList<>(list);
            for(int k = 0; k < 3; k++) {
                for(int j = 0; j < sub_list.size(); j++) {
                    if(strings[k].equals(sub_list.get(j))) {
                        sub_list.set(j - 1, calc(sub_list.get(j - 1), sub_list.get(j), sub_list.get(j + 1)));
                        sub_list.remove(j);
                        sub_list.remove(j);
                        j--;
                    }
                }
            }
            answer = Math.max(answer, Math.abs(Long.parseLong(sub_list.get(0))));
        }

        return answer;
    }

    private static String calc(String a, String op, String b) {
        long x = Long.parseLong(a);
        long y = Long.parseLong(b);

        if(op.equals("+"))
            return x + y + "";
        else if(op.equals("-"))
            return x - y + "";

        return x * y + "";
    }
}