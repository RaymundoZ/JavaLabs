package com.raymundo.lab5.implementations;

import com.raymundo.lab5.interfaces.HelloInterface;

public class ImplHello implements HelloInterface {

    @Override
    public void printHello() {
        System.out.println("Hello");
    }

}
