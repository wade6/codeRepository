package com.code.repository.study.goodcode;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class uniqueElement {

    @Test
    public void uniqueElement() {
        List<Object> list = new ArrayList<>();
        list.add("12");
        list.add("12");
        int size = list.size();
        if (size==0) {
            System.out.println("list is null");
        }
        Object first = list.get(0);
        for ( int i=1; i<size; i++ ) {
            if ( list.get(i)!=first ) {
                System.out.println("list element is not unique");
            }
        }
        System.out.println("===boject is :"+first);
    }
}
