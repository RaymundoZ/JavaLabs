package com.raymundo.lab2;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A utility class that calculates mathematical expressions in string form
 *
 * @author RaymundoZ
 */
public class ExpressionResolver {

    /**
     * A method that resolves math expression using RegEx.
     * The expression splits into parts and evaluates by {@link ExpressionResolver#resolvePart(String, char)} method
     *
     * @param expression Math expression
     * @return Calculation result
     */
    public static double resolve(String expression) {
        checkExpression(expression);
        Pattern pattern = Pattern.compile("\\((\\d+\\.?\\d*[\\+\\-\\*\\/]?)+(\\d+\\.?\\d*[\\+\\-\\*\\/]?)*\\)");
        Matcher matcher = pattern.matcher(expression);
        expression = matcher.replaceAll(matchResult -> Double.toString(resolve(matchResult.group().replaceAll("[\\(\\)]", ""))));

        expression = expression.replaceAll("[\\(\\)]", "");

        pattern = Pattern.compile("\\d+\\.?\\d*\\*\\-?\\d+\\.?\\d*");
        matcher = pattern.matcher(expression);
        expression = matcher.replaceAll(matchResult -> resolvePart(matchResult.group(), '*'));

        pattern = Pattern.compile("\\d+\\.?\\d*\\/\\-?\\d+\\.?\\d*");
        matcher = pattern.matcher(expression);
        expression = matcher.replaceAll(matchResult -> resolvePart(matchResult.group(), '/'));

        pattern = Pattern.compile("\\d+\\.?\\d*\\-\\-?\\d+\\.?\\d*");
        matcher = pattern.matcher(expression);
        expression = matcher.replaceAll(matchResult -> resolvePart(matchResult.group(), '-'));

        pattern = Pattern.compile("\\d+\\.?\\d*\\+\\-?\\d+\\.?\\d*");
        matcher = pattern.matcher(expression);
        expression = matcher.replaceAll(matchResult -> resolvePart(matchResult.group(), '+'));

        return Double.parseDouble(expression);
    }

    /**
     * A method that is called by {@link ExpressionResolver#resolve(String)} method to evaluate part of an expression using RegEx
     *
     * @param part      Partial expression
     * @param operation Math operation
     * @return Calculation result
     */
    private static String resolvePart(String part, char operation) {
        String[] numbers;
        if (part.startsWith("-")) {
            String temp = part.substring(1);
            numbers = temp.split("\\" + operation);
            numbers[0] = "-" + numbers[0];
        } else
            numbers = part.split("\\" + operation);
        switch (operation) {
            case '+':
                return Double.toString(Double.parseDouble(numbers[0]) + Double.parseDouble(numbers[1]));
            case '-':
                return Double.toString(Double.parseDouble(numbers[0]) - Double.parseDouble(numbers[1]));
            case '*':
                return Double.toString(Double.parseDouble(numbers[0]) * Double.parseDouble(numbers[1]));
            case '/':
                return Double.toString(Double.parseDouble(numbers[0]) / Double.parseDouble(numbers[1]));
            default:
                return "";
        }
    }

    /**
     * A method that checks if the given expression is valid. Invokes {@link ExpressionResolver#checkBrackets(String)}
     * and {@link ExpressionResolver#checkOperations(String)} methods.
     * Throws {@link InvalidExpressionException} if one of them returns false
     *
     * @param expression Math expression
     */
    private static void checkExpression(String expression) {
        if (!checkBrackets(expression) || !checkOperations(expression))
            try {
                throw new InvalidExpressionException("Invalid expression");
            } catch (InvalidExpressionException e) {
                throw new RuntimeException(e);
            }
    }

    /**
     * A method that checks if brackets are valid in the given expression
     *
     * @param expression Math expression
     * @return true if brackets are valid, false otherwise
     */
    private static boolean checkBrackets(String expression) {
        Stack<Character> stack = new Stack<>();
        for (char c : expression.toCharArray())
            if (c == '(')
                stack.push(c);
            else if (c == ')') {
                if (stack.isEmpty())
                    return false;
                stack.pop();
            }
        return stack.isEmpty();
    }

    /**
     * A method that checks if operations are valid in the given expression
     *
     * @param expression Math expression
     * @return true if operations are valid, false otherwise
     */
    private static boolean checkOperations(String expression) {
        String ex = expression.replaceAll("[\\(\\)]", "");
        Pattern pattern = Pattern.compile("(^\\-?\\d+\\.?\\d*[\\+\\-\\*\\/]?)+(\\d+\\.?\\d*[\\+\\-\\*\\/]?)*");
        Matcher matcher = pattern.matcher(ex);
        return matcher.matches();
    }

}
