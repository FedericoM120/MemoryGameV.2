public class GameLogic {
    private GameBuilder game;
    public GameLogic(GameBuilder game){
        this.game = game;
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
    }
}
