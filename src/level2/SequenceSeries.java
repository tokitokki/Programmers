package src.level2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class SequenceSeries { //연속된 부분 수열의 합
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("정수 배열의 길이를 입력하세요 (5 부터 1000000 까지의 자연수 중 하나 입력) >>> ");
        String length = scan.next();
        int changeLength = 0;
        try {
            changeLength = Integer.parseInt(length);
            if(changeLength < 5 || changeLength > 1000000) {
                System.out.println("범위가 잘못되었습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("잘못 입력하였습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }
        System.out.println();

        int[] array = new int[changeLength];
        for(int x = 0; x < array.length; x++) {
            System.out.println((x + 1) + "번째 배열에 입력할 원소를 입력하세요 (1 이상 1000 까지의 자연수 중 하나 입력) >>> ");
            String value = scan.next();
            int changeValue;
            try {
                changeValue = Integer.parseInt(value);
                if(changeValue < 1 || changeValue > 1000) {
                    System.out.println("범위가 잘못되었습니다. 다시 입력해주세요.");
                    System.out.println();
                    x--;
                    continue;
                }
            } catch(Exception e) {
                System.out.println("잘못 입력하였습니다. 다시 입력해주세요.");
                System.out.println();
                x--;
                continue;
            }

            System.out.println("입력한 " + (x + 1) + "번째 칸의 원소의 값 : " + changeValue);
            System.out.println();
            array[x] = changeValue;
        }

        System.out.println("출력한 배열 : " + Arrays.toString(array));
        System.out.println("비내림차순으로 정렬한 배열 : " + Arrays.toString(sortArray(array)));
        System.out.println();

        System.out.println("검색할 부분 수열의 합을 입력하세요 (5 이상 1000000 이하의 자연수 중 하나 입력) >>> ");
        String hap = scan.next();
        int changeTypeHap = 0;
        try {
            changeTypeHap = Integer.parseInt(hap);
            if(changeTypeHap < 5 || changeTypeHap > 1000000000) {
                System.out.println("범위가 잘못되었습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("잘못 입력하였습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }

        System.out.println("검색할 부분 수열의 합 : " + changeTypeHap);
        System.out.println();

        int[] answer = findIndex(sortArray(array), changeTypeHap);
        if(answer[0] == -1 && answer[1] == -1) {
            System.out.println("검색된 부분 수열의 합이 " + changeTypeHap + "인 수열이 없습니다.");
        } else {
            System.out.println("부분 수열의 시작 index, 마지막 index : " + Arrays.toString(answer));
        }
    }

    public static int[] sortArray(int[] array) {
        for(int x = 0; x < array.length; x++) {
            for(int y = 0; y < array.length; y++) {
                int changeA = array[x];
                int changeB = array[y];
                if(x < y && array[x] > array[y]) {
                    array[x] = changeB;
                    array[y] = changeA;
                } else if(x > y && array[x] < array[y]) {
                    array[x] = changeB;
                    array[y] = changeA;
                }
            }
        }

        return array;
    }

    public static int[] findIndex(int[] array, int searchValue) {
        int[] findResult = new int[2];
        findResult[0] = -1;
        findResult[1] = -1;

        HashMap<int[], Integer> eachIndexLengths = new HashMap<>();
        for(int x = 0; x < array.length; x++) {
            for(int i = x; i <= array.length - 1; i++) {
                int[] partSeries = new int[i - x + 1];
                System.arraycopy(array, x, partSeries, 0, i - x + 1);

                int sum = 0;
                for(int z : partSeries) {
                    sum += z;
                }

                if(sum == searchValue) {
                    int[] result = new int[2];
                    result[0] = x;
                    result[1] = i;
                    eachIndexLengths.put(result, partSeries.length);
                }
            }
        }

        int minLength = 0;
        if(!eachIndexLengths.isEmpty()) {
            for(int[] indexes : eachIndexLengths.keySet()) {
                if(minLength == 0 || eachIndexLengths.get(indexes) < minLength) {
                    minLength = eachIndexLengths.get(indexes);
                    findResult = indexes;
                } else if(eachIndexLengths.get(indexes) == minLength && findResult[0] > indexes[0]) {
                    findResult = indexes;
                }
            }
        }

        return findResult;
    }
}
