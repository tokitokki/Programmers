package src.level2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class functionDevelopment { //기능 개발
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("배포하려는 파일의 개수를 입력하세요 (1000개까지 제한) >>> ");
        String files = scan.next();
        int changeTypeFiles;
        try {
            changeTypeFiles = Integer.parseInt(files);
            if(changeTypeFiles <= 0) {
                System.out.println("자연수가 아닌 수를 입력하였습니다. 처음부터 다시 입력하세요");
                System.exit(0);
            }

            if(changeTypeFiles > 1000) {
                System.out.println("1000이 넘는 값을 입력하였습니다. 처음부터 다시 입력하세요.");
                System.exit(0);
            }

            int[] progresses = new int[changeTypeFiles];
            int[] speeds = new int[changeTypeFiles];
            for(int x = 0; x < changeTypeFiles; x++) {
                System.out.println("배포하려는 " + (x + 1) +"번째 파일의 진행률을 입력하세요 (1부터 99까지의 자연수 중 하나 입력) >>> ");
                String inputProgress = scan.next();
                int progress;
                try {
                    progress = Integer.parseInt(inputProgress);
                    if(progress <= 0 || progress >= 100) {
                        System.out.println("1부터 99까지의 자연수를 입력해 주세요.");
                        x--;
                        continue;
                    }
                } catch(Exception e) {
                    System.out.println("문자열을 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 다시 입력하세요.");
                    x--;
                    continue;
                }

                System.out.println((x + 1) + "번째 파일의 진행 속도를 입력하세요 (1부터 100까지의 자연수 중 하나 입력) >>> ");
                String inputSpeed = scan.next();
                int speed;
                try {
                    speed = Integer.parseInt(inputSpeed);
                    if(speed <= 0 || speed > 100) {
                        System.out.println("1부터 100까지의 자연수를 입력해 주세요.");
                        x--;
                        continue;
                    }

                } catch(Exception e) {
                    System.out.println("문자열을 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 다시 입력하세요.");
                    x--;
                    continue;
                }

                System.out.println("대입한 " + (x + 1) + "번째 파일의 진행률 : " + progress + "%");
                System.out.println("대입한 " + (x + 1) + "번째 파일의 개발 진행 속도 : 하루당 " + speed + "%");
                progresses[x] = progress;
                speeds[x] = speed;
            }

            System.out.println("각 파일들의 진행률(%) : " + Arrays.toString(progresses));
            System.out.println("각 파일들의 하루당 개발 진행 속도(%) : " + Arrays.toString(speeds));
            int submitFile = 0;
            int submitFileToday;
            int finish;
            int alreadySubmit;
            ArrayList<Integer> submitFileCountPerDay = new ArrayList<>();
            boolean flag = true;
            while(flag) {
                submitFileToday = 0;
                for(int x = 0; x < changeTypeFiles; x++) {
                    if(progresses[x] > 0 && progresses[x] < 100) {
                        progresses[x] += speeds[x];
                        if(progresses[x] > 100) {
                            progresses[x] = 100;
                        }
                    }
                }

                for(int x = 0; x < changeTypeFiles; x++) {
                    alreadySubmit = 0;
                    finish = 0;
                    if(progresses[x] == 100) {
                        for(int y = 0; y <= x; y++) {
                            if(progresses[y] == 0) alreadySubmit++;
                            if(progresses[y] == 100) finish++;
                        }

                        if(alreadySubmit + finish == x + 1) {
                            submitFileToday++;
                            progresses[x] = 0;

                            if(x != changeTypeFiles - 1) {
                                for (int y = x + 1; y <= changeTypeFiles - 1; y++) {
                                    if (progresses[y] != 100) {
                                        break;
                                    } else {
                                        submitFileToday++;
                                        progresses[y] = 0;
                                    }
                                }
                            }
                        }

                        break;
                    }
                }

                if(submitFileToday > 0) {
                    submitFileCountPerDay.add(submitFileToday);
                    submitFile += submitFileToday;
                }

                if(submitFile == changeTypeFiles) {
                    flag = false;
                }
            }

            System.out.println("반환된 배열 : " + submitFileCountPerDay);

        } catch(Exception e) {
            System.out.println("문자를 포함해서 입력하였습니다. 처음부터 다시 입력하세요.");
            System.exit(0);
        }
    }
}
