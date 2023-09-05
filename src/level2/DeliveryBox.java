package src.level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class DeliveryBox { //택배상자
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("택배 상자의 개수를 입력하세요 (1부터 1000000까지 자연수 중 하나 임력) >>> ");
        int boxCount = 0;
        try {
            boxCount = scan.nextInt();
            if(boxCount <= 0 || boxCount > 1000000) {
                System.out.println("잘못 입력하였습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("문자를 포함해서 입력하였습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }

        int[] containerBeltOrders = new int[boxCount];
        ArrayList<Integer> inputOrders = new ArrayList<>();
        for(int x = 0; x < containerBeltOrders.length; x++) {
            int duplicate = 0;
            System.out.println("택배 기사님이 지정한 " + (x + 1) + "번째 상자의 번호는 몇 번입니까?");
            int order;
            try {
                order = scan.nextInt();
                if(order > boxCount) {
                    System.out.println("번호는 택배 상자의 개수를 초과할 수 없습니다. 다시 입력해주세요.");
                    x--;
                    continue;
                }

                if(order <= 0) {
                    System.out.println("번호는 0 또는 음의 정수가 될 수 없습니다. 다시 입력해주세요.");
                    x--;
                    continue;
                }

                for(int inputOrder : inputOrders) {
                    if(order == inputOrder) duplicate++;
                }

                if(duplicate > 0) {
                    System.out.println("번호가 중복되었습니다. 다시 입력해주세요.");
                    x--;
                    continue;
                }

                System.out.println((x + 1) + "번째 상자의 번호 : " + order);
                containerBeltOrders[x] = order;
                inputOrders.add(order);

            } catch(Exception e) {
                System.out.println("문자를 포함해서 입력하였습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        }

        System.out.println("상자 번호를 나열한 배열 : " + Arrays.toString(containerBeltOrders));
        System.out.println("실을 수 있는 상자의 개수 : " + movingBoxCount(containerBeltOrders));
    }

    public static int movingBoxCount(int[] boxOrders) {
        int count = 0;
        int order = 0;
        int number = 0;
        Stack<Integer> assistantContainerBelt = new Stack<>();
        for(int x = 0; x < boxOrders.length; x++) {
            count++;
            if(boxOrders[x] != x + 1) {
                order = x;
                number = boxOrders[x];
                break;
            }
        }

        for(int x = 1; x < number; x++) {
            assistantContainerBelt.push(x);
        }

        int assistantContainerBeltSize = assistantContainerBelt.size();
        if(!(assistantContainerBelt.isEmpty())) {
            for(int x = 0; x < assistantContainerBeltSize; x++) {
                if(boxOrders[order + x + 1] == assistantContainerBelt.peek()) {
                    assistantContainerBelt.pop();
                    count++;
                } else break;
            }
        }

        return count;
    }
}
