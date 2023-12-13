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
        int i = 0;
        while (i < 9){
            for (int j = 0; j < appearance.length; j++) {
                System.out.print(appearance[j] + "| ");
            }
            System.out.println("");

            System.out.println("Enter first index:");
            int firstIndex = scanner.nextInt();
            int validFirstIndex = validIndexChosen(firstIndex);
            System.out.println(validFirstIndex + "=" +  game.getIndexValue(validFirstIndex));

            System.out.println("Enter second index:");
            int secondIndex = scanner.nextInt();
            int validSecondIndex = validIndexChosen(secondIndex);
            System.out.println(validSecondIndex + "=" + game.getIndexValue(validSecondIndex));

            updateStateOfGame(validFirstIndex, validSecondIndex);
            i++;
        }

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

    public void updateStateOfGame(int firstIndex, int secondIndex) {
        if (game.getIndexValue(firstIndex) == game.getIndexValue(secondIndex)) {
            appearance[firstIndex] = String.valueOf(game.getIndexValue(firstIndex));
            appearance[secondIndex] = String.valueOf(game.getIndexValue(secondIndex));
        } else if (game.getIndexValue(firstIndex) != game.getIndexValue(secondIndex)) {
            appearance[firstIndex] = " ";
            appearance[secondIndex] = " ";
        }
    }




}
