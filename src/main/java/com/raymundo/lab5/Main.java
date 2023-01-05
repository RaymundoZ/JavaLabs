package com.raymundo.lab5;

public class Main {

    public static void main(String[] args) {
        Bean bean = (Bean) new Injector().inject(new Bean());
        bean.test();
    }

}
