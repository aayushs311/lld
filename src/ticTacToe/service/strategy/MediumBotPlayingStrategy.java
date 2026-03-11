package ticTacToe.service.strategy;

import ticTacToe.model.*;
import ticTacToe.model.constants.CellState;

import java.util.List;

public class MediumBotPlayingStrategy implements BotPlayingStrategy{
    public Move executeMove(Player player, Game game) {
        Board board = game.getBoard();
        Move move = null;
        for(List<Cell> cells: board.getCells()) {
            for(Cell cell: cells) {
                if(cell.getCellState().equals(CellState.EMPTY)) {
                    cell.setCellState(CellState.FULL);
                    cell.setPlayer(player);
                    move = new Move(cell, player);
                    game.getMoves().add(move);
                    game.getBoardState().add(board.clone());
                    return move;
                }
            }
        }
        return move;
        // TODO: Randomize the move for the bot, figure out all the empty cells, and then choose a cell randomly.
    }
}
