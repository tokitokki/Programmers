package src.level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MusicSearch { // 방금그곡
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("검색하려는 음을 순서대로 입력하세요 (음 개수를 1개부터 1439개까지 중 하나로 입력하세요.) >>> ");
        System.out.println("[입력 가능한 음 : C, C#, D, D#, E, F, F#, G, G#, A, A#, B]");
        String notes = scan.nextLine();
        notes = notes.toUpperCase();
        notes = notes.replace(" ", "");

        System.out.println("입력한 음 : " + notes);

        if(notes.charAt(0) == '#') {
            System.out.println("음의 첫 글자는 #이 올 수 없습니다. 처음부터 다시 입력하세요.");
            System.exit(0);
        }

        String[] noteList = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
        boolean correctNote = true;
        for(int x = 0; x < notes.length(); x++) {
            if(notes.charAt(x) != '#') {
                boolean collectPair = false;
                for(String note : noteList) {
                    if(x != notes.length() - 1) {
                        if(notes.charAt(x + 1) == '#' && (note.length() == 2 && notes.substring(x, x + 2).equals(note))) collectPair = true;
                        else if(notes.charAt(x + 1) != '#' && note.length() == 1 && notes.charAt(x) == note.charAt(0)) collectPair = true;
                    } else {
                        if(note.length() == 1 && notes.charAt(x) == note.charAt(0)) collectPair = true;
                    }
                }

                if(!collectPair) {
                    correctNote = false;
                    break;
                }
            }
        }

        if(!correctNote) {
            System.out.println("음을 잘못 입력하였습니다. 처음부터 다시 입력하세요.");
            System.exit(0);
        }

        int noteCounts = notes.replace("#", "").length();

        if(noteCounts < 1 || noteCounts > 1439) {
            System.out.println("잘못된 범위입니다. 처음부터 다시 입력하세요.");
            System.exit(0);
        }
        System.out.println("입력한 음의 개수 : " + noteCounts + "개");
        System.out.println();

        ArrayList<MusicInfo> musics = new ArrayList<>();
        System.out.println("곡 정보를 몇 개 담을까요? (1 이상 100 이하의 자연수 중 하나 입력) >>> ");
        String musicCount = scan.nextLine();
        int changeTypeMusicCount = 0;

        try {
            changeTypeMusicCount = Integer.parseInt(musicCount);
            if(changeTypeMusicCount < 1 || changeTypeMusicCount > 100) {
                System.out.println("잘못된 범위입니다. 처음부터 다시 입력하세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }

        System.out.println(changeTypeMusicCount + "곡의 정보를 담습니다.");
        System.out.println();

        for(int x = 1; x <= changeTypeMusicCount; x++) {
            System.out.println(x + "번째 곡의 재생 시작 시는 몇 시 입니까? (0부터 23까지의 정수 중 하나 입력) >>> ");
            String startHour = scan.nextLine();
            int changeTypeStartHour;

            try {
                changeTypeStartHour = Integer.parseInt(startHour);
                if(changeTypeStartHour < 0 || changeTypeStartHour > 23) {
                    System.out.println("범위가 잘못되었습니다. 다시 입력해주세요.");
                    System.out.println();
                    x--;
                    continue;
                }
            } catch(Exception e) {
                System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 다시 입력해주세요.");
                System.out.println();
                x--;
                continue;
            }

            System.out.println();
            System.out.println(x + "번째 곡의 재생 시작 분은 몇 분 입니까? (0부터 59까지의 정수 중 하나 입력) >>> ");
            String startMinute = scan.nextLine();
            int changeTypeStartMinute;

            try {
                changeTypeStartMinute = Integer.parseInt(startMinute);
                if(changeTypeStartMinute < 0 || changeTypeStartMinute > 59) {
                    System.out.println("범위가 잘못되었습니다. 시작 시 입력부터 다시 입력해주세요.");
                    System.out.println();
                    x--;
                    continue;
                }
            } catch(Exception e) {
                System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 시작 시 입력부터 다시 입력해주세요.");
                System.out.println();
                x--;
                continue;
            }

            System.out.println();
            System.out.println(x + "번째 곡의 시작 시각 : " + changeTypeStartHour + "시 " + changeTypeStartMinute + "분");
            System.out.println(x + "번째 곡의 재생 종료 시는 몇 시 입니까? (0부터 23까지의 정수 중 하나 입력) >>> ");
            String endHour = scan.nextLine();
            int changeTypeEndHour;

            try {
                changeTypeEndHour = Integer.parseInt(endHour);
                if(changeTypeEndHour < 0 || changeTypeEndHour > 23) {
                    System.out.println("범위가 잘못되었습니다. 시작 시 입력부터 다시 입력해주세요.");
                    System.out.println();
                    x--;
                    continue;
                }
            } catch(Exception e) {
                System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 시작 시 입력부터 다시 입력해주세요.");
                System.out.println();
                x--;
                continue;
            }

            System.out.println();
            System.out.println(x + "번째 곡의 재생 종료 분은 몇 분 입니까? (0부터 59까지의 정수 중 하나 입력) >>> ");
            String endMinute = scan.nextLine();
            int changeTypeEndMinute;

            try {
                changeTypeEndMinute = Integer.parseInt(endMinute);
                if(changeTypeEndMinute < 0 || changeTypeEndMinute > 59) {
                    System.out.println("범위가 잘못되었습니다. 시작 시 입력부터 다시 입력해주세요.");
                    System.out.println();
                    x--;
                    continue;
                }
            } catch(Exception e) {
                System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 시작 시 입력부터 다시 입력해주세요.");
                System.out.println();
                x--;
                continue;
            }

            System.out.println(x + "번째 곡의 종료 시각 : " + changeTypeEndHour + "시 " + changeTypeEndMinute + "분");
            if(changeTypeStartHour > changeTypeEndHour || (changeTypeStartHour == changeTypeEndHour && changeTypeStartMinute >= changeTypeEndMinute)) {
                System.out.println("시작 시각이 종료 시각보다 빠르거나 같습니다. 시작 시 입력부터 다시 입력하세요.");
                System.out.println();
                x--;
                continue;
            }

            System.out.println();
            System.out.println(x + "번째 곡의 제목은 무엇입니까? (',' 빼고 64자 이내로 입력) >>> ");
            String title = scan.nextLine();
            if(title.length() < 1 || title.length() > 64) {
                System.out.println("제목 글자 수가 잘못되었습니다. 시작 시 입력부터 다시 입력해주세요");
                System.out.println();
                x--;
                continue;
            }

            boolean check = true;
            for(int y = 0; y < title.length(); y++) {
                if(title.charAt(y) == ',') {
                    check = false;
                    break;
                }
            }

            if(!check) {
                System.out.println("노래 제목에는 ','를 입력할 수 없습니다. 시작 시 입력부터 다시 입력해주세요");
                System.out.println();
                x--;
                continue;
            }
            System.out.println(x + "번째 노래의 제목 : " + title);

            System.out.println();
            System.out.println(x + "번째 음악의 악보는 어떻게 됩니까? (음 개수를 1개부터 1439개까지 중 하나로 입력하세요.) >> ");
            System.out.println("[입력 가능한 음 : C, C#, D, D#, E, F, F#, G, G#, A, A#, B]");
            String thisNote = scan.nextLine();
            thisNote = thisNote.toUpperCase();
            thisNote = thisNote.replace(" ", "");

            System.out.println("입력한 " + x + "번째 노래의 악보 : " + thisNote);
            if(thisNote.charAt(0) == '#') {
                System.out.println("음의 첫 글자는 #이 올 수 없습니다. 시작 시 입력부터 다시 입력해주세요.");
                System.out.println();
                x--;
                continue;
            }

            for(int y = 0; y < thisNote.length(); y++) {
                if(thisNote.charAt(y) != '#') {
                    boolean collectPair = false;
                    correctNote = true;
                    for(String note : noteList) {
                        if(y != thisNote.length() - 1) {
                            if(thisNote.charAt(y + 1) == '#' && (note.length() == 2 && thisNote.substring(y, y + 2).equals(note))) collectPair = true;
                            else if(thisNote.charAt(y + 1) != '#' && note.length() == 1 && thisNote.charAt(y) == note.charAt(0)) collectPair = true;
                        } else {
                            if(note.length() == 1 && thisNote.charAt(y) == note.charAt(0)) collectPair = true;
                        }
                    }

                    if(!collectPair) {
                        correctNote = false;
                        break;
                    }
                }
            }

            if(!correctNote) {
                System.out.println("음을 잘못 입력하였습니다. 시작 시 입력부터 다시 입력해주세요.");
                System.out.println();
                x--;
                continue;
            }

            String startTime;
            if(startHour.length() == 1 && startMinute.length() == 1) startTime = "0" + startHour + ":0" + startMinute;
            else if(startHour.length() == 1) startTime = "0" + startHour + ":" + startMinute;
            else if(startMinute.length() == 1) startTime = startHour + ":0" + startMinute;
            else startTime = startHour + ":" + startMinute;

            String endTime;
            if(endHour.length() == 1 && endMinute.length() == 1) endTime = "0" + endHour + ":0" + endMinute;
            else if(endHour.length() == 1) endTime = "0" + endHour + ":" + endMinute;
            else if(endMinute.length() == 1) endTime = endHour + ":0" + endMinute;
            else endTime = endHour + ":" + endMinute;

            MusicInfo music = new MusicInfo(startTime, endTime, title, thisNote);
            musics.add(music);
            System.out.println();
        }

        System.out.println("----노래 리스트----");
        for(MusicInfo song : musics) {
            System.out.println("-----------------");
            System.out.println("시작 시각 : " + song.startTime);
            System.out.println("종료 시각 : " + song.endTime);
            System.out.println("제목 : " + song.musicTitle);
            System.out.println("악보 : " + song.musicNote);
        }

        System.out.println("-----------------");
        System.out.println("검색된 노래 제목 : " + searchSong(notes, musics));
    }

    public static String searchSong(String note, ArrayList<MusicInfo> lists) {
        String songName = "(None)";
        int playLength = 0;
        for(MusicInfo list : lists) {
            int startMinute = Integer.parseInt(list.startTime.substring(0, 2)) * 60 + Integer.parseInt(list.startTime.substring(3, 5));
            int endMinute = Integer.parseInt(list.endTime.substring(0, 2)) * 60 + Integer.parseInt(list.endTime.substring(3, 5));
            String musicNoteConcat = "";
            for(int x = 1; x <= endMinute - startMinute; x++) {
                int turn = (x - 1) % list.musicNote.length();
                musicNoteConcat = musicNoteConcat.concat(list.musicNote.substring(turn, turn + 1));
            }

            int noteCount = note.replace("#", "").length();
            int listNoteCount = musicNoteConcat.replace("#", "").length();
            String[] noteOrderArray = new String[noteCount];
            Arrays.fill(noteOrderArray, "");
            String[] listNoteArray = new String[listNoteCount];
            Arrays.fill(listNoteArray, "");
            int x = -1;
            int y = -1;
            for(int a = 0; a < note.length(); a++) {
                if(note.charAt(a) != '#') x++;
                noteOrderArray[x] = noteOrderArray[x].concat(note.substring(a, a + 1));
            }

            for(int b = 0; b < musicNoteConcat.length(); b++) {
                if(musicNoteConcat.charAt(b) != '#') y++;
                listNoteArray[y] = listNoteArray[y].concat(musicNoteConcat.substring(b, b + 1));
            }

            if(noteOrderArray.length <= listNoteArray.length) {
                boolean contain = false;
                for(int c = 0; c <= listNoteArray.length - noteOrderArray.length - 1; c++) {
                    int count = 0;
                    for(int d = 0; d <= noteOrderArray.length - 1; d++) {
                        if(listNoteArray[c + d].equals(noteOrderArray[d])) count++;
                    }

                    if(count == noteOrderArray.length) {
                        contain = true;
                        break;
                    }
                }

                if(contain && (playLength == 0 || playLength < endMinute - startMinute)) {
                    playLength = endMinute - startMinute;
                    songName = list.musicTitle;
                }
            }
        }

        return songName;
    }
}

class MusicInfo { // 각 음악의 재생 시작 시각, 재생 종료 시각, 제목, 악보를 담는 생성자 생성
    String startTime;
    String endTime;
    String musicTitle;

    String musicNote;

    MusicInfo(String startTime, String endTime, String musicTitle, String musicNote) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.musicTitle = musicTitle;
        this.musicNote = musicNote;
    }
}