package src.level2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Cache { // 캐시
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("캐시의 크기를 얼마로 지정하겠습니까? (1 이상 30 이하의 자연수 중 하나 입력) >>> ");
        String length = scan.nextLine();
        int cacheLength = 0;

        try {
            cacheLength = Integer.parseInt(length);
            if(cacheLength < 0 || cacheLength > 30) {
                System.out.println("범위가 잘못되었습니다. 처음부터 다시 입력해주세요.");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("문자를 포함해서 입력하였거나 정수가 아닌 실수를 입력하였습니다. 처음부터 다시 입력해주세요.");
            System.exit(0);
        }
        System.out.println();

        ArrayList<String> cities = new ArrayList<>();

        while(true) {
            System.out.println("등록할 도시의 이름을 입력하세요 (영어, 숫자, 공백으로만 입력) >>> ");
            System.out.println("[도시 이름 입력을 종료하려면 엔터키만 눌러주세요]");
            String sentence = scan.nextLine();
            if(sentence.equals("")) {
                System.out.println("도시 이름 입력을 종료합니다.");
                break;
            }

            boolean pass = true;
            for(int x = 0; x < sentence.length(); x++) {
                if(!((sentence.charAt(x) >= 'A' && sentence.charAt(x) <= 'Z') || (sentence.charAt(x) >= 'a' && sentence.charAt(x) <= 'z'))) {
                    pass = false;
                    break;
                }
            }

            if(!pass) {
                System.out.println("도시 이름을 띄어쓰기 없이 영어 문자로만 입력해주세요.");
                System.out.println();
                continue;
            }

            cities.add(sentence);
            System.out.println("입력한 " + cities.size() + "번째 도시 이름 : " + sentence);
            System.out.println();

            if(cities.size() == 100000) break;
        } 

        if(cities.isEmpty()) {
            System.out.println("도시를 1개부터 100000개까지 입력해주세요.");
            System.exit(0);
        }
        System.out.println("입력한 도시를 나열한 배열 : " + cities);

        int order = -1;
        String[] changeTypeCities = new String[cities.size()];
        for(String city : cities) {
            order++;
            changeTypeCities[order] = city;
        }

        System.out.println("실행 시간 : " + runTimes(cacheLength, changeTypeCities));
    }

    public static int runTimes(int cacheLength, String[] sentences) {
        if(cacheLength == 0) return sentences.length * 5;
        int hitCount = 0;
        int missCount = 0;
        Queue<String> caches = new LinkedList<>();
        for(String sentence : sentences) {
            sentence = sentence.toUpperCase();
            if(caches.isEmpty()) {
                caches.add(sentence);
                missCount++;
            }
            else {
                boolean findCache = false;
                for(String cache : caches) {
                    if(cache.equals(sentence)) {
                        hitCount++;
                        findCache = true;
                        caches.remove(cache);
                        caches.add(cache);
                        break;
                    }
                }

                if(!findCache) {
                    if(caches.size() == cacheLength) {
                        caches.poll();
                        caches.add(sentence);
                    } else caches.add(sentence);

                    missCount++;
                }
            }
        }

        return hitCount + missCount * 5;
    }
}