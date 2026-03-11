package ticTacToe.service.strategy;

import ticTacToe.exception.GameDrawnException;
import ticTacToe.model.Board;
import ticTacToe.model.Game;
import ticTacToe.model.Move;
import ticTacToe.model.Player;

import java.util.HashMap;
import java.util.List;

public class O1WinnerCheckStrategy implements WinnerCheckStrategy{
    private HashMap<Character, Integer> topLeftDiagonalMap;
    private HashMap<Character, Integer> topRightDiagonalMap;
    private HashMap<Character, Integer> cornerMap;
    private List<HashMap<Character, Integer>> rowMaps;
    private List<HashMap<Character, Integer>> colMaps;

    public O1WinnerCheckStrategy(int size) {
        this.topLeftDiagonalMap = new HashMap<>();
        this.topRightDiagonalMap = new HashMap<>();
        this.cornerMap = new HashMap<>();
        initializeMaps(size);
    }

    @Override
    public Player checkWinner(Board board, Move currentMove) {
        int size = board.getSize();
        char symbol = currentMove.getPlayer().getSymbol();
        int row = currentMove.getCell().getRow();
        int col = currentMove.getCell().getCol();

        // Update row & col hashmaps
        HashMap<Character, Integer> rowMap = rowMaps.get(row);
        HashMap<Character, Integer> colMap = colMaps.get(col);
        rowMap.put(symbol, rowMap.getOrDefault(symbol, 0) + 1);
        colMap.put(symbol, colMap.getOrDefault(symbol, 0) + 1);
        if(rowMap.get(symbol) == size || colMap.get(symbol) == size) {
            return currentMove.getPlayer();
        }

        // update diagonal maps
        if(row == col) {
            topLeftDiagonalMap.put(symbol, topLeftDiagonalMap.getOrDefault(symbol, 0) + 1);
        }
        if(row + col == size - 1){
            topRightDiagonalMap.put(symbol, topRightDiagonalMap.getOrDefault(symbol, 0) + 1);
        }
        if(topLeftDiagonalMap.get(symbol) == size || topRightDiagonalMap.get(symbol) == size) {
            return currentMove.getPlayer();
        }
        // update corner maps
        if((row == 0 || row == size - 1) && (col == 0 || col == size - 1)) {
            cornerMap.put(symbol, cornerMap.getOrDefault(symbol, 0) + 1);
        }

        if(cornerMap.get(symbol) == 4) {
            return currentMove.getPlayer();
        }

        if(checkDraw()) {
            throw new GameDrawnException("Game is drawn, nobody can win");
        }

        return null;
    }

    private void initializeMaps(int size) {
        for(int i = 0; i < size; i++) {
            rowMaps.add(new HashMap<>());
            colMaps.add(new HashMap<>());
        }
    }

    private boolean checkDraw() {
        // Optimize the TC with a counter for hashmaps
        for(HashMap<Character, Integer> map: rowMaps) {
            if(map.size() <= 1) {
                return false;
            }
        }
        for(HashMap<Character, Integer> map: colMaps) {
            if(map.size() <= 1) {
                return false;
            }
        }
        if(topRightDiagonalMap.size() <= 1 || topLeftDiagonalMap.size() <= 1 || cornerMap.size() <= 1) {
            return false;
        }
        return true;
    }
}
