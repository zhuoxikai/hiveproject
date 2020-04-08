package com.example.hiveproject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.Serializable;
import java.util.Map;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SpringStartTests implements Serializable {


    HiveApplication hiveApplication;

    /*    @Autowired
        HiveApplication hiveApplication;*/
    @Test
    public void test() throws Exception {
//        hiveApplication.create_in();
//        hiveApplication.create_out();
//        hiveApplication.create_od();
//        hiveApplication.insert_in();
//        hiveApplication.insert_out();
//        hiveApplication.insert_od();
        hiveApplication.drop_in();
        hiveApplication.drop_out();
        hiveApplication.drop_od();
    }

}