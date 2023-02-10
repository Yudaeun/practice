package org.example.Scanner;

//반복문: cpu 연산만 사용
public class RecursiveTest {
    public static void main(String[] args) {
        for(int i=0;i<=4;i++){
            System.out.printf("%3d",i);
        }
        System.out.println();
        //재귀로 바꿔보기
        //1.re()
        //re(0,4,0);
        re(0,5,2);
        sum1(1,100);
        sum2(1,100 );
        System.out.println(getPower(2,2));
        System.out.println(getLen("ssafy"));


//        sum(1,100); //start,end를 입력 받아 start~end까지의 합을 출력
//        sum2(1,100); //start,end를 입력 받아 start~end까지의 합을 리턴
    }
    public static void re(int start,int end,int step) {
        //1. 종료조건(basis case)
        if (start > end) {
            System.out.println(""); //줄바꿈
        } else {
            //2. 반복조건(재귀호출 발생)
            System.out.printf("%3d",start); //숫자 한개 출력
            re(start + step, end, step);

        }
    }
    static int total=0;
    public static void sum1(int start,int end){
//        if (start==end){
//            System.out.println(sum);
//        }else{
//            sum1(start+1,end,sum+start);
//        }
        if(start>end){
            System.out.println(total);
        }else{
            total+=start;
            sum1(start+1,end);
        }
    }
    static int total2=0;
    public static int sum2(int start,int end){
        if(start>end){
            return total2;
        }else{
            return start+sum2(start+1,end);
        }

    }

    public static int getPower(int n,int m){
        if (m==1){
            return n;
        }
        return n*getPower(n,m-1);
    }
    public static int getLen(String str){
        if(str.equals("")){
            return 0;
        }else{
            // 글자 내용이 최소 1글자 이상인 경우
            return 1+getLen(str.substring(1));

        }
    }
    public static int fact(int n){
        if (n<=1)
            return 1;
        else
            return n*fact(n-1);
    }
    public static int fibo(int n){
        if (n<2)
            return n;
        else
            return fibo(n-1)+fibo(n-2);
    }
}
