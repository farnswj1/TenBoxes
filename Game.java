/* Justin Farnsworth
 * February 21, 2016
 * Ten Boxes
 * 
 * RULES OF THE GAME:
 * Two players per game.
 * Click 1-3 squares per turn.
 * The player that must click the last box loses. The other player wins.
 * Use your strategies to force your opponent to click the last box.
 * Have fun!
 * 
 * Java Compile & Run Commands:
 * javac -cp +libs/doodlepad.jar Game.java
 * java -cp .;+libs/doodlepad.jar Game
 */

import doodlepad.*;

public class Game {
    // Boxes
    private static Rectangle box0;
    private static Rectangle box1;
    private static Rectangle box2;
    private static Rectangle box3;
    private static Rectangle box4;
    private static Rectangle box5;
    private static Rectangle box6;
    private static Rectangle box7;
    private static Rectangle box8;
    private static Rectangle box9;
    
    // Swap and reset buttons
    private static RoundRect reset;
    private static RoundRect swap;
    
    // Number of boxes
    private static Text textNumBoxes;
    private static int numBoxes;
    
    // Number of clicks
    private static Text textNumClicks;
    private static int numClicks;
    
    // Player
    private static Text player;
    private static int playerNum;
    
    // Winner
    private static Text winner;
    
    // Main function
    public static void main(String[] args){
        // Generate the game window
        Pad pad = new Pad(635, 325);
        
        // Generate the background
        Rectangle background = new Rectangle(5, 5, 625, 315);
        background.setFillColor(0, 0 , 0, 100);
        background.setEventsEnabled(false);
        background.setStrokeWidth(3);
        
        // Generate the boxes
        box0 = new Rectangle(50, 100, 50, 50);
        box0.setMouseEnteredHandler(Game::highlight);
        box0.setMouseExitedHandler(Game::unhighlight);
        
        box1 = new Rectangle(125, 100, 50, 50);
        box1.setMouseEnteredHandler(Game::highlight);
        box1.setMouseExitedHandler(Game::unhighlight);
        
        box2 = new Rectangle(200, 100, 50, 50);
        box2.setMouseEnteredHandler(Game::highlight);
        box2.setMouseExitedHandler(Game::unhighlight);
        
        box3 = new Rectangle(275, 100, 50, 50);
        box3.setMouseEnteredHandler(Game::highlight);
        box3.setMouseExitedHandler(Game::unhighlight);
        
        box4 = new Rectangle(350, 100, 50, 50);
        box4.setMouseEnteredHandler(Game::highlight);
        box4.setMouseExitedHandler(Game::unhighlight);
        
        box5 = new Rectangle(50, 175, 50, 50);
        box5.setMouseEnteredHandler(Game::highlight);
        box5.setMouseExitedHandler(Game::unhighlight);
        
        box6 = new Rectangle(125, 175, 50, 50);
        box6.setMouseEnteredHandler(Game::highlight);
        box6.setMouseExitedHandler(Game::unhighlight);
        
        box7 = new Rectangle(200, 175, 50, 50);
        box7.setMouseEnteredHandler(Game::highlight);
        box7.setMouseExitedHandler(Game::unhighlight);
        
        box8 = new Rectangle(275, 175, 50, 50);
        box8.setMouseEnteredHandler(Game::highlight);
        box8.setMouseExitedHandler(Game::unhighlight);
        
        box9 = new Rectangle(350, 175, 50, 50);
        box9.setMouseEnteredHandler(Game::highlight);
        box9.setMouseExitedHandler(Game::unhighlight);
        
        reset = new RoundRect(50, 25, 350, 30, 25, 25);
        reset.setFillColor(255, 0, 0);
        reset.setEventsEnabled(false);
        reset.setMouseEnteredHandler(Game::highlightReset);
        reset.setMouseExitedHandler(Game::unhighlightOptions);
        reset.setMouseClickedHandler(Game::resetGame);
        
        Text res1 = new Text("Reset", 210, 36);
        res1.setEventsEnabled(false);
        
        swap = new RoundRect(50, 270, 350, 30, 25, 25);
        swap.setFillColor(0, 255, 0);
        swap.setEventsEnabled(false);
        swap.setMouseEnteredHandler(Game::highlightSwap);
        swap.setMouseExitedHandler(Game::unhighlightOptions);
        swap.setMouseClickedHandler(Game::switchTurns);
        
        Text swa1 = new Text("Swap", 209, 279);
        swa1.setEventsEnabled(false);
        
        textNumBoxes = new Text("10 Boxes Remain", 425, 120);
        numBoxes = 10;

        textNumClicks = new Text("Choose 3 Boxes", 425, 195);
        numClicks = 3;
        
        // Text is empty until a winner has been declared
        winner = new Text("", 70, 140, 50);
        
        // Defaults to Player 1
        player = new Text("Player 1", 470, 265, 40);
        playerNum = 1;
    }

