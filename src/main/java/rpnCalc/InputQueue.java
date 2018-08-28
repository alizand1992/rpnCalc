package rpnCalc;

import java.util.LinkedList;
import java.util.Stack;

public class InputQueue {
    private Stack<String> buffer;
    private LinkedList<String> inputs;

    public InputQueue() {
        this("");
    }

    public InputQueue(String input) {
        inputs = new LinkedList<String>();
        buffer = new Stack<String>();
        addInput(input);
    }

    public int size() {
        return inputs.size() + buffer.size();
    }

    public String getOperator() {
        return inputs.poll();
    }

    public String peek() {
        return inputs.peek();
    }

    public String pop() {
        return inputs.poll();
    }

    public String[] getOperands() {
        String[] temp = {
            buffer.pop(),
            buffer.pop()
        };

        return temp;
    }

    public void moveToBuffer() {
        while (inputs.size() != 0 && !RpnCalc.isOperator(inputs.peek())) {
            buffer.push(inputs.pop());
        }
    }

    public boolean hasEnoughOperands() {
        return buffer.size() >= 2;
    }

    public String printInputStack() {
        while (!buffer.empty()) {
            inputs.addFirst(buffer.pop());
        }

        return String.join(" ", inputs);
    }

    public String getResult() {
        if (buffer.size() == 1) {
            return buffer.peek();
        } else {
            return "0.0";
        }
    }

    public void addToHead(String input) {
        if (input.trim().equals("")) {
            return;
        }
        buffer.push(input);
    }

    public void addInput(String input) {
        if (input.equals("")) {
            return;
        }

        for (String token : input.split(" ")) {
            inputs.add(token);
        }
    }
}
