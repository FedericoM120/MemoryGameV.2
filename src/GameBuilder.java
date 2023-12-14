import java.util.Random;
//makes the board for the game
public class GameBuilder {
    private int[] checkIfIndexTaken;
    private char[] actualChoices;
    public GameBuilder() {
        //this array uses 10 as an arbitrary value to check if the space is taken, if the index value is 10, then the index is free
        this.checkIfIndexTaken = new int[]{10,10,10,10,10,10,10,10};
        //this array uses the arbitrary char value '!' which will be replaced by a letter during the construction of the board
        this.actualChoices = new char[]{'!', '!','!', '!','!', '!','!', '!'};
    }
    //
    public void makeGame() {
        //there are 4 pairs of matching letters so only 4 loops necessary
        for (int i = 0; i < 4; i++){
            //assigns a random character to matchingCharacter by calling the createRandomCharacter function
            char matchingCharacter = createRandomCharacter();
            //assigns the random character to the first index called by chooseIndex()
            actualChoices[chooseIndex()] = matchingCharacter;
            //assigns the random character to the second index called by chooseIndex()
            actualChoices[chooseIndex()] = matchingCharacter;
        }
    }
    //returns the letter from the index passed as a parameter
    public char getIndexValue(int index) {
        int i;
        for (i = 0; i < actualChoices.length; i++) {
            if (i == index) {
                break;
            }
        }
        return actualChoices[i];
    }
    //chooseIndex finds a random available index to assign the characters to
    public int chooseIndex() {
        boolean availableIndexFound = false;
        //initialize randomIndex to 0 due to scope
        int randomIndex = 0;
        //will continue to loop until an available index (an index value that is 10) is found
        while (availableIndexFound == false){
            //generate random number and assign it to randomIndex
            randomIndex = generateRandomNumberForMatchingCharacter();
            //the random index value generated equals 10 then it's an available spot to place a character
            if (this.checkIfIndexTaken[randomIndex] == 10){
                checkIfIndexTaken[randomIndex] = randomIndex;
                availableIndexFound = true;
            }
        }
        return randomIndex;
    }
    //generates a random number from 0-7 for the indexes
    public int generateRandomNumberForMatchingCharacter(){
        Random r = new Random();
        int randomValue = r.nextInt(8);
        return randomValue;
    }
    //generates a random character from A - Z
    public char createRandomCharacter() {
        Random r = new Random();
        //generates a number from 1-25 and adds it to the ascii value of A to generate an int, then casts char to the int to convert the number to a char letter
        char randomChar = (char)(r.nextInt(26) + 'A');
        for (int i = 0; i < actualChoices.length; i++){
            if (randomChar == actualChoices[i]) {
                randomChar = (char)(r.nextInt(26) + 'A');
                i = 0;
            }
        }
        return randomChar;
    }
}
