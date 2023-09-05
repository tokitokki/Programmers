package src.level2;

import java.util.Arrays;
import java.util.Scanner;

public class DiscountEvent { //할인 행사
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("사고 싶은 할인하는 상품의 서로 다른 종류 수는 몇 개입니까? (1 이상 10 이하의 자연수로 입력 ) >>> ");
        String goodCount = scan.nextLine();
        int parseGoodCount = 0;
        try {
            parseGoodCount = Integer.parseInt(goodCount);
            if(parseGoodCount < 1 || parseGoodCount > 10) {
                System.out.println("범위가 잘못되었습니다. 처음부터 다시 입력하세요.");
                System.exit(0);
            }

        } catch(Exception e) {
            System.out.println("잘못 입력하셨습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }

        System.out.println();

        String[] goodArray = new String[parseGoodCount];
        for(int x = 0; x < goodArray.length; x++) {
            System.out.println("구매하고 싶은 " + (x + 1) + "번째 상품의 종류는 무엇입니까? (띄어쓰기 없이 영어 소문자로만 입력) >>> ");
            String word = scan.nextLine();
            boolean correct = true;

            for(int y = 0; y < word.length(); y++) {
                if(!(word.charAt(y) >= 97 && word.charAt(y) <= 122)) {
                    correct = false;
                    break;
                }
            }

            if(!correct) {
                System.out.println("띄어쓰기 없이 소문자로 된 알파벳만 입력하세요.");
                System.out.println();
                x--;
                continue;
            }

            boolean duplicate = false;
            if(x > 0) {
                for(int i = 0; i <= x - 1; i++) {
                    if(goodArray[i].equals(word)) {
                        duplicate = true;
                        break;
                    }
                }
            }

            if(duplicate) {
                System.out.println("상품을 중복해서 입력할 수 없습니다.");
                System.out.println();
                x--;
                continue;
            }

            System.out.println((x + 1) + "번째 상품 종류 : " + word);
            System.out.println();
            goodArray[x] = word;
        }

        System.out.println("구매할 상품 종류 : " + Arrays.toString(goodArray));
        System.out.println();

        int[] goodCounts = new int[parseGoodCount];
        int countLimit = 10;

        System.out.println("※ 상품의 개수가 10개가 되도록 입력해주세요. ※");
        for(int x = 0; x < goodArray.length; x++) {
            int maxValue = countLimit - (goodArray.length - x - 1);
            if(maxValue == 1) System.out.println(goodArray[x] + "의 개수를 입력하세요 (지정할 수 있는 상품의 개수가 1개니까 1을 입력하세요.) >>> ");
            else System.out.println(goodArray[x] + "의 개수를 입력하세요 (1부터 " + maxValue + "까지의 자연수 중 하나 입력) >>> ");
            
            String count = scan.nextLine();
            int parseCount;
            try {
                parseCount = Integer.parseInt(count);
                if(parseCount < 1 || parseCount > maxValue) {
                    System.out.println("범위를 잘못 입력하였습니다. 다시 입력하세요.");
                    System.out.println();
                    x--;
                    continue;
                }

            } catch(Exception e) {
                System.out.println("잘못 입력하였습니다. 다시 입력하세요.");
                System.out.println();
                x--;
                continue;
            }

            System.out.println(goodArray[x] + "의 개수 : " + parseCount + "개");
            goodCounts[x] = parseCount;
            countLimit -= parseCount;
            System.out.println("남은 상품의 개수 : " + countLimit + "개");
            System.out.println();
        }

        if(countLimit > 0) {
            System.out.println("상품을 " + (10 - countLimit) + "개만 입력해서 잘못 입력하였습니다. 처음부터 다시 입력하세요.");
            System.exit(0);
        }

        System.out.println("구매할 제품의 각 개수 : " + Arrays.toString(goodCounts));

        System.out.println("할인 행사를 며칠동안 진행할까요? (10 이상 100000 이하의 자연수 중 하나 입력) >>> ");
        String discountDays = scan.nextLine();
        int parseDiscountDays = 0;
        try {
            parseDiscountDays = Integer.parseInt(discountDays);
            if(parseDiscountDays < 10 || parseDiscountDays > 100000) {
                System.out.println("범위가 잘못되었습니다. 처음부터 다시 입력하세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("잘못 입력하셨습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }
        
        String[] discountGoods = new String[parseDiscountDays];
        for(int x = 0; x < discountGoods.length; x++) {
            System.out.println((x + 1) + "일차에는 어떤 종류의 상품을 할인하나요? (띄어쓰기 없이 영어 소문자로만 입력) >>> ");
            String discountGood = scan.nextLine();

            boolean correct = true;
            for(int y = 0; y < discountGood.length(); y++) {
                if(!(discountGood.charAt(y) >= 97 && discountGood.charAt(y) <= 122)) {
                    correct = false;
                    break;
                }
            }

            if(!correct) {
                System.out.println("띄어쓰기 없이 소문자로 된 알파벳만 입력하세요.");
                System.out.println();
                x--;
                continue;
            }

            System.out.println((x + 1) + "일차에 할인하는 상품의 종류 : " + discountGood);
            System.out.println();
            discountGoods[x] = discountGood;
        }

        System.out.println("일차별 할인하는 상품 종류 : " + Arrays.toString(discountGoods));
        System.out.println("회원가입이 가능한 경우의 수 : " + result(goodArray, goodCounts, discountGoods));
    }

    public static int result(String[] good, int[] count, String[] discountList) {
        int cases = 0;
        int[] correctArray;
        for(int x = 0; x < discountList.length - 9; x++) {
            correctArray = new int[good.length];

            for(int y = 0; y < 10; y++) {
                for (int z = 0; z < good.length; z++) {
                    if (discountList[x + y].equals(good[z])) correctArray[z]++;
                }
            }

            int correct = 0;
            for(int y = 0; y < correctArray.length; y++) {
                if(correctArray[y] == count[y]) correct++;
            }

            if(correct == good.length) cases++;
        }

        return cases;
    }
}