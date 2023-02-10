package org.example.day4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Meeting {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());
        int[][] meeting=new int[n][n];
        for(int i=0;i<n;i++){
            StringTokenizer st=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());
            meeting[i][0]=a;
            meeting[i][1]=b;
        }
        int answer=0;
        int current=meeting[0][1];

        for(int i=0;i<n;i++){
            if (current<=meeting[i][0]){
                    current=meeting[i][1];
                    answer+=1;
            }
        }
        System.out.println(answer+1);
    }
}
