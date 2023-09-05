package src.level2;
import java.util.Arrays;
import java.util.Scanner;

public class SkillTree { //스킬트리

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("스킬트리 순서들을 담는 배열의 길이를 입력하세요 (배열의 길이는 1 이상 26 이하) >>> ");
        int arrayLength = 0;
        try {
            arrayLength = scan.nextInt();
            if(arrayLength <= 0 || arrayLength > 26) {
                System.out.println("배열 길이에 해당되지 않는 범위입니다. 처음부터 다시 시도하세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 시도하세요.");
            System.exit(0);
        }

        boolean flag = true;
        String skill = "";
        while(flag) {
            System.out.println("선행 스킬 순서를 입력하세요 (대문자만 중복되지 않게 사용해서 입력한다.) >>> ");
            skill = scan.next();

            for(int i = 0; i < skill.length(); i++) {
                if(!(skill.charAt(i) >= 65 && skill.charAt(i) <= 90)) {
                    System.out.println("대문자가 아닌 문자를 포함해서 입력하였습니다. 처음부터 다시 입력하세요.");
                    System.exit(0);
                }
            }

            for(int x = 65; x <= 90; x++) {
                int duplicateCount = 0;
                for(int y = 0; y < skill.length(); y++) {
                    if(skill.charAt(y) == x) duplicateCount++;
                }

                if(duplicateCount >= 2) {
                    System.out.println("중복된 알파벳이 존재합니다. 처음부터 다시 입력하세요.");
                    System.exit(0);
                }
            }

            flag = false;
        }

        System.out.println("선행 스킬 순서는 " + skill + "입니다.");
        String[] skillTrees = new String[arrayLength];

        for(int x = 0; x < skillTrees.length; x++) {
            System.out.println((x + 1) + "번째 배열에 스킬트리를 입력하세요 >>> ");
            String answer = scan.next();

            if(answer.length() > 26) {
                System.out.println("잘못 입력하였습니다. 다시 입력하세요.");
                x--;
                continue;
            }

            String[] answerSplit = answer.split("");
            int spellComplete = 0;
            for(String spell : answerSplit) {
                if(spell.charAt(0) >= 65 && spell.charAt(0) <= 90) spellComplete++;
            }

            if(spellComplete != answer.length()) {
                System.out.println("잘못 입력하였습니다. 다시 입력하세요.");
                x--;
                continue;
            }

            int[] checkArray = new int[26];
            int[] checkCount = new int[26];
            for(int y = 65; y <= 90; y++) {
                checkArray[y - 65] = y;
            }

            for(int i = 0; i < checkArray.length; i++) {
                for(int j = 0; j <answer.length(); j++) {
                    if(answer.charAt(j) == checkArray[i]) checkCount[i]++;
                }
            }

            int checked = 0;
            for (int j : checkCount) {
                if (j < 2) checked++;
            }

            if(checked < 26) {
                System.out.println("대문자 알파벳을 중복하여 입력하였습니다. 다시 입력하세요.");
                x--;
                continue;
            }

            System.out.println("입력한 스킬트리 : " + answer);
            skillTrees[x] = answer;
        }

        System.out.println("입력한 배열 : " + Arrays.toString(skillTrees));
        System.out.println("배열의 내용들 중에서 설정할 수 있는 스킬트리의 개수 : " + ableSkillTreeCount(skill, skillTrees));
    }

    public static int ableSkillTreeCount(String skill, String[] skillTrees) {
        int count = 0;
        for(String skillTree : skillTrees) {
            StringBuilder stringBuilder = new StringBuilder();

            for(int j = 0; j < skillTree.length(); j++) {
                if(skill.contains(String.valueOf(skillTree.charAt(j)))) {
                    stringBuilder.append(skillTree.charAt(j));
                }
            }

            for(int j = skill.length(); j > -1; j--) {
                String subString = skill.substring(0, j);
                if(subString.equals(stringBuilder.toString())) {
                    count++;
                    break;
                }
            }
        }

        return count;
    }
}
