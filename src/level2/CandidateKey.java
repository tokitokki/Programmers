package src.level2;

import java.util.*;

public class CandidateKey { // 후보키

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("자료를 담는 컬럼의 개수를 입력하세요 (1부터 8까지의 자연수 중 하나 입력) >>> ");
        String width = scan.nextLine();
        int columnCount = 0;

        try {
            columnCount = Integer.parseInt(width);
            if(columnCount < 1 || columnCount > 8) {
                System.out.println("범위가 잘못되었습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }
        System.out.println();

        System.out.println("자료의 개수를 입력하세요 (1부터 20까지의 자연수 중 하나 입력) >>> ");
        String height = scan.nextLine();
        int rowCount = 0;
        
        try {
            rowCount = Integer.parseInt(height);
            if(rowCount < 1 || rowCount > 20) {
                System.out.println("범위가 잘못되었습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }
        System.out.println();
        
        String[][] table = new String[rowCount][columnCount];
        for(int x = 0; x < table.length; x++) {
            for(int y = 0; y < table[x].length; y++) {
                System.out.println((x + 1) + "번째 자료의 " + (y + 1) + "번째 컬럼의 값을 입력하세요 (1글자부터 8글자까지, 숫자와 알파벳 소문자로만 입력) >>> ");
                String data = scan.nextLine();
                if(data.length() < 1 || data.length() > 8) {
                    System.out.println("자료의 길이의 범위가 잘못되었습니다. 다시 입력하세요.");
                    System.out.println();
                    y--;
                    continue;
                }

                boolean check = true;
                for(int z = 0; z < data.length(); z++) {
                    if(!(data.charAt(z) >= 48 && data.charAt(z) <= 57) && !(data.charAt(z) >= 97 && data.charAt(z) <= 122)) {
                        check = false;
                        break;
                    }
                }

                if(!check) {
                    System.out.println("자료에 숫자 또는 알파벳 소문자가 아닌 문자가 있습니다. 다시 입력하세요.");
                    System.out.println();
                    y--;
                    continue;
                }

                System.out.println((x + 1) + "번째 자료의 " + (y + 1) + "번째 컬럼 값 : " + data);
                System.out.println();
                table[x][y] = data;
            }
        }

        Solution solution = new Solution();
        System.out.println("후보 키의 개수 : " + solution.solution(table));
    }

    public static class Solution {
        private String[][] relation;
        private final List<String> indexList = new LinkedList<>();

        public int solution(String[][] relation) {
            this.relation = relation;

            String[] arr = new String[relation[0].length];
            for(int i=0; i<relation[0].length; i++)
                arr[i] = Integer.toString(i);

            for (int i = 1; i <= relation[0].length; i++)
                combination(arr, new boolean[relation[0].length], 0, i);

            return indexList.size();
        }

        public void combination(String[] arr, boolean[] check, int index, int r) {
            if (r == 0) {
                StringBuilder result = new StringBuilder();
                for (int i = 0; i < arr.length; i++) {
                    if (check[i])
                        result.append(arr[i]);
                }

                for (String sub : indexList) {
                    int count = 0;
                    for (int i = 0; i < sub.split("").length; i++) {
                        if(result.toString().contains(String.valueOf(sub.charAt(i))))
                            count++;
                    }

                    if (sub.length() == count)
                        return;
                }

                if (dupleCheck(result.toString()))
                    indexList.add(result.toString());
            } else {
                for (int i = index; i < arr.length; i++) {
                    check[i] = true;
                    combination(arr, check, i + 1, r - 1);
                    check[i] = false;
                }
            }
        }

        public boolean dupleCheck(String index) {
            Set<String> set = new HashSet<>();

            for (String[] strings : relation) {
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < index.split("").length; j++) {
                    builder.append(strings[Integer.parseInt(String.valueOf(index.charAt(j)))]);
                    builder.append("/");
                }

                if (set.contains(builder.toString()))
                    return false;
                else
                    set.add(builder.toString());
            }

            return true;
        }
    }
}