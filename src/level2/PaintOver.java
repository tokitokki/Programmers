import java.util.Arrays;
import java.util.Scanner;

public class PaintOver { //덧칠하기
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("벽의 길이는 몇 미터입니까? (1 이상 100000 이하의 자연수 중 하나 입력) >>> ");
        int wallLength = 0;
        try {
            wallLength = scan.nextInt();
            if(wallLength < 1 || wallLength > 100000) {
                System.out.println("잘못 입력하였습니다. 처음부터 다시 입력하세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 입력하세요");
            System.exit(0);
        }

        System.out.println("페인트를 칠하는 룰러의 길이를 입력하세요 (1 이상 해당 벽의 길이 이하의 자연수 중 하나 입력) >>> ");
        int paintRulerLength = 0;
        try {
            paintRulerLength = scan.nextInt();
            if(paintRulerLength < 1 || paintRulerLength > wallLength) {
                System.out.println("잘못 입력하였습니다. 처음부터 다시 입력하세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 입력하세요");
            System.exit(0);
        }

        System.out.println("미터당 구역을 순서대로 나누었을 때, 덧칠해야 되는 구역은 몇 군데입니까? >>> ");
        int areaCount = 0;
        try {
            areaCount = scan.nextInt();
            if(areaCount < 1 || areaCount > wallLength) {
                System.out.println("잘못 입력하였습니다. 처음부터 다시 입력하세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 입력하세요");
            System.exit(0);
        }

        int[] paintOverAreaNumbers = new int[areaCount];
        for(int i = 0; i < paintOverAreaNumbers.length; i++) {
            System.out.println("덧칠할 " + (i + 1) + "번째 구역은 몇 번째 구역입니까?");
            String order = scan.next();
            int index;
            try {
                index = Integer.parseInt(order);
                if(index < 1 || index > wallLength) {
                    System.out.println("잘못 입력하였습니다. 다시 입력하세요.");
                    i--;
                    continue;
                }

                if(i > 0) {
                    boolean duplicate = false;
                    for(int j = 0; j <= i - 1; j++) {
                        if(paintOverAreaNumbers[j] == index) {
                            duplicate = true;
                            break;
                        }
                    }

                    if(duplicate) {
                        System.out.println("순서가 중복되었습니다. 다시 입력하세요.");
                        i--;
                        continue;
                    }
                }

            } catch(Exception e) {
                System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 다시 입력하세요");
                i--;
                continue;
            }

            System.out.println(index + "번째 구역이 덧칠이 필요합니다.");
            paintOverAreaNumbers[i] = index;
        }

        for(int i = 0; i < paintOverAreaNumbers.length; i++) {
            for(int j = 0; j < paintOverAreaNumbers.length; j++) {
                if(i != j && paintOverAreaNumbers[i] < paintOverAreaNumbers[j]) {
                    int changeA = paintOverAreaNumbers[i];
                    int changeB = paintOverAreaNumbers[j];
                    paintOverAreaNumbers[i] = changeB;
                    paintOverAreaNumbers[j] = changeA;
                }
            }
        }

        System.out.println("덧칠한 구역을 순서대로 나타낸 배열 : " + Arrays.toString(paintOverAreaNumbers));
        System.out.println("페인트를 칠하는 최소 개수 : " + paintRulerUseCount(wallLength, paintRulerLength, paintOverAreaNumbers));
    }

    public static int paintRulerUseCount(int wallLength, int paintRulerLength, int[] paintOverNumbers) {
        int paintCount = 0;
        boolean[] paintRequire = new boolean[wallLength];

        for(int x = 0; x < paintRequire.length; x++) {
            for(int paintOverNumber : paintOverNumbers) {
                if(x + 1 == paintOverNumber) {
                    paintRequire[x] = true;
                    break;
                }
            }
        }

        for(int x = 0; x < paintRequire.length; x++) {
            if(paintRequire[x]) {
                paintCount++;
                paintRequire[x] = false;
                if(paintRulerLength >= 2) {
                    for(int y = 2; y <= paintRulerLength; y++) {
                        if(x + y - 1 < paintRequire.length) paintRequire[x + y - 1] = false;
                    }
                }
            }
        }

        return paintCount;
    }
}
