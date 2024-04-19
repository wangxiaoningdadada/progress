package com.progress.progressapi.test;

import java.util.ArrayList;

/**
 * description: test1
 * date: 2023/8/31 16:54
 *
 * @author: wangxiaoning
 */
public class StringTest {

    public static void main(String[] args) {
        print();
        print("a");
        print("b", "c");
        print(new String[] {"d", "e"});

        String[] scopes = {"read:mobile:histories", "read:mobile:histories2"};
        System.out.println(String.join(" ", scopes));
    }

    public static void print(String... args) {
        for (String str: args) {
            System.out.println(str);
        }
    }


}
