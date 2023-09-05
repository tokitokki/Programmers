package src.level2;
import java.util.ArrayList;
import java.util.Scanner;

public class Camouflage { //위장
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("스파이가 가진 의상의 개수를 입력하세요 (최대 30까지 입력) >>> ");
        int answer = 0;
        try {
            answer = scan.nextInt();
            if(answer <= 0 || answer > 30) {
                System.out.println("의상의 개수를 잘못 입력하였습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }

        ArrayList<String[]> clothes = new ArrayList<>();
        for(int x = 0; x < answer; x++) {
            String[] cloth = new String[2];
            System.out.println((x + 1) + "번째 의상의 이름을 입력하세요 >>> ");
            String thisCloth = scan.next();

            if(thisCloth.length() > 20) {
                System.out.println("의상 이름의 길이는 20자를 초과할 수 없습니다.");
                x--;
                continue;
            }

            int charCheck = 0;
            for(int y = 0; y < thisCloth.length(); y++) {
                if((thisCloth.charAt(y) >= 97 && thisCloth.charAt(y) <= 122) || thisCloth.charAt(y) == 95) {
                    charCheck++;
                }
            }

            if(charCheck != thisCloth.length()) {
                System.out.println("소문자 또는 \"_\" 기호 외에 다른 문자를 포함하여 입력하였습니다. 다시 입력하세요.");
                x--;
                continue;
            }

            if(x != 0) {
                int duplicateCheck = 0;
                for(String[] equipment : clothes) {
                    if(equipment[0].equals(thisCloth)) duplicateCheck++;
                }

                if(duplicateCheck > 0) {
                    System.out.println("이미 있는 의상을 입력하였습니다. 다시 입력하세요.");
                    x--;
                    continue;
                }
            }

            System.out.println((x + 1) + "번째 의상의 종류를 입력하세요 (얼굴장식, 모자, 상의, 하의, 겉옷) >>> ");
            String type = scan.next();
            if(!(type.equals("얼굴장식") || type.equals("모자") || type.equals("상의") || type.equals("하의") || type.equals("겉옷"))) {
                System.out.println("잘못된 종류를 입력하였습니다. 다시 입력해주세요.");
                x--;
                continue;
            }

            System.out.println((x + 1) + "번째 의상 : " + thisCloth + ", 의상의 종류 : " + type);
            cloth[0] = thisCloth;
            cloth[1] = type;
            clothes.add(cloth);
        }

        System.out.println("서로 다른 옷의 조합의 수 : " + changeClothesCount(clothes));
    }

    public static int changeClothesCount(ArrayList<String[]> clothes) {
        int faceDecoration = 0;
        int hat = 0;
        int topCloth = 0;
        int bottomCloth = 0;
        int outerClothing = 0;
        for(String[] cloth : clothes) {
            switch(cloth[1]) {
                case "얼굴장식" -> faceDecoration++;
                case "모자" -> hat++;
                case "상의" -> topCloth++;
                case "하의" -> bottomCloth++;
                case "겉옷" -> outerClothing++;
            }
        }

        int value = 1;
        if(faceDecoration != 0) value = value * (faceDecoration + 1);
        if(hat != 0) value = value * (hat + 1);
        if(topCloth != 0) value = value * (topCloth + 1);
        if(bottomCloth != 0) value = value * (bottomCloth + 1);
        if(outerClothing != 0) value = value * (outerClothing + 1);

        return value - 1;
    }
}
