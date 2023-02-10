package org.example.day3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class jun11659 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());
        int[] num=new int[n+1];
        StringTokenizer t=new StringTokenizer(br.readLine());
        StringBuilder sb=new StringBuilder();
        for(int i=1;i<=n;i++){
            num[i]=Integer.parseInt(t.nextToken());
        }
        int[] answer=new int[n+1];

        //i부터 j까지의 합을 S(i,j)라고 했을 때 S(i,j)=sum[j+1]-sum[i]이다.
        for(int j=1;j<answer.length;j++){
            answer[j]+=answer[j-1]+num[j];
        } //누적합

        for(int i=0;i<m;i++){
            StringTokenizer s=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(s.nextToken());
            int b=Integer.parseInt(s.nextToken());
            sb.append(answer[b]-answer[a-1]+"\n"); //누적합으로 구간합 구하기
        }
        System.out.println(sb.toString());
    }
}
