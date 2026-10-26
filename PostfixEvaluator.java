import java.util.Scanner;

/** STUDENT FILE: Complete evaluate. Leave all other project files unchanged. */
public class PostfixEvaluator {
    /**
     * Evaluates a space-separated postfix expression using integer arithmetic.
     * Supported operators: +, -, *, / (integer division).
     *
     * @throws PostFixException for blank, malformed, or invalid expressions
     * @throws ArithmeticException for division by zero
     */
    public static int evaluate(String expression) {
        if (expression == null || expression.trim().isEmpty()) {
            throw new PostFixException("Expression must not be blank.");
        }

        BoundedStackInterface<Integer> stack = new ArrayStack<>(100);
        String[] items = expression.trim().split("\\s+");
        if(items.length < 3){
            throw new PostFixException("Malformed Equation");
        }
        if(items[0].equals("/") || items[0].equals("+") || items[0].equals("*") || items[0].equals("-") || items[1].equals("/") || items[1].equals("+") || items[1].equals("*") || items[1].equals("-")){
            throw new PostFixException("Malformed Equation");
        }
        for(int i = 0; i < items.length; i++){
            String current = items[i];
                if(!(current.equals("/") || current.equals("*") || current.equals("-") || current.equals("+"))){
                    try{
                        int num = Integer.parseInt(current);
                        stack.push(num);
                    }
                    catch(NumberFormatException e){
                        throw new PostFixException("Invalid token: " + current);
                    }
                }
                else{
                    try{
                        int num2 = stack.top();
                        stack.pop();
                        int num1 = stack.top();
                        stack.pop();
                        if(current.equals("+")){
                            stack.push(num1 + num2);
                        }
                        else if(current.equals("-")){
                            stack.push(num1 - num2);
                        }
                        else if(current.equals("/")){
                            if(num2 == 0){
                               throw new ArithmeticException("Division by zero");
                            }
                            stack.push(num1 / num2);
                        }
                        else if(current.equals("*")){
                            stack.push(num1 * num2);
                        }
                    }
                    catch(StackUnderflowException e){
                        throw new PostFixException("Malformed postfix expression.");
                    }
            }
        }
        

        // TODO 1: Process tokens one at a time.
        // TODO 2: If a token is an integer, push it onto the stack.
        // TODO 3: If it is +, -, *, or /, pop two operands.
        //         IMPORTANT: the first popped number is the RIGHT operand.
        // TODO 4: Calculate and push the result.
        // TODO 5: Reject unknown tokens and expressions with too few operands.
        // TODO 6: After processing, require EXACTLY one result on the stack.
        //         Catch stack underflow/overflow or check before operations.
        // Close the Scanner when you are finished.

        if (stack.isEmpty()) {
            throw new PostFixException("Malformed postfix expression.");
        }
        int result = stack.top();
        stack.pop();
        if (!stack.isEmpty()) {
            throw new PostFixException("Malformed postfix expression.");
        }
        return result;
    }
}
