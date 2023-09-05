package src.level2;
import java.util.*;

public class ElectricalGridDivide { //전력망을 둘로 나누기
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("송전탑의 개수를 입력하세요 (2 이상 100 이하의 자연수 입력) >>> ");
        int answer = 0;
        try {
            answer = scan.nextInt();
            if(answer <= 1 || answer > 100) {
                System.out.println("송전탑의 개수를 잘못 입력하였습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("문자를 포함해서 입력하였습니다. 처음부터 다시 입력하세요.");
            System.exit(0);
        }

        int[][] wires = new int[answer - 1][2];
        boolean flag = true;
        while(flag) {
            for(int x = 0; x < answer - 1; x++) {
                System.out.println((x + 1) + "번째 전력망을 연결할 시작점의 위치 번호를 입력하세요 (번호 : 1번부터 전력망의 최대 개수 번호까지) >>> ");
                int startNumber;
                try {
                    startNumber = scan.nextInt();
                } catch (Exception e) {
                    System.out.println("문자를 포함해서 입력하였습니다. 다시 입력하세요.");
                    x--;
                    continue;
                }

                if (startNumber > answer || startNumber <= 0) {
                    System.out.println("시작점의 위치 번호를 잘못 입력하였습니다. 다시 입력하세요.");
                    x--;
                    continue;
                }

                System.out.println((x + 1) + "번째 전력망을 연결할 끝점의 위치 번호를 입력하세요 (1번부터 전력망의 최대 개수 번호까지의 번호들 중에 시작점보다 더 큰 번호를 입력하세요.");
                int endNumber;
                try {
                    endNumber = scan.nextInt();
                } catch (Exception e) {
                    System.out.println("문자를 포함해서 입력하였습니다. 다시 입력하세요.");
                    x--;
                    continue;
                }

                if (endNumber > answer || endNumber <= 0) {
                    System.out.println("끝의 위치 번호를 잘못 입력하였습니다. 다시 입력하세요.");
                    x--;
                    continue;
                }

                if (startNumber >= endNumber) {
                    System.out.println("끝점의 번호가 시작점의 번호보다 작거나 같을 수 없습니다. 다시 입력하세요.");
                    x--;
                    continue;
                }

                if(x != 0) {
                    int duplicate = 0;
                    for(int y = 0; y < x; y++) {
                        if(wires[y][0] == startNumber && wires[y][1] == endNumber) duplicate++;
                    }

                    if(duplicate > 0) {
                        System.out.println("이미 전력망을 연결한 구간입니다. 다시 입력하세요.");
                        x--;
                        continue;
                    }
                }

                wires[x][0] = startNumber;
                wires[x][1] = endNumber;
                System.out.println("전력망의 시작점과 끝점 번호를 나열한 배열 : " + Arrays.toString(wires[x]));
            }

            ArrayList<Integer> connectWireNumber = new ArrayList<>();
            for(int[] wire : wires) {
                if(!connectWireNumber.contains(wire[0])) connectWireNumber.add(wire[0]);
                if(!connectWireNumber.contains(wire[1])) connectWireNumber.add(wire[1]);
            }

            if(connectWireNumber.size() == answer) flag = false;
            else System.out.println("전력망을 하나의 트리로 연결하지 못하였습니다. 첫 번째 전력망부터 다시 입력하세요.");
        }

        System.out.println("전력망의 시작 번호와 끝 번호를 나열한 배열들을 나타내는 배열 : " + Arrays.deepToString(wires));
        System.out.println("반환되는 값 : " + divideElectricalGrid(answer, wires));
    }

    public static int divideElectricalGrid(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        List<List<Integer>> map = new ArrayList<>();

        for(int i = 0; i <= n; i++) {
            map.add(new ArrayList<>());
        }

        for(int[] wire : wires) {
            map.get(wire[0]).add(wire[1]);
            map.get(wire[1]).add(wire[0]);
        }

        for(int[] wire : wires) {
            map.get(wire[0]).remove((Integer) wire[1]);
            map.get(wire[1]).remove((Integer) wire[0]);

            bfs(map, n);
            answer = Math.min(Math.abs(n - bfsNum * 2), answer);
            bfsNum = 1;

            map.get(wire[0]).add(wire[1]);
            map.get(wire[1]).add(wire[0]);
        }

        return answer;
    }

    static int bfsNum = 1;
    static Queue<Integer> queue = new LinkedList<>();

    public static void bfs(List<List<Integer>> map, int n) {
        boolean[] check = new boolean[n + 1];
        check[1] = true;
        queue.add(1);
        while(!queue.isEmpty()) {
            List<Integer> list = map.get(queue.poll());
            for(int i : list) {
                if(!check[i]) {
                    queue.add(i);
                    check[i] = true;
                    bfsNum += 1;
                }
            }
        }
    }
}
