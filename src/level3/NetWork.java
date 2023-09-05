package src.level3;

import java.util.*;

public class NetWork { // 네트워크
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("컴퓨터의 대수를 입력하세요 (1 이상 200 이하의 자연수 중 하나 입력) >>> ");
        String computers = scan.nextLine();
        int computerCount = 0;

        try {
            computerCount = Integer.parseInt(computers);
            if(computerCount < 1 || computerCount > 200) {
                System.out.println("범위를 초과하였습니다. 처음부터 다시 입력하세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("잘못 입력하였습니다. 처음부터 다시 입력하세요.");
            System.exit(0);
        }
        System.out.println("컴퓨터는 " + computerCount + "대가 있습니다.");
        System.out.println();

        int[][] connected = new int[computerCount][computerCount];
        for(int a = 0; a < connected.length; a++) {
            connected[a][a] = 1;
            if(a < connected.length - 1) {
                for(int b = a + 1; b <= connected.length - 1; b++) {
                    System.out.println((a + 1) + "번 네트워크와 " + (b + 1) + "번 네트워크는 서로 연결되어 있나요? >>> ");
                    System.out.println("연결되면 1, 연결되지 않으면 0을 입력하세요");

                    String connectYn = scan.nextLine();
                    int changeTypeYn;
                    try {
                        changeTypeYn = Integer.parseInt(connectYn);
                        if(!(changeTypeYn == 0 || changeTypeYn == 1)) {
                            System.out.println("0 또는 1의 숫자를 입력하세요.");
                            System.out.println();
                            b--;
                            continue;
                        }
                    } catch(Exception e) {
                        System.out.println("잘못 입력하였습니다. 다시 입력하세요.");
                        System.out.println();
                        b--;
                        continue;
                    }

                    if(changeTypeYn == 1) System.out.println((a + 1) + "번 네트워크와 " + (b + 1) + "번 네트워크는 연결되어 있습니다.");
                    else System.out.println((a + 1) + "번 네트워크와 " + (b + 1) + "번 네트워크는 연경이 안 되어 있습니다.");
                    System.out.println();

                    connected[a][b] = changeTypeYn;
                    connected[b][a] = changeTypeYn;
                }
            }
        }

        if(connected.length > 1) System.out.println("네트워크 연결 확인이 모두 끝났습니다.");
        System.out.println("네트워크의 수 : " + netWorkCounts(connected));
    }

    public static int netWorkCounts(int[][] lan) {
        if(lan.length == 1) return 1;

        HashMap<Integer, ArrayList<Integer>> netWorks = new HashMap<>();
        for(int x = 1; x <= lan.length; x++) {
            ArrayList<Integer> network = new ArrayList<>();
            network.add(x);
            netWorks.put(x, network);
        }

        for(int x = 1; x <= lan.length; x++) {
            for(int y = x + 1; y <= lan.length; y++) {
                if(lan[x - 1][y - 1] == 1) {
                    ArrayList<Integer> listA = netWorks.get(x);
                    ArrayList<Integer> listB = netWorks.get(y);
                    for(int a : listA) {
                        if(!listB.contains(a)) listB.add(a);
                    }

                    Collections.sort(listB);
                    for(int b : listB) {
                        netWorks.put(b, listB);
                    }
                }
            }
        }

        ArrayList<ArrayList<Integer>> listCount = new ArrayList<>();
        for(int a : netWorks.keySet()) {
            if(listCount.isEmpty()) listCount.add(netWorks.get(a));
            else {
                boolean duplicate = false;
                for(ArrayList<Integer> list : listCount) {
                    if(equals(netWorks.get(a), list)) {
                        duplicate = true;
                        break;
                    }
                }

                if(!duplicate) listCount.add(netWorks.get(a));
            }
        }

        return listCount.size();
    }

    public static boolean equals(ArrayList<Integer> A, ArrayList<Integer> B) {
        if(A.size() != B.size()) return false;
        int correct = 0;
        for(int a : A) {
            if(B.contains(a)) correct++;
        }

        return correct == B.size();
    }
}