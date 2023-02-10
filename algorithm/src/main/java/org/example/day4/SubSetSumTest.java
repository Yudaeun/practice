package org.example.day4;

import java.util.Scanner;

public class SubSetSumTest {
    static int n,s,totalCnt;
    static int[] input; //입력받은 수들
    static boolean[] visited; //각 원소가 부분집합의 구성에 포함되어 있는지 여부
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        s=sc.nextInt(); //목표 합
        input=new int[n];
        visited=new boolean[n];

        for(int i=0;i<n;i++){
            input[i]=sc.nextInt();
        }
        generateSubSet(0,0);
        System.out.println(totalCnt);

    }
//    private static void generateSubSet(int cnt){ //cnt: 직전까지 고려된 원소 수
//
//        if(cnt==n){
//            int sum=0;
//            //부분집합의 요소들의 합 구하기
//            for(int i=0;i<n;i++){
//                if(visited[i]) sum+=input[i];
//            }
//            if(sum==s) {
//                totalCnt++;
//                for (int i = 0; i < n; i++) {
//                    if (visited[i]) System.out.print((input[i]) + "\t");
//                }
//                System.out.println();
//            }
//            return;
//
//        }
//
//        //현재 원소를 부분집합의 구성에 포함
//        visited[cnt]=true;
//        generateSubSet(cnt+1);
//        //현재 원소를 부분집합의 구성에 비포함
//        visited[cnt]=false;
//        generateSubSet(cnt+1);
//    }
    private static void generateSubSet(int cnt,int sum){ //cnt: 직전까지 고려된 원소 수
    //sum: 직전까지 선택된 원소들의 합
        if(cnt==n){
            if(sum==s) {
                totalCnt++;
                for (int i = 0; i < n; i++) {
                    if (visited[i]) System.out.print((input[i]) + "\t");
                }
                System.out.println();
            }
            return;

        }

        //현재 원소를 부분집합의 구성에 포함
        visited[cnt]=true;
        generateSubSet(cnt+1,sum+input[cnt]);
        //현재 원소를 부분집합의 구성에 비포함
        visited[cnt]=false;
        generateSubSet(cnt+1,sum);
    }
}
