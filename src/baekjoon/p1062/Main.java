package baekjoon.p1062;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
//  1. K���� ���ڸ� ����ģ��
//  2. K���� ���ڷθ� �̷���� �ܾ ���� �� �ִ�
//  3. � K���� ���ڸ� �����ľ� �л����� ���� �� �ִ� �ܾ��� ������ �ִ밡 �Ǵ°�?
//  4. ��� �ܾ�� antaXXXtica ���� -> a n t i c �� ������ �˾ƾ� �ܾ ���� �� �ִ�. (���ĺ��� 26��)
//    4-1. K < 5 -> ���� �� �ִ� �ܾ ����
//    4-2. K == 5 -> ������ ���� �ʿ����, �ٷ� �ܾ� üũ�� �غ���
//    4-3. K == 26 -> ��� �ܾ� �б� ����
//    4-3. 5 < K < 26 -> ��� �� �ִ� ���ĺ����� ����� ���� ���Ͽ� �ܾ ���� �� �ִ��� üũ�غ���.
//        4-3-1. a n t i c�� �̹� �湮�� ����
//
//  4-3-2. K���� 5���� �̹� ī��Ʈ�� ���·� ����
//  5. K���� ���ٰ� ���� ��, ��� �� �ִ� ���ĺ� ������ ��� ���غ��� -> �� �� �л����� �־��� �ܾ���� ���� ���� ���� �� �ִ� ���
//  6. ��� ����� �� -> DFS -> ���� �� �ִ� �ܾ��� ������ �ִ밡 �� ���� �� ������ ���

    static boolean[] visited;
    static int N, K;
    static int count = 0;
    static int max = 0;
    static String[] words;

    public static void main(String[] args) throws IOException {
  
        // System.setIn(new FileInputStream("src/baekjoon/p1062/input.txt"));
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken()); // �ܾ��� ����
        K = Integer.parseInt(st.nextToken()); // ����ĥ ���ĺ� ����

        words = new String[N];
        visited = new boolean[26];

        visited['a' - 'a'] = true;
        visited['n' - 'a'] = true;
        visited['t' - 'a'] = true;
        visited['i' - 'a'] = true;
        visited['c' - 'a'] = true;

        count = 5;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            words[i] = st.nextToken().replace("[antic]" ,"");
        }

        if (K < 5) {
            max = 0;
        }
        else if (K == 26) {
            max = N;
        }
        else if (K == 5) {
            max = wordCheck();
        }
        else {
            for (int i = 0; i < 26; i++) { // ���ĺ� 26����
                if (visited[i] == false) {
                    dfs(i);
                }
            }
        }

        System.out.println(max);
    }

    static void dfs(int current) {
        // 1. üũ��
        visited[current] = true;
        count++;

        // 2. �������ΰ�
        if (count == K) {
            max = Math.max(max, wordCheck());
        }

        else {
            // 3. ����� ���� ��ȸ
            for (int i = current + 1; i < 26; i++) {
                // 4. �� �� �ִ°�
                if (visited[i] == false) {
                    // 5. ����
                    dfs(i);
                }
            }
        }

        // 6. üũ�ƿ�
        visited[current] = false;
        count--;
    }

    // ���� �� �ִ� �ܾ �� ���ΰ��� üũ
    static int wordCheck() {
        int readCount = 0;

        for (int i = 0; i < N; i++) {
            boolean readPossible = true;
            String word = words[i];
            for (int j = 0; j < words[i].length(); j++) {
                char alpha = word.charAt(j);
                if (visited[word.charAt(j) - 'a'] == false) {
                    readPossible = false;
                    break;
                }
            }
            if (readPossible == true) {
                readCount++;
            }
        }
        return readCount;
    }
}
