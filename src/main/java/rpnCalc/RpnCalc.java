package com.rpnCalc;

import com.rpnCalc.InputQueue;

public class RpnCalc {
    private double result;
    private InputQueue inputs;

    public RpnCalc() {
        this("");
    }

    public RpnCalc(String input) {
        inputs = new InputQueue(input);
    }

    public void addInput(String input) {

    }

    public void calculate() {

    }

    public double getResult() {
        return result;
    }
}
