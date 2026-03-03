### Requirement Gathering for TIC-TAC-TOE Game ###

1. Size of the board can be N X N
2. Number of players = N - 1
3. each player can choose their symbol at the start of the game
4. Each symbol for all players should be unique, there should be no duplicate symbols
5. winning conditions -
   A. Same symbol in diagonal, or vertical or horizontal line.
   B. if same symbol is present in all 4 corners, then that player is winner.
6. Undo is possible
7. Draw condition -
   A. All the blocks are full, and we don’t have any winner
   B. If at any point, all the rows, columns and all 4 corners and diagonals have more than 1 type of
   symbol, then the game is drawn.
8. We can have bots in the game.
9. Bots will have different difficulty level -> easy, medium, hard.
10. We will maintain a list of all player,s and at the start of the game, we will shuffle the list. The game
    will be played in the order of shuffled list.
11. Game ends as soon as there is a winner, or the game becomes draw. After the same, we will start
    the game with a new empty board.
12. We will show the replay of the game.

Controller:
- First point of contact ->  APIs 
- In Tic-Tac-Toe, we don't have APIs, hence we will keep, methods to play the game inside controller.

Service:
- Service: Logic for winner, draw, etc.

Repository:
- Since it's not persistent, we don't need to store anything , hence no repository needed.