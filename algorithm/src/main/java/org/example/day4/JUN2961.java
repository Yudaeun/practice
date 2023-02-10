package org.example.day4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JUN2961 {
    static int[][] num;
    static int check;
    static long midAnswer;
    static int[][] cook;
    static boolean[] visited;
    static int n;
    static long answer = 999999999999999999L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        cook = new int[n][2];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            cook[i][0] = a;
            cook[i][1] = b;
        }
        visited=new boolean[n];
        answer = Long.MAX_VALUE;
        comb(0, 1, 0);
        System.out.println(answer);

    }

    public static void comb(int cnt, int first, int second) {
        if (cnt == n) {
            int temp = 0;
            for (int i = 0; i < n; i++) {
                if (visited[i]) continue;
                temp++;
            }
            if (temp == n) return;
            answer = Math.min(answer, Math.abs(first - second));
            return;

        }
        visited[cnt] = true;
        comb(cnt + 1, first * cook[cnt][0], second + cook[cnt][1]);
        visited[cnt] = false;
        comb(cnt + 1, first, second);
    }
}