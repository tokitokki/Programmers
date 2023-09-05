package src.level3;

import java.util.*;

public class Move110 { // 110 옮기기
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<String> sentences = new ArrayList<>();
        int lengthAdd = 0;
        while(true) {
            System.out.println("0과 1로 이루어진 문자열을 1000000자 이내로 입력하세요. (종료는 x 버튼을 눌러주세요) >>> ");
            System.out.println("[모든 원소의 길이의 합이 1000000자 이내가 되도록 입력하세요.]");
            String text = scan.nextLine();

            if(text.equalsIgnoreCase("x")) {
                System.out.println("입력을 종료합니다.");
                System.out.println();
                break;
            }

            if(text.length() == 0) {
                System.out.println("빈 칸은 입력할 수 없습니다.");
                continue;
            }

            boolean pass = true;
            for(int x = 0; x < text.length(); x++) {
                if(!(text.charAt(x) == 48 || text.charAt(x) == 49)) {
                    pass = false;
                    break;
                }
            }

            if(!pass) {
                System.out.println("0과 1로 이루어진 문자를 입력해주세요.");
                System.out.println();
                continue;
            }

            if(text.length() > 1000000) {
                System.out.println("글자를 10000000자 이하로 입력하세요.");
                System.out.println();
                continue;
            }

            System.out.println("입력한 문자열 : " + text);
            System.out.println();
            sentences.add(text);
            lengthAdd += text.length();
        }

        if(sentences.isEmpty()) {
            System.out.println("문자열이 하나도 없습니다. 문자열을 입력해주세요.");
            System.exit(0);
        }

        if(lengthAdd > 1000000) {
            System.out.println("모든 원소의 길이의 합이 1000000자 이하로 되도록 입력하세요.");
            System.exit(0);
        }

        System.out.println("입력한 문자열을 순서대로 나타낸 배열 : " + sentences);
        System.out.println("정렬한 문자열을 순서대로 나타낸 배열 : " + Arrays.toString(orderSentences(sentences)));
    }

    public static String[] orderSentences(ArrayList<String> words) {
        String[] changeTypeWords = new String[words.size()];
        int answerIndex = -1;

        for(String word : words) {
            answerIndex++;
            changeTypeWords[answerIndex] = word;
        }

        String[] answers = new String[words.size()];
        for(int x = 0; x < changeTypeWords.length; x++) {
            String changeWord = "";
            String word = changeTypeWords[x];
            while(true) {
                if(word.contains("110")) {
                        changeWord = changeWord.concat("A");    // A = '110' 으로 가정
                    int index = word.indexOf("110");
                    if(index + 2 == word.length()) {
                        word = word.substring(0, index);
                    } else {
                        word = word.substring(0, index) + word.substring(index + 3);
                    }
                } else {
                    for(int i = 0; i < word.length(); i++) {
                        changeWord = changeWord.concat(word.substring(i, i + 1));
                    }
                    break;
                }
            }

            ArrayList<String> orderWords = new ArrayList<>();
            for(int i = 0; i < changeWord.length(); i++) {
                if(orderWords.isEmpty()) {
                    orderWords.add(changeWord.substring(i, i + 1));
                } else {
                    ArrayList<String> nextOrderWords = new ArrayList<>();
                    for(String orderWord : orderWords) {
                        String plusWord;
                        for(int j = 0; j < orderWord.length() + 1; j++) {
                            if(j == 0) {
                                plusWord = changeWord.charAt(i) + orderWord;
                            } else if(j == orderWord.length()) {
                                plusWord = orderWord + changeWord.charAt(i);
                            } else {
                                plusWord = orderWord.substring(0, j) + changeWord.charAt(i) + orderWord.substring(j);
                            }

                            if(nextOrderWords.isEmpty()) nextOrderWords.add(plusWord);
                            else if(!nextOrderWords.contains(plusWord)) nextOrderWords.add(plusWord);
                        }
                    }

                    orderWords = nextOrderWords;
                }
            }

            HashMap<Integer, String> originalWorlds = new HashMap<>();
            for(String orderWord : orderWords) {
                String newWord = "";
                for(int i = 0; i < orderWord.length(); i++) {
                    if(orderWord.charAt(i) == '0') {
                        newWord = newWord.concat("0");
                    } else if(orderWord.charAt(i) == '1') {
                        newWord = newWord.concat("1");
                    } else {
                        newWord = newWord.concat("110");
                    }
                }

                if(newWord.charAt(0) == changeTypeWords[x].charAt(0)) originalWorlds.put(Integer.parseInt(newWord), newWord);
            }

            int minValue = Collections.min(originalWorlds.keySet());
            answers[x] = originalWorlds.get(minValue);
        }

        return answers;
    }
}