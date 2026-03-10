package ticTacToe.controller;

import ticTacToe.model.Bot;
import ticTacToe.model.Game;
import ticTacToe.model.Move;
import ticTacToe.model.Player;
import ticTacToe.model.constants.PlayerType;
import ticTacToe.service.GameService;
import ticTacToe.service.PlayerService;

import java.util.*;

public class GameController {
    private final PlayerService playerService;
    private final Scanner sc;
    private GameService gameService;

    public GameController(PlayerService playerService, GameService gameService) {
        this.playerService = playerService;
        this.sc = new Scanner(System.in);
    }

    public List<Player> generateplayerList(int playerCount) {
        List<Player> players = new ArrayList<>();

        System.out.println("Please enter 1 for BOT and 0 for HUMAN");
        int botcheck = sc.nextInt();

        if(botcheck == 1) {
            // TODO: take user input for bot difficulty level
            // TODO: take user input for bot name and bit symbol
            Bot bot = playerService.createBot("Bot", '$');
            players.add(bot);
            playerCount--;
        }

        for(int i = 0; i < playerCount; i++) {
            System.out.println("Enter the player name");
            String playerName = sc.nextLine();
            System.out.println("Enter the symbol for player: " + playerName);
            char symbol = sc.nextLine().charAt(0);
            Player player = playerService.createPlayer(playerName, symbol);
            players.add(player);
            Collections.shuffle(players);
        }
        return players;
    }

    public Move createMove(Player player, Game game) {
        if(player.getPlayerType() == PlayerType.HUMAN) {
            System.out.println("Enter row");
            int row = sc.nextInt();
            System.out.println("Enter col");
            int col = sc.nextInt();
            // TODO: Validate row and column before executing move
            return gameService.executeMove(player, game, row, col);
        } else {
            return null;
        }
    }
}
