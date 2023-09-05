package src.level2;

import java.util.Arrays;
import java.util.Scanner;

public class EnglishWordChainGame { //영어 끝말잇기
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int peopleCount = 0;
        int wordsCount = 0;

        System.out.println("끝말잇기에 참가하는 사람 수는 몇 명입니까? (2 이상 50 이하의 자연수 중 하나로 입력) >>> ");
        try {
            peopleCount = scan.nextInt();
            if(peopleCount < 2 || peopleCount > 10) {
                System.out.println("해당되지 않는 범위입니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("잘못 입력하셨습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }

        System.out.println("끝말잇기를 몇 번 진행할까요? (지정한 사람 수 이상 100 이하의 자연수 중 하나로 입력) >>> ");
        try {
            wordsCount = scan.nextInt();
            if(wordsCount < peopleCount || wordsCount > 100) {
                System.out.println("해당되지 않는 범위입니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("잘못 입력하셨습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }

        String[] words = new String[wordsCount];
        for(int i = 0; i < words.length; i++) {
            System.out.println((i + 1) + "번째에 입력할 소문자로 된 영단어를 공백 없이 입력하세요 (2자 이상 50자 이하로 입력) >>> ");
            String word = scan.nextLine();
            int count = 0;
            for(int j = 0; j < word.length(); j++) {
                if(!(word.charAt(j) >= 97 && word.charAt(j) <= 122)) count++;
            }

            if(count > 0) {
                System.out.println("단어를 잘못 입력하였습니다. 다시 입력하세요");
                i--;
                continue;
            }

            if(word.length() < 2 || word.length() > 50) {
                System.out.println("2자 이상 50자 이하로 단어를 입력해주세요");
                i--;
                continue;
            }

            System.out.println((i + 1) + "번째로 입력한 단어 : " + word);
            words[i] = word;
        }

        System.out.println("결과값 : " + Arrays.toString(results(words, peopleCount)));
    }

    public static int[] results(String[] words, int peopleCount) {
        int[] turnCountAndPeopleIndex = new int[2];
        int turns = 0;
        for(int i = 0; i < words.length; i++) {
            if(i > 0) {
                int duplicate = 0;
                for(int j = 0; j <= i - 1; j++) {
                    if(words[i].equals(words[j])) duplicate++;
                }

                if(duplicate > 0) {
                    turns = i + 1;
                    break;
                }

                if(words[i - 1].charAt(words[i - 1].length() - 1) != words[i].charAt(0)) {
                    turns = i + 1;
                    break;
                }
            }
        }

        if(turns != 0) {
            if(turns % peopleCount == 0) {
                turnCountAndPeopleIndex[0] = peopleCount;
                turnCountAndPeopleIndex[1] = turns / peopleCount;
            } else {
                turnCountAndPeopleIndex[0] = turns % peopleCount;
                turnCountAndPeopleIndex[1] = turns / peopleCount + 1;
            }
        }

        return turnCountAndPeopleIndex;
    }
}