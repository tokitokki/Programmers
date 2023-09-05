package src.level2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Boat { //구명보트

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("무인도에 갇힌 사람 수는 몇 명입니까? >>> ");
        String answer = scan.next();
        int intTypeAnswer;
        try {
            intTypeAnswer = Integer.parseInt(answer);
            if(intTypeAnswer <= 0 || intTypeAnswer > 50000) {
                System.out.println("무인도에 갇힌 사람 수는 1명에서 50000명까지의 수로 입력해주세요.");
                System.exit(0);
            }

            System.out.println("구조할 때 필요한 구명보트의 체중은 몇 kg입니까? >>> ");
            int boatWeight = scan.nextInt();

            if(boatWeight < 40 || boatWeight > 240) {
                System.out.println("구명보트의 무게 제한은 40kg 이상 240kg 이하입니다.");
                System.exit(0);
            }

            System.out.println("구명보트의 무게는 " + boatWeight + "kg입니다.");
            int[] personWeights = new int[intTypeAnswer];
            for(int x = 0; x < personWeights.length; x++) {
                System.out.println((x + 1) + "번째 사람의 체중을 입력하세요. >>> ");
                String weight = scan.next();
                int personWeight;
                try {
                    personWeight = Integer.parseInt(weight);
                    if(personWeight < 40 || personWeight > 240) {
                        System.out.println("각 사람의 몸무게는 40kg 이상 240kg 이하입니다.");
                        x--;
                        continue;
                    }

                    if(personWeight > boatWeight) {
                        System.out.println("구명보트의 체중보다 작거나 같도록 " + (x + 1) + "번째 사람의 체중을 입력해 주세요");
                        x--;
                        continue;
                    }

                    System.out.println((x + 1) + "번째 사람의 체중은 " + personWeight + "kg입니다.");
                    personWeights[x] = personWeight;

                } catch (Exception e) {
                    System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다.");
                    x--;
                }
            }
            System.out.println("조난된 사람들의 체중 목록 : " + Arrays.toString(personWeights));

            ArrayList<int[]> ridingBoat = new ArrayList<>();
            int[] boat;
            for(int x = 0; x < personWeights.length; x++) {
                boat = new int[2];
                if(personWeights[x] != 0) {
                    boat[0] = personWeights[x];
                    int count = 0;
                    if(x != personWeights.length - 1) {
                        for (int y = x + 1; y < personWeights.length; y++) {
                            if (count == 0 && x != y && personWeights[x] + personWeights[y] <=
                                    boatWeight && personWeights[x] != 0 && personWeights[y] != 0) {
                                boat[1] = personWeights[y];
                                personWeights[y] = 0;
                                ridingBoat.add(boat);
                                count++;
                            }
                        }
                    }

                    if(boat[1] == 0) {
                        ridingBoat.add(boat);
                    }
                }
            }

            System.out.println("최소 구명 보트 수 : " + ridingBoat.size());

        } catch(Exception e) {
            System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다.");
            System.exit(0);
        }
    }
}
