package src.level3;

import java.util.*;

public class TheFurthestNode { // 가장 먼 노드
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("노드의 개수를 입력하세요 (2 이상 20000 이하의 자연수 중 하나 입력)");

        String nodes = scan.nextLine();
        int changeTypeNodes = 0;

        try {
            changeTypeNodes = Integer.parseInt(nodes);
            if(changeTypeNodes < 2 || changeTypeNodes > 20000) {
                System.out.println("노드의 범위가 잘못되었습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("잘못 입력하셨습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }

        System.out.println("노드의 개수 : " + changeTypeNodes);
        System.out.println();

        ArrayList<int[]> arteries = new ArrayList<>();
        while(true) {
            System.out.println("간선의 시작 지점의 번호를 입력하세요 (종료하려면 x를 입력하세요) >>> ");
            String answer = scan.nextLine();

            if(answer.equalsIgnoreCase("x")) {
                System.out.println("입력을 종료합니다.");
                System.out.println();
                break;
            }

            int changeTypeAnswer;
            try {
                changeTypeAnswer = Integer.parseInt(answer);
                if(changeTypeAnswer < 1 || changeTypeAnswer > changeTypeNodes) {
                    System.out.println("간선의 번호가 잘못되었습니다. 다시 입력해주세요.");
                    continue;
                }
            } catch(Exception e) {
                System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
                continue;
            }

            System.out.println("간선의 끝 지점의 번호를 입력하세요 >>> ");
            String endAt = scan.next();
            int changeTypeEndAt;
            try {
                changeTypeEndAt = Integer.parseInt(endAt);
                if(changeTypeEndAt < 1 || changeTypeEndAt > changeTypeNodes) {
                    System.out.println("간선의 번호가 잘못되었습니다. 간선의 시작 지점부터 다시 입력해주세요.");
                    System.out.println();
                    continue;
                }

                if(changeTypeEndAt == changeTypeAnswer) {
                    System.out.println("시작 지점과 끝 지점이 같습니다. 간선의 시작 지점부터 다시 입력해주세요.");
                    System.out.println();
                    continue;
                }
            } catch(Exception e) {
                System.out.println("잘못 입력하셨습니다. 간선의 시작 지점부터 다시 입력해주세요.");
                System.out.println();
                continue;
            }

            int[] line = new int[2];
            if(changeTypeAnswer > changeTypeEndAt) {
                line[0] = changeTypeEndAt;
                line[1] = changeTypeAnswer;
            } else {
                line[0] = changeTypeAnswer;
                line[1] = changeTypeEndAt;
            }

            boolean pass = true;
            for(int[] checkLine : arteries) {
                if(checkLine[0] == line[0] && checkLine[1] == line[1]) {
                    pass = false;
                    break;
                }
            }

            if(!pass) {
                System.out.println("해당 지점을 연결하는 간선이 이미 있습니다. 간선의 시작 지점부터 다시 입력해주세요.");
                System.out.println();
                continue;
            }

            System.out.println("간선의 연결 지점을 나타내는 배열 : " + Arrays.toString(line));
            System.out.println();
            arteries.add(line);
        }

        if(arteries.isEmpty()) {
            System.out.println("간선을 하나 이상 입력해주세요.");
            System.exit(0);
        }

        if(arteries.size() > 50000) {
            System.out.println("간선의 범위를 초과하였습니다. 처음부터 다시 입력하세요.");
            System.exit(0);
        }

        System.out.print("간선들의 지점 : ");
        for(int[] line : arteries) {
            System.out.print(Arrays.toString(line) + " ");
        }
        System.out.println();

        System.out.println("가장 멀리 떨어진 노드의 개수 : " + nodeCounts(arteries));
    }

    public static int nodeCounts(ArrayList<int[]> arrayList) {
        int nodeCount = 0;
        int maxNumber = Integer.MIN_VALUE;
        for(int[] line : arrayList) {
            for(int number : line) {
                maxNumber = Math.max(maxNumber, number);
            }
        }

        HashMap<Integer, Integer> lineLength = new HashMap<>();
        for(int x = 2; x <= maxNumber; x++) {
            int length = 1;
            ArrayList<int[]> selectLine = new ArrayList<>();
            while(true) {
                boolean matched = false;
                if(length == 1) {
                    for(int[] line : arrayList) {
                        if(line[0] == 1) {
                            selectLine.add(line);
                        }
                    }

                    if(selectLine.isEmpty()) {
                        lineLength.put(x, 0);
                        break;
                    } else{
                        for(int[] line : selectLine) {
                            if(line[0] == 1 && line[1] == x) {
                                matched = true;
                                break;
                            }
                        }
                    }

                    if(matched) {
                        lineLength.put(x, length);
                        break;
                    } else length++;

                } else {
                    ArrayList<int[]> nextSelectLine = new ArrayList<>();
                    ArrayList<Integer> nextNumber = new ArrayList<>();
                    for(int[] line : arrayList) {
                        if(!nextNumber.contains(line[1])) nextNumber.add(line[1]);
                    }

                    for(int[] line : arrayList) {
                        for(int number : nextNumber) {
                            if(line[0] == number) nextSelectLine.add(line);
                        }
                    }

                    if(nextSelectLine.isEmpty()) {
                        lineLength.put(x, 0);
                        break;
                    } else {
                        for(int[] line : nextSelectLine) {
                            for(int number : nextNumber) {
                                if(line[0] == number && line[1] == x) {
                                    matched = true;
                                    break;
                                }
                            }

                            if (matched) break;
                        }
                    }

                    if(matched) {
                        lineLength.put(x, length);
                        break;
                    } else {
                        selectLine = nextSelectLine;
                        length++;
                    }
                }
            }
        }

        int maxLength = Collections.max(lineLength.values());
        for(int nodeNumber : lineLength.keySet()) {
            if(lineLength.get(nodeNumber) == maxLength) nodeCount++;
        }

        return nodeCount;
    }
}