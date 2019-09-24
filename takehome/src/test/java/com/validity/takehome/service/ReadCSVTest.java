package com.validity.takehome.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReadCSVTest {

    @Autowired
    DedupService dedupService;

    private String srcPath = "/Users/jrwang8/Downloads/Validity_take_home_exercise/normal.csv";

    List<String[]> res;

    @Test
    public void readFileTest() {

        String s = "xxxx";

        s.hashCode();

//        res = readCSV.readFile(srcPath);

        System.out.println(res.size());

    }


    @Test
    public void checkDuplicate() {

//        res = readCSV.readFile(srcPath);

        if(res == null || res.size() == 0) {
            System.out.println("error");
            return;
        }

        List<String[]> list = dedupService.checkDuplicate(res);

        System.out.println("=================");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("=================");

        for(String[] strs : list) {
            for(String str : strs) {
                System.out.print("||   ");
                System.out.print(str);
            }
            System.out.println();
        }

        System.out.println(list.size());

//        System.out.println(res);
    }

    @Test
    public void distance() {
//        res = readCSV.readFile(srcPath);


    }
}