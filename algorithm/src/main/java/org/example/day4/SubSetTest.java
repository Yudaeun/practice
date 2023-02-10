package org.example.day4;

import java.util.Scanner;

public class SubSetTest {
    static int n,totalCnt;
    static int[] input; //입력받은 수들
    static boolean[] visited; //각 원소가 부분집합의 구성에 포함되어 있는지 여부
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        input=new int[n];
        visited=new boolean[n];

        for(int i=0;i<n;i++){
            input[i]=sc.nextInt();
        }
        generateSubSet(0);
        System.out.println(totalCnt);

    }
    private static void generateSubSet(int cnt){ //cnt: 직전까지 고려된 원소 수

        if(cnt==n){
            totalCnt++;
            for (int i=0;i<n;i++){
                System.out.print((visited[i]?input[i]:"X")+"\t");
            }System.out.println();
            return;
        }

        //현재 원소를 부분집합의 구성에 포함
        visited[cnt]=true;
        generateSubSet(cnt+1);
        //현재 원소를 부분집합의 구성에 비포함
        visited[cnt]=false;
        generateSubSet(cnt+1);
    }
}
