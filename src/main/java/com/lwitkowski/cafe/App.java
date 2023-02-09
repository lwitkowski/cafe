package com.lwitkowski.cafe;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Invalid arguments, will exit now. Args: " + Arrays.asList(args));
            System.exit(-1);
        }
        var order = OrderParser.parse(args[0]).thatWillBeAll();
        System.out.println(order.receipt());
    }
}
