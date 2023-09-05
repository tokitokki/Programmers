package src.level3;

import java.util.ArrayList;
import java.util.Scanner; 

public class ExpressionN { // N으로 표현
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("연산에 사용할 한 자리 자연수를 입력하세요. >>> ");
        String number = scan.nextLine();
        int changeTypeNumber = 0;

        try {
            changeTypeNumber = Integer.parseInt(number);
            if(changeTypeNumber < 1 || changeTypeNumber > 9) {
                System.out.println("한 자리 자연수를 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("잘못 입력하셨습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }
        System.out.println();

        System.out.println("어떤 값이 나오도록 연산을 할까요? (1 이상 32000 이하의 자연수 중 하나 입력)");
        String answer = scan.nextLine();
        int changeTypeAnswer = 0;

        try {
            changeTypeAnswer = Integer.parseInt(answer);
            if(changeTypeAnswer < 1 || changeTypeAnswer > 32000) {
                System.out.println("범위가 잘못되었습니다. 처음부터 다시 입력하세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("잘못 입력하셨습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }
        System.out.println();

        System.out.println("연산에 사용되는 자연수 : " + changeTypeNumber + ", 도출할 값 : " + changeTypeAnswer);
        System.out.println("결과값 (최소 연산 횟수가 9회 이상이면 -1) : " + minNumberCount(changeTypeNumber, changeTypeAnswer));
    }

    public static int minNumberCount(int number, int answer) {
        int numberCount = -1;
        ArrayList<Calculation> calculates = new ArrayList<>();
        ArrayList<Calculation> answers = new ArrayList<>();
        for(int x = 1; x <= 8; x++) {
            String stringNumber = "";
            for(int y = 1; y <= x; y++) {
                stringNumber = stringNumber.concat(String.valueOf(number));
            }

            Calculation calculateResult = new Calculation(Integer.parseInt(stringNumber), stringNumber, x);
            calculates.add(calculateResult);
        }

        ArrayList<Calculation> results = new ArrayList<>();
        for(Calculation calculateX : calculates) {
            for(Calculation calculateY : calculates) {
                if(calculateX.numberCount + calculateY.numberCount <= 8) {
                    Calculation result = new Calculation();
                    result.value = calculateX.value + calculateY.value;
                    result.explanation = "(" + calculateX.explanation + " + " + calculateY.explanation + ")";
                    result.numberCount = calculateX.numberCount + calculateY.numberCount;
                    results.add(result);

                    result = new Calculation();
                    result.value = calculateX.value - calculateY.value;
                    result.explanation = "(" + calculateX.explanation + " - " + calculateY.explanation + ")";
                    result.numberCount = calculateX.numberCount + calculateY.numberCount;
                    results.add(result);

                    result = new Calculation();
                    result.value = calculateX.value * calculateY.value;
                    result.explanation = "(" + calculateX.explanation + " × " + calculateY.explanation + ")";
                    result.numberCount = calculateX.numberCount + calculateY.numberCount;
                    results.add(result);

                    try {
                        result = new Calculation();
                        result.value = calculateX.value / calculateY.value;
                        result.explanation = "(" + calculateX.explanation + " ÷ " + calculateY.explanation + ")";
                        result.numberCount = calculateX.numberCount + calculateY.numberCount;
                        results.add(result);
                    } catch(Exception ignored) {}
                }
            }
        }

        for(Calculation result : results) {
            if(result.value == answer) {
                answers.add(result);
            }
        }

        while(true) {
            ArrayList<Calculation> nextResults = new ArrayList<>();
            for(Calculation calculateX : results) {
                for(Calculation calculateY : calculates) {
                    if(calculateX.numberCount + calculateY.numberCount <= 8) {
                        Calculation result = new Calculation();
                        result.value = calculateX.value + calculateY.value;
                        result.explanation = "(" + calculateX.explanation + " + " + calculateY.explanation + ")";
                        result.numberCount = calculateX.numberCount + calculateY.numberCount;
                        nextResults.add(result);

                        result = new Calculation();
                        result.value = calculateX.value - calculateY.value;
                        result.explanation = "(" + calculateX.explanation + " - " + calculateY.explanation + ")";
                        result.numberCount = calculateX.numberCount + calculateY.numberCount;
                        nextResults.add(result);

                        result = new Calculation();
                        result.value = calculateX.value * calculateY.value;
                        result.explanation = "(" + calculateX.explanation + " × " + calculateY.explanation + ")";
                        result.numberCount = calculateX.numberCount + calculateY.numberCount;
                        nextResults.add(result);

                        try {
                            result = new Calculation();
                            result.value = calculateX.value / calculateY.value;
                            result.explanation = "(" + calculateX.explanation + " ÷ " + calculateY.explanation + ")";
                            result.numberCount = calculateX.numberCount + calculateY.numberCount;
                            nextResults.add(result);
                        } catch(Exception ignored) {}
                    }
                }
            }

            if(nextResults.isEmpty()) break;

            for(Calculation result : nextResults) {
                if(result.value == answer) {
                    answers.add(result);
                }
            }

            results = nextResults;
        }

        String explain = "";
        for(Calculation correctAnswer : answers) {
            if(numberCount == -1) {
                numberCount = correctAnswer.numberCount;
                explain = correctAnswer.explanation;
            } else {
                if(numberCount > correctAnswer.numberCount) {
                    explain = correctAnswer.explanation;
                }
                numberCount = Math.min(numberCount, correctAnswer.numberCount);
            }
        }

        if(numberCount > -1) System.out.println(explain + " = " + answer);
        return numberCount;
    }
}

class Calculation {
    int value;
    String explanation;
    int numberCount;

    Calculation() {}

    Calculation(int x, String y, int z) {
        value = x;
        explanation = y;
        numberCount = z;
    }

    public String toString() {
        return "[" + value + ", " + explanation + ", " + numberCount + "]";
    }
}