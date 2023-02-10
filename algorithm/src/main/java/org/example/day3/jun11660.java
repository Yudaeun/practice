package org.example.day3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class jun11660 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());
        int[][] num=new int[n+1][n+1];
        StringBuilder sb=new StringBuilder();
        for(int i=1;i<=n;i++){
            StringTokenizer t=new StringTokenizer(br.readLine());
            for(int j=1;j<=n;j++){
                num[i][j]=Integer.parseInt(t.nextToken());
            }
        }

        int[][] answer=new int[n+1][n+1];
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                answer[i][j]=answer[i][j-1]+num[i][j];
            }
        }
//        System.out.println((answer[x1][y2]-answer[x1][y2-1])+(answer[x2][y2]-answer[x2][y1-1]));
//        System.out.println(answer[3][4]-answer[3][1]);
//        answer[3][4]-answer[3][3]+answer[3][4]-answer[3][3]


        for(int i=1;i<=m;i++){
                StringTokenizer tt=new StringTokenizer(br.readLine());
                int x1=Integer.parseInt(tt.nextToken());
                int y1=Integer.parseInt(tt.nextToken());
                int x2=Integer.parseInt(tt.nextToken());
                int y2=Integer.parseInt(tt.nextToken());
                if ((x1==x2)&&(y1==y2)){
                    sb.append((answer[x1][y1]-answer[x1][y1-1])+"\n");}
                else{
                    int temp=0;
                    for(int j=x1;j<=x2;j++){
                    temp+=(answer[j][y2]-answer[j][y1-1]);

                    }   sb.append(temp+"\n");
                }
        }
        System.out.println(sb.toString());

        //(x1,y1) 부터 (x2,y2)의 합 , i는 1부터시작, J는 0부터 시작

    }
}
