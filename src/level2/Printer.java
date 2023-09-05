package src.level2;

import java.util.*;

public class Printer { // 프린터
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int fileCounts = 0;
        System.out.println("자료의 개수를 입력하세요 (1 이상 100 이하의 자연수 중 하나 입력) >>> ");
        try {
            fileCounts = scan.nextInt();
            if(fileCounts < 1 || fileCounts > 100) {
                System.out.println("범위에 포함되지 않는 정수입니다. 처음부터 다시 입력하세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("잘못 입력하였습니다. 처음부터 다시 입력하세요.");
            System.exit(0);
        }

        int[] files = new int[fileCounts];
        for(int x = 0; x < files.length; x++) {
            System.out.println((x + 1) + "번째 대기목록의 자료의 중요도를 입력하세요 (1 이상 9 이하의 자연수 중 하나 입력) >>> ");
            String important = scan.next();
            int changeValueImportant;
            try {
                changeValueImportant = Integer.parseInt(important);
                if(changeValueImportant < 1 || changeValueImportant > 9) {
                    System.out.println("범위에 포함되지 않는 정수입니다. 다시 입력하세요.");
                    x--;
                    continue;
                }
            } catch(Exception e) {
                System.out.println("잘못 입력하였습니다. 다시 입력하세요.");
                x--;
                continue;
            }

            System.out.println((x + 1) + "번째 대기목록의 자료의 중요도 : " + changeValueImportant);
            files[x] = changeValueImportant;
        }

        System.out.println("파일의 중요도를 순서대로 나타낸 배열 : " + Arrays.toString(files));
        System.out.println();

        System.out.println("출력 순서를 확인할 파일이 있는 배열의 인덱스를 입력하세요 >>> ");
        int arrayIndex = -1;
        try {
            arrayIndex = scan.nextInt();
            if(arrayIndex < 0 || arrayIndex > files.length - 1) {
                System.out.println("범위에 포함되지 않는 정수입니다. 처음부터 다시 입력하세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("잘못 입력하였습니다. 처음부터 다시 입력하세요.");
            System.exit(0);
        }

        int[] orders;
        Queue<int[]> print = new LinkedList<>();

        for(int i = 0; i < files.length; i++) {
            orders = new int[2];
            orders[0] = i;
            orders[1] = files[i];

            print.add(orders);
        }

        boolean flag = true;
        int printOrder = 0;
        while(flag) {
            boolean printed = true;
            for(int[] order : print) {
                if(print.peek()[0] != order[0] && print.peek()[1] < order[1]) {
                    printed = false;
                    break;
                }
            }

            if(!printed) {
                print.add(print.poll());
            } else {
                int[] removeArray = print.poll();
                printOrder++;

                if(removeArray[0] == arrayIndex) {
                    flag = false;
                }
            }
        }

        System.out.println("해당 파일의 출력 순서 : " + printOrder);
    }
}