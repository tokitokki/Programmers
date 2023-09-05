package src.level2;

import java.util.Arrays;
import java.util.Scanner;

public class SeesawPair { //시소 짝꿍
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.println("몸무게를 측정할 사람의 수를 입력하세요 (2명 이상 100000명 이하) >>> ");
        String peopleCount = scan.next();
        int peopleNumber = 0;
        try {
            peopleNumber = Integer.parseInt(peopleCount);
            if(peopleNumber < 2 || peopleNumber > 100000) {
                System.out.println("잘못 입력하였습니다. 처음부터 다시 입력하세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 입력하세요");
            System.exit(0);
        }

        System.out.println(peopleNumber + "명의 몸무게를 측정합니다.");
        System.out.println();

        int[] weightArray = new int[peopleNumber];
        for(int x = 0; x < weightArray.length; x++) {
            System.out.println((x + 1) + "번째 사람의 몸무게를 입력하세요 (100 이상 100000 이하 중 하나의 자연수로 입력, 뉴턴 단위) >>> ");
            String personWeight = scan.next();
            int weight;
            try {
                weight = Integer.parseInt(personWeight);
                if(weight < 100 || weight > 100000) {
                    System.out.println("잘못 입력하였습니다. 다시 입력하세요.");
                    System.out.println();
                    x--;
                    continue;
                }
            } catch(Exception e) {
                System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 입력하세요");
                System.out.println();
                x--;
                continue;
            }

            System.out.println((x + 1) + "번째 사람의 몸무게 : " + weight + "N(뉴턴)");
            System.out.println();
            weightArray[x] = weight;
        }

        System.out.println("사람들의 몸무게를 나타낸 배열 : " + Arrays.toString(weightArray));
        System.out.println("시소 짝꿍 개수 : " + seesawPairCounts(weightArray) + "쌍");
    }

    public static int seesawPairCounts(int[] array) {
        int count = 0;

        for(int i = 0; i < array.length; i++) {
            if(i != array.length - 1) {
                for(int j = i + 1; j < array.length; j++) {
                    int[] seesawWeightA = new int[3];
                    int[] seesawWeightB = new int[3];

                    for(int z = 0; z < 3; z ++) {
                        seesawWeightA[z] = array[i] * (z + 2);
                        seesawWeightB[z] = array[j] * (z * 2);
                    }

                    boolean match = false;
                    for(int weightA : seesawWeightA) {
                        for(int weightB : seesawWeightB) {
                            if(weightA == weightB) {
                                match = true;
                                break;
                            }
                        }
                    }

                    if(match) count++;
                }
            }
        }

        return count;
    }
}
