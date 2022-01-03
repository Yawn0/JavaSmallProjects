package Aste;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class Prova {

    public static void main(String[ ] args){

        ArrayList<Integer> a = new ArrayList<>();
        a.add(1);
        a.add(2);
        a.add(3);

        List<String> nomi = new ArrayList<>();

        for (String sString : nomi){
            System.out.println(sString);
        }

        nomi.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });

        nomi.forEach(s -> System.out.println(s));   //TYPE INFERENCE

        nomi.forEach(System.out::println);  // METHOD REFERENCE

        Iterator<Integer> s = a.iterator();

        while(s.hasNext()){
            System.out.println(s.next());
        }


    }
}

