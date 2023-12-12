import java.util.Random;
public class GameBuilder {
    private int[] checkIfIndexTaken;
    private char[] actualChoices;
    public GameBuilder() {
        this.checkIfIndexTaken = new int[]{10,10,10,10,10,10,10,10};
        this.actualChoices = new char[]{'!', '!','!', '!','!', '!','!', '!'};
    }

    public void makeGame() {
        for (int i = 0; i < 4; i++){
            char matchingCharacter = createRandomCharacter();
            actualChoices[chooseIndex()] = matchingCharacter;
            actualChoices[chooseIndex()] = matchingCharacter;
        }
    }
    public void printGame(){
        for (int i = 0; i < actualChoices.length; i++) {
            System.out.print(actualChoices[i] + "| ");
        }
    }

    public int chooseIndex() {
        boolean availableIndexFound = false;
        int randomIndex = generateRandomNumberForMatchingCharacter();
        while (availableIndexFound == false){
            randomIndex = generateRandomNumberForMatchingCharacter();
            if (this.checkIfIndexTaken[randomIndex] == 10){
                checkIfIndexTaken[randomIndex] = randomIndex;
                availableIndexFound = true;
            }
        }
        return randomIndex;
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
