import java.util.Random;
public class GameBuilder {
    private int[] checkIfIndexTaken;
    private char[] actualChoices;
    public GameBuilder() {
        this.checkIfIndexTaken = new int[]{10,10,10,10,10,10,10,10};
        this.actualChoices = new char[]{'!', '!','!', '!','!', '!','!', '!'};
    }

    public void makeGame() {
        for (int i = 0; i < 8; i++){
            char matchingCharacter = createRandomCharacter();
            actualChoices[chooseIndex()] = matchingCharacter;
            actualChoices[chooseIndex()] = matchingCharacter;
        }
    }
    public void printGame(){
        for (int i = 0; i < actualChoices.length; i++) {
            System.out.println(actualChoices[i]);
        }
    }

    public int chooseIndex() {
        for (int i = 0; i < checkIfIndexTaken.length; i++) {
            if (checkIfIndexTaken[generateRandomNumberForMatchingCharacter()] == 10){
                return generateRandomNumberForMatchingCharacter();
            } else {
                continue;
            }
        }
        return -1;
    }

    public void chooseIndexTwo() {

    }

    public int generateRandomNumberForMatchingCharacter(){
        Random r = new Random();
        int randomValue = r.nextInt(8);
        return randomValue;
    }

    public char createRandomCharacter() {
        Random r = new Random();
        char memoryGameValue = (char)(r.nextInt(26) + 'A');
        return memoryGameValue;
    }

}
