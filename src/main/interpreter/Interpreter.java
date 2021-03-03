package main.interpreter;


import main.interpreter.parser.Parser;
import main.interpreter.lexer.Tokenizer;
import main.interpreter.parser.TokenNode;

public class Interpreter {
    /**
     * Calls the Tokenizer and Parser, and returns the evaluated result of the parsed token tree
     */
    public static double getResult(String inputString) throws Exception {
        TokenNode expressionTree = Parser.parseTokens(Tokenizer.generateTokens(inputString));
        return evaluateExpressionTree(expressionTree);
    }

    /**
     * Evaluates the result of the mathematical expression by recursively going through all the TokenNodes in the Parsed
     * Token Tree and evaluate the results at each node
     *
     * @return the result of the mathematical expression
     */
    private static double evaluateExpressionTree(TokenNode node) {
        switch (node.type) {
            case NUMBER: {
                return node.isNegative() ? -node.nodeValue : node.nodeValue;
            }
            case MULTIPLY: {
                return evaluateExpressionTree(node.operand1) * evaluateExpressionTree(node.operand2);
            }
            case DIVIDE: {
                return evaluateExpressionTree(node.operand1) / evaluateExpressionTree(node.operand2);
            }
            case PLUS: {
                return evaluateExpressionTree(node.operand1) + evaluateExpressionTree(node.operand2);
            }
            case MINUS: {
                return evaluateExpressionTree(node.operand1) - evaluateExpressionTree(node.operand2);
            }
            default: {
                // if somehow an invalid token gets processed by the Parser/Tokenizer
                throw new InternalError("Unknown Error Encountered");
            }
        }
    }
}
