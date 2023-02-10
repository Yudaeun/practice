package org.example.day4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Pari {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int t=Integer.parseInt(br.readLine());
        for (int c=0;c<t;c++){
            StringTokenizer st=new StringTokenizer(br.readLine());
            int n=Integer.parseInt(st.nextToken());
            int m=Integer.parseInt(st.nextToken());
            int[][] num=new int[n][n];
            for(int i=0;i<n;i++){
                StringTokenizer s=new StringTokenizer(br.readLine());
                for(int j=0;j<n;j++){
                    num[i][j]=Integer.parseInt(s.nextToken());
                }
            }

            for(int i=0;i<n-1-(m-1);i++){
                for(int j=0;j<n-1-(m-1);j++){
                    int sum=0;
                    for(int k=i;k<i+m;k++){
                        for(int s=j;s<j+m;s++){
                            sum+=num[i][j];
                        }
                    }System.out.println(sum);
                }
            }


        }
    }
}
