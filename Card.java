import java.util.Random;

public class Card{

    private int value;
    private char seed;
    
    private final int n_values = 13;
    private final int n_seed =  4;


    public Card(){

        this.value = 0;
        this.seed = 0;

    }

    public int getValue(){

        return this.value;

    }

    public char getSeed(){

        return this.seed;

    }

    private char switchSeed(int numberSeed){

        char newSeed = 0;
        switch(numberSeed){
            case 0:
                newSeed = 'c';
                break;
            case 1:
                newSeed =  'q';
                break;
            case 2:
                newSeed = 'f';
                break;
            case 3:
                newSeed = 'p';
                break;
        }
        return newSeed;

    }

    public void setRandomCard(){

        Random rand = new Random();
        this.value = rand.nextInt(n_values) + 2;
        int numberSeed = rand.nextInt(n_seed);
        this.seed = switchSeed(numberSeed);

    }

    public void setCard(int value, char seed){

        this.value = value;
        this.seed = seed;

    }

    public void printCard(){

        String stringValue = Integer.toString(value);
        switch(value){
            case 1:
                stringValue = "A"; //nel caso della scala
                break;
            case 11:
                stringValue = "J";
                break;
            case 12:
                stringValue = "Q";
                break;
            case 13:
                stringValue = "K";
                break;
            case 14:
                stringValue = "A";
                break;
        }
        System.out.print(stringValue);
        System.out.print(seed + " ");

    }

    public boolean checkEqualCard(Card card2){

        if((this.seed == card2.getSeed()) && (this.value == card2.getValue())){
            return true;
        }
        else{
            return false;
        }

    }

    public boolean checkEqualValueCard(Card card2){

        if(this.value == card2.getValue()){
            return true;
        }
        else{
            return false;
        }

    }

    public void changeAssValueForStraight(int value){ //1 or 14

        this.value = value;

    }
    
}