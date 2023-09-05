package src.level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/*
 *  올바른 값을 확인할 때는 이러한 문장으로만 올바른 값이 나오는지 테스트 해볼 것
 *  입력 - {{2},{2,1},{2,1,3},{2,1,3,4}}    결과 : [2,1,3,4]
 *  입력 - {{1,2,3},{2,1},{1,2,4,3},{2}}    결과 : [2,1,3,4]
 *  입력 - {{20,111},{111}}                 결과 : [111,20]
 *  입력 - {{123}}                          결과 : [123]
 *  입력 - {{4,2,3},{3},{2,3,4,1},{2,3}}    결과 : [3,2,4,1]
 */

public class Tuple { // 튜플
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("튜플을 튜플 기호를 같이 써서 입력하세요 (공백 없이 5자에서 1000자까지 입력하세요) >>> ");
        String tuple = scan.nextLine();

        if(tuple.length() < 5 || tuple.length() > 1000) {
            System.out.println("튜플을 잘못 입력하였습니다. 처음부터 다시 입력하세요.");
            System.exit(0);
        } else if(!tuple.startsWith("{{")) {
            System.out.println("튜플을 잘못 입력하였습니다. 처음부터 다시 입력하세요.");
            System.exit(0);
        } else if(!tuple.endsWith("}}")) {
            System.out.println("튜플을 잘못 입력하였습니다. 처음부터 다시 입력하세요.");
            System.exit(0);
        }

        boolean pass = true;
        for(int x = 0; x < tuple.length(); x++) {
            if(!((tuple.charAt(x) >= '0' && tuple.charAt(x) <= '9') || tuple.charAt(x) == ',' || tuple.charAt(x) == '{' || tuple.charAt(x) == '}')) {
                pass= false;
                break;
            } else if(tuple.charAt(x) == '}') {
                if(x < tuple.length() - 5) {
                    if(!(tuple.charAt(x + 1) == ',' && tuple.charAt(x + 2) == '{')) {
                        pass= false;
                        break;
                    }
                } else if(x < tuple.length() - 2){
                    pass= false;
                    break;
                }
            } else if(tuple.charAt(x) == ',') {
                if(x > 2 && x < tuple.length() - 3) {
                    if(!(tuple.charAt(x - 1) >= '0' && tuple.charAt(x - 1) <= '9' && tuple.charAt(x + 1) >= '0' && tuple.charAt(x + 1) <= '9') && (!(tuple.charAt(x - 1) == '}' && tuple.charAt(x + 1) == '{'))) {
                        pass= false;
                        break;
                    }
                } else {
                    pass= false;
                    break;
                }
            } else if(tuple.charAt(x) == '{') {
                if(x > 4) {
                    if(!(tuple.charAt(x - 2) == '}' && tuple.charAt(x - 1) == ',')) {
                        pass= false;
                        break;
                    }
                } else if(x > 1) {
                    pass= false;
                    break;
                }
            }
        }

        if(!pass) {
            System.out.println("튜플을 잘못 입력하였습니다. 처음부터 다시 입력하세요.");
            System.exit(0);
        }

        if(arrayReturn(tuple).length >= 1 && arrayReturn(tuple).length <= 500) {
            System.out.println("결과값 : " + Arrays.toString(arrayReturn(tuple)));
        } else System.out.println("결과값이 없거나 결과값의 길이가 500을 초과하였습니다.");
    }

    public static int[] arrayReturn(String tuple) {
        String tupleEdit = tuple.substring(2, tuple.length() - 2);
        String[] tupleSplit = tupleEdit.split("},\\{");

        int[][] changeTypeTuple = new int[tupleSplit.length][];
        for(int x = 0; x < changeTypeTuple.length; x++) {
            String[] tupleArraySplit = tupleSplit[x].split(",");
            int[] parseValue = new int[tupleArraySplit.length];
            for(int y = 0; y < parseValue.length; y++) {
                parseValue[y] = Integer.parseInt(tupleArraySplit[y]);
                if(parseValue[y] < 1 || parseValue[y] > 100000) {
                    System.out.println("튜플의 원소를 1 이상 100000 이하인 자연수로 입력해주세요.");
                    System.exit(0);
                }
            }

            changeTypeTuple[x] = parseValue;
            boolean duplicate = false;
            for(int y = 0; y < parseValue.length; y++) {
                for(int z = 0; z < parseValue.length; z++) {
                    if(y != z && parseValue[y] == parseValue[z]) {
                        duplicate = true;
                        break;
                    }
                }
            }

            if(duplicate) {
                System.out.println("튜플이 중복되었습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        }

        int[] answer = new int[changeTypeTuple.length];
        for(int x = 0; x < changeTypeTuple.length; x++) {
            for(int y = 0; y < changeTypeTuple.length; y++) {
                if(x < y && changeTypeTuple[x].length > changeTypeTuple[y].length) {
                    int[] changeX = changeTypeTuple[y];
                    int[] changeY = changeTypeTuple[x];

                    changeTypeTuple[x] = changeX;
                    changeTypeTuple[y] = changeY;
                }
            }
        }

        ArrayList<Integer> duplicateChk = new ArrayList<>();
        for(int x = 0; x < changeTypeTuple.length; x++) {
            for(int y = 0; y < changeTypeTuple[x].length; y++) {
                if(duplicateChk.isEmpty()) {
                    answer[x] = changeTypeTuple[x][y];
                    duplicateChk.add(changeTypeTuple[x][y]);
                } else {
                    boolean pass = true;
                    for(int duplicateNumber : duplicateChk) {
                        if(duplicateNumber == changeTypeTuple[x][y]) {
                            pass = false;
                            break;
                        }
                    }

                    if(pass) {
                        answer[x] = changeTypeTuple[x][y];
                        duplicateChk.add(changeTypeTuple[x][y]);
                        break;
                    }
                }
            }
        }

        return answer;
    }
}