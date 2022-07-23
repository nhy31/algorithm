package baekjoon.p1713;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

// 1. ����Ʋ : ��õ���� �л� ���� + ��õ���� Ƚ���� ǥ��
// 2. ����ִ� ����Ʋ�� ���� ��� -> ���� ���� ��õ���� �л� ������ ����, ���Ӱ� ��õ���� �л� ���� �Խ�
//        -> �̶�, ���� ���� ��õ���� �л��� 2�� �̻� (��õ���� ���� ���) -> ���� ������ ���� ����
//    2-1. ��õ�� ���� -> if(==) �ð��� ����
// 3. �Խõ� �л��� ��õ������ ��õ�� ����
// 4. ���� ���� -> ��õ�� 0���� �ٲ�
// 5. ����Ʋ�� ���� N, ��ü �л��� ��õ ����� ��õ���� ������� �־����� ��, ���� �ĺ��� �������� ����϶�
// 6. ����Ʋ�� ������ ����� ���� �ĺ��� �л� ��ȣ�� �����ϴ� ������� ����Ѵ�.
//    6-1. ��ȣ�� ����

// 	���� ���� �ʿ�
//  �� ���� �迭 -> �л��� 100��, ����Ʋ N��
//  ����Ʋ �ȿ��� ��õ���� �л��� ������ ����ִ� -> �л���ȣ, ��õ��, �Խõ� �ð�

    static int N;
    static int T;

    static List<Student> pictures = new ArrayList<>();
    static Student[] students = new Student[101]; // �л��� 1-100����

    public static void main(String[] args) throws IOException {
        // System.setIn(new FileInputStream("src/DAY01/q4_p1713_nominee/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // ����Ʋ�� ���� 1~20

        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken()); // �� ��õ Ƚ�� 1000����

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < T; i++) {
            int num = Integer.parseInt(st.nextToken()); // �л���ȣ

            // �ش� �ĺ��� ó�� ȣ�� -> ��ü�� �����
            if (students[num] == null) {
                students[num] = new Student(num, 0, 0, false);
            }

            // ����Ʋ�� �����ϸ� ��õ���� �ø���. (ó�� ȣ���� �ƴ�)
            if (students[num].isIn == true) {
                students[num].count++;
            }

            // ����Ʋ�� �������� ������ ����Ʋ�� �Խ��Ѵ�. (ó�� ȣ���̰ų� �����Ǿ��� ���)
            else {
                // ����Ʋ�� �ڸ��� ������ �����Ѵ�.
                if (pictures.size() == N) {
                    Collections.sort(pictures);
                    Student s = pictures.remove(0);
                    s.count = 0;
                    s.isIn = false;
                }

                // ����Ʋ�� �Խ�
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
    int num; // �л���ȣ
    int count; // ��õ��
    int time; // ���� ����
    boolean isIn; // �� �л��� ����Ʋ�� �ִ°�

    public Student(int num, int count, int time, boolean isIn) {
        this.num = num;
        this.count = count;
        this.time = time;
        this.isIn = isIn;
    }

    @Override
    public int compareTo(Student o) {
        int comp = Integer.compare(count, o.count); // ��õ�� �������� ����
        if (comp == 0) {
            return Integer.compare(time, o.time);
        }
        return comp;
    }
}
