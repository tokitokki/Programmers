package src.level2;

import java.util.Scanner;

public class AntilogarithmGame { //N진수 게임
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("N진수 게임에서 사용할 기수법을 선택하세요 (2진법부터 16진법까지) >>> ");
        int antilogarithm = 0;
        try {
            antilogarithm = scan.nextInt();
            if(antilogarithm < 2 || antilogarithm > 16) {
                System.out.println("해당되지 않는 범위의 값을 입력하였습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("문자를 포함하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }

        System.out.println("해당 플레이어가 몇 번 숫자를 구하나요? (1회부터 1000회까지 중에서 입력) >>> ");
        int playCount = 0;
        try {
            playCount = scan.nextInt();
            if(playCount <= 0 || playCount > 1000) {
                System.out.println("해당되지 않는 범위의 값을 입력하였습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("문자를 포함하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }

        System.out.println("N진수 게임에 참가하는 인원은 몇 명입니까? (2명부터 100명까지 중에서 입력) >>> ");
        int peopleCount = 0;
        try {
            peopleCount = scan.nextInt();
            if(peopleCount <= 1 || peopleCount > 100) {
                System.out.println("해당되지 않는 범위의 값을 입력하였습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("문자를 포함하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }

        System.out.println("자신의 순서는 몇 번째입니까? >>> ");
        int myOrder = 0;
        try {
            myOrder = scan.nextInt();
            if(myOrder < 1 || myOrder > peopleCount) {
                System.out.println("잘못 입력하였습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("문자를 포함하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }

        System.out.println("진법 : " + antilogarithm + "진법");
        System.out.println("한 사람 당 N진수 게임 진행 횟수 : " + playCount + "회");
        System.out.println("N진수 게임에 참가하는 인원 : " + peopleCount + "명");
        System.out.println("자신의 순서 : " + myOrder + "번째");

        System.out.println("전체 자리 수 : " + (playCount * peopleCount) + "자리");
        int value = 0;
        String answer = "";
        boolean flag = true;
        while(flag) {
            String changeRestType;
            int dividedNumber = value;
            if(changeNumberDigitCount(dividedNumber, antilogarithm) >= 1) {
                StringBuilder changeType = new StringBuilder();
                for(int i = 1; i <= changeNumberDigitCount(dividedNumber, antilogarithm) + 1; i++) {
                    switch(dividedNumber % antilogarithm) {
                        case 10 -> changeRestType = "A";
                        case 11 -> changeRestType = "B";
                        case 12 -> changeRestType = "C";
                        case 13 -> changeRestType = "D";
                        case 14 -> changeRestType = "E";
                        case 15 -> changeRestType = "F";
                        default -> changeRestType = String.valueOf(dividedNumber % antilogarithm);
                    }

                    if(answer.length() < playCount * peopleCount) {
                        changeType.insert(0, changeRestType);
                    }
                    dividedNumber = dividedNumber / antilogarithm;
                }

                switch(dividedNumber) {
                    case 10 -> changeType.insert(0, "A");
                    case 11 -> changeType.insert(0, "B");
                    case 12 -> changeType.insert(0, "C");
                    case 13 -> changeType.insert(0, "D");
                    case 14 -> changeType.insert(0, "E");
                    case 15 -> changeType.insert(0, "F");
                    default -> changeType.insert(0, dividedNumber);
                }

                for(int x = 0; x < changeType.length(); x++) {
                    if(answer.length() < playCount * peopleCount) {
                        answer = answer.concat(String.valueOf(changeType.charAt(x)));
                    }
                }
            } else {
                switch(dividedNumber) {
                    case 10 -> changeRestType = "A";
                    case 11 -> changeRestType = "B";
                    case 12 -> changeRestType = "C";
                    case 13 -> changeRestType = "D";
                    case 14 -> changeRestType = "E";
                    case 15 -> changeRestType = "F";
                    default -> changeRestType = String.valueOf(dividedNumber);
                }

                if(answer.length() < playCount * peopleCount) {
                    answer = answer.concat(changeRestType);
                }
            }

            if(answer.length() == playCount * peopleCount) {
                flag = false;
            } else {
                value++;
            }
        }

        String myAnswer = "";
        for(int i = myOrder; i <= answer.length(); i += peopleCount) {
            myAnswer = myAnswer.concat(String.valueOf(answer.charAt(i - 1)));
        }

        System.out.println("반환된 결과 : " + myAnswer);
    }

    public static int changeNumberDigitCount(int number, int powNumber) {
        if(number == 0) return 0;
        int count = 0;
        boolean flag = true;
        while(flag) {
            if(Math.pow(powNumber, count) <= number && number < Math.pow(powNumber, count + 1)) {
                flag = false;
            } else {
                count++;
            }
        }

        return count;
    }
}
