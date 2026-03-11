package ticTacToe.service;

import ticTacToe.exception.DuplicateSymbolException;
import ticTacToe.model.Bot;
import ticTacToe.model.Player;
import ticTacToe.model.constants.BotDifficultyLevel;
import ticTacToe.model.constants.PlayerType;

import java.util.HashSet;

public class PlayerService {
    private static int counter = 1;
    private final HashSet<Character> symbolSet;

    public PlayerService() {
        this.symbolSet = new HashSet<>();
    }

    public Player createPlayer(String name, char symbol) {
        validateAndRegisterSymbol(symbol);
        return new Player(counter++, name, symbol, PlayerType.HUMAN);
    }

    public Bot createBot(String name, char symbol) {
        validateAndRegisterSymbol(symbol);
        return new Bot(counter++, name, symbol, PlayerType.BOT, BotDifficultyLevel.MEDIUM);
    }

    private void validateAndRegisterSymbol(char symbol) {
        if(symbolSet.contains(symbol)) {
            throw new DuplicateSymbolException("Symbol already selected: " + symbol);
        }
        symbolSet.add(symbol);
    }
}
