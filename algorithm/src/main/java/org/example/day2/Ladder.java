package org.example.day2;
import java.util.Scanner;

public class Ladder {
    static int[] dx = {-1, 0, 0};
    static int[] dy = {0, -1, 1};
    static int answer=0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int destX = 0;
        int destY = 0;
        for (int i = 0; i < 10; i++) {
            int t = sc.nextInt();
            int[][] arr = new int[100][100];
            for (int j = 0; j < 100; j++) {
                for (int k = 0; k < 100; k++) {
                    arr[j][k] = sc.nextInt();
                    if (arr[j][k] == 2) {
                        destX = j;
                        destY = k;
                    }
                }
            }

            bfs(destX,destY,arr);
            System.out.printf("#%d %d\n",i+1,answer);


        }
    }

    public static void bfs( int x, int y, int[][] arr) {
        while(true){
            if(x==0){
                answer=y;
                break;
            }
            for(int i=0;i<3;i++){
                int nx=x+dx[i];
                int ny=y+dy[i];
                if(nx<0||nx>=100||ny<0||ny>=100)
                    continue;
                if(arr[nx][ny]==1){
                    arr[x][y]=3;
                    x=nx;y=ny;
                }
            }
        }
    }
}