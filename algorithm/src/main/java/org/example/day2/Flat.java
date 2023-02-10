package org.example.day2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Flat {
    public static void main(String[] args) throws IOException {
        Scanner sc=new Scanner(System.in);
        // 상자 배열을 저장한다.
        // 덤프횟수만큼 루프 돌린다.
        // 배열을 정렬한다.
        // 상자에서 가장 마지막에 있는 값에서 -1 해주고, 가장 앞에 있는 값에 +1해준다.
        for(int i=0;i<10;i++) {
            int d=sc.nextInt();
            int[] arr = new int[100];
            for (int j = 0; j < 100; j++) {
                arr[j] = sc.nextInt();
            }

            for (int j = 0; j < d; j++) {
                Arrays.sort(arr);
                arr[0]+=1;
                arr[arr.length - 1]-=1;
            }
            Arrays.sort(arr);
            System.out.printf("#%d %d\n", i + 1, (arr[arr.length - 1] - arr[0]));
        }
    }
}
