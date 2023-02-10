package org.example.Scanner;

import java.util.Scanner;

public class RecursiveCallTest2 {
	static int N;
	public static int factorial1(int n) {
		if(n == 1) {
            return n;
		}else {
            return n*factorial1(n-1);
		}
	}

	public static int factorial2(int n) {
		if(n == N) {
            return n;
		}else {
            return n*factorial2(n+1);
		}
	}

	public static int factorial3(int n) {
		if(n > 1) {
            return n*factorial3(n-1);
		}else {
            return n;
		}

	}

	public static int factorial4(int n) {
		if(n < N) {
            return n*factorial4(n+1);
		}else {
            return n;
		}
	}
	public static void main(String[] args) {

		Scanner  scan = new Scanner(System.in);
		N =scan.nextInt();
		System.out.println(N + "! :"+factorial1(N));
		System.out.println(N + "! :"+factorial2(1));
		System.out.println(N + "! :"+factorial3(N));
		System.out.println(N + "! :"+factorial4(1));
	}
}
