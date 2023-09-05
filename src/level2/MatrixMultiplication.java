package src.level2;

import java.util.Arrays;
import java.util.Scanner;

public class MatrixMultiplication { //행렬의 곱셈
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int matrixARow = 0;
        int matrixACol = 0;
        int matrixBRow = 0;
        int matrixBCol = 0;

        System.out.println("행렬 A의 행을 입력하세요 (2 이상 100 이하의 자연수 중 하나 입력) >>> ");
        try {
            matrixARow = scan.nextInt();
            if(matrixARow < 2 || matrixARow > 100) {
                System.out.println("범위를 잘못 입력하였습니다. 처음부터 다시 입력하세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("잘못 입력하였습니다. 처음부터 다시 입력하세요.");
            System.exit(0);
        }

        System.out.println("행렬 A의 열을 입력하세요 (2 이상 100 이하의 자연수 중 하나 입력) >>> ");
        try {
            matrixACol = scan.nextInt();
            if(matrixACol < 2 || matrixACol > 100) {
                System.out.println("범위를 잘못 입력하였습니다. 처음부터 다시 입력하세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("잘못 입력하였습니다. 처음부터 다시 입력하세요.");
            System.exit(0);
        }

        System.out.println("행렬 A : " + matrixARow + " × " + matrixACol + " 행렬");
        System.out.println();

        System.out.println("행렬 B의 행을 입력하세요 (2 이상 100 이하의 자연수 중 하나 입력) >>> ");
        try{
            matrixBRow = scan.nextInt();
            if(matrixBRow < 2 || matrixBRow > 100) {
                System.out.println("범위를 잘못 입력하였습니다. 처음부터 다시 입력하세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("잘못 입력하였습니다. 처음부터 다시 입력하세요.");
            System.exit(0);
        }

        System.out.println("행렬 B의 열을 입력하세요 (2 이상 100 이하의 자연수 중 하나 입력) >>> ");
        try {
            matrixBCol = scan.nextInt();
            if(matrixBCol < 2 || matrixBCol > 100) {
                System.out.println("범위를 잘못 입력하였습니다. 처음부터 다시 입력하세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("잘못 입력하였습니다. 처음부터 다시 입력하세요.");
            System.exit(0);
        }

        System.out.println("행렬 B : " + matrixBRow + " × " + matrixBCol + " 행렬");
        System.out.println();

        if(matrixACol != matrixBRow) {
            System.out.println("행렬 A의 열과 행렬 B의 행은 서로 같아야 곱셈이 가능합니다.");
            System.exit(0);
        }

        int[][] matrixA = new int[matrixARow][matrixACol];
        int[][] matrixB = new int[matrixBRow][matrixBCol];

        for(int i = 0; i < matrixA.length; i++) {
            for(int j = 0; j < matrixA[i].length; j++) {
                System.out.println("행렬 A의 " + (i + 1) + "행 " + (j + 1) + "열에 들어갈 값을 입력하세요");
                String value = scan.next();
                int changeValue;
                try {
                    changeValue = Integer.parseInt(value);
                    if(changeValue < -10 || changeValue > 20) {
                        System.out.println("범위를 잘못 입력하였습니다. 다시 입력하세요.");
                        j--;
                        continue;
                    }
                } catch(Exception e) {
                    System.out.println("잘못 입력하였습니다. 다시 입력하세요.");
                    j--;
                    continue;
                }

                System.out.println("행렬 A의 " + (i + 1) + "행 " + (j + 1) + "열의 값 : " + changeValue);
                matrixA[i][j] = changeValue;
            }
        }

        System.out.println();
        System.out.println("===[행렬 A]===");
        for(int[] row : matrixA) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();

        for(int i = 0; i < matrixB.length; i++) {
            for(int j = 0; j < matrixB[i].length; j++) {
                System.out.println("행렬 B의 " + (i + 1) + "행 " + (j + 1) + "열에 들어갈 값을 입력하세요");
                String value = scan.next();
                int changeValue;
                try {
                    changeValue = Integer.parseInt(value);
                    if(changeValue < -10 || changeValue > 20) {
                        System.out.println("범위를 잘못 입력하였습니다. 다시 입력하세요.");
                        j--;
                        continue;
                    }
                } catch(Exception e) {
                    System.out.println("잘못 입력하였습니다. 다시 입력하세요.");
                    j--;
                    continue;
                }

                System.out.println("행렬 B의 " + (i + 1) + "행 " + (j + 1) + "열의 값 : " + changeValue);
                matrixB[i][j] = changeValue;
            }
        }

        System.out.println();
        System.out.println("===[행렬 B]===");
        for(int[] row : matrixB) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();

        System.out.println("===[곱셈 결과]===");
        for(int[] row : multiplication(matrixA, matrixB)) {
            System.out.println(Arrays.toString(row));
        }
    }

    public static int[][] multiplication(int[][] matrixA, int[][] matrixB) {
        int[][] result = new int[matrixA.length][matrixB[0].length];
        for(int x = 0; x < result.length; x++) {
            for(int y = 0; y < result[x].length; y++) {
                for(int z = 0; z < result[x].length; z++) {
                    result[x][y] += matrixA[x][z] * matrixB[z][y];
                }
            }
        }
        return result;
    }
}
