package src.level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class HotelRoom { //호텔 대실
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("예약 손님은 총 몇명입니까? (1 이상 1000 이하의 자연수 중 하나로 입력) >>> ");
        String people = scan.nextLine();
        int parsePeople = 0;
        try {
            parsePeople = Integer.parseInt(people);
            if(parsePeople < 1 || parsePeople > 1000) {
                System.out.println("범위가 잘못되었습니다. 처음부터 다시 입력하세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("잘못 입력하셨습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }
        System.out.println();

        String[][] reservations = new String[parsePeople][2];
        for(int x = 0; x < reservations.length; x++) {
            System.out.println((x + 1) + "번째 손님의 대실 시작 시간은 몇 시입니까?");
            String startTime = "";
            String startHour = scan.nextLine();
            int parseStartHour;
            try {
                parseStartHour = Integer.parseInt(startHour);
                if(parseStartHour < 0 || parseStartHour >= 24) {
                    System.out.println("시간을 잘못 입력하였습니다. 다시 입력해주세요.");
                    System.out.println();
                    x--;
                    continue;
                }
            } catch(Exception e) {
                System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
                System.out.println();
                x--;
                continue;
            }

            if(parseStartHour <= 9) startTime += "0" + parseStartHour;
            else startTime += String.valueOf(parseStartHour);

            System.out.println((x + 1) + "번째 손님의 대실 시작 분은 몇 분입니까?");
            String startMinute = scan.nextLine();
            int parseStartMinute;
            try {
                parseStartMinute = Integer.parseInt(startMinute);
                if(parseStartMinute < 0 || parseStartMinute >= 60) {
                    System.out.println("분을 잘못 입력하였습니다. 시작 시간부터 다시 입력해주세요.");
                    System.out.println();
                    x--;
                    continue;
                }
            } catch(Exception e) {
                System.out.println("잘못 입력하셨습니다. 시작 시간부터 다시 입력해주세요.");
                System.out.println();
                x--;
                continue;
            }

            if(parseStartMinute <= 9) startTime += ":0" + parseStartMinute;
            else startTime += ":" + parseStartMinute;

            String endTime = "";
            System.out.println((x + 1) + "번째 손님의 대실 종료 시간은 몇 시입니까?");
            String endHour = scan.nextLine();
            int parseEndHour;
            try {
                parseEndHour = Integer.parseInt(endHour);
                if(parseEndHour < 0 || parseEndHour >= 24) {
                    System.out.println("시간을 잘못 입력하였습니다. 시작 시간부터 다시 입력해주세요.");
                    System.out.println();
                    x--;
                    continue;
                }
            } catch(Exception e) {
                System.out.println("잘못 입력하셨습니다. 시작 시간부터 다시 입력해주세요.");
                System.out.println();
                x--;
                continue;
            }

            if(parseEndHour <= 9) endTime += "0" + parseEndHour;
            else endTime += String.valueOf(parseEndHour);

            System.out.println((x + 1) + "번째 손님의 대실 종료 분은 몇 분입니까?");
            String endMinute = scan.nextLine();
            int parseEndMinute;
            try {
                parseEndMinute = Integer.parseInt(endMinute);
                if(parseEndMinute < 0 || parseEndMinute >= 60) {
                    System.out.println("분을 잘못 입력하였습니다. 시작 시간부터 다시 입력해주세요.");
                    System.out.println();
                    x--;
                    continue;
                }
            } catch(Exception e) {
                System.out.println("잘못 입력하셨습니다. 시작 시간부터 다시 입력해주세요.");
                System.out.println();
                x--;
                continue;
            }

            if(parseEndMinute <= 9) endTime += ":0" + parseEndMinute;
            else endTime += ":" + parseEndMinute;

            System.out.println((x + 1) + "번째 손님의 대실 예약 시작 시간 : " + startTime);
            System.out.println((x + 1) + "번째 손님의 대실 예약 종료 시간 : " + endTime);

            if(parseStartHour > parseEndHour || (parseStartHour == parseEndHour && parseStartMinute > parseEndMinute)) {
                System.out.println("시작 시간이 종료 시간보다 더 느립니다. 시작 시간부터 다시 입력해주세요.");
                System.out.println();
                x--;
                continue;
            }

            System.out.println();
            reservations[x][0] = startTime;
            reservations[x][1] = endTime;
        }

        System.out.println("각 예약 손님들의 대실 투숙 시간 : " + Arrays.deepToString(reservations));
        System.out.println("최소 객실의 개수 : " + minRoomCounts(reservations));
    }

    public static int minRoomCounts(String[][] reserveTime) {
        int rooms = 0;
        for(int i = 1; i <= factorial(reserveTime.length); i++) {
            String[][] sortReserveTime = new String[reserveTime.length][2];
            int[] orderNumbers = reserveOrders(reserveTime.length, i);
            for(int x = 0; x < reserveTime.length; x++) {
                sortReserveTime[x][0] = reserveTime[orderNumbers[x] - 1][0];
                sortReserveTime[x][1] = reserveTime[orderNumbers[x] - 1][1];
            }

            int[][] changeMinutes = new int[reserveTime.length][2];
            for(int x = 0; x < reserveTime.length; x++) {
                int[] changeMinute = new int[2];
                changeMinute[0] = Integer.parseInt(sortReserveTime[x][0].substring(0, 2)) * 60 + Integer.parseInt(sortReserveTime[x][0].substring(3, 5));
                changeMinute[1] = Integer.parseInt(sortReserveTime[x][1].substring(0, 2)) * 60 + Integer.parseInt(sortReserveTime[x][1].substring(3, 5));
                changeMinutes[x][0] = changeMinute[0];
                changeMinutes[x][1] = changeMinute[1];
            }

            ArrayList<int[]> roomReservationTimes = new ArrayList<>();
            for(int x = 0; x < changeMinutes.length; x++) {
                if(x != changeMinutes.length - 1 && changeMinutes[x][0] > -1 && changeMinutes[x][1] > -1) {
                    for(int y = x + 1; y <= changeMinutes.length - 1; y++) {
                        if(changeMinutes[y][0] > -1 && changeMinutes[y][1] > -1) {
                            if(changeMinutes[x][1] + 10 <= changeMinutes[y][0]) {
                                changeMinutes[x][1] = changeMinutes[y][1];
                                changeMinutes[y][0] = -1;
                                changeMinutes[y][1] = -1;
                            } else if(changeMinutes[x][0] - 10 >= changeMinutes[y][1]) {
                                changeMinutes[x][0] = changeMinutes[y][0];
                                changeMinutes[y][0] = -1;
                                changeMinutes[y][1] = -1;
                            }
                        }
                    }
                }

                if(changeMinutes[x][0] != -1) {
                    roomReservationTimes.add(changeMinutes[x]);
                }
            }

            if(rooms == 0) rooms = roomReservationTimes.size();
            else if(rooms > roomReservationTimes.size()) rooms = roomReservationTimes.size();
        }

        return rooms;
    }

    public static int factorial(int n) {
        if(n == 0 || n == 1) return 1;
        else return n * factorial(n - 1);
    }

    public static int[] reserveOrders(int n, int k) {
        int[] number = new int[n];
        for(int x = 0; x < n; x++) {
            number[x] = x + 1;
        }

        int[] reserveOrder = new int[n];
        int index = 1;
        for(int x = 1; x <= reserveOrder.length; x++) {
            int[] changeNumberIndex = new int[0];
            for(int y = 0; y < number.length; y++) {
                if(k >= index + factorial(n - x) * y && k <= index + factorial(n - x) * (y + 1) - 1) {
                    reserveOrder[x - 1] = number[y];
                    index += factorial(n - x) * y;
                    changeNumberIndex = new int[n - 1];
                    for(int z = 0; z < number.length; z++) {
                        if(z < y) changeNumberIndex[z] = number[z];
                        else if(z > y) changeNumberIndex[z - 1] = number[z];
                    }
                    break;
                }
            }

            number = changeNumberIndex;
        }

        return reserveOrder;
    }
}