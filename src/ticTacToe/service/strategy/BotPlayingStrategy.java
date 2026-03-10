package ticTacToe.service.strategy;

import ticTacToe.model.Game;
import ticTacToe.model.Move;
import ticTacToe.model.Player;

public interface BotPlayingStrategy {
    Move executeMove(Player player, Game game);
}
