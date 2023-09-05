package src.level3;

import java.util.Arrays;
import java.util.Scanner;

public class ArcheologyBestDiscover { // 고고학 최고의 발견
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("시곗바늘들의 행과 열의 수를 입력하세요 (2 이상 8 이하 자연수 중 하나 입력) >>> ");
        String length = scan.nextLine();
        int changeTypeLength = 0;
        try {
            changeTypeLength = Integer.parseInt(length);
            if(changeTypeLength < 2 || changeTypeLength > 8) {
                System.out.println("범위가 잘못되었습니다. 처음부터 다시 입력하세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("잘못 입력하셨습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }

        System.out.println("시곗바늘들은 " + changeTypeLength + " × " + changeTypeLength + " 행렬입니다.");
        System.out.println();

        int[][] clockDirections = new int[changeTypeLength][changeTypeLength];

        for(int x = 0; x < clockDirections.length; x++) {
            for(int y = 0; y < clockDirections[x].length; y++) {
                System.out.println((x + 1) + "행 " + (y + 1) + "번째 칸의 원소를 입력하세요.");
                System.out.println("[12시 방향일 때 0, 3시 방향일 때 1, 6시 방향일 때 2, 9시 방향일 때 3을 입력하세요.]");
                String element = scan.nextLine();
                int changeTypeElement;

                try {
                    changeTypeElement = Integer.parseInt(element);
                    if(changeTypeElement < 0 || changeTypeElement > 3) {
                        System.out.println("범위가 잘못되었습니다. 다시 입력하세요.");
                        System.out.println();
                        y--;
                        continue;
                    }
                } catch(Exception e) {
                    System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
                    System.out.println();
                    y--;
                    continue;
                }

                String time = switch(changeTypeElement) {
                    case 0 -> "12시";
                    case 1 -> "3시";
                    case 2 -> "6시";
                    default -> "9시";
                };

                System.out.println((x + 1) + "행 " + (y + 1) + "열의 시계 방향은 " + time + " 방향입니다.");
                System.out.println();
                clockDirections[x][y] = changeTypeElement;
            }
        }

        System.out.println("-------[시곗바늘들의 행렬]-------");
        for(int[] clockDirection : clockDirections) {
            System.out.println(Arrays.toString(clockDirection));
        }
        System.out.println("문제를 해결하기 위한 최소 조작 횟수 : " + rotateMinCount(clockDirections));
    }

    public static int rotateMinCount(int[][] clockHandMatrix) {
        int count = 0;
        int[][] moreRotateCount = new int[clockHandMatrix.length][clockHandMatrix.length];
        for(int x = 0; x < moreRotateCount.length; x++) {
            for(int y = 0; y < moreRotateCount[x].length; y++) {
                if(clockHandMatrix[x][y] == 0) moreRotateCount[x][y] = 0;
                else moreRotateCount[x][y] = 4 - clockHandMatrix[x][y];
            }
        }

        while(true) {
            boolean running = false;
            for(int x = 0; x < moreRotateCount.length; x++) {
                for(int y = 0; y < moreRotateCount[x].length; y++) {
                    int beforeOperate = moreRotateCount[x][y];
                    int afterOperate = operate(x, y, moreRotateCount)[x][y];

                    if(beforeOperate != afterOperate) {
                        running = true;
                        count++;
                        break;
                    }
                }

                if(running) break;
            }

            if(running) continue;

            int zeroCount = 0;
            for(int[] rotateLine : moreRotateCount) {
                for(int rotateElement : rotateLine) {
                    if(rotateElement == 0) zeroCount++;
                }
            }

            if(zeroCount == Math.pow(moreRotateCount.length, 2)) break;
            else {
                System.out.println("문제를 해결할 수 없도록 시곗바늘을 설정했습니다.");
                System.exit(0);
            }
        }

        return count;
    }

    public static int[][] operate(int a, int b, int[][] matrix) {
        if(a == 0 && matrix[a][b] > 0) {
            if(b == 0) {
                if(matrix[a + 1][b] > 0 && matrix[a][b + 1] > 0) {
                    matrix[a][b]--;
                    matrix[a + 1][b]--;
                    matrix[a][b + 1]--;
                }
            } else if(b == matrix[a].length - 1) {
                if(matrix[a + 1][b] > 0 && matrix[a][b - 1] > 0) {
                    matrix[a][b]--;
                    matrix[a + 1][b]--;
                    matrix[a][b - 1]--;
                }
            } else {
                if(matrix[a][b - 1] > 0 && matrix[a][b + 1] > 0 && matrix[a + 1][b] > 0) {
                    matrix[a][b]--;
                    matrix[a][b - 1]--;
                    matrix[a][b + 1]--;
                    matrix[a + 1][b]--;
                }
            }
        } else if(a == matrix.length - 1 && matrix[a][b] > 0) {
            if(b == 0) {
                if(matrix[a - 1][b] > 0 && matrix[a][b + 1] > 0) {
                    matrix[a][b]--;
                    matrix[a - 1][b]--;
                    matrix[a][b + 1]--;
                }
            } else if(b == matrix[a].length - 1) {
                if(matrix[a - 1][b] > 0 && matrix[a][b - 1] > 0) {
                    matrix[a][b]--;
                    matrix[a - 1][b]--;
                    matrix[a][b - 1]--;
                }
            } else {
                if(matrix[a][b + 1] > 0 && matrix[a][b - 1] > 0 && matrix[a - 1][b] > 0) {
                    matrix[a][b]--;
                    matrix[a][b + 1]--;
                    matrix[a][b - 1]--;
                    matrix[a - 1][b]--;
                }
            }
        } else if(matrix[a][b] > 0){
            if(b == 0) {
                if(matrix[a - 1][b] > 0 && matrix[a + 1][b] > 0 && matrix[a][b + 1] > 0) {
                    matrix[a][b]--;
                    matrix[a - 1][b]--;
                    matrix[a + 1][b]--;
                    matrix[a][b + 1]--;
                }
            } else if(b == matrix[a].length - 1) {
                if(matrix[a - 1][b] > 0 && matrix[a + 1][b] > 0 && matrix[a][b - 1] > 0) {
                    matrix[a][b]--;
                    matrix[a - 1][b]--;
                    matrix[a + 1][b]--;
                    matrix[a][b - 1]--;
                }
            } else {
                if(matrix[a - 1][b] > 0 && matrix[a + 1][b] > 0 && matrix[a][b - 1] > 0 && matrix[a][b + 1] > 0) {
                    matrix[a][b]--;
                    matrix[a - 1][b]--;
                    matrix[a + 1][b]--;
                    matrix[a][b - 1]--;
                    matrix[a][b + 1]--;
                }
            }
        }

        return matrix;
    }
}