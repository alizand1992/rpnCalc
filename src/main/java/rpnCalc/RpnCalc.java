package rpnCalc;

import rpnCalc.IllegalInputException;
import rpnCalc.InputQueue;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class RpnCalc {

    private static final HashSet<String> ALLOWED_OPS = getAllowedOps();
    private static HashSet<String> getAllowedOps() {
        HashSet<String> ops = new HashSet<>();
        ops.add("+");
        ops.add("-");
        ops.add("*");
        ops.add("/");
        ops.add("AC");

        return ops;
    }

    private BigDecimal result;
    private InputQueue inputs;

    public static void main(String[] args) {
        RpnCalc rpn = new RpnCalc();
        System.out.println("---=== Welcome To RealPage RPN Calculator ===---");
        System.out.println("---=== Supported Ops: + - * /   \"AC\" to clear ===---");

        if (args.length > 1) {
            ArrayList<String> argsInput = new ArrayList<>(Arrays.asList(args));
            try {
                rpn.addInput(argsInput);
                rpn.calculate();
            } catch (IllegalInputException e) {
                System.err.println(e.getMessage());
            }
        }

        rpn.requestInput();
    }

    public static boolean isOperator(String token) {
        return ALLOWED_OPS.contains(token);
    }

    public void requestInput() {
        Scanner scanner = new Scanner(System.in);
        String line = "";
        try {
            while (true) {
                System.out.print("q or EOF to exit > ");

                line = scanner.nextLine();

                if (line.equals("q")) {
                    scanner.close();
                    System.out.println("Goodbye!");
                    return;
                }

                try {
                    this.addInput(line);
                    this.calculate();
                } catch (IllegalInputException e) {
                    System.err.println(e.getMessage());
                    System.out.println("Input Buffer: " + inputs.printInputStack());
                }
            }
        } catch (Exception e) {
            System.out.println("\nGoodbye!");
            return;
        } finally {
            scanner.close();
        }
    }

    public RpnCalc() {
        this("");
    }

    public RpnCalc(String input) {
        inputs = new InputQueue(input);
        result = new BigDecimal(0);
    }

    public void addInput(String input) throws IllegalInputException {
        if (input.equals("")) {
            return;
        }

        for (String token : input.split(" ")) {
            token = token.trim();
            if (token.equals(""))
                continue;

            isAllowed(token);
            inputs.addInput(token);
        }
    }

    public void addInput(ArrayList<String> inputs) throws IllegalInputException {
        if (inputs.size() == 0) {
            return;
        }

        for (String input : inputs) {
            addInput(input);
        }
    }

    public void calculate() throws IllegalInputException {
        if (inputs.size() < 3) {
            System.out.println("Input Buffer: " + inputs.printInputStack());
            return;
        }

        while (inputs.size() >= 3) {
            inputs.moveToBuffer();

            if (inputs.peek() == null) {
                break;
            }

            if (!inputs.hasEnoughOperands()) {
                removeIllegalInput();
                throw new IllegalInputException("Not enought operands before the operator.");
            }

            String fullOp = inputs.getOperator();

            if (!fullOp.equals("AC")) {
                char op = fullOp.charAt(0);
                String[] operands = inputs.getOperands();
                BigDecimal lhs = new BigDecimal(operands[1]);
                BigDecimal rhs = new BigDecimal(operands[0]);

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
            } else {
                cleanUp();
            }
        }

        if (inputs.size() == 1) {
            result = new BigDecimal(inputs.getResult());
            System.out.println("Result: " + getResult());
        } else {
            System.out.println("Input Buffer: " + inputs.printInputStack());
        }
    }

    public BigDecimal getResult() {
        return result;
    }

    private BigDecimal add(BigDecimal lhs, BigDecimal rhs) {
        return lhs.add(rhs);
    }

    private BigDecimal deduct(BigDecimal lhs, BigDecimal rhs) {
        return lhs.subtract(rhs);
    }

    private BigDecimal multiple(BigDecimal lhs, BigDecimal rhs) {
        return lhs.multiply(rhs);
    }

    private BigDecimal divide(BigDecimal lhs, BigDecimal rhs) throws IllegalInputException {
        if (rhs.equals(new BigDecimal(0.0))) {
            removeIllegalInput();
            throw new IllegalInputException("Division by 0 is not allowed. Cleaning up now...");
        }

        BigDecimal result = new BigDecimal(0);
        try {
            result = lhs.divide(rhs);
        } catch (ArithmeticException e) {
            result = lhs.divide(rhs, 15, RoundingMode.HALF_UP);
        }
        return result;
    }

    private void cleanUp() {
        inputs = new InputQueue();
        result = new BigDecimal(0);
    }

    private void removeIllegalInput() {
        inputs.pop();
    }

    private void isAllowed(String input) throws IllegalInputException {
        if (!isOperator(input)) {
            try {
                Double.parseDouble(input);
            } catch (Exception e) {
                throw new IllegalInputException("Illegal Input Detected");
            }
        }
    }
}
