import java.util.ArrayList;

public class Player {
    
    private Card [] cards;
    private int fiches;
    private String name;
    private ArrayList<Card> allPoints; //all 7 or less cards
    private ArrayList<Card> bestPoints; //best 5 or less cards
    private int scoreValue;
    private int risky;

    private void printAllPoints(){

        for(int i = 0; i < allPoints.size(); i++){
            allPoints.get(i).printCard();
        }

    }

    public Player(String name, int fiches, int risky){

        this.name = name;
        this.fiches = fiches;
        cards = new Card[2];
        allPoints = new ArrayList<Card>(0);
        bestPoints = new ArrayList<Card>(0);
        scoreValue = -1;
        this.risky = risky;

    }

    public int getScoreValue(){

        return scoreValue;

    }

    public int getRisky(){

        return risky;

    }

    public ArrayList<Card> getBestPoints(){

        return bestPoints;

    }

    public void setScoreValue(int scoreValue){

        this.scoreValue = scoreValue;

    }

    public void setBestPoints(ArrayList<Card> points){

        this.bestPoints = new ArrayList<Card>(points);

    }

    public void printBestPoints(){

        for(int i = 0; i < bestPoints.size(); i++){
            bestPoints.get(i).printCard();
        }

    }

    public ArrayList<Card> getAllPoints(){

        return allPoints;

    }

    public void setFiches(int fiches){

        this.fiches = fiches;

    }

    public void addFiches(int fiches){

        this.fiches += fiches;

    }

    public int getFiches(){

        return fiches;

    }

    public String getName(){

        return name;

    }

    public void setName(String name){

        this.name = name;

    }

    public Card [] getCards(){

        return cards;

    }

    public void setCards(int index, Card card){

        this.cards[index] = card;

    }

    public ArrayList<Card> getSortedCopyAllPoints(String mode){

        ArrayList<Card> copyCards = new ArrayList<Card>(this.allPoints);

        if(mode=="ASSO1"){      // A = 1 
            for(int i = 0; i < copyCards.size(); i++){
                if(copyCards.get(i).getValue() == 14){
                    copyCards.get(i).changeAssValueForStraight(1);
                }
            }
        }else if(mode == "ASSO14"){   // A = 14
            for(int i = 0; i < copyCards.size(); i++){
                if(copyCards.get(i).getValue() == 1){
                    copyCards.get(i).changeAssValueForStraight(14);
                }
            }
        }
        for (int i = 0; i < copyCards.size()-1; i++) {
            for (int j = 0; j < copyCards.size() - 1 - i; j++) {
                if (copyCards.get(j).getValue() < copyCards.get(j + 1).getValue()) {
                    Card temp = copyCards.get(j);
                    copyCards.set(j, copyCards.get(j + 1));
                    copyCards.set(j + 1,temp);
                }
            }
        }
        return copyCards;

    }

    public void printStatus(){

        System.out.print(this.name+" [" + this.fiches + "] :\t");
        for(int i = 0; i < 2; i++){
            cards[i].printCard();
        }
        //System.out.print("\t fullPoint: ");
        //printFullPoints();

        System.out.println();

    }
}
