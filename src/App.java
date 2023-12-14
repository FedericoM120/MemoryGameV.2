public class App {
    public static void main(String[] args) {
        //initializes gameBoard object
        GameBuilder gameBoard = new GameBuilder();
        //makes the boardGame
        gameBoard.makeGame();

        //initializes the GameLogic object
        GameLogic myGame = new GameLogic(gameBoard);
        //starts the game
        myGame.playGame();
    }
}
