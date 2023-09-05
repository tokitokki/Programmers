package src.level2;
import java.util.Scanner;

public class Hindex { //H-index

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("정하고 싶은 배열의 길이 개수를 입력하세요 >>> ");
        String words = scan.next();
        int length = 0;
        try {
            length = Integer.parseInt(words);

            if(length <= 0 || length > 1000) {
                System.out.println("1000 이하의 자연수 값을 입력하지 않았습니다. 처음부터 다시 시도하세요");
                System.exit(0);
            }

        } catch(Exception e) {
            System.out.println("자연수 값을 입력하지 않았습니다.");
            System.exit(0);
        }

        int[] arrays = new int[length];
        for(int x = 0; x < arrays.length; x++) {
            System.out.println("계산하고 싶은 음이 아닌 " + (x + 1) + "번째 정수를 입력하세요 >>> ");
            String answer = scan.next();
            int number = 0;
            try {
                number = Integer.parseInt(answer);

                if(number < 0 || number > 10000) {
                    System.out.println("0 또는 10000 이하의 자연수 값을 입력하세요 >>> ");
                    x--;
                    continue;
                }
            } catch (Exception e) {
                System.out.println("0 또는 자연수 값을 입력하세요");
                x--;
                continue;
            }

            arrays[x] = number;
            System.out.println("입력한 " + (x + 1) + "번째 수 : " + number);
        }

        for(int x = 0; x < arrays.length; x++) {
            if(arrays.length == 1) System.out.println("입력한 배열 : [" + arrays[x] + "]");
            else {
                if (x == 0) {
                    System.out.print("입력한 배열 : [" + arrays[x]);
                } else if (x == arrays.length - 1) {
                    System.out.println(", " + arrays[x] + "]");
                } else {
                    System.out.print(", " + arrays[x]);
                }
            }
        }

        System.out.println("해당 배열의 H-index 값 : " + h_index(arrays));
    }

    public static int h_index(int[] arr) {
        int result = 0;
        int count;
        for(int x = 1; x <= 10000; x++) {
            count = 0;
            for(int y = 0; y < arr.length; y++) {
                if(arr[y] >= x) count++;
            }

            if(count < x) {
                result = x - 1;
                break;
            }
        }

        return result;
    }
}
