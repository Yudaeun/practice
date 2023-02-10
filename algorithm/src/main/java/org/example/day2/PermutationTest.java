package org.example.day2;

import java.util.Arrays;
import java.util.Scanner;

//nPr
//n개 중에 r개를 뽑아 순서있게 출력
public class PermutationTest {
//    static int totalCnt;
    static StringBuilder sb=new StringBuilder();
    static int n,r; //nPr
    static int[] result,numbers; //result: 순열로 뽑힌 숫자들이 들어있는 배열, numbers: 전체 데이터 배열
    static boolean[] visited; //순열에 사용된 숫자인지 체크하기 위한 배열
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        r=sc.nextInt();

        numbers=new int[n]; //모든 값
        result=new int[r];// 순열을 구성하는 숫자가 들어갈 배열
        visited=new boolean[n];

        for(int i=1;i<n+1;i++){
            numbers[i-1]=i;
        }

        //순열에 사용할 값 넣어 놓기
        perm(0); //순열을 구성하는 수의 개수
        System.out.println(sb.toString());
//        System.out.println("총 경우의 수: "+totalCnt);
    }
    //현재까지 뽑힌 숫자 개수를 입력받아 체크한 뒤 종료하거나 숫자를 뽑거나 함
    //cnt: 이제까지 뽑은 순열을 구성하는 숫자 개수
    public static void perm(int num){
        if(num==r){ //다 뽑음
//            totalCnt++;
            for (int x:result) {
               sb.append(x+" ");

            }
            sb.append("\n");
            return; //프로그램 실행이 멈춤
        }

        //숫자를 뽑을 수 있음
        for(int i=0;i<n;i++){ //전체 숫자를 대상으로 뽑기
            if(visited[i]==false){ //사용가능
                visited[i]=true; //사용할것이다
                result[num]=numbers[i]; //i번째 값을 선택
                perm(num+1); //다음 숫자 뽑으러 보냄
                visited[i]=false; //사용했던 위치 해제
                }
            }
        }

    }

