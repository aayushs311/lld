package ticTacToe.exception;

public class GameDrawnException extends RuntimeException {
    public GameDrawnException(String s) {
        super(s);
    }
}
