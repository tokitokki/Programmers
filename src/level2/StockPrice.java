package src.level2;
import java.util.Arrays;
import java.util.Scanner;

public class StockPrice { //주식 가격
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("초 단위로 기록된 주식 가격이 담긴 배열의 길이를 입력하세요 (2 이상 100000 이하) >>> ");
        int length = 0;
        try {
            length = scan.nextInt();
            if(length <= 1 || length > 100000) {
                System.out.println("잘못 입력하였습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }

        int[] prices = new int[length];
        for(int x = 0; x < prices.length; x++) {
            if(x == 0) System.out.println("처음 주식의 가격을 입력하세요 (1 이상 10000 이하) >>> ");
            else System.out.println(x + "초 후의 주식의 가격을 입력하세요 (1 이상 10000 이하) >>> ");
            int price;
            try {
                price = scan.nextInt();
                if(price <= 0 || price > 10000) {
                    System.out.println("잘못 입력하였습니다. 다시 입력해주세요.");
                    x--;
                    continue;
                }
            } catch(Exception e) {
                System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 다시 입력해주세요.");
                x--;
                continue;
            }

            prices[x] = price;
        }

        System.out.println("초 단위로 기록된 주식의 가격 : " + Arrays.toString(prices));
        System.out.println("가격이 떨어지지 않은 기간들을 나열한 배열 : " + Arrays.toString(notLowerPricePeriod(prices)));
    }

    public static int[] notLowerPricePeriod(int[] prices) {
        int[] results = new int[prices.length];
        for(int x = 0; x < results.length; x++) {
            if(x == results.length - 1) results[x] = 0;
            else {
                int days = 0;
                for(int y = x + 1; y < prices.length; y++) {
                    days++;
                    if(prices[x] > prices[y]) break;
                }

                results[x] = days;
            }
        }

        return results;
    }
}
