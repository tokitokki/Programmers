package src.level2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class TableHashFunction { //테이블 해시 함수
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("테이블 데이터의 세로 길이를 입력하세요 (1부터 2500까지 자연수 중 하나 입력) >>> ");
        String height = scan.nextLine();
        int changeTypeHeight = 0;

        try {
            changeTypeHeight = Integer.parseInt(height);
            if (changeTypeHeight < 1 || changeTypeHeight > 2500) {
                System.out.println("범위가 잘못되었습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch (Exception e) {
            System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }

        System.out.println("테이블 데이터의 세로 길이 : " + changeTypeHeight);
        System.out.println();

        System.out.println("테이블 데이터의 가로 길이를 입력하세요 (1부터 500까지 자연수 중 하나 입력) >>> ");
        String width = scan.nextLine();
        int changeTypeWidth = 0;

        try {
            changeTypeWidth = Integer.parseInt(width);
            if (changeTypeWidth < 1 || changeTypeWidth > 500) {
                System.out.println("범위가 잘못되었습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch (Exception e) {
            System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }

        System.out.println("테이블 데이터의 가로 길이 : " + changeTypeWidth);
        System.out.println();

        int[][] table = new int[changeTypeHeight][changeTypeWidth];
        for (int x = 0; x < table.length; x++) {
            for (int y = 0; y < table[x].length; y++) {
                System.out.println((x + 1) + "행 " + (y + 1) + "열의 원소를 입력하세요 >>> ");
                String value = scan.nextLine();
                int changeTypeValue;
                try {
                    changeTypeValue = Integer.parseInt(value);
                } catch (Exception e) {
                    System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 다시 입력해주세요.");
                    System.out.println();
                    y--;
                    continue;
                }

                if (x > 0 && y == 0) {
                    boolean duplicate = false;
                    for (int z = 0; z <= x - 1; z++) {
                        if (table[z][0] == changeTypeValue) {
                            duplicate = true;
                            break;
                        }
                    }

                    if (duplicate) {
                        System.out.println("첫 번째 열들끼리는 중복 없이 입력해주세요.");
                        System.out.println();
                        y--;
                        continue;
                    }
                }

                System.out.println((x + 1) + "행 " + (y + 1) + "열의 원소 값 : " + changeTypeValue);
                System.out.println();
                table[x][y] = changeTypeValue;
            }
        }

        System.out.println("테이블의 데이터를 나타낸 배열 : " + Arrays.deepToString(table));
        System.out.println("몇 번째 열의 컬럼을 기준으로 테이블을 정렬할까요? (1부터 해당 테이블의 가로 길이 중 하나의 자연수로 입력) >>> ");
        String arrayCol = scan.nextLine();
        int changeTypeArrayCol = 0;
        try {
            changeTypeArrayCol = Integer.parseInt(arrayCol);
            if (changeTypeArrayCol < 1 || changeTypeArrayCol > changeTypeWidth) {
                System.out.println("범위가 잘못되었습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch (Exception e) {
            System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }
        System.out.println();

        System.out.println("정렬할 기준의 열 : " + changeTypeArrayCol + "열");
        System.out.println("정렬된 테이블 : " + Arrays.deepToString(arrayTable(changeTypeArrayCol, table)));
        int[][] arrayedTable = arrayTable(changeTypeArrayCol, table);
        System.out.println("몇 번째 행부터 'Bitwise XOR'을 실행할까요? (1부터 해당 테이블의 세로 길이 중 하나의 자연수로 입력) >>> ");
        String bitwiseBeginRow = scan.nextLine();
        int beginRow = 0;

        try {
            beginRow = Integer.parseInt(bitwiseBeginRow);
            if (beginRow < 1 || beginRow > changeTypeHeight) {
                System.out.println("범위가 잘못되었습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch (Exception e) {
            System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }

        System.out.println();
        System.out.println("몇 번째 행까지 'Bitwise XOR'을 실행할까요? (실행 시작 행 순서부터 해당 테이블의 세로 길이 중 하나의 자연수로 입력) >>> ");
        String bitwiseEndRow = scan.nextLine();
        int endRow = 0;

        try {
            endRow = Integer.parseInt(bitwiseEndRow);
            if (endRow < beginRow || endRow > changeTypeHeight) {
                System.out.println("범위가 잘못되었습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch (Exception e) {
            System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }
        System.out.println();

        System.out.println(beginRow + "행부터 " + endRow + "행까지 'Bitwise XOR'을 구합니다.");
        System.out.println("'Bitwise XOR'의 값 : " + bitWiseXORValue(beginRow, endRow, arrayedTable));
    }

    public static int[][] arrayTable(int arrayColIndex, int[][] database) {
        for (int x = 0; x < database.length; x++) {
            if (x != database.length - 1) {
                for (int i = x; i <= database.length - 1; i++) {
                    if (x != i) {
                        if (database[x][arrayColIndex - 1] > database[i][arrayColIndex - 1]) {
                            int[] xCol = database[x];
                            int[] iCol = database[i];
                            database[i] = xCol;
                            database[x] = iCol;

                        } else if (arrayColIndex > 1 && database[x][arrayColIndex - 1] == database[i][arrayColIndex - 1] && database[x][0] < database[i][0]) {
                            int[] xCol = database[x];
                            int[] iCol = database[i];
                            database[i] = xCol;
                            database[x] = iCol;
                        }
                    }
                }
            }
        }

        return database;
    }

    public static int bitWiseXORValue(int startNo, int endNo, int[][] database) {
        int value = 0;
        HashMap<Integer, Integer> restAdds = new HashMap<>();
        for (int x = startNo - 1; x < endNo; x++) {
            int restAdd = 0;
            for (int y = 0; y < database[x].length; y++) {
                restAdd += database[x][y] % (x + 1);
            }

            restAdds.put(x, restAdd);
        }

        if (restAdds.size() == 1) return restAdds.get(startNo - 1);
        for (int key : restAdds.keySet()) {
            value = bitwiseXOR(value, restAdds.get(key));
        }

        return value;
    }

    public static int bitwiseXOR(int a, int b) {
        String binaryA = Integer.toBinaryString(a);
        String binaryB = Integer.toBinaryString(b);

        String addZero = "";
        if (binaryA.length() > binaryB.length()) {
            for (int x = 1; x <= binaryA.length() - binaryB.length(); x++) {
                addZero = addZero.concat("0");
            }

            binaryB = addZero + binaryB;
        } else if (binaryA.length() < binaryB.length()) {
            for (int x = 1; x <= binaryB.length() - binaryA.length(); x++) {
                addZero = addZero.concat("0");
            }

            binaryA = addZero + binaryA;
        }

        String bitwiseXOR = "";
        for (int i = 0; i < binaryA.length(); i++) {
            if (binaryA.charAt(i) != binaryB.charAt(i)) bitwiseXOR = bitwiseXOR.concat("1");
            else bitwiseXOR = bitwiseXOR.concat("0");
        }

        int result = 0;
        for (int i = 0; i < bitwiseXOR.length(); i++) {
            if (bitwiseXOR.charAt(bitwiseXOR.length() - 1 - i) == '1') result += Math.pow(2, i);
        }

        return result;
    }
}