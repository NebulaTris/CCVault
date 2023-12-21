package InfixToPostfix;
/*
 * Algorithm for Infix to Postfix:
 * 1. Create a stack to store operators and paranthesis.
 * 2. Scan the infix expression from left to right.
 * 3. If the scanned character is an operand, output it.
 * 4. Else,
 *     4.1 If the precedence of the scanned operator is greater than the precedence of the operator in the stack(or the stack is empty or the stack contains a '('), push it.
 *     4.2 Else, Pop all the operators from the stack which are greater than or equal to in precedence than that of the scanned operator. After doing that Push the scanned operator to the stack. (If you encounter parenthesis while popping then stop there and push the scanned operator in the stack.) 
 * 5. If scanned character is an '(', push it to the stack.
 * 6. If scanned character is an ')', pop the stack and output it until a '(' is encountered, and discard both the parenthesis.
 * 7. Repeat steps 2-6 until infix expression is scanned.
 * 8. Print the output
 * 9. Pop and output from the stack until it is not empty.
 * 
 */

import java.util.ArrayList;
import java.util.Scanner;

class CharArrayList {
    private ArrayList<Character> list;

    public CharArrayList() {
        this.list = new ArrayList<>();
    }

    public void add(char data) {
        list.add(data);
    }

    public char remove() {
        if (!isEmpty()) {
            return list.remove(list.size() - 1);
        } else {
            throw new IllegalStateException("List is empty. Cannot remove.");
        }
    }

    public char get(int index) {
        return list.get(index);
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
}

public class InfixToPostfix {
    private static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^';
    }

    private static int getPrecedence(char operator) {
        switch (operator) {
            case '^':
                return 3;
            case '*':
            case '/':
                return 2;
            case '+':
            case '-':
                return 1;
            default:
                return 0;
        }
    }

    public static String infixToPostfix(String infixExpression) {
        StringBuilder postfix = new StringBuilder();
        CharArrayList stack = new CharArrayList();

        for (int i = 0; i < infixExpression.length(); i++) {
            char currentChar = infixExpression.charAt(i);

            if (Character.isLetterOrDigit(currentChar)) {
                postfix.append(currentChar);
            } else if (currentChar == '(') {
                stack.add(currentChar);
            } else if (currentChar == ')') {
                while (!stack.isEmpty() && stack.get(stack.size() - 1) != '(') {
                    postfix.append(stack.remove());
                }
                stack.remove(); // Remove the '('
            } else if (isOperator(currentChar)) {
                while (!stack.isEmpty() && getPrecedence(currentChar) <= getPrecedence(stack.get(stack.size() - 1))) {
                    postfix.append(stack.remove());
                }
                stack.add(currentChar);
            }
        }

        while (!stack.isEmpty()) {
            postfix.append(stack.remove());
        }

        return postfix.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the infix expression:");
        String infixExpression = scanner.nextLine();

        String postfixExpression = infixToPostfix(infixExpression);

        System.out.println("Infix Expression: " + infixExpression);
        System.out.println("Postfix Expression: " + postfixExpression);
    }
}


