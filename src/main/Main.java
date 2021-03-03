package main;


import main.interpreter.Interpreter;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    /**
     * Runs the interpreter and prints result to console
     */
    public static void run() {
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();

            if (input.equals("exit")) System.exit(0);
            else if (input.equals("")) System.out.println(0);
            else {
                try {
                    System.out.println(Interpreter.getResult(input));
                } catch (Exception ignored) {
                    System.out.println("Invalid Input");
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Simple Arithmetic Interpreter. Enter an arithmetic expression[e.g.\"(2+3)*5\"] to evaluate the result.");
        System.out.println("type \"exit\" to quit program.\n");

        run();
    }
}
