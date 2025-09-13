public class testDeck {

    private final int n_cards = 52;
    private Deck deck;

    public testDeck(){

        this.deck = new Deck();
        this.deck.setDeck();

    }

    private Deck getDeck(){

        return this.deck;

    }

    private void countCard(int value){

        int count = 0;
        for(int i = 0; i < this.n_cards; i++){
            if(value == ((this.deck.getDeck())[i].getValue())){
                count++;
            }
        }
        System.out.print(value + ":" + count + " ");

    }

    private void countSeed(char seed){

        int count = 0;
        for(int i = 0; i < this.n_cards; i++){
            if(seed == ((this.deck.getDeck())[i].getSeed())){
                count++;
            }
        }
        System.out.print(seed + ":" + count + " ");
    
    }

    private void countSameCard(){

        int count = 0;
        for(int i = 0; i < this.n_cards; i++){
            for(int j = 0; j < this.n_cards; j++){
                if(i == j){
                    continue;
                }
                if((this.deck.getDeck())[i].checkEqualCard((this.deck.getDeck())[j])){
                    count++;
                }
            }
        }
        System.out.println("\nSAME CARD FOUND: " + count);

    }
    public static void main(String[] args){

        final int n_values = 13;
        testDeck test = new testDeck();
        for(int i = 2; i <= n_values+1; i++){
            test.countCard(i);
        }
        test.countSeed('c');
        test.countSeed('q');
        test.countSeed('f');
        test.countSeed('p');
        test.countSameCard();
        test.getDeck().printDeck();

    }
}