    // Executes when a box is clicked
    private static void setVisible(Shape shp, double x, double y, int but) {
        // Make the box invisible
        shp.setVisible(false);
        
        // Decrement the number of boxes and clicks
        numBoxes--;
        numClicks--;
        
        // Enable events for the swap and reset buttons
        swap.setEventsEnabled(true);
        reset.setEventsEnabled(true);
        
        // Update the number of remaining boxes
        if (numBoxes != 1) {
            textNumBoxes.setText(numBoxes + " Boxes Remain");
        } else {
            textNumBoxes.setText(numBoxes + " Box Remains");
        }
        
        // Update the number of remaining clicks for the player
        if (numClicks == 2) {
            textNumClicks.setText("Choose " + numClicks + " Boxes OR Swap Turns");
        } else if (numClicks == 1) {
            textNumClicks.setText("Choose " + numClicks + " Box OR Swap Turns");
        } else if (numClicks == 0) {
            textNumClicks.setText("Swap Turns");
            
            // Disable the boxes once the player has used all their clicks
            box0.setEventsEnabled(false);
            box1.setEventsEnabled(false);
            box2.setEventsEnabled(false);
            box3.setEventsEnabled(false);
            box4.setEventsEnabled(false);
            box5.setEventsEnabled(false);
            box6.setEventsEnabled(false);
            box7.setEventsEnabled(false);
            box8.setEventsEnabled(false);
            box9.setEventsEnabled(false);
        }
        
        // Declares player 2 the winner if Player 1 clicks the last box
        if (numBoxes == 0 && playerNum == 1) {
            winner.setText("Player 2 wins!");
            swap.setEventsEnabled(false);
            textNumClicks.setText("");
        }
        
        // Declares player 1 the winner if Player 2 clicks the last box
        if (numBoxes == 0 && playerNum == 2) {
            winner.setText("Player 1 wins!");
            swap.setEventsEnabled(false);
            textNumClicks.setText("");
        }
    }

    // Highlights the box when the mouse hovers over the box
    private static void highlight(Shape shp, double x, double y, int but) {
        shp.setFillColor(255, 255, 0);
        shp.setMouseClickedHandler(Game::setVisible);
    }

    // Unhighlights the box when the mouse hovers away from the box
    private static void unhighlight(Shape shp, double x, double y, int but) {
        shp.setFillColor(255, 255, 255);
    }
    
    // Highlights the reset button when the mouse hovers over it
    private static void highlightReset(Shape shp, double x, double y, int but) {
        shp.setStrokeWidth(5);
        shp.setStrokeColor(255, 0, 0);
    }    
    
    // Highlights the swap button when the mouse hovers over it
    private static void highlightSwap(Shape shp, double x, double y, int but) {
        shp.setStrokeWidth(5);
        shp.setStrokeColor(0, 255, 0);
    } 
    // Unhighlights the swap/reset buttons when the mouse hovers away from them
    private static void unhighlightOptions(Shape shp, double x, double y, int but) {
        shp.setStrokeWidth(1);
        shp.setStrokeColor(0, 0, 0);
    } 

    // Resets the game when the reset button has been clicked
    private static void resetGame(Shape shp, double x, double y, int but) {
        // Reset the reset button
        reset.setStrokeWidth(1);
        reset.setStrokeColor(0, 0, 0);
        
        // Make all boxes visible
        box0.setVisible(true);
        box1.setVisible(true);
        box2.setVisible(true);
        box3.setVisible(true);
        box4.setVisible(true);
        box5.setVisible(true);
        box6.setVisible(true);
        box7.setVisible(true);
        box8.setVisible(true);
        box9.setVisible(true);
        
        // Enable events for all boxes
        box0.setEventsEnabled(true);
        box1.setEventsEnabled(true);
        box2.setEventsEnabled(true);
        box3.setEventsEnabled(true);
        box4.setEventsEnabled(true);
        box5.setEventsEnabled(true);
        box6.setEventsEnabled(true);
        box7.setEventsEnabled(true);
        box8.setEventsEnabled(true);
        box9.setEventsEnabled(true);
        
        // Reset the number of boxes
        numBoxes = 10;
        textNumBoxes.setText("10 Boxes Remain");
        
        // Reset the number of clicks
        numClicks = 3;
        textNumClicks.setText("Choose 3 Boxes");
        
        // Reset defaults to Player 1
        player.setText("Player 1");
        playerNum = 1;
        
        // Remove the winner declaration from the screen
        winner.setText("");
        
        // Disable events for the swap and reset buttons
        swap.setEventsEnabled(false);
        reset.setEventsEnabled(false);
    }

    // Switches to the next player
    private static void switchTurns(Shape shp, double x, double y, int but) {
        // Reset the swap button
        swap.setStrokeWidth(1);
        swap.setStrokeColor(0, 0, 0);
        
        // Reset the number of clicks
        numClicks = 3;
        swap.setEventsEnabled(false);
        
        // Reset the text representing the number of clicks
        textNumClicks.setText("Choose " + numClicks + " Boxes");
        
        // Enable events for all boxes (note that invisible boxes cannot be clicked)
        box0.setEventsEnabled(true);
        box1.setEventsEnabled(true);
        box2.setEventsEnabled(true);
        box3.setEventsEnabled(true);
        box4.setEventsEnabled(true);
        box5.setEventsEnabled(true);
        box6.setEventsEnabled(true);
        box7.setEventsEnabled(true);
        box8.setEventsEnabled(true);
        box9.setEventsEnabled(true);
        
        // Switch to the next player
        if (playerNum == 1) {
            playerNum = 2;
        } else {
            playerNum = 1;
        }
        
        // Print the current player's turn
        player.setText("Player " + playerNum);
    }
}