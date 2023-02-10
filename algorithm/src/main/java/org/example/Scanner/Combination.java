package org.example.Scanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Combination {
    private static int comb(int n,int r){
        if(r==0||n==r){
            //다 뽑았거나, 하나도 안 뽑았거나
            return 1;
        }
        else{
            //n개 중에 r개를 뽑아야 한다.
            //comb(n-1,r-1): 특정원소가 r개 중에 포함된 경우
            //comb(n-1,r): 특정원소가 r개 중에 포함이 안된 경우
            return comb(n-1,r)+comb(n-1,r-1);
        }
    }

    public static void main(String[] args) throws IOException {
//        System.out.println(comb(3,2));
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());
        int r=Integer.parseInt(br.readLine());
        System.out.println(comb(n,r));
    }
}
