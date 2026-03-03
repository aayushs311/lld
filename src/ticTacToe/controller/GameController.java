package ticTacToe.controller;

import ticTacToe.model.Bot;
import ticTacToe.model.Player;
import ticTacToe.service.PlayerService;

import java.util.*;

public class GameController {
    private final PlayerService playerService;

    public GameController(PlayerService playerService) {
        this.playerService = playerService;
    }

    public List<Player> generateplayerList(int playerCount) {
        Scanner sc = new Scanner(System.in);
        List<Player> players = new ArrayList<>();

        System.out.println("Please enter 1 for BOT and 0 for HUMAN");
        int botcheck = sc.nextInt();

        if(botcheck == 1) {
            Bot bot = playerService.createBot("Bot", 'X');
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
}
