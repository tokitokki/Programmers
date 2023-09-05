package src.level2;

import java.util.Arrays;
import java.util.Scanner;

public class SoloPlayTickTackTo { //혼자서 하는 틱택토
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] board = new String[3];
        for(int x = 0; x < 3; x++) {
            String sentence = "";
            for(int y = 0; y < 3; y++) {
                System.out.println((x + 1) + "행 " + (y + 1) + "열에 표기할 문자를 입력하세요 (알파벳 대문자 O, 알파벳 대문자 X, . 3개 중 하나만 입력) >>> ");

                String word = scan.nextLine();
                if(word.length() > 1) {
                    System.out.println("문자를 제대로 입력하세요.");
                    System.out.println();
                    y--;
                    continue;
                } else if(!(word.charAt(0) == 46 || word.charAt(0) == 79 || word.charAt(0) == 88)) {
                    System.out.println("문자를 제대로 입력하세요.");
                    System.out.println();
                    y--;
                    continue;
                }

                System.out.println((x + 1) + "행 " + (y + 1) + "열의 문자 : " + word);
                System.out.println();
                sentence = sentence.concat(word);
            }

            board[x] = sentence;
        }

        System.out.println("문자열을 순서대로 입력한 배열 : " + Arrays.toString(board));
        System.out.println("진행할 수 있는 게임 상황을 나타낸 값 : " + enableTickTackTo(board));
    }

    public static int enableTickTackTo(String[] board) {
        String[][] wordSeparate = new String[3][3];
        for(int x = 0; x < board.length; x++) {
            for(int y = 0; y < board[x].length(); y++) {
                wordSeparate[x][y] = board[x].substring(y, y + 1);
            }
        }

        int firstPlayerCount = 0;
        int secondPlayerCount = 0;

        for(String[] sentence : wordSeparate) {
            for(String word : sentence) {
                if(word.equals("O")) firstPlayerCount++;
                else if(word.equals("X")) secondPlayerCount++;
            }
        }

        int totalPlayerCount = firstPlayerCount + secondPlayerCount;
        boolean flag = true;


        if(totalPlayerCount % 2 == 0 && !(firstPlayerCount == totalPlayerCount / 2 && secondPlayerCount == totalPlayerCount / 2)) flag = false;
        else if(totalPlayerCount % 2 == 1 && !(firstPlayerCount == totalPlayerCount / 2 + 1 && secondPlayerCount == totalPlayerCount / 2)) flag = false;

        for(int x = 0; x < 3; x++) {
            if(wordSeparate[x][0].equals("0") && wordSeparate[x][1].equals("0") && wordSeparate[x][2].equals("0")) flag = false;
            if(wordSeparate[x][0].equals("X") && wordSeparate[x][1].equals("X") && wordSeparate[x][2].equals("X")) flag = false;
            if(wordSeparate[0][x].equals("0") && wordSeparate[1][x].equals("0") && wordSeparate[2][x].equals("0")) flag = false;
            if(wordSeparate[0][x].equals("X") && wordSeparate[1][x].equals("X") && wordSeparate[2][x].equals("X")) flag = false;
        }

        if(wordSeparate[0][0].equals("0") && wordSeparate[1][1].equals("0") && wordSeparate[2][2].equals("0")) flag = false;
        if(wordSeparate[0][2].equals("0") && wordSeparate[1][1].equals("0") && wordSeparate[2][0].equals("0")) flag = false;
        if(wordSeparate[0][0].equals("X") && wordSeparate[1][1].equals("X") && wordSeparate[2][2].equals("X")) flag = false;
        if(wordSeparate[0][2].equals("X") && wordSeparate[1][1].equals("X") && wordSeparate[2][0].equals("X")) flag = false;

        return flag ? 1 : 0;
    }
}