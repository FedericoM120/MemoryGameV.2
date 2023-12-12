public class App {
    public static void main(String[] args) {
        GameBuilder gameBoard = new GameBuilder();
        gameBoard.makeGame();

        GameLogic myGame = new GameLogic(gameBoard);
        myGame.start();
    }
}
