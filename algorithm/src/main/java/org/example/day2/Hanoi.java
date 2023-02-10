package org.example.day2;

public class Hanoi {
    static StringBuilder result=new StringBuilder();
    public static void main(String[] args) {

    }
    private static void hanoi(int cnt,int from,int temp,int to){
        if(cnt==0) return;
        hanoi(cnt-1,from,to,temp);
        result.append(cnt+" : "+from+"->"+to+"\n");
        hanoi(cnt-1,temp,from,to);
    }
    private static void hanoi2(int cnt,int from,int temp,int to){
        if(cnt-1>0) hanoi2(cnt-1,from,to,temp);
        result.append(cnt+" : "+from+"->"+to+"\n");
        if(cnt-1>0) hanoi2(cnt-1,temp,from,to);
    }
    private static void hanoi3(int cnt,int from,int to){
        int temp=6-from-to;
        if(cnt-1>0) hanoi2(cnt-1,from,to,temp);
        result.append(cnt+" : "+from+"->"+to+"\n");
        if(cnt-1>0) hanoi2(cnt-1,temp,from,to);
    }
}
