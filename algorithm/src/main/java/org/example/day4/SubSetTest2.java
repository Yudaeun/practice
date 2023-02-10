package org.example.day4;

import java.util.Scanner;

public class SubSetTest2 {
    static int n;
    static int totalCnt;
    static int[] input;
    static boolean[] visited;
    public static void main(String[] args) {
//        int n=3; //원소 수
//        boolean[] visited=new boolean[n+1];
//        for(int i=0;i<=1;i++){
//            //첫번째 숫자 선택 여부 결정
//            visited[1]=(i==1); //1: 선택
//            for(int j=0;j<=1;j++){ //두번째 숫자 선택 여부 결정
//                visited[2]=(j==1);
//                for(int k=0;k<=1;k++){ //세번째 숫자 선택 여부 결정
//                    visited[3]=(k==1);
//
//                    for(int m=1;m<=n;m++){
//                        System.out.println((visited[m]?m:'-')+" ");
//                    }System.out.println();
//                }
//            }
//        }
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        input=new int[n];
        for(int i=0;i<n;++i){
            input[i]=sc.nextInt();
        }
        visited=new boolean[n];
        subset(0);
    }
    private static void subset(int cnt){
        if(cnt==n){ //배열 안의 모든 데이터 체크 끝
            //하나의 부분집합이 완성됐을 때 할 일
            ++totalCnt;
        for (int i=0;i<n;++i){
            System.out.print((visited[i]?input[i]:"X")+" ");
        }System.out.println();
        return;}
        visited[cnt]=true;
        subset(cnt+1);
        visited[cnt]=false;
        subset(cnt+1);
    }
}
