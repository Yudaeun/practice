package org.example.Scanner;

public class RecursiveCallTest1 {
	static int N=10;

	//1-10
	public static int sum(int n) {
		if(n == 1) {
			return n;
		}else {
			return n+sum(n-1);
		} 
		
	}

	public static int sum2(int n) {
		if(n == N) {
			return n;
		}else {
			return n+sum2(n+1);
		} 
		
	}

	public static int sum3(int n) {
		if(n > 1) {
			return n+sum3(n-1);
		}else {
			return n;
		} 
			
	}
	
	public static int sum4(int n) {
		if(n<N) {
			return n+sum4(n+1);
		}else {
			return n;
		}
				
	}	
	
	public static void main(String[] args) {
		System.out.println("sum :"+sum(10));
		System.out.println("sum :"+sum2(1));
		System.out.println("sum :"+sum3(10));
		System.out.println("sum :"+sum4(1));
	}
}
