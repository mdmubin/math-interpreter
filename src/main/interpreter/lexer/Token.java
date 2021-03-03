package main.interpreter.lexer;


/**
 * Class representing the mathematical tokens. All tokens have a type, {@link  Token.TokenType}, and a Double value.
 * If the lexical token is not a number, then it has the value Double.NaN
 **/
public class Token {
    /**
     * An enum representing all the valid token types.
     * TokenTypes are either mathematical operators or a Number
     */
    public enum TokenType {
        NUMBER,
        PLUS,
        MINUS,
        MULTIPLY,
        DIVIDE,
        PAREN_OPEN,
        PAREN_CLOSE
    }

    public final TokenType type;
    public final double val;

    /**
     * Create a new token with double value, {@code val}
     */
    public Token(TokenType type, double val) {
        this.type = type;
        this.val = val;
    }

    /**
     * Create a new token with no double value
     */
    public Token(TokenType type) {
        this.type = type;
        this.val = Double.NaN;
    }

    @Override
    public String toString() {
        return String.format("Token{ type: %s, value: %s }", this.type, this.val);
    }
}
