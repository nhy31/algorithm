package baekjoon.p1713;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

// 1. 사진틀 : 추천받은 학생 사진 + 추천받은 횟수를 표시
// 2. 비어있는 사진틀이 없을 경우 -> 가장 적은 추천수의 학생 사진을 삭제, 새롭게 추천받은 학생 사진 게시
//        -> 이때, 가장 적은 추천수의 학생이 2명 이상 (추천수가 같을 경우) -> 가장 오래된 사진 삭제
//    2-1. 추천수 정렬 -> if(==) 시간순 정렬
// 3. 게시된 학생이 추천받으면 추천수 증가
// 4. 사진 삭제 -> 추천수 0으로 바뀜
// 5. 사진틀의 개수 N, 전체 학생의 추천 결과가 추천받은 순서대로 주어졌을 때, 최종 후보가 누구인지 출력하라
// 6. 사진틀에 사진이 게재된 최종 후보의 학생 번호를 증가하는 순서대로 출력한다.
//    6-1. 번호순 정렬

// 	정렬 구현 필요
//  두 가지 배열 -> 학생들 100명, 사진틀 N개
//  사진틀 안에는 추천받은 학생의 정보가 담겨있다 -> 학생번호, 추천수, 게시된 시간

    static int N;
    static int T;

    static List<Student> pictures = new ArrayList<>();
    static Student[] students = new Student[101]; // 학생은 1-100까지

    public static void main(String[] args) throws IOException {
        // System.setIn(new FileInputStream("src/DAY01/q4_p1713_nominee/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 사진틀의 개수 1~20

        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken()); // 총 추천 횟수 1000이하

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < T; i++) {
            int num = Integer.parseInt(st.nextToken()); // 학생번호

            // 해당 후보가 처음 호출 -> 객체를 만든다
            if (students[num] == null) {
                students[num] = new Student(num, 0, 0, false);
            }

            // 사진틀에 존재하면 추천수를 올린다. (처음 호출이 아님)
            if (students[num].isIn == true) {
                students[num].count++;
            }

            // 사진틀에 존재하지 않으면 사진틀에 게시한다. (처음 호출이거나 삭제되었던 경우)
            else {
                // 사진틀에 자리가 없으면 삭제한다.
                if (pictures.size() == N) {
                    Collections.sort(pictures);
                    Student s = pictures.remove(0);
                    s.count = 0;
                    s.isIn = false;
                }

                // 사진틀에 게시
                students[num].count = 1;
                students[num].isIn = true;
                students[num].time = i;  
                pictures.add(students[num]);
            }
        }

        Collections.sort(pictures, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.num - o2.num;
            }
        });

        for (int i = 0; i < pictures.size(); i++) {
            System.out.print(pictures.get(i).num + " ");
        }
    }
}

class Student implements Comparable<Student> {
    int num; // 학생번호
    int count; // 추천수
    int time; // 들어온 순서
    boolean isIn; // 이 학생이 사진틀에 있는가

    public Student(int num, int count, int time, boolean isIn) {
        this.num = num;
        this.count = count;
        this.time = time;
        this.isIn = isIn;
    }

    @Override
    public int compareTo(Student o) {
        int comp = Integer.compare(count, o.count); // 추천수 오름차순 정렬
        if (comp == 0) {
            return Integer.compare(time, o.time);
        }
        return comp;
    }
}
