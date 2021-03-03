package main.interpreter.parser;


import main.interpreter.lexer.Token;

/**
 * Representation of a token for expression evaluation
 */
public class TokenNode {
    public final Token.TokenType type;
    public final TokenNode operand1, operand2;
    public double nodeValue;
    private boolean negative = false; //keeps track of whether a - has been added to negate the value held in this node

    /**
     * Creates a Number TokenNode with the value in {@link Token}.
     */
    TokenNode(Token token) {
        this.type = Token.TokenType.NUMBER;
        this.nodeValue = token.val;
        operand1 = operand2 = null;
    }

    /**
     * Create a new TokenNode for arithmetic operators.
     */
    TokenNode(Token.TokenType type, TokenNode operand1, TokenNode operand2) {
        this.type = type;
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.nodeValue = Double.NaN;
    }

    /**
     * negate the value of this node.
     */
    public void negateNode() {
        negative = !negative;
    }

    /**
     * Check if the value of the node has been negated
     */
    public boolean isNegative() {
        return negative;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        if (this.type == Token.TokenType.NUMBER) {
            res.append(this.nodeValue);
        } else {
            String format = "%s %c % s";
            switch (this.type) {
                case PLUS: {
                    res.append(String.format(format, operand1, '+', operand2));
                    break;
                }
                case MINUS: {
                    res.append(String.format(format, operand1, '-', operand2));
                    break;
                }
                case MULTIPLY: {
                    res.append(String.format(format, operand1, '/', operand2));
                    break;
                }
                case DIVIDE: {
                    res.append(String.format(format, operand1, '*', operand2));
                    break;
                }
            }
        }
        return res.toString();
    }
}