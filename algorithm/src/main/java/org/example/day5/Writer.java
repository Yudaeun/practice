package org.example.day5;

public class Writer implements Comparable<Writer>{
    int no;
    String FirstName;
    String lastName;
    String bookTitle;

    public Writer(int no, String firstName, String lastName, String bookTitle) {
        this.no = no;
        FirstName = firstName;
        this.lastName = lastName;
        this.bookTitle = bookTitle;
    }

    @Override
    public String toString() {
        return "Wrtier{" +
                "no=" + no +
                ", FirstName='" + FirstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", bookTitle='" + bookTitle + '\'' +
                '}';
    }

    //정렬 기준을 표시하는 메서드
    @Override
    public int compareTo(Writer other) {
        //음수,0,양수: 음수이거나 0인경우 객체들의 자리 이동x, 양수인 경우 객체들의 자리 이동 o
//        int x=this.no-other.no; //오름차순 정렬
//        int x=other.no-this.no; //내림차순 정렬

//        return x;
//        return this.no-other.no;//번호 기준 오름차순 정렬
//        return this.lastName.compareTo(other.lastName);
            int x=this.lastName.compareTo(other.lastName);

            if(x==0){
                x=other.no-this.no; //비교 기준 값이 같은 경우
            }
            return x;
    }
}
