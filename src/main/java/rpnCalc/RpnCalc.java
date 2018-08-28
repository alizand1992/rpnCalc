package rpnCalc;

import rpnCalc.IllegalInputException;
import rpnCalc.InputQueue;

import java.math.BigDecimal;

import java.util.Scanner;
import java.util.stream.IntStream;

public class RpnCalc {
    private static final String[] ALLOWED_OPS = {
        "+",
        "-",
        "*",
        "/"
    };

    private BigDecimal result;
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
            System.out.print("q or EOF to exit > ");

            line = scanner.nextLine();

            if (line.equals("q")) {
                return;
            }

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
        result = new BigDecimal(0);
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
            inputs.moveToBuffer();

            if (inputs.peek() == null) {
                break;
            }

            if (!inputs.hasEnoughOperands()) {
                cleanUp();
                throw new IllegalInputException("Not enought operands before the operator.");
            }

            char op = inputs.getOperator().charAt(0);;
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
        }

        if (inputs.size() == 1) {
            System.out.println("Result: " + inputs.getResult());
            result = new BigDecimal(inputs.getResult());
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
            System.err.println("Division by 0 is not allowed. Cleaning up now...");
            cleanUp();
            throw new IllegalInputException("Division by 0 is not allowed.");
        }

        return lhs.divide(rhs);
    }

    private void cleanUp() {
        inputs = new InputQueue();
        result = new BigDecimal(0);
    }

    private void isAllowed(String input) throws IllegalInputException {
        input = input.trim();
        for (String token : input.split(" ")) {
            boolean isOp = isOperator(token);
            if (!isOp) {
                try {
                    Double.parseDouble(token);
                } catch (Exception e) {
                    throw new IllegalInputException("Illegal Input Detected");
                }
            }
        }
    }

    public static boolean isOperator(String token) {
        for (String op : ALLOWED_OPS) {
            if (op.equals(token)) {
                return true;
            }
        }

        return false;
    }
}
