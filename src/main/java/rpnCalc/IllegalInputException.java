package rpnCalc;

import java.lang.Exception;

public class IllegalInputException extends Exception {
    public IllegalInputException() {
        super("");
    }

    public IllegalInputException(String message) {
        super(message);
    }
}
