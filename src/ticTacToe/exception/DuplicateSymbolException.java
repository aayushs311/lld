package ticTacToe.exception;

public class DuplicateSymbolException extends RuntimeException{
    public DuplicateSymbolException(String s) {
        super(s);
    }
}
