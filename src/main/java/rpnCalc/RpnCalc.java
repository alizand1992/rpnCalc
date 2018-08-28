package com.rpnCalc;

import com.rpnCalc.IllegalInputException;
import com.rpnCalc.InputQueue;

import java.util.Scanner;
import java.util.stream.IntStream;

public class RpnCalc {
    private static final String[] ALLOWED_OPS = {
        "+",
        "-",
        "*",
        "/"
    };

    private double result;
    private InputQueue inputs;

    public static void main(String[] args) {
        RpnCalc rpn = new RpnCalc();
        System.out.println("---=== Welcome To RealPage RPN Calculator ===---");

        if (args.length > 1) {
            String argsInput = String.join(" ", IntStream.range(1, args.length - 1)
                                           .mapToObj(i -> args[i])
                                           .toArray(String[]::new));

            System.out.println("q or EOF to exit > " + argsInput);
            try {
                rpn.addInput(argsInput);
                rpn.calculate();
            } catch (IllegalInputException e) {
                System.err.println("Illegal input detected, please enter a valid input...");
            }
        }

        rpn.requestInput();
    }

    public void requestInput() {
        Scanner scanner = new Scanner(System.in);
        String line = "";
        while (true) {
            line = scanner.nextLine();
            if (line.equals("q")) {
                return;
            }
            System.out.print("q or EOF to exit > ");
            try {
                this.addInput(line);
                this.calculate();
            } catch (IllegalInputException e) {
                System.err.println("Illegal input detected, please enter a valid input...");
            }
        }
    }

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
