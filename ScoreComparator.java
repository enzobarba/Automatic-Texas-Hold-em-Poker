import java.util.ArrayList;

public class ScoreComparator {
    
    private SingleScore singleScore;

    private ArrayList<Card> fillPoints(ArrayList<Card> points, Player player){ 

        int cardsToSet = 5 - points.size(); 
        if(cardsToSet == 0){  
            return points;
        }   
        ArrayList<Card> orderedCards = player.getSortedCopyAllPoints("ASSO14");
        int countCardsSetted = 0;
        for(int i = 0; i < orderedCards.size() && countCardsSetted < cardsToSet; i++){
            if(points.contains(orderedCards.get(i)) == false){
                points.add(orderedCards.get(i));
                countCardsSetted++;
            }
        }
        return points;
        
    }

    private String getStringScore(int valueScore){

        switch(valueScore){
            case 0:
                return "CARTA ALTA";
            case 1:
                return "COPPIA";
            case 2:
                return "DOPPIA COPPIA";   
            case 3:
                return "TRIS";
            case 4:
                return "SCALA";
            case 5:
                return "COLORE";
            case 6:
                return "FULL";   
            case 7:
                return "POKER";
            case 8:
                return "SCALA DI COLORE";   
            case 9:
                return "SCALA REALE";
            default:
                return "ERROR";      
        }

    }

    private int indexPlayerSameScoreCompare(int index1, int index2, ArrayList<Card> points1, ArrayList<Card> points2){

        for(int i = 0; i < points1.size(); i++){
            if(points1.get(i).getValue() > points2.get(i).getValue()){
                return index1;
            }
            else if(points1.get(i).getValue() < points2.get(i).getValue()){
                return index2;
            }
        }
        return -1;

    }

    public ScoreComparator(ArrayList<Player> players){

        this.singleScore = new SingleScore();

    }

    public SingleScore getSingleScore(){

        return this.singleScore;

    }

    public void setScores(ArrayList<Player> players){
    
        for(int i = 0; i < players.size(); i++){
            Player player = players.get(i);
            int score = 0;
            ArrayList<Card> points = null;
            ArrayList<Card> tmpPoints = null;
            tmpPoints = singleScore.hasSinglePair(player,"",0);
            if(tmpPoints != null){
                points = tmpPoints;
                score = 1;
            }
            tmpPoints = singleScore.hasDoublePair(player);
            if(tmpPoints != null){
                points = tmpPoints;
                score = 2;
            }
            tmpPoints = singleScore.hasTris(player);
            if(tmpPoints != null){
                points = tmpPoints;
                score = 3;
            }
            ArrayList<Card> scale_A_14 = singleScore.hasStraight(player,"ASSO14","",' ');
            ArrayList<Card> scale_A_1 = singleScore.hasStraight(player,"ASSO1","",' ');
            tmpPoints = scale_A_1;
            if(scale_A_14 != null){
                tmpPoints = scale_A_14;
            }
            if(tmpPoints != null){
                points = tmpPoints;
                score = 4;
            }
            tmpPoints = singleScore.hasFlush(player);
            if(tmpPoints != null){
                points = tmpPoints;
                score = 5;
            }
            tmpPoints = singleScore.hasFull(player);
            if(tmpPoints != null){
                points = tmpPoints;
                score = 6;
            }
            tmpPoints = singleScore.hasPoker(player);
            if(tmpPoints != null){
                points = tmpPoints;
                score = 7;
            }
            tmpPoints = singleScore.hasStraightFlush(player);
            if(tmpPoints != null){
                points = tmpPoints;
                score = 8;
            }
            tmpPoints = singleScore.hasRoyalStraight(player);
            if(tmpPoints != null){
                points = tmpPoints;
                score = 9;
            }
            String stringScore = getStringScore(score);
            if(stringScore.compareTo("CARTA ALTA") == 0){ 
                points = player.getSortedCopyAllPoints("ASSO14");
                if(points.size() == 7){
                    points.remove(6);
                }
                if(points.size() == 6){
                    points.remove(5);  //if point = high card, we must take the best 5 if cards > 5.
                }
            }
            else{  //else if point != high card, the n-high card search, are in the best 5 cards if n-points < 5
                points = fillPoints(points, player);
            }

            player.setScoreValue(score);
            player.setBestPoints(points);

            System.out.print(stringScore+ " " + player.getName() + " : ");
            player.printBestPoints();
            System.out.print("\t"); 
        }

    }

    public ArrayList<Integer> winnerScores(ArrayList<Player> players){

        ArrayList<Integer> indexWinners = new ArrayList<Integer>(0);
        indexWinners.add(0);
        for(int i = 1; i < players.size(); i++){
            int scoreValue = players.get(i).getScoreValue();
            int maxScoreValue = players.get(indexWinners.get(0)).getScoreValue(); 
            if(scoreValue > maxScoreValue){
                indexWinners =  new ArrayList<Integer>(0);
                indexWinners.add(i);
            }
            else if(scoreValue == maxScoreValue){
                int maxPlayerIndex = indexWinners.get(0);
                int sameScoreWinner = indexPlayerSameScoreCompare(i, maxPlayerIndex, players.get(i).getBestPoints(), players.get(maxPlayerIndex).getBestPoints());
                if(sameScoreWinner == -1){
                    indexWinners.add(i);
                }
                else if(sameScoreWinner == i){
                    indexWinners = new ArrayList<Integer>(0);
                    indexWinners.add(sameScoreWinner);
                }
            }
        }
        System.out.print("\nVINCITORE: ");
        for(int i = 0; i < indexWinners.size(); i++){
            int indexWinner = indexWinners.get(i);
            System.out.print(players.get(indexWinner).getName() + " ");
            System.out.print(getStringScore(players.get(indexWinner).getScoreValue()) + " ");
            players.get(indexWinner).printBestPoints();
        }
        return indexWinners;

    }
}


