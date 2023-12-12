import java.util.Scanner;
public class GameLogic {
    private GameBuilder game;
    private Scanner scanner;
    public GameLogic(GameBuilder game){
        this.game = game;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        game.printGame();
    }

    public void playGame(){
        String[] appearance = new String[]{"null", "null", "null", "null", "null", "null", "null", "null"};
        for (int j = 0; j < appearance.length; j++) {
            System.out.print(appearance[j] + "| ");
        }
        System.out.println("");
        System.out.println("Enter first index:");
        int firstIndex = scanner.nextInt();
        System.out.println(firstIndex + "=" +  game.getIndexValue(firstIndex));
    }

    public void indexChosen() {

    }




}
