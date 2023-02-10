package org.example.day3;

import java.util.Arrays;
import java.util.Scanner;

public class Comb {
    static int n;
    static int r;
    static int totalCnt;
    static int[] numbers;
    static int[] result;
    static StringBuilder sb=new StringBuilder();
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        r=sc.nextInt();
        numbers=new int[n];
        result=new int[r];

        for(int i=0;i<n;i++){
            numbers[i]=i+1;
        }
        comb(0,0);
        System.out.println(sb.toString());
    }
    //현재까지 조합을 구성하는 숫자의 개수와 조합을 구성할 수 있는 시작 인덱스를 입력받아 조합 구성
    private static void comb(int cnt,int start){
        if(cnt==r){
            totalCnt++;
            for (int x:result){
                sb.append(x+" ");
            }sb.append("\n");
            return;
        }
        for(int i=start;i<n;i++){
            result[cnt]=numbers[i];
            comb(cnt+1,i+1);
        }
    }
}
