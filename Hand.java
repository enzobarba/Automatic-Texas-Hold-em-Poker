import java.util.ArrayList;

public class Hand {
    
    private int nPlayers;
    private ArrayList <Player> players;
    private Deck deck;
    private ArrayList <Card> tableCards;
    private ScoreComparator scoreComparator;

    private void deliverTableCardsToPlayers(String type){

        if(type == "FLOP"){
            for(int i = 0; i < this.nPlayers; i++){
                for(int j = 0; j < 3; j++){
                    players.get(i).getAllPoints().add(tableCards.get(j));
                }
            }
        }
        else if(type == "TURN"){
            for(int i = 0; i < this.nPlayers; i++){
                players.get(i).getAllPoints().add(tableCards.get(3));
            }
        }
        else if(type == "RIVER"){
            for(int i = 0; i < this.nPlayers; i++){
                players.get(i).getAllPoints().add(tableCards.get(4));
            }
        }

    }

    private void deliverCards(){

        Card [] cards = this.deck.getDeck();
        int indexCardDeck = 0;

        for(int j = 0; j < 2; j++){
            for(int i = 0; i < this.nPlayers; i++){
                indexCardDeck = this.deck.getDeliveredCards();
                players.get(i).setCards(j, cards[indexCardDeck]);
                players.get(i).getAllPoints().add(cards[indexCardDeck]);
                deck.increaseDeliveredCards();
            }
        }

    }

    private void deliverTableCards(String type){

        Card [] cards = deck.getDeck();
        int indexCardDeck = 0;
        int n_cards = 0;
        switch(type){
            case "FLOP":
                n_cards = 3;
                break;
            case "TURN":
                n_cards = 1;
                break;
            case "RIVER":
                n_cards = 1;
                break;
            default:
                System.exit(-1);
        }
        for(int i = 0; i < n_cards; i++){
            indexCardDeck = deck.getDeliveredCards();
                tableCards.add(cards[indexCardDeck]);
                deck.increaseDeliveredCards();
        }
        deliverTableCardsToPlayers(type);

    }

    private void printTableCards(String type){

        if(type == "FLOP"){
            System.out.print("FLOP:\t");
            for(int i = 0; i < 3; i++){
                tableCards.get(i).printCard();
            }
        }
        else if(type == "TURN"){
            System.out.print("TURN:\t");
            tableCards.get(3).printCard();
        }
        else if(type == "RIVER"){
            System.out.print("RIVER:\t");
            tableCards.get(4).printCard();
        }
        else if(type == "ALL"){
            System.out.print("TAVOLO:\t");
            for(int i = 0; i < tableCards.size(); i++){
                tableCards.get(i).printCard();
            }
        }
        System.out.println();
        
    }

    private void printPlayers(){

        for(int i = 0; i < this.nPlayers; i++){
            players.get(i).printStatus();
        }

    }

    private void findBestPoint(){

        scoreComparator.setScores(getPlayers());
        System.out.println();

    }
    
    private void sleep(int seconds){

        try{
            Thread.sleep(seconds * 1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

    }

    //for testHand
    public Hand(int nPlayers){

        this.nPlayers = nPlayers;
        this.players = new ArrayList<Player>(0);
        this.deck = new Deck();
        this.deck.setDeck();
        this.tableCards = new ArrayList<Card>(0);
        this.scoreComparator = new ScoreComparator(players);

    }    

    public Hand(ArrayList<Player> players){

        this.players = players;
        this.nPlayers = players.size();
        this.deck = new Deck();
        this.deck.setDeck();
        this.tableCards = new ArrayList<Card>(0);
        this.scoreComparator = new ScoreComparator(players);

    }    

    public int get_nPlayers(){

        return this.nPlayers;

    }

    public ArrayList<Player> getPlayers(){

        return this.players;

    }

    public Deck getDeck(){

        return this.deck;

    }

    public void playHand(){
        deliverCards();
        printPlayers();
        sleep(4);
        findBestPoint();

        deliverTableCards("FLOP");
        printTableCards("FLOP");
        findBestPoint();
        sleep(5);
        deliverTableCards("TURN");
        printTableCards("TURN");
        findBestPoint();
        sleep(5);

        deliverTableCards("RIVER");
        printTableCards("RIVER");
        printTableCards("ALL");
        sleep(5);
        findBestPoint();
        ArrayList<Integer> indexWinner = scoreComparator.winnerScores(players);
        /* 
        for(int i = 0; i < this.players.size(); i++){
            if(!(indexWinner.contains(i))){
                players.get(i).addFiches(-300);
            }
            else{
                players.get(i).addFiches(100);
            }
        }
        for(int i = this.players.size() - 1; i >= 0; i--){
            if(players.get(i).getFiches() <= 0){
                players.remove(i);
            }
            
        }
        */
    }

}
