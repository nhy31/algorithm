package baekjoon.p1062;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
//  1. K개의 글자를 가르친다
//  2. K개의 글자로만 이루어진 단어를 읽을 수 있다
//  3. 어떤 K개의 글자를 가르쳐야 학생들이 읽을 수 있는 단어의 개수가 최대가 되는가?
//  4. 모든 단어는 antaXXXtica 형태 -> a n t i c 는 무조건 알아야 단어를 읽을 수 있다. (알파벳은 26개)
//    4-1. K < 5 -> 읽을 수 있는 단어가 없다
//    4-2. K == 5 -> 조합을 구할 필요없이, 바로 단어 체크만 해본다
//    4-3. K == 26 -> 모든 단어 읽기 가능
//    4-3. 5 < K < 26 -> 배울 수 있는 알파벳들의 경우의 수를 구하여 단어를 읽을 수 있는지 체크해본다.
//        4-3-1. a n t i c는 이미 방문한 상태
//
//  4-3-2. K에서 5개는 이미 카운트한 상태로 시작
//  5. K개를 배운다고 했을 때, 배울 수 있는 알파벳 조합을 모두 구해본다 -> 그 중 학생들이 주어진 단어들을 가장 많이 읽을 수 있는 경우
//  6. 모든 경우의 수 -> DFS -> 읽을 수 있는 단어의 개수가 최대가 될 때를 그 개수를 출력

    static boolean[] visited;
    static int N, K;
    static int count = 0;
    static int max = 0;
    static String[] words;

    public static void main(String[] args) throws IOException {
  
        // System.setIn(new FileInputStream("src/baekjoon/p1062/input.txt"));
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken()); // 단어의 개수
        K = Integer.parseInt(st.nextToken()); // 가르칠 알파벳 개수

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
            for (int i = 0; i < 26; i++) { // 알파벳 26가지
                if (visited[i] == false) {
                    dfs(i);
                }
            }
        }

        System.out.println(max);
    }

    static void dfs(int current) {
        // 1. 체크인
        visited[current] = true;
        count++;

        // 2. 목적지인가
        if (count == K) {
            max = Math.max(max, wordCheck());
        }

        else {
            // 3. 연결된 곳을 순회
            for (int i = current + 1; i < 26; i++) {
                // 4. 갈 수 있는가
                if (visited[i] == false) {
                    // 5. 간다
                    dfs(i);
                }
            }
        }

        // 6. 체크아웃
        visited[current] = false;
        count--;
    }

    // 읽을 수 있는 단어가 몇 개인가를 체크
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
