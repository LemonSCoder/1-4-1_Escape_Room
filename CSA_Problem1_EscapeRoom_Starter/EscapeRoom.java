

/*
* Problem 1: Escape Room
* 
* V1.0
* 10/10/2019
* Copyright(c) 2019 PLTW to present. All rights reserved
*/
/* endGame

getSteps               

isTrap

movePlayer

pickupPrize

replay      

springTrap */
import java.util.Scanner;

/**
 * Create an escape room game where the player must navigate
 * to the other side of the screen in the fewest steps, while
 * avoiding obstacles and collecting prizes.
 */
public class EscapeRoom
{

      // describe the game with brief welcome message
      // determine the size (length and width) a player must move to stay within the grid markings
      // Allow game commands:
      //    right, left, up, down: if you try to go off grid or bump into wall, score decreases
      //    jump over 1 space: you cannot jump over walls
      //    if you land on a trap, spring a trap to increase score: you must first check if there is a trap, if none exists, penalty
      //    pick up prize: score increases, if there is no prize, penalty
      //    help: display all possible commands
      //    end: reach the far right wall, score increase, game ends, if game ended without reaching far right wall, penalty
      //    replay: shows number of player steps and resets the board, you or another player can play the same board
      // Note that you must adjust the score with any method that returns a score
      // Optional: create a custom image for your player use the file player.png on disk
    
      /**** provided code:
      // set up the game
      boolean play = true;
      while (play)
      {
        // get user input and call game methods to play 
        play = false;
      }
      */

  public static void main(String[] args) 
  {      
    // welcome message
    System.out.println("Welcome to EscapeRoom!");
    System.out.println("Get to the other side of the room, avoiding walls and invisible traps,");
    System.out.println("pick up all the prizes.\n");
    
    GameGUI game = new GameGUI();
    game.createBoard();
    //Five traps set on board
    game.setTraps(5);
    UserInput userInput = new UserInput();

    // size of move
    int m = 60; 
    // individual player moves
    int px = 0;
    int py = 0; 
    
    int score = 0;

    Scanner in = new Scanner(System.in);
    //Added "help", "h", and "?" commands. User can type those in to get a list of commands returned to them.
    String[] validCommands = { "right", "left", "up", "down", "r", "l", "u", "d",
    "jump", "jr", "jumpleft", "jl", "jumpup", "ju", "jumpdown", "jd",
    "pickup", "p", "quit", "q", "s", "spring", "replay", "help", "h", "?"};
  
    // set up game
    boolean play = true;
    int currentPrizeNum = game.returnPrizeNum();
    int currentPickupNum = game.returnPickupNum();
    //Game continues as long as the player has not picked up all the possible prizes in the maze.
    while (play && currentPrizeNum != currentPickupNum)
    {
      currentPrizeNum = game.returnPrizeNum();
      currentPickupNum = game.returnPickupNum();
      /* TODO: get all the commands working */
    /* Your code here */
      System.out.println("Input a command: Type 'h' for a list of commands.");
      String input = userInput.getValidInput(validCommands);
      // userInput.getValidInput(validCommands);
      // Input.equals("q") = quit the game
      if (input.equals("q")) {
        game.endGame();
        play = false;
      } else if (input.equals("h")) {
        System.out.println("These are your commands: " + validCommands);
      //Player controls (left, right, down, up, jump right, jump left, jump down, and jump up); simple movement moves player 50 units in a certain direction while jumping moves player 100 units in a certain direction.
      } else if (input.equals("r")) {
        //Checks for traps
        game.isTrap(px, py);
        game.movePlayer(50, 0);
        //Adds appropriate value to px and py so that they can be accurately used for future reference when methods are called.
        px += 50;
        //Each step adds points to the player's score (with several exceptions)
        score += 10;
      } else if (input.equals("l")) {
        game.isTrap(px, py);
        game.movePlayer(-50, 0);
        //Each step subtracts points to the player's score (with several exceptions)
        px -= 50;
        score += 10;
      } else if (input.equals("u")) {
        game.isTrap(px, py);
        game.movePlayer(0, -50);
        py += 50;
        score += 10;
      } else if (input.equals("d")) {
        game.isTrap(px, py);
        game.movePlayer(0, 50);
        py -= 50;
        score += 10;
      } else if (input.equals("re")) {
        //The game will restart with a new maze, new prize locations, and new traps if the player types in "re"
        game.replay();
        //Score/Step Numbers, and reset confirmation message printed. Score is also reset.
        System.out.println("score=" + score);
        System.out.println("steps=" + game.getSteps());
        System.out.println("Your game has been reset!");
        score = 0;
      } else if (input.equals("p")) {
        //Player will pickup a prize if they're on a square containing it. Prizes are worth 5x more points than a step/jump.
        game.pickupPrize();
        score += 50;
      } else if (input.equals("jr")) {
        game.isTrap(px, py);
        game.movePlayer(100, 0);
        px += 100;
        score += 10;
      } else if (input.equals("jl")) {
        game.isTrap(px, py);
        game.movePlayer(-100, 0);    
        px -= 100;
        score += 10; 
      } else if (input.equals("ju")) {
        game.isTrap(px, py);
        game.movePlayer(0, -100);
        py += 100;
        score += 10;
      } else if (input.equals("jd")) {
          game.isTrap(px, py);
          game.movePlayer(0, -100);
          py -= 100;
          score += 10;
      } else if (input.equals("s")) {
        //Checks if trap is in close enough proximity to the player (50 pixels up, down, left, or right). If so, points are added to the score.
        if (Math.abs(game.springTrap(50, 0)) == game.springTrap(50, 0) || Math.abs(game.springTrap(-50, 0)) == game.springTrap(-50, 0) || Math.abs(game.springTrap(0, 50)) == game.springTrap(0, 50) || Math.abs(game.springTrap(0, -50)) == game.springTrap(0, -50)) {
          score += 10;
        } else {
          //If the player springs a trap that does not exist, there will be a penalty and a message that notifies the player that this is the case.
          score -= 10;
          System.out.println("You sprung a trap that didn't exist.");
        }
      } else {
        //Invalid input = penalty
        score -= 10;
      }
    }
    //Message that appears upon the pickup of all prizes.
    System.out.println("You picked up all the prizes you could!");

  

    score += game.endGame();
        
    //Score/Step Numbers printed.
    System.out.println("score=" + score);
    System.out.println("steps=" + game.getSteps());
  }
}

        
