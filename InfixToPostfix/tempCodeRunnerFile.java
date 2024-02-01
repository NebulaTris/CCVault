package InfixToPostfix;


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
