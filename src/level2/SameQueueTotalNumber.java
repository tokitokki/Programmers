package src.level2;

import java.util.*;

public class SameQueueTotalNumber { //두 큐 합 같게 만들기
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("공통으로 설정할 큐의 길이를 입력하세요 (1부터 30만 까지 자연수 중 하나로 입력) >>> ");
        int queueLength = 0;
        try {
            queueLength = scan.nextInt();
            if (queueLength <= 0 || queueLength > 300000) {
                System.out.println("범위에 해당되지 않는 정수를 입력하였습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }

        } catch (Exception e) {
            System.out.println("문자를 포함하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }

        System.out.println("두 큐 각각의 길이 : " + queueLength);

        Queue<Long> firstQueue = new LinkedList<>();
        Queue<Long> secondQueue = new LinkedList<>();

        for (int x = 0; x < queueLength; x++) {
            System.out.println("첫번째 큐에 추가할 " + (x + 1) + "번째 값을 입력하세요 (1 이상 10억 이하의 자연수 중 하나로 입력) >>> ");
            long queueValue = 0;
            try {
                queueValue = scan.nextLong();
                if (queueValue <= 0 || queueValue > 1000000000) {
                    System.out.println("범위에 해당되지 않는 정수를 입력하였습니다. 다시 입력해주세요.");
                    x--;
                    continue;
                }
            } catch (Exception e) {
                System.out.println("문자를 포함하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }

            System.out.println("첫번째 큐에 추가할 " + (x + 1) + "번째 값 : " + queueValue);
            firstQueue.add(queueValue);
        }

        System.out.println("첫 번째 큐의 원소의 값들을 나타낸 배열 : " + firstQueue);

        for (int x = 0; x < queueLength; x++) {
            System.out.println("두번째 큐에 추가할 " + (x + 1) + "번째 값을 입력하세요 (1 이상 10억 이하의 자연수 중 하나로 입력) >>> ");
            long queueValue = 0;
            try {
                queueValue = scan.nextLong();
                if (queueValue <= 0 || queueValue > 1000000000) {
                    System.out.println("범위에 해당되지 않는 정수를 입력하였습니다. 다시 입력해주세요.");
                    x--;
                    continue;
                }
            } catch (Exception e) {
                System.out.println("문자를 포함하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }

            System.out.println("두번째 큐에 추가할 " + (x + 1) + "번째 값 : " + queueValue);
            secondQueue.add(queueValue);
        }

        System.out.println("두 번째 큐의 원소의 값들을 나타낸 배열 : " + secondQueue);
        System.out.println("반환값 : " + caseCount(queueLength, firstQueue, secondQueue));
    }

    public static int caseCount(int queueLength, Queue<Long> firstQueue, Queue<Long> secondQueue) {
        int answer = -1;
        Queue<Long> first;
        Queue<Long> second;

        for(int x = 0; x <= queueLength; x++) {
            first = new LinkedList<>(firstQueue);
            second = new LinkedList<>(secondQueue);
            if(x != 0) {
                for(int i = 1; i <= x; i++) {
                    if(!second.isEmpty()) {
                        long value = second.poll();
                        first.add(value);
                    }
                }
            }

            Queue<Long> firstChangeQueue;
            Queue<Long> secondChangeQueue;

            for(int y = 0; y <= first.size(); y++) {
                int count = x;
                firstChangeQueue = new LinkedList<>(first);
                secondChangeQueue = new LinkedList<>(second);
                if(y != 0) {
                    for(int i = 1; i <= y; i++) {
                        if(!firstChangeQueue.isEmpty()) {
                            long value = firstChangeQueue.poll();
                            secondChangeQueue.add(value);
                            count++;
                        }
                    }
                }

                long firstQueueTotal;
                long secondQueueTotal;
                if(firstChangeQueue.isEmpty()) firstQueueTotal = 0;
                else {
                    firstQueueTotal = 0;
                    for(long value : firstChangeQueue) {
                        firstQueueTotal += value;
                    }
                }

                if(secondChangeQueue.isEmpty()) secondQueueTotal = 0;
                else {
                    secondQueueTotal = 0;
                    for(long value : secondChangeQueue) {
                        secondQueueTotal += value;
                    }
                }

                if(!(x == queueLength && y == queueLength)) {
                    if(firstQueueTotal == secondQueueTotal) {
                        if(answer == -1) answer = count;
                        else answer = Math.min(answer, count);
                    }
                }
            }
        }

        return answer;
    }
}
