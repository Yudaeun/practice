package org.example.day5;

import java.util.Stack;

public class PostExp {
    public static void main(String[] args) {
        //stack을 활용한 후위 표기식
        String exp="6525-*2/+";
        //후위표기식: 피연산자 (숫자)의 경우 stack에 push하고 연산자의 경우 스택에서 값을 pop, pop 해서 연산 후 다시 스택에 넣음
        Stack<Integer> st=new Stack<>();
        int size=exp.length();
        int k=0;
        for(int i=0;i<size;i++){
            char temp=exp.charAt(i);
            //temp
            k=temp-'0';
            if(k>=0&&k<=9){
                st.push(k);
            }
            else{
                int val2=st.pop();
                int val1=st.pop();

                switch (temp){
                    case '+':
                        st.push(val1+val2);
                        break;
                    case '-':
                        st.push(val1-val2);
                        break;
                    case '*':
                        st.push(val1*val2);
                        break;
                    case '/':
                        st.push(val1/val2);

                }
            }System.out.println(st.peek());
//            Character Caracter;
//            if(Caracter.isDigit(temp)){}
        }
    }
}
