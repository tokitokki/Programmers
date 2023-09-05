package src.level3;

import java.util.Scanner;

public class TheLargestPalindrome { // 가장 긴 팰린드롬
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("알파벳 소문자로만 구성된 문자열을 2500자 이내로 입력하세요 >>> ");
        String word = scan.nextLine();

        if(word.length() == 0) {
            System.out.println("알파벳 소문자로 구성된 문자열을 입력해 주세요.");
            System.exit(0);
        }

        if(word.length() > 2500) {
            System.out.println("문자열의 길이가 범위보다 더 큽니다.");
            System.exit(0);
        }

        boolean pass = true;
        for(int x = 0; x < word.length(); x++) {
            if(!(word.charAt(x) >= 97 && word.charAt(x) <= 122)) {
                pass = false;
                break;
            }
        }

        if(!pass) {
            System.out.println("알파벳 소문자 이외에 다른 문자가 있습니다.");
            System.exit(0);
        }

        System.out.println("입력한 문자열 : " + word);
        System.out.println("가장 큰 팰린드롬의 길이 : " + maxLength(word));
    }

    public static int maxLength(String word) {
        int length = 0;
        for(int a = 0; a <= word.length() - 1; a++) {
            for(int b = a; b <= word.length() - 1; b++) {
                String subWord = word.substring(a, b + 1);
                if(subWord.length() == 1) length = Math.max(length, subWord.length());
                else {
                    boolean palindrome = true;
                    for(int c = 0; c < subWord.length() / 2; c++) {
                        if(subWord.charAt(c) != subWord.charAt(subWord.length() - c - 1)) {
                            palindrome = false;
                            break;
                        }
                    }

                    if(palindrome) length = Math.max(length, subWord.length());
                }
            }
        }

        return length;
    }
}