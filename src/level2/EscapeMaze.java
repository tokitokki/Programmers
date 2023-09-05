package src.level2;

import java.util.*;

public class EscapeMaze { // 미로 탈출

    private static char[][] map;
    private static boolean[][] visited;
    private static Map<Character, int[]> mapInfo;
    private static final int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("미로의 세로 길이를 입력하세요 (5 이상 100 이하의 자연수 중 하나로 입력) >>> ");
        String height = scan.nextLine();
        int changeTypeHeight = 0;
        try {
            changeTypeHeight = Integer.parseInt(height);
            if (changeTypeHeight < 5 || changeTypeHeight > 100) {
                System.out.println("해당되지 않는 범위입니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch (Exception e) {
            System.out.println("잘못 입력하셨습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }
        System.out.println();

        System.out.println("미로의 가로 길이를 입력하세요 (5 이상 100 이하의 자연수 중 하나로 입력) >>> ");
        String width = scan.nextLine();
        int changeTypeWidth = 0;
        try {
            changeTypeWidth = Integer.parseInt(width);
            if (changeTypeWidth < 5 || changeTypeWidth > 100) {
                System.out.println("해당되지 않는 범위입니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch (Exception e) {
            System.out.println("잘못 입력하셨습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }
        System.out.println();

        System.out.println("직사각형 미로의 가로 길이 : " + changeTypeWidth + ", 세로 길이 : " + changeTypeHeight);
        String[][] map = new String[changeTypeHeight][changeTypeWidth];
        boolean startPlace = false;
        boolean endPlace = false;
        boolean leverPlace = false;
        ArrayList<String> keyCheck = new ArrayList<>();
        keyCheck.add("E");
        keyCheck.add("L");
        keyCheck.add("O");
        keyCheck.add("S");
        keyCheck.add("X");
        int keys = 5;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.println("직사각형 미로의 " + (i + 1) + "행 " + (j + 1) + "열을 어떤 칸으로 설정할까요? ");
                System.out.println(keys + "가지 영어 단축키 중 하나를 입력하세요.");
                if (!startPlace) System.out.println("-------[S : 시작 지점]-------");
                if (!endPlace) System.out.println("-------[E : 출구]-------");
                if (!leverPlace) System.out.println("-------[L : 레버]-------");
                System.out.println("-------[O : 통로]-------");
                System.out.println("-------[X : 벽]-------");

                String key = scan.nextLine();

                if (key.length() != 1) {
                    System.out.println("단축키를 제대로 입력해주세요");
                    System.out.println();
                    j--;
                    continue;
                }

                if (key.charAt(0) >= 97 && key.charAt(0) <= 122) {
                    key = key.toUpperCase();
                }

                boolean pass = false;

                System.out.println(key);
                for (String word : keyCheck) {
                    if (word.equals(key)) {
                        pass = true;
                        break;
                    }
                }

                if (!pass) {
                    System.out.println("단축키를 잘못 입력하였습니다.");
                    System.out.println();
                    j--;
                    continue;
                }

                switch (key.charAt(0)) {
                    case 'E' -> {
                        System.out.println((i + 1) + "행 " + (j + 1) + "열 위치에 출구를 설치하였습니다.");
                        System.out.println();
                        keys--;
                        endPlace = true;
                        keyCheck.remove(String.valueOf('E'));
                    }
                    case 'L' -> {
                        System.out.println((i + 1) + "행 " + (j + 1) + "열 위치에 레버를 설치하였습니다.");
                        System.out.println();
                        keys--;
                        leverPlace = true;
                        keyCheck.remove(String.valueOf('L'));
                    }
                    case 'O' -> {
                        System.out.println((i + 1) + "행 " + (j + 1) + "열 위치에 통로를 설치하였습니다.");
                        System.out.println();
                    }
                    case 'S' -> {
                        System.out.println((i + 1) + "행 " + (j + 1) + "열 위치에 시작 지점을 설치하였습니다.");
                        System.out.println();
                        keys--;
                        startPlace = true;
                        keyCheck.remove(String.valueOf('S'));
                    }
                    default -> {
                        System.out.println((i + 1) + "행 " + (j + 1) + "열 위치에 벽을 설치하였습니다.");
                        System.out.println();
                    }
                }

                map[i][j] = key;
            }
        }

        System.out.println("-------[맵 구조]-------");
        for(String[] line : map) {
            System.out.println(Arrays.toString(line));
        }

        if(!startPlace) {
            System.out.println("시작 지점을 입력하지 않았습니다. 처음부터 다시 입력하세요.");
            System.exit(0);
        } else if(!endPlace) {
            System.out.println("출구를 입력하지 않았습니다. 처음부터 다시 입력하세요.");
            System.exit(0);
        } else if(!leverPlace) {
            System.out.println("레버를 입력하지 않았습니다. 처음부터 다시 입력하세요.");
            System.exit(0);
        }

        System.out.println("걸리는 최소 시간 (탈출할 수 없다면 -1을 반환) : " + minSeconds(map));
    }

    public static int minSeconds(String[][] maps) {
        int answer = 0;
        map = new char[maps.length][maps[0].length];

        mapInfo = new HashMap<>();

        //Data를 Map으로 변환
        for(int r = 0; r < maps.length; r++) {
            for(int c = 0; c < maps[r].length; c++) {
                char data = maps[r][c].charAt(0);

                if(data == 'S') {
                    mapInfo.put('S', new int[]{r, c});
                } else if(data == 'L') {
                    mapInfo.put('L', new int[]{r, c});
                } else if(data == 'E') {
                    mapInfo.put('E', new int[]{r, c});
                }

                map[r][c] = data;
            }
        }

        //S에서 L을 찾는 과정
        visited = new boolean[maps.length][maps[0].length];
        int rtval = bfs('S', 'L');

        //L을 못 찾았으면 그대로 종료
        if(rtval == 0) return -1;
        answer += rtval;

        //L에서 E를 찾는 과정
        visited = new boolean[maps.length][maps[0].length];
        rtval = bfs('L', 'E');

        //E를 못 찾았으면 그대로 종료
        if (rtval == 0) return -1;
        answer += rtval;

        return answer;
    }

    private static int bfs(char startChar, char targetChar) {
        Deque<int[]> deque = new ArrayDeque<>();
        int[] info = mapInfo.get(startChar);
        deque.add(new int[]{info[0], info[1], 0});

        while (!deque.isEmpty()) {
            int[] tempInfo = deque.poll();
            int r = tempInfo[0];
            int c = tempInfo[1];
            int moveCount = tempInfo[2];

            if (map[r][c] == targetChar) {
                return moveCount;
            }

            visited[r][c] = true;

            for (int[] direction : directions) {
                int newR = r + direction[0];
                int newC = c + direction[1];

                if (isOutOfBound(newR, newC) || map[newR][newC] == 'X')
                    continue;


                if (!visited[newR][newC]) {
                    visited[newR][newC] = true;
                    deque.add(new int[]{newR, newC, moveCount + 1});
                }
            }
        }

        return 0;
    }

    private static boolean isOutOfBound(int r, int c) {
        return r < 0 || r >= map.length || c < 0 || c >= map[0].length;
    }
}
