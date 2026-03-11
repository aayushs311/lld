package ticTacToe.service.strategy;

import ticTacToe.model.Board;
import ticTacToe.model.Game;
import ticTacToe.model.Move;
import ticTacToe.model.Player;

public interface WinnerCheckStrategy {
    Player checkWinner(Board board, Move move);
}
