package src.level2;
import java.util.Arrays;
import java.util.Scanner;

public class MoreSpicy { //더 맵게
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int spicyFoodCounts = 0;
        System.out.println("매운 음식의 개수를 입력하세요 (2 이상 1000000 이하로 입력) >>> ");
        try {
            spicyFoodCounts = scan.nextInt();
            if(spicyFoodCounts < 2 || spicyFoodCounts > 1000000) {
                System.out.println("범위에 해당되지 않는 정수를 입력하였습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("문자를 포함하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }

        int minScovilles = 0;
        System.out.println("모든 음식을 어떤 스코빌 정수 이상으로 만들고 싶으십니까? (0 이상 1000000 이하로 입력) >>> ");
        try{
            minScovilles = scan.nextInt();
            if(minScovilles < 0 || minScovilles > 1000000) {
                System.out.println("범위에 해당되지 않는 정수를 입력하였습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("문자를 포함하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }

        int[] spicyFoodScovilles = new int[spicyFoodCounts];
        for(int x = 0; x < spicyFoodScovilles.length; x++) {
            System.out.println((x + 1) + "번째 음식의 스코빌 지수를 입력하세요 >>> ");
            String scoville = scan.next();
            int changeTypeScoville;
            try {
                changeTypeScoville = Integer.parseInt(scoville);
                if(changeTypeScoville < 0 || changeTypeScoville > 1000000) {
                    System.out.println("범위에 해당되지 않는 정수를 입력하였습니다. 다시 입력해주세요.");
                    x--;
                    continue;
                }

                System.out.println((x + 1) + "번째 음식의 스코빌 지수 : " + changeTypeScoville);
                spicyFoodScovilles[x] = changeTypeScoville;
            } catch(Exception e) {
                System.out.println("문자를 포함하였거나 정수가 아닌 실수를 입력하였습니다. 다시 입력해주세요.");
                x--;
            }
        }

        Arrays.sort(spicyFoodScovilles);
        System.out.println("대입한 스코빌 지수를 오름차순으로 정리한 배열 : " + Arrays.toString(spicyFoodScovilles));
        int answer = mixCount(minScovilles, spicyFoodScovilles);
        switch (answer) {
            case 0 -> System.out.println("안 섞어도 모든 음식의 맵기가 " + minScovilles + " 스코빌 이상입니다.");
            case -1 -> System.out.println("아무리 섞어도 " + minScovilles + " 스코빌 이상을 만들 수 없습니다.");
            default -> System.out.println(answer + "번 섞으면 모든 음식의 스코빌 지수가 " + minScovilles + " 이상이 됩니다.");
        }
    }

    public static int mixCount(int minScovilles, int[] spicyFoodScovilles) {
        for(int x = 0; x < spicyFoodScovilles.length; x++) {
            if(x == 0) {
                if(spicyFoodScovilles[0] >= minScovilles) return 0;
            } else {
                int[] changeArrays = new int[spicyFoodScovilles.length - 1];
                for(int y = 0; y < spicyFoodScovilles.length - 1; y++) {
                    if(y == 0) {
                        changeArrays[0] = spicyFoodScovilles[0] + spicyFoodScovilles[1] * 2;
                    } else {
                        changeArrays[y] = spicyFoodScovilles[y + 1];
                    }
                }

                spicyFoodScovilles = changeArrays;
                Arrays.sort(spicyFoodScovilles);
                if(spicyFoodScovilles[0] >= minScovilles) return x;
            }
        }

        return -1;
    }
}
