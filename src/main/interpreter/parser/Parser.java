package main.interpreter.parser;


import main.interpreter.lexer.Token;

import java.util.ArrayList;
import java.util.ListIterator;


/**
 * Parses the tokens from user input and generates a token tree
 */
public class Parser {
    // to iterate over all tokens in the token list passed from the lexer
    private static ListIterator<Token> tokenIter;

    /**
     * Parse the list of tokens passed in and build a tree to evaluate results
     *
     * @param tokenList ArrayList of all the valid tokens to be parsed
     */
    public static TokenNode parseTokens(ArrayList<Token> tokenList) throws Exception {
        tokenIter = tokenList.listIterator();
        TokenNode result = expression(tokenIter.next());

        if (tokenIter.hasNext()) // unreachable / unexpected tokens found
            throw new Exception();

        return result;
    }

    /**
     * Create an expression from the generated tokens of the input
     */
    static TokenNode expression(Token current) throws Exception {
        TokenNode currentExpr = term(current);

        while (tokenIter.hasNext()) {
            current = tokenIter.next();
            // new terms encountered
            if (current.type == Token.TokenType.PLUS) {
                currentExpr = new TokenNode(Token.TokenType.PLUS, currentExpr, term(tokenIter.next()));
            } else if (current.type == Token.TokenType.MINUS) {
                currentExpr = new TokenNode(Token.TokenType.MINUS, currentExpr, term(tokenIter.next()));
            }
            // unexpected tokens found
            else {
                tokenIter.previous();
                break;
            }
        }
        return currentExpr;
    }

    /**
     * Create terms from the factors in the expression
     */
    static TokenNode term(Token current) throws Exception {
        TokenNode currentTerm = factor(current);

        while (tokenIter.hasNext()) {
            current = tokenIter.next();
            // new factors / expressions encountered
            if (current.type == Token.TokenType.MULTIPLY) {
                currentTerm = new TokenNode(Token.TokenType.MULTIPLY, currentTerm, factor(tokenIter.next()));
            } else if (current.type == Token.TokenType.DIVIDE) {
                currentTerm = new TokenNode(Token.TokenType.DIVIDE, currentTerm, factor(tokenIter.next()));
            } else if (current.type == Token.TokenType.PAREN_OPEN) {
                currentTerm = new TokenNode(Token.TokenType.MULTIPLY, currentTerm, expression(current));
            }
            // unexpected token found
            else {
                tokenIter.previous();
                break;
            }
        }
        return currentTerm;
    }

    /**
     * Create a tokenNode for the factors in the terms of the expression
     */
    static TokenNode factor(Token current) throws Exception {
        switch (current.type) {
            // an bracket enclosed expression found.
            case PAREN_OPEN: {
                TokenNode expr = expression(tokenIter.next());

                if (tokenIter.hasNext()) // iterate once more as PAREN_CLOSE encountered
                    tokenIter.next();

                return expr;
            }
            // a +ve expression/number found
            case PLUS: {
                return factor(tokenIter.next());
            }
            // a -ve expression/number found
            case MINUS: {
                TokenNode next = factor(tokenIter.next());
                next.negateNode();
                return next;
            }
            // a number found
            case NUMBER: {
                return new TokenNode(current);
            }
            // unexpected token at start
            default: {
                throw new Exception();
            }
        }
    }
}