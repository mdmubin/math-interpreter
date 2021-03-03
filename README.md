# Java Math Interpreter

This is a math interpreter that can evaluate simple arithmetic expressions, written from scratch in Java. The interpreter analyzes and processes the input in the Lexer and the Parser respectively.

This is my attempt to create a version of [David Callanan's](https://github.com/davidcallanan) interpreter that was written in Python and can be found [here](https://github.com/davidcallanan/py-simple-math-interpreter).


## The Lexer
The Lexer groups the input characters into small segments called tokens and identifies the type of each token. Whitespaces are ignored by the lexer.
As an example, the characters in the input `12 + 24` are grouped into the tokens `NUMBER:12`, `PLUS`, and `NUMBER:24`.


## The Parser
The parser takes the sequence of tokens generated by the Lexer and anazlyzes what needs to happen in what order. 

When the parser sees `NUMBER`, followed by `PLUS`, followed by `NUMBER`, it passes on that the two numbers should be added together. In the case of a multiply operation added into the mix, the parser can determine that the two numbers next to the multiply operator should be multiplied first before the addition takes place.

The result, respresented as a tree, is then pased on to the interpreter.


## The Interpreter
The interpeter simply does what's intended according to the parser's results, and contains the code for all the different math operations.

The interpeter could be swapped out for a compiler which generates machine-readable code that your computer can later execute, or could be swapped out for a transpiler which generates code for another language.


---
**Tested on OpenJDK 11**
