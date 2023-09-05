package src.level2;

import java.util.*;

public class RicoChatRobot { // 리코챗 로봇

    public static char[][] points = new char[0][];
    public static boolean[][] visited;
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("보드판의 가로 길이를 입력하세요 (1부터 100까지 자연수 중 하나 입력) >>> ");
        String width = scan.nextLine();
        int changeTypeWidth = 0;
        try {
            changeTypeWidth = Integer.parseInt(width);
            if(changeTypeWidth < 1 || changeTypeWidth > 100) {
                System.out.println("범위가 잘못되었습니다. 처음부터 다시 입력하세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 입력하세요.");
            System.exit(0);
        }
        System.out.println();

        System.out.println("보드판의 세로 길이를 입력하세요 (1부터 100까지 자연수 중 하나 입력) >>> ");
        String height = scan.nextLine();
        int changeTypeHeight = 0;
        try {
            changeTypeHeight = Integer.parseInt(height);
            if(changeTypeHeight < 1 || changeTypeHeight > 100) {
                System.out.println("범위가 잘못되었습니다. 처음부터 다시 입력하세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 입력하세요.");
            System.exit(0);
        }
        System.out.println("보드판의 가로 길이 : " + changeTypeWidth + ", 세로 길이 : " + changeTypeHeight);
        System.out.println();

        points = new char[changeTypeHeight][changeTypeWidth];
        boolean startLine = false;
        boolean goalLine = false;

        ArrayList<Character> places = new ArrayList<>();
        places.add('.');
        places.add('D');
        places.add('R');
        places.add('G');

        for(int x = 0; x < points.length; x++) {
            for(int y = 0; y < points[x].length; y++) {
                System.out.println((x + 1) + "행 " + (y + 1) + "번째 열에 설정할 좌표를 다음 보기 중에 하나 입력하세요.");
                System.out.println("[빈 공간 : '.']");
                System.out.println("[장애물 : 'D']");
                if(!startLine) System.out.println("[시작 지점 : 'R']");
                if(!goalLine) System.out.println("[종료 지점 : 'G']");

                String place = scan.next();
                char changeTypePlace = place.charAt(0);

                boolean complete = false;
                for(char word : places) {
                    if(word == changeTypePlace) {
                        complete = true;
                        break;
                    }
                }

                if(!complete) {
                    System.out.println("제시된 좌표 값만 입력해주세요.");
                    System.out.println();
                    y--;
                    continue;
                }

                if(changeTypePlace == 'R') {
                    places.remove(Character.valueOf('R'));
                    startLine = true;
                }

                if(changeTypePlace == 'G') {
                    places.remove(Character.valueOf('G'));
                    goalLine = true;
                }

                System.out.println("입력한 좌표 값 : " + changeTypePlace);
                System.out.println();
                points[x][y] = changeTypePlace;
            }
        }

        System.out.println("-----보드 맵-----");
        for(char[] point : points) {
            System.out.println(Arrays.toString(point));
        }
        System.out.println();

        if(!startLine) {
            System.out.println("시작 지점이 없습니다. 처음부터 다시 입력하세요.");
            System.exit(0);
        }

        if(!goalLine) {
            System.out.println("종료 지점이 없습니다. 처음부터 다시 입력하세요.");
            System.exit(0);
        }

        System.out.println("최소 이동 횟수 (없으면 -1을 반환) : " + minValue(points));
    }

    public static Position position(int index, Position now) {
        int x = now.x;
        int y = now.y;
        int ix = dx[index];
        int iy = dy[index];
        while(x + ix >= 0 && x + ix < points.length
                && y + iy >= 0 && y + iy < points[0].length
                && points[x + ix][y + iy] != 'D')
        {
            x += ix;
            y += iy;
        }

        return new Position(x, y, now.level + 1);
    }

    public static int bfs(Position start, Position goal) {
        Queue<Position> q = new LinkedList<>();
        visited[start.x][start.y] = true;
        q.add(start);
        while(!q.isEmpty()) {
            Position now = q.poll();
            int x = now.x;
            int y = now.y;
            int level = now.level;
            if(x == goal.x && y == goal.y)
                return level;
            for(int i = 0; i < 4; i++) {
                Position next = position(i, now);
                if(!visited[next.x][next.y]) {
                    visited[next.x][next.y] = true;
                    q.add(next);
                }
            }
        }
        return -1;
    }

    public static int minValue(char[][] board) {
        int answer;
        points = new char[board.length][board[0].length];
        visited = new boolean[points.length][points[0].length];
        Position start = null;
        Position goal = null;
        for(int i = 0; i < points.length; i++) {
            for(int j = 0; j < points[0].length; j++) {
                points[i][j] = board[i][j];
                if(points[i][j] == 'R')
                    start = new Position(i, j, 0);
                if(points[i][j] == 'G')
                    goal = new Position(i, j, 0);
            }
        }

        assert start != null;
        answer = bfs(start, goal);
        return answer;
    }
}

class Position {
    int x;
    int y;
    int level;
    Position(int x, int y, int level) {
        this.x = x;
        this.y = y;
        this.level = level;
    }
}