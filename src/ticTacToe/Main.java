package ticTacToe;

import ticTacToe.controller.GameController;
import ticTacToe.exception.GameDrawnException;
import ticTacToe.exception.InvalidCellException;
import ticTacToe.model.Game;
import ticTacToe.model.Move;
import ticTacToe.model.Player;
import ticTacToe.model.constants.GameState;
import ticTacToe.model.constants.PlayerType;
import ticTacToe.service.BoardService;
import ticTacToe.service.GameService;
import ticTacToe.service.PlayerService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("WELCOME TO TIC TAC TOE GAME");
        System.out.println("Please enter the dimensions of the board for the game");
        int size = sc.nextInt();

        GameService gameService = new GameService(size);
        PlayerService playerService = new PlayerService();
        BoardService boardService = new BoardService();
        GameController gameController = new GameController(playerService, gameService, boardService);
        List<Player> players = gameController.generateplayerList(size - 1);
        Game game = gameService.createGame(players, size);
        game = gameService.startGame(game);

        int moveIndex = 0;

        gameController.displayBoard(game);

        while (true) {
            try {
                Player currentPlayer = game.getPlayers().get(moveIndex);
                System.out.println("Player to make a move: " + currentPlayer.getName());
                Move currentMove =  gameController.createMove(currentPlayer, game);
                gameController.displayBoard(game);

                if(currentPlayer.getPlayerType().equals(PlayerType.HUMAN) && !currentPlayer.isHasUsedUndo()) {
                    System.out.println("Enter the number of steps you want to undo, if you don't want to undo press 0");
                    int undoCount = sc.nextInt();
                    if(undoCount > 0) {
                        gameController.undo(undoCount, game);
                        currentPlayer.setHasUsedUndo(true);
                        gameController.displayBoard(game);
                    } else {
                        System.out.println("Undo not done, moving forward");
                    }
                }

                GameState gameState = gameController.checkWinner(game, currentMove);
                if(gameState.equals(GameState.WINNER)) {
                    game.setWinner(currentPlayer);
                    System.out.println("Congratulations to the winner: " + currentPlayer.getName());
                    break;
                }
            } catch (GameDrawnException e) {
                    System.out.println("Game has drawn, no winners");
                    break;
            } catch (InvalidCellException e) {
                System.out.println("Player has chosen a wrong cell for input, please enter again");
                continue;
            }
            moveIndex = (moveIndex + 1) % (size - 1);
        }
    }
}
