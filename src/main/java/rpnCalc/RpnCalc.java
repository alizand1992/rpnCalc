package com.rpnCalc;

import com.rpnCalc.IllegalInputException;
import com.rpnCalc.InputQueue;

import java.util.ArrayList;

public class RpnCalc {
    private static final String[] ALLOWED_OPS = {
        "+",
        "-",
        "*",
        "/"
    };

    private double result;
    private InputQueue inputs;

    public RpnCalc() {
        this("");
    }

    public RpnCalc(String input) {
        inputs = new InputQueue(input);
    }

    public void addInput(String input) throws IllegalInputException {
        if (input.equals("")) {
            return;
        }

        isAllowed(input);
        inputs.addInput(input);
    }

    public void calculate() throws IllegalInputException {
        if (inputs.size() < 3) {
            return;
        }

        while (inputs.size() >= 3) {
            Double lhs = Double.parseDouble(inputs.pop());
            Double rhs = Double.parseDouble(inputs.pop());
            char op = inputs.pop().charAt(0);

            switch (op) {
            case '+':
                inputs.addToHead(add(lhs, rhs).toString());
                break;
            case '-':
                inputs.addToHead(deduct(lhs, rhs).toString());
                break;
            case '*':
                inputs.addToHead(multiple(lhs, rhs).toString());
                break;
            case '/':
                inputs.addToHead(divide(lhs, rhs).toString());
                break;
            }
        }

        result = Double.parseDouble(inputs.peek());
    }

    public double getResult() {
        return result;
    }

    private Double add(double lhs, double rhs) {
        return lhs + rhs;
    }

    private Double deduct(double lhs, double rhs) {
        return lhs - rhs;
    }

    private Double multiple(double lhs, double rhs) {
        return lhs * rhs;
    }

    private Double divide(double lhs, double rhs) throws IllegalInputException {
        if (rhs == 0.0) {
            System.err.println("Division by 0 is not allowed. Cleaning up now...");
            cleanUp();
            throw new IllegalInputException("Division by 0 is not allowed.");
        }

        return lhs / rhs;
    }

    private void cleanUp() {
        inputs = new InputQueue();
        result = 0;
    }

    private void isAllowed(String input) throws IllegalInputException {
        input = input.trim();
        for (String token : input.split(" ")) {
            boolean isOp = false;
            for (String op : ALLOWED_OPS) {
                if (op.equals(token)) {
                    isOp = true;
                    break;
                }
            }

            if (!isOp) {
                try {
                    Double.parseDouble(token);
                } catch (Exception e) {
                    throw new IllegalInputException("Illegal Input Detected");
                }
            }
        }
    }
}
