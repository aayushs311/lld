package ticTacToe.service;

import ticTacToe.exception.InvalidCellException;
import ticTacToe.model.*;
import ticTacToe.model.constants.BotDifficultyLevel;
import ticTacToe.model.constants.CellState;
import ticTacToe.model.constants.GameState;
import ticTacToe.service.strategy.BotPlayingStrategy;
import ticTacToe.service.strategy.BotPlayingStrategyFactory;
import ticTacToe.service.strategy.O1WinnerCheckStrategy;
import ticTacToe.service.strategy.WinnerCheckStrategy;

import java.util.Collections;
import java.util.List;

public class GameService {
    private WinnerCheckStrategy winnerCheckStrategy;

    public GameService(int size) {
        this.winnerCheckStrategy = new O1WinnerCheckStrategy(size);
    }
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

    public Move executeMove(Player player, Game game) {
//        BotDifficultyLevel botDifficultyLevel = bot.getBotDifficultyLevel();
        BotPlayingStrategy botPlayingStrategy = BotPlayingStrategyFactory.getBotPlayingStrategy(BotDifficultyLevel.EASY);
        return botPlayingStrategy.executeMove(player, game);
    }

    public Game createGame(List<Player> players, int size) {
        Board board = new Board(size);
        return new Game(board, players);
    }

    public Game startGame(Game game) {
        game.setGameState(GameState.IN_PROGRESS);
        Collections.shuffle(game.getPlayers());
        return game;
    }

    public GameState checkWinner(Game game, Move currentMove) {
        Player player = winnerCheckStrategy.checkWinner(game.getBoard(), currentMove);
        if(player != null) {
            return GameState.WINNER;
        } else {
            return GameState.IN_PROGRESS;
        }
    }
}
