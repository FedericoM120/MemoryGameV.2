import java.util.Scanner;
//Handles the logic for game play
public class GameLogic {
    private GameBuilder game;
    private Scanner scanner;
    private String[] appearance;
    public GameLogic(GameBuilder game){
        this.game = game;
        this.scanner = new Scanner(System.in);
        //this is content of the output the user will see when starting a new game
        this.appearance = new String[]{"null", "null", "null", "null", "null", "null", "null", "null"};
    }
    //playGame loops through all the game logic instructions
    public void playGame(){
        //willAlwaysBezeroUnlessUserChoosestoEndGame essentially is just there to infintely loop until the user declares to end game which increases the value to 200 ending the game
        int willAlwaysBeZeroUnlessUserChoosesToEndGame = 0;
        while (willAlwaysBeZeroUnlessUserChoosesToEndGame < 9){
            //prints out the array and shows all the possible choices differentiating  choices by using |
            for (int j = 0; j < appearance.length; j++) {
                System.out.print(appearance[j] + "| ");
            }
            System.out.println("");
            System.out.println("Enter first index:");
            int firstIndex = scanner.nextInt();
            //assigns the value returned by the method validIndexChosen to validFirstIndex
            int validFirstIndex = validIndexChosen(firstIndex);
            //shows the user the index they chose and the value assigned to that index
            System.out.println(validFirstIndex + "=" +  game.getIndexValue(validFirstIndex));
            //prints out the board with the index value the user chose
            for (int j = 0; j < appearance.length; j++) {
                if (j == validFirstIndex) {
                    System.out.print(game.getIndexValue(validFirstIndex) + "| ");
                } else {
                    System.out.print(appearance[j] + "| ");
                }
            }
            System.out.println("\nEnter second index:");
            int secondIndex = scanner.nextInt();
            //assigns the value returned by checkIfBothValidAndNotDupe to validSecondIndexNotRepeatingFirstIndex
            int validSecondIndexNotRepeatingFirstIndex = checkIfBothValidAndNotDupe(validFirstIndex, secondIndex);
            //shows the user the index they chose and the value assigned to that index
            System.out.println(validSecondIndexNotRepeatingFirstIndex + "=" + game.getIndexValue(validSecondIndexNotRepeatingFirstIndex));
            //updateStateOfGame updates the board game to either show the correct mathc with letters or the two indexes chosen by the user as blank
            updateStateOfGame(validFirstIndex, validSecondIndexNotRepeatingFirstIndex);
            //assigns the willAlwaysBeZeroUnlessUserChoosesToEndGame to either 200 if user ends game or starts a new game recursively
            willAlwaysBeZeroUnlessUserChoosesToEndGame = playAgain(willAlwaysBeZeroUnlessUserChoosesToEndGame);
        }
    }
    //checkIfBothValidAndNotDupe will continue to loop until the user inputs a choice for the second index that hasn't been found yet and is a different index from their first choice
    public int checkIfBothValidAndNotDupe(int indexOne, int indexTwo) {
        boolean dupe = true;
        boolean notAvail = true;

        int firstIndex = 0;
        //will continue to loop until the user inputs a non-duplicate value and an available index
        while (dupe && notAvail) {
            //checks if the index chosen is in scope
            indexTwo = checkIfInScope(indexTwo);
            //checks if the index chosen is a letter by checking to see if the space is either nor a string "null" or a blank space " " confirming that index is not available bc it has a letter assigned to it already
            if (appearance[indexTwo] != "null" && appearance[indexTwo] != " ") {
                System.out.println("This index has already been found. Please choose another index:");
                firstIndex = scanner.nextInt();
                indexTwo = firstIndex;
                notAvail = true;
                continue;
            }
            //checks if the second index chosen by the user is same as the first index chosen and loops until the user chooses a unique index
            if (indexOne == indexTwo) {
                System.out.println("Can't choose the same index for both choices. Choose a new second index:");
                indexTwo = scanner.nextInt();
                dupe = true;
                continue;

            } else {
                dupe = false;
            }
        }
        return indexTwo;
    }
    //checks if the index chosen is in scope
    public int checkIfInScope(int indexPassed) {
        boolean indexInScope = false;
        while (indexInScope == false) {
            if (indexPassed < 0 || indexPassed > 7) {
                System.out.println("Please choose an index between 0 and 7");
                int inScopeIndex = scanner.nextInt();
                indexPassed = inScopeIndex;
            } else {
                indexInScope = true;
            }
        }
        return indexPassed;
    }
    //validIndexChosen checks if the first index chosen by user doesn't have a letter assigned to the index
    public int validIndexChosen(int indexSelected) {
        //checks if the index chosen is in scope
        boolean validIndexSelection = false;
        //needed to declare firstIndex outside of while loop due to scope
        int firstIndex = 0;
        while (validIndexSelection != true) {
            indexSelected = checkIfInScope(indexSelected);
            if (appearance[indexSelected] != "null" && appearance[indexSelected] != " ") {
                System.out.println("This index has already been found. Please choose another index:");
                firstIndex = scanner.nextInt();
                indexSelected = firstIndex;
            } else {
                break;
            }
        }
        return indexSelected;
    }
    //updates the board visually
    public void updateStateOfGame(int firstIndex, int secondIndex) {
        //if the users two indexes chosen have the same character then update the board with the characters in the indexes chosen
        if (game.getIndexValue(firstIndex) == game.getIndexValue(secondIndex)) {
            appearance[firstIndex] = String.valueOf(game.getIndexValue(firstIndex));
            appearance[secondIndex] = String.valueOf(game.getIndexValue(secondIndex));
            //if the indexes chosen characters dont match update the board with a blank " "
        } else if (game.getIndexValue(firstIndex) != game.getIndexValue(secondIndex)) {
            appearance[firstIndex] = " ";
            appearance[secondIndex] = " ";
        }
    }
    //handles the play again option
    public int playAgain(int currentValueOfI){
        //counts the amount of null or blanks on the board and if zero, that indicates that there are only letters meaning the user won
        int nullOrBlankCounter = 0;
        for (int i = 0; i < appearance.length; i++) {
            if (appearance[i] == "null" || appearance[i] == " ") {
                nullOrBlankCounter++;
            }
        }
        //if nullOrBlankCounter equals zero then prompt user to play again
        if (nullOrBlankCounter == 0) {
            System.out.println("You won! Game over. \nPlay again? Enter y for yes or n for no");
            String playAgain = scanner.next();
            if (playAgain.equals("y")) {
                //initializes a new gameboard from the gameBuilder class
                GameBuilder gameBoard = new GameBuilder();
                gameBoard.makeGame();
                //create a new gamelogic option
                GameLogic myGame = new GameLogic(gameBoard);
                System.out.println(" ");
                //starts the new game
                myGame.playGame();
                //need to assign the current games value to 200 to end it when the new game finishes due to recursion
                currentValueOfI = 200;
            } else {
                //if player choses to end the game then we return the value 200 immediately
                currentValueOfI = 200;
                System.out.println("bye!");
            }
        }
        //this will return 200 to the variable willAlwaysBeZeroUnlessUserChoosesToEndGame
        return currentValueOfI;
    }
}
