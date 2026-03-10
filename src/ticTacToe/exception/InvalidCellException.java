package ticTacToe.exception;

public class InvalidCellException extends RuntimeException {
    public InvalidCellException(String message) {
        super(message);
    }
}
