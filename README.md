# Reverse Polish Notation Calculator
This is an implementation if Reverse Polish Notation Calculator in Java. 

This implementation consists of 3 classes.
* InputQueue:
  * I used a stack and a linkedlist in this class. the linkedlist will store all of the inputs, once the values need to be calculated it will transfer all values to the buffer, until the method reaches an operator. This way RpnCalc is capable of calculating values from the middle of the input.
* RpnCalc
  * This is the engine for the calculator.
  * All of the checks are abstracted out to this class.
  * Values are calculated then pushed back to the stack portion of the input to be used in the next operation.
* IllegalInputException
  * This class will handle errors made from inputs that are not allowed. It will handle errors for not having enough operands and having the wrong input.

## Requirements
- Java 8
  - OpenJDK 1.8.0
- Maven

## Installation Instructions
1. Install Java 8:
  - `sudo apt-get install openjdk-8-jdk`
  - Answer Yes when prompted.
2. Install Maven testing library:
  - `sudo apt-get install maven`
  - Answer Yes when prompted.
3. Clone this repository into your local machine:
  - `git clone https://github.com/jeremywatkins/rpnCalc.git`

## Run Tests
1. Navigate to the root directory of the project
2. `mvn test`

## To Compile (Ubuntu)
1. Please navigate to `src/main/java` under the rpnCalc directory
2. `javac rpnCalc/RpnCalc.java rpnCalc/InputQueue.java rpnCalc/IllegalInputException.java`

## To Use
This implementation accepts arguments in console as input so there are two way of using it.
1. Please navigate to `src/main/java`

### Using Arguments:
`java rpnCalc.RpnCalc [arguments]`
example:
`java rpnCalc.RpnCalc 1 2 3 + 3 + +`

After running this, the calculator will move to the standard mode where it accepts inputs  from the keyboard. The inputs can be either a string of operands and operators or just one  operand or operator per entry.

### Example output:

```---=== Welcome To RealPage RPN Calculator ===---
---=== Supported Ops: + - * /   AC to clear ===---
q or EOF to exit > 1
Input Buffer: 1
q or EOF to exit > +
Input Buffer: 1 +
q or EOF to exit > 2
Not enought operands before the operator.
Input Buffer: 1 2
q or EOF to exit > 3
Input Buffer: 1 2 3
q or EOF to exit > +
Input Buffer: 1 5
q or EOF to exit > 8
Input Buffer: 1 5 8
q or EOF to exit > +
Input Buffer: 1 13
q or EOF to exit > +
Result: 14
q or EOF to exit > q
Goodbye!
```

**Note:** Please note that if an illegal value or not enough operands before an operator, at most one extra operator will stay in the input until a number is entered. then it will clear.
Ex:
```---=== Welcome To RealPage RPN Calculator ===---
---=== Supported Ops: + - * /   AC to clear ===---
q or EOF to exit > 1
Input Buffer: 1
q or EOF to exit > +
Input Buffer: 1 +
q or EOF to exit > +
Not enought operands before the operator.
Input Buffer: 1 +
q or EOF to exit > 1
Not enought operands before the operator.
Input Buffer: 1 1
q or EOF to exit >
```

## Reasoning behind my technical choice:
I decided to use String within the InputQueue because I wanted to be able to store any type of input. This might not be significant now, but this makes the InputQueue more flexible, enabling RpnCalc to expand beyond what it is now, a lot easier.

Additionally I used two different data structures (Stack and LinkedList) within InputQueue because early in the project, I realized that RPN style causes the calculations to start happening in the middle. and having just one data structure to store inputs would make the processing more difficult. For example if you have `1 2 3 + +` you are first processing `2 3 +` then `1 result +`

### BigDecimal:
I Had picked BigDecimal class instead of double because of issues with double percision error. For instance adding 0.1 and 0.2 will not result in 0.3. A drawback of using BigDecimal is that on divisions where the default scale is not sufficient to represent an acurate division result, it will throw an ArithmeticException. To resolve this issue, I decided to use scale of 15 and a rounding mode of HALF_UP. The 15 scale will result to up to 15 decimals, and HALF_UP will round up the the last decimal if it needs to.

### Big O Notation:
Finally going through my code, I have found that my code has a performance of O(N) at the slowest since all of the methods used/created are linear.

**Note:** Please note that there are a few places where I have used trim() within a loop which results in O(N\*M), N being number of tokens, and M being the length of each token (worst case of the entire input being spaces, since Java 8's trim() does not traverse the entire string in other cases), we can easily assume that M is negligible so those methods are still O(N).
