package src.level2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class SoloPlayMaster { // 혼자 놀기의 달인
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("몇 장의 카드로 카드 게임을 시작할까요? (2 부터 100 이하의 자연수 중 하나 입력) >>> ");
        String cards = scan.next();
        int changeTypeCards = 0;
        try {
            changeTypeCards = Integer.parseInt(cards);
            if(changeTypeCards < 2 || changeTypeCards > 100) {
                System.out.println("잘못된 범위입니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }
        System.out.println();

        int[] cardsOrder = new int[changeTypeCards];
        for(int x = 0; x < cardsOrder.length; x++) {
            System.out.println((x + 1) + "번째 배열에 담겨진 카드 번호는 무엇입니까? (1부터 " + changeTypeCards + "까지의 카드 번호를 중복 없이 입력) >>> ");
            String cardNumber = scan.next();
            int changeTypeCardNumber;
            try {
                changeTypeCardNumber = Integer.parseInt(cardNumber);
                if(changeTypeCardNumber < 1 || changeTypeCardNumber > changeTypeCards) {
                    System.out.println("잘못된 범위입니다. 다시 입력해주세요.");
                    System.out.println();
                    x--;
                    continue;
                }
            } catch(Exception e) {
                System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 다시 입력해주세요.");
                System.out.println();
                x--;
                continue;
            }

            if(x != 0) {
                boolean duplicate = false;
                for(int y = 0; y <= x - 1; y++) {
                    if(cardsOrder[y] == changeTypeCardNumber) {
                        duplicate = true;
                        break;
                    }
                }

                if(duplicate) {
                    System.out.println("카드 번호가 중복되었습니다. 다시 입력해주세요.");
                    System.out.println();
                    x--;
                    continue;
                }
            }

            System.out.println((x + 1) + "번째 배열의 카드 번호 : " + changeTypeCardNumber);
            System.out.println();
            cardsOrder[x] = changeTypeCardNumber;
        }

        System.out.println("카드 번호의 순서를 나타낸 배열 : " + Arrays.toString(cardsOrder));
        System.out.println("결과의 최고 점수 : " + maxValue(cardsOrder));
    }

    public static int maxValue(int[] order) {
        int answer = 0;
        boolean flag = true;
        boolean otherCardAble;
        HashMap<Integer, Integer> values = new HashMap<>();
        int flagOrder = 0;

        while(flag) {
            flagOrder++;
            otherCardAble = true;
            int count = 0;

            for(int x = 0; x < order.length; x++) {
                int cardNumber;
                if(order[x] > 0) {
                    cardNumber = order[x];
                    count++;
                    order[x] = 0;

                    while(otherCardAble) {
                        int number;
                        if(order[cardNumber - 1] > 0) {
                            number = order[cardNumber - 1];
                            order[cardNumber - 1] = 0;
                            count++;
                            cardNumber = number;


                        } else otherCardAble = false;
                    }

                    break;
                }
            }

            values.put(flagOrder, count);

            int cardCount = 0;
            for(int cardYn : order) {
                if(cardYn == 0) cardCount++;
            }

            if(cardCount == order.length) flag = false;
        }

        int[] changeArrayList = new int[values.size()];
        for(int key : values.keySet()) {
            changeArrayList[key - 1] = values.get(key);
        }

        for(int x = 0; x < changeArrayList.length; x++) {
            for(int y = 0; y < changeArrayList.length; y++) {
                if(x != y) {
                    if(answer < changeArrayList[x] * changeArrayList[y]) answer = changeArrayList[x] * changeArrayList[y];
                }
            }
        }

        return answer;
    }
}