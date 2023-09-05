package src.level2;
import java.util.Arrays;
import java.util.Scanner;

public class BinaryConvertRepeat { //이진 변환 반복하기
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("0과 1만으로 이루어진 문자열을 입력하세요 >>> ");
        String sentence = scan.next();
        for(int x = 0; x < sentence.length(); x++) {
            if(!(sentence.charAt(x) >= '0' && sentence.charAt(x) <= '1')) {
                System.out.println("0과 1 이외의 다른 숫자 또는 문자를 입력하였습니다. 처음부터 다시 입력해주세요,");
                System.exit(0);
            }
        }

        System.out.println("반환된 배열 : " + Arrays.toString(returnResultArray(sentence)));
    }

    public static int[] returnResultArray(String sentence) {
        int[] resultArray = new int[2];
        boolean flag = true;
        int repeatCount = -1;
        int removeZeroCount = 0;
        String changeSentence = sentence;
        while(flag) {
            repeatCount++;
            if(changeSentence.equals("1")) flag = false;
            int check = 0;
            for(int x = 0; x < changeSentence.length(); x++) {
                if(String.valueOf(changeSentence.charAt(x)).equals("0")) removeZeroCount++;
                else check++;
            }

            changeSentence = Integer.toBinaryString(check);
        }

        resultArray[0] = repeatCount;
        resultArray[1] = removeZeroCount;
        return resultArray;
    }
}
