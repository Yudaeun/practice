package org.example.day5;

import java.util.ArrayList;
import java.util.Collections;

public class WriterTest {
    public static void main(String[] args) {
        ArrayList<Writer> list=new ArrayList<>();
        list.add(new Writer(3,"jane","letros","white house"));
        list.add(new Writer(1,"dane","setros","green house"));
        list.add(new Writer(2,"mane","qetros","red house"));
        list.add(new Writer(4,"rane","getros","pink house"));
        list.add(new Writer(5,"sane","betros","purple house"));

        Collections.sort(list);
        for(Writer writer:list){
            System.out.println(writer);
        }
    }
}
