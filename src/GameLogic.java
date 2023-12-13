import java.util.Scanner;
public class GameLogic {
    private GameBuilder game;
    private Scanner scanner;
    private String[] appearance;
    public GameLogic(GameBuilder game){
        this.game = game;
        this.scanner = new Scanner(System.in);
        this.appearance = new String[]{"null", "null", "null", "null", "null", "null", "null", "null"};
    }
    public void start() {
        game.printGame();
    }
    public void playGame(){
        //String[] appearance = new String[]{"null", "null", "null", "null", "null", "null", "null", "null"};
        int willAlwaysBeZeroUnlessUserChoosesToEndGame = 0;
        while (willAlwaysBeZeroUnlessUserChoosesToEndGame < 9){
            for (int j = 0; j < appearance.length; j++) {
                System.out.print(appearance[j] + "| ");
            }
            System.out.println("");

            System.out.println("Enter first index:");
            int firstIndex = scanner.nextInt();
            int validFirstIndex = validIndexChosen(firstIndex);
            System.out.println(validFirstIndex + "=" +  game.getIndexValue(validFirstIndex));

            for (int j = 0; j < appearance.length; j++) {
                if (j == validFirstIndex) {
                    System.out.print(game.getIndexValue(validFirstIndex) + "| ");
                } else {
                    System.out.print(appearance[j] + "| ");
                }
            }
            //
            //print statement for space
            System.out.println("Enter second index:");
            int secondIndex = scanner.nextInt();
            int validSecondIndexNotRepeatingFirstIndex = checkIfBothValidAndNotDupe(firstIndex, secondIndex);

            //int validSecondIndex = validIndexChosen(secondIndex);
            //int validSecondIndexNotRepeatingFirstIndex = checkIfIndexesAreDifferent(validFirstIndex, validSecondIndex);



            System.out.println(validSecondIndexNotRepeatingFirstIndex + "=" + game.getIndexValue(validSecondIndexNotRepeatingFirstIndex));

            updateStateOfGame(validFirstIndex, validSecondIndexNotRepeatingFirstIndex);
            willAlwaysBeZeroUnlessUserChoosesToEndGame = playAgain(willAlwaysBeZeroUnlessUserChoosesToEndGame);
        }
    }

    public int checkIfBothValidAndNotDupe(int indexOne, int indexTwo) {
        boolean dupe = true;
        boolean avail = true;

        int firstIndex = 0;
        while (dupe && avail) {
            if (appearance[indexTwo] != "null" && appearance[indexTwo] != " ") {
                System.out.println("This index has already been found. Please choose another index:");
                firstIndex = scanner.nextInt();
                indexTwo = firstIndex;
                avail = true;
                continue;
            } else if (appearance[indexTwo] == "null" && appearance[indexTwo] == " ") {
                avail = false;
            }
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



    public int validIndexChosen(int indexSelected) {
        boolean validIndexSelection = false;
        int firstIndex = 0;
        while (validIndexSelection != true) {
//            System.out.println("Enter first index: ");
//            firstIndex = scanner.nextInt();
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
    public int checkIfIndexesAreDifferent(int firstIndex, int secondIndex) {
        while (firstIndex == secondIndex) {
            System.out.println("Can't choose the same index for both choices. Choose a new second index:");
            secondIndex = scanner.nextInt();
        }
        return secondIndex;
    }

    public void updateStateOfGame(int firstIndex, int secondIndex) {
        if (game.getIndexValue(firstIndex) == game.getIndexValue(secondIndex)) {
            appearance[firstIndex] = String.valueOf(game.getIndexValue(firstIndex));
            appearance[secondIndex] = String.valueOf(game.getIndexValue(secondIndex));
        } else if (game.getIndexValue(firstIndex) != game.getIndexValue(secondIndex)) {
            appearance[firstIndex] = " ";
            appearance[secondIndex] = " ";
        }
    }

    public int playAgain(int currentValueOfI){
        int nullOrBlankCounter = 0;
        for (int i = 0; i < appearance.length; i++) {
            if (appearance[i] == "null" || appearance[i] == " ") {
                nullOrBlankCounter++;
            }
        }

        if (nullOrBlankCounter == 0) {
            System.out.println("You won! Game over. \nPlay again? Enter y for yes or n for no");
            String playAgain = scanner.next();
            if (playAgain.equals("y")) {
                GameBuilder gameBoard = new GameBuilder();
                gameBoard.makeGame();

                GameLogic myGame = new GameLogic(gameBoard);
                System.out.println(" ");

                myGame.playGame();
            } else {
                currentValueOfI = 200;
                System.out.println("bye!");
            }
        }
        return currentValueOfI;
    }
}
