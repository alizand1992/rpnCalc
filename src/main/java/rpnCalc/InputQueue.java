package com.rpnCalc;

import java.util.LinkedList;

public class InputQueue {
    private LinkedList<String> inputs;

    public InputQueue() {
        this("");
    }

    public InputQueue(String input) {
        inputs = new LinkedList<String>();
        addInput(input);
    }

    public int size() {
        return inputs.size();
    }

    public String pop() {
        return inputs.poll();
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
