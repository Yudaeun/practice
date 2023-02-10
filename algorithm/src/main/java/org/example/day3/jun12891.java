package org.example.day3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class jun12891 {
    static int s, p;
    static int[] num = new int[4];
    static int answer;
    static char[] cha;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringTokenizer ss = new StringTokenizer(br.readLine(), " ");
        s = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        String dna = ss.nextToken();

        StringTokenizer t = new StringTokenizer(br.readLine(), " ");

        for (int i = 0; i < 4; i++) {
            num[i] = Integer.parseInt(t.nextToken());
        }
        cha = new char[s];
        for (int i = 0; i < s; i++) {
            cha[i] = dna.charAt(i);
        }
        int start = 0;
        int end = p-1 ;
        int[] check = new int[4];
        for (int i = 0; i < p; i++) {
            if (cha[i] == 'A') {
                check[0]++;
            } else if (cha[i] == 'C') {
                check[1]++;
            } else if (cha[i] == 'G') {
                check[2]++;
            } else {
                check[3]++;
            }
        }
//        System.out.printf("%d%d%d%d\n",check[0],check[1],check[2],check[3]);
        while(end<s) {
            if (check[0] >= num[0] && check[1] >= num[1] && check[2] >= num[2] && check[3] >= num[3])
                answer++;
            end++;
            if(end>=s)
                break;
            if (cha[start] == 'A')
                check[0]--;
            else if (cha[start] == 'C')
                check[1]--;
            else if (cha[start] == 'G')
                check[2]--;
            else
                check[3]--;
            start++;
            if (cha[end] == 'A')
                check[0]++;
            else if (cha[end] == 'C')
                check[1]++;
            else if (cha[end] == 'G')
                check[2]++;
            else
                check[3]++;
//            System.out.println(Arrays.toString(check)+" "+cha[start]+" "+cha[end]);

        }
        System.out.println(answer);

    }
}
