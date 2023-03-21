package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Car {
    @Autowired
    private static Service service;

    public static void start (String[] args) {

        String command = "", text = "";
        boolean isE = false, isS = false;
        for (String s : args) {
            if (s.equals("-e")) {
                isE = true;
            } else if (s.equals("-s")) {
                isS = true;
            } else {
                if (isE) {
                    command = s;
                    isE = false;
                } else if (isS) {
                    text = s;
                    isS = false;
                }
            }
        }
        System.out.println(text);
        if (command.equals("add")) {
            System.out.println(service.post1(text));
        } else if (command.equals("search")) {
            System.out.println(service.name(text));
        } else {
            System.out.println("Произошла ошибка, проверьте правильность введения запроса");
        }
    }
}

