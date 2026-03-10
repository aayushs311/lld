package ticTacToe.service;

import ticTacToe.exception.InvalidCellException;
import ticTacToe.model.*;
import ticTacToe.model.constants.BotDifficultyLevel;
import ticTacToe.model.constants.CellState;
import ticTacToe.service.strategy.BotPlayingStrategy;
import ticTacToe.service.strategy.BotPlayingStrategyFactory;

public class GameService {
    public Move executeMove(Player player, Game game, int row, int col) {
        Cell cell = game.getBoard().getCells().get(row).get(col);
        if(!cell.getCellState().equals(CellState.EMPTY)) {
            throw new InvalidCellException("Entered invalid cell for the move, please try again");
        }
        cell.setCellState(CellState.FULL);
        cell.setPlayer(player);
        Move move = new Move(cell, player);
        game.getMoves().add(move);
        game.getBoardState().add(game.getBoard().clone());
        return move;
    }

    public Move executeMove(Bot bot, Game game) {
        BotDifficultyLevel botDifficultyLevel = bot.getBotDifficultyLevel();
        BotPlayingStrategy botPlayingStrategy = BotPlayingStrategyFactory.getBotPlayingStrategy(botDifficultyLevel);
        return botPlayingStrategy.executeMove(bot, game);
    }
}
