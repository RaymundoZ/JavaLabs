package com.raymundo.lab5;

import com.raymundo.lab5.interfaces.ByeInterface;
import com.raymundo.lab5.interfaces.HelloInterface;

public class Bean {

    @AutoInjectable
    private HelloInterface helloInterface;

    @AutoInjectable
    private ByeInterface byeInterface;

    public void test() {
        helloInterface.printHello();
        byeInterface.printGoodBye();
    }

}
