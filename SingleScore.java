import java.util.ArrayList;
import java.util.Collections;

public class SingleScore {

    private void initializePoints(ArrayList<Card> points,int len){

        for(int i = 0; i < len; i++){
            points.add(new Card());
        }

    }

    public ArrayList<Card> hasSinglePair(Player player,String mode, int valueToSkip){

        ArrayList<Card> cards = player.getSortedCopyAllPoints("ASSO14");
        ArrayList<Card> pair = new ArrayList<Card>(0);
        initializePoints(pair,2);
        for(int i = 0; i < cards.size() - 1; i++){
            if( (mode == "FULL" || mode == "DOUBLE PAIR") && ((cards.get(i).getValue() == valueToSkip) )){
                continue;
            }
            for(int j = i + 1; j < cards.size(); j++){
                if(cards.get(i).checkEqualValueCard(cards.get(j))){
                    pair.set(0, cards.get(i));
                    pair.set(1, cards.get(j));
                    return pair;
                }
            }
        }
        return null;        

    }

    public ArrayList<Card> hasDoublePair(Player player){

        ArrayList<Card> pair1 = hasSinglePair(player, "", 0);
        if(pair1 == null){
            return null;
        }
        int valueToSkip = pair1.get(0).getValue();
        ArrayList<Card> pair2 = hasSinglePair(player, "DOUBLE PAIR", valueToSkip);    
        if(pair2 == null){
            return null;
        }

        ArrayList<Card> doublePair = new ArrayList<Card>(0);
        doublePair.add(pair1.get(0));
        doublePair.add(pair1.get(1));
        doublePair.add(pair2.get(0));
        doublePair.add(pair2.get(1));

        return doublePair;
    }


    public ArrayList<Card> hasTris(Player player){

        ArrayList<Card> cards = player.getSortedCopyAllPoints("ASSO14");
        ArrayList<Card> tris = new ArrayList<Card>(0);
        initializePoints(tris,3);
        int cont = 0;
        for(int i = 0; i < cards.size() - 1; i++){
            cont = 1;
            for(int j = i + 1; j < cards.size(); j++){
                if(cards.get(i).checkEqualValueCard(cards.get(j))){
                    if(cont == 1){
                        tris.set(0, cards.get(i));
                        tris.set(1, cards.get(j));
                    }
                    else{
                        tris.set(2,cards.get(j)) ;
                    }
                    cont++;
                }
            }
            if(cont == 3){
                return tris;
            }
        }
        return null;

    }

    public ArrayList<Card> hasStraight(Player player, String mode, String mode2, char seed){

        ArrayList<Card> straight = new ArrayList<Card>(0);
        initializePoints(straight,5);
        ArrayList<Card> copyCards = player.getSortedCopyAllPoints(mode);
        int count = 0;
        if(mode2 == "COLORE"){
            copyCards = hasFlush(player);
        }
        for(int i = 0; i < copyCards.size() - 1; i++){
            if( (copyCards.get(i).getValue()) == (copyCards.get(i + 1).getValue() + 1)){
                straight.set(count,copyCards.get(i));
                count++;
                if(count == 4){
                    straight.set(count,copyCards.get(i + 1));
                    Collections.reverse(straight);
                    return straight;
                }
            }
            else if( ! (copyCards.get(i).checkEqualValueCard(copyCards.get(i + 1)))){
                straight = new ArrayList<Card>(0);
                initializePoints(straight,5);
                count = 0;
            }
        }

        return null;
    }

    
    public boolean findSeedFlush(ArrayList<Card> cards, char seed){

        int contSameSeed = 0;
        for(int i = 0;i < cards.size(); i++){
            if(cards.get(i).getSeed() == seed){
                contSameSeed++;
            }
        }
        if(contSameSeed >= 5){
            return true;
        }
        else{
            return false;
        }

    }

    public ArrayList<Card> hasFlush(Player player){

        ArrayList<Card> copyCards = player.getSortedCopyAllPoints("ASSO14");//per prendere le 5 carte più alte in caso di colore a più di 6 carte
        ArrayList<Card> flush = new ArrayList<Card>(0);
        initializePoints(flush,5);
        boolean findHearts = findSeedFlush(copyCards, 'c');
        boolean findDiamonds = findSeedFlush(copyCards, 'q');
        boolean findFlowers = findSeedFlush(copyCards, 'f');
        boolean findSpades = findSeedFlush(copyCards, 'p');
        char colourSeed;
        if(findHearts){
            colourSeed = 'c';
        }
        else if(findDiamonds){
            colourSeed = 'q';
        }
        else if(findFlowers){
            colourSeed = 'f';
        }
        else if(findSpades){
            colourSeed = 'p';
        }
        else{
            return null;
        }
        int count = 0;
        for(int i = 0; i < copyCards.size() && count <= 4 ; i++){
            if(copyCards.get(i).getSeed() == colourSeed){
                flush.set(count, copyCards.get(i));
                count++;
            }
        }
        return flush;

    }

    public ArrayList<Card> hasFull(Player player){

        ArrayList<Card> full = new ArrayList<Card>(0);
        initializePoints(full,5);
        ArrayList<Card> tris = hasTris(player); 
        if(tris == null){
            return null;
        }        
        int valueToSkip = tris.get(0).getValue();
        for(int i = 0; i < 3; i++){
            full.set(i, tris.get(i));
        }
        ArrayList<Card> pair = hasSinglePair(player, "FULL", valueToSkip);
        if(pair == null){
            return null;    
        }      
        for(int i = 0; i < 2; i++){
            full.set(i + 3, pair.get(i));
        }
        return full;

    }

    public ArrayList<Card> hasPoker(Player player){

        ArrayList<Card> cards = player.getSortedCopyAllPoints("ASSO14");
        ArrayList<Card> poker = new ArrayList<Card>(0);
        initializePoints(poker,4);
        int cont = 0;
        for(int i = 0; i < cards.size() - 1; i++){
            cont = 1;
            for(int j = i + 1; j < cards.size(); j++){
                if(cards.get(i).checkEqualValueCard(cards.get(j))){
                    if(cont == 1){
                        poker.set(0, cards.get(i));
                        poker.set(1, cards.get(j));
                    }
                    else if(cont == 2){
                        poker.set(2, cards.get(j));
                    }
                    else{
                        poker.set(3, cards.get(j));
                    }
                    cont++;
                }
            }
            if(cont == 4){
                return poker;
            }
        }
        return null;

    }

    public ArrayList<Card> hasStraightFlush(Player player){

        ArrayList<Card> flush = hasFlush(player);
        if(flush == null){
            return null;
        }
        char seed = flush.get(0).getSeed();
        ArrayList <Card> straightFlush1 = hasStraight(player, "ASSO14","COLORE", seed);   
        ArrayList <Card> straightFlush2 = hasStraight(player, "ASSO1","COLORE", seed);        
        
        if(straightFlush1 != null){
            return straightFlush1;
        }
        if(straightFlush2 != null){
            return straightFlush2;

        }
        return null;

    }

    public ArrayList<Card> hasRoyalStraight(Player player){

        ArrayList<Card> royalStraight = hasStraightFlush(player);   
        if(royalStraight != null && (royalStraight.get(0).getValue() == 10)){
            return royalStraight;
        }
        else{
            return null;
        }        

    }

}
