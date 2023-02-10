package org.example.day3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class prof11660 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken()); //배열크기
        int m=Integer.parseInt(st.nextToken()); //연산횟수

        //구간합이 계산되면 입력값은 더이상 필요하지 않아서 배열x
        // 배열 인덱스가 1부터 시작하므로 배열 크기를 하나 더 크게 잡음
        int[][] sum=new int[n+1][n+1];

        for(int i=1;i<=n;i++){
            st=new StringTokenizer(br.readLine()," ");
            for(int j=1;j<=n;j++){
                sum[i][j]=sum[i][j-1]+Integer.parseInt(st.nextToken());
            }
        }System.out.println(Arrays.deepToString(sum));

        for(int i=0;i<m;i++){//연산횟수만큼
            st=new StringTokenizer(br.readLine()," ");
            int x1=Integer.parseInt(st.nextToken());
            int y1=Integer.parseInt(st.nextToken());
            int x2=Integer.parseInt(st.nextToken());
            int y2=Integer.parseInt(st.nextToken());
            //행마다 구간합을 계산해서 더하기
            int total=0;
            for(int x=x1;x<=x2;x++){ //행 단위로 처리
                total+=sum[x][y2]-sum[x][y1-1]; //한 행에서의 구간합
            }
        }

    }
}
