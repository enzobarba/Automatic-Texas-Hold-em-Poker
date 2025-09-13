public class Deck{

    private final int n_cards = 52;
    private Card [] deck;
    private int deliveredCards;

    public Deck(){

        this.deck = new Card[n_cards];
        for(int i = 0; i < this.n_cards; i++){
            this.deck[i] = new Card();
        }
        this.deliveredCards = 0;

    }

    public int getDeliveredCards(){

        return this.deliveredCards;
    
    }
    
    public Card [] getDeck(){

        return this.deck;

    }

    public void setDeck(){

        for(int i = 0; i < this.n_cards; i++){
            this.deck[i].setRandomCard();
        }
        for(int i = 0; i < this.n_cards; i++){
            boolean sameCardFound;
            do{
                sameCardFound = false;
                this.deck[i].setRandomCard();
                for(int j = 0; j < this.n_cards; j++){
                    if(i != j && this.deck[i].checkEqualCard(this.deck[j])){
                        sameCardFound = true;
                    }
                }
            }
            while(sameCardFound == true);
        }

    }
    
    public void printDeck(){

        for(int i=0; i<this.n_cards; i++){
            this.deck[i].printCard();
        }
        System.out.println();

    }

    public void increaseDeliveredCards(){

        this.deliveredCards++;
        
    }
}


