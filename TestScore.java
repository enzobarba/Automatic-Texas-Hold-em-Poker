import java.util.ArrayList;
import java.util.Scanner;

//da cambiare in testPoints


public class TestScore {

    private int nPlayers;
    private Hand hand;
    private SingleScore singleScore;
    private ScoreComparator scoreComparator;


    public TestScore(int nPlayers, String name){

        this.nPlayers = nPlayers;
        this.hand = new Hand(nPlayers);
        this.scoreComparator = new ScoreComparator(hand.getPlayers());
        this.singleScore = scoreComparator.getSingleScore();

    }

    private void checkPoints(Player player, String point, int len){

        ArrayList<Card> points = new ArrayList<Card>(0);
        for(int i = 0; i < len; i++){
            points.add(new Card());
        }
        if (point.compareTo("COPPIA") == 0){
            points = singleScore.hasSinglePair(player,"",0);
        }
        else if (point.compareTo("DOPPIA COPPIA") == 0){
            points = singleScore.hasDoublePair(player);
        }
        else if (point.compareTo("TRIS") == 0){
            points = singleScore.hasTris(player);
        }
        else if(point.compareTo("SCALA") == 0){
            ArrayList<Card> straight_A_14 = singleScore.hasStraight(player,"ASSO14","",' ');
            ArrayList<Card> straight_A_1 = singleScore.hasStraight(player,"ASSO1","",' ');
            points = straight_A_1;
            if(straight_A_14 != null){
                points = straight_A_14;
            }
        }
        else if (point.compareTo("COLORE") == 0){
            points = singleScore.hasFlush(player);
        }
        else if (point.compareTo("FULL") == 0){
            points = singleScore.hasFull(player);
        }
        else if (point.compareTo("POKER") == 0){
            points = singleScore.hasPoker(player);
        }  
        else if (point.compareTo("SCALADICOLORE") == 0){
            points = singleScore.hasStraightFlush(player);
        } 
        else if (point.compareTo("SCALAREALE") == 0){
            points = singleScore.hasRoyalStraight(player);
        } 
        if(points != null){
            System.out.print(point+ " " + player.getName() + " : ");
            for(int j = 0; j < len; j++){
                points.get(j).printCard();
            }
            System.out.print("\t");
        }

    }

    private void checkAll(){

        for(int i = 0; i < nPlayers; i++){
            checkPoints(hand.getPlayers().get(i),"COPPIA",2);
            checkPoints(hand.getPlayers().get(i),"DOPPIA COPPIA",4);
            checkPoints(hand.getPlayers().get(i),"TRIS",3);
            checkPoints(hand.getPlayers().get(i),"SCALA",5);
            checkPoints(hand.getPlayers().get(i),"COLORE",5);
            checkPoints(hand.getPlayers().get(i),"FULL",5);
            checkPoints(hand.getPlayers().get(i),"POKER",4);
            checkPoints(hand.getPlayers().get(i),"SCALADICOLORE",5);
            checkPoints(hand.getPlayers().get(i),"SCALAREALE",5);
        }
        System.out.println();

    }

    private void checkPoint(String point,int len){

        for(int i = 0; i < nPlayers; i++){
            checkPoints(hand.getPlayers().get(i),point, len);
        }
        System.out.println();

    }

    private void testPoints(String pointToCheck){ 

        this.hand = new Hand(this.nPlayers);

        if(pointToCheck.compareTo("COPPIA") == 0){
            checkPoint(pointToCheck, 2);
        }
        else if(pointToCheck.compareTo("DOPPIA COPPIA") == 0){
            checkPoint(pointToCheck, 4);
        }
        else if(pointToCheck.compareTo("TRIS") == 0){
            checkPoint(pointToCheck, 3);
        }
        else if(pointToCheck.compareTo("SCALA") == 0){
            checkPoint(pointToCheck, 5);
        }
        else if(pointToCheck.compareTo("COLORE") == 0){
            checkPoint(pointToCheck, 5);
        }
        else if(pointToCheck.compareTo("FULL") == 0){
            checkPoint(pointToCheck, 5);
        }
        else if(pointToCheck.compareTo("POKER") == 0){
            checkPoint(pointToCheck, 4);
        }
        else if(pointToCheck.compareTo("SCALADICOLORE") == 0){
            checkPoint(pointToCheck, 5);
        }
        else if(pointToCheck.compareTo("SCALAREALE") == 0){
            checkPoint(pointToCheck, 5);
        }
        else if(pointToCheck.compareTo("ALL") == 0){
            checkAll();
        }

    }

    private void testHand(){

        this.hand = new Hand(nPlayers);
        this.hand.playHand();

    }
    
    private void test(int n_test,String type){

        for(int i = 0; i < n_test; i++){
            System.out.println("\n\n\nNUOVA MANO [" + (i + 1) + "]: \n\n\n");
            testHand();
            //testPoints(type);
        }

    }
    
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        int nPlayers = 0;
        do{
            System.out.print("Inserisci numero giocatori (minimo 2, massimo 20): ");
            nPlayers = scanner.nextInt();
        }while(nPlayers < 2 || nPlayers > 20);
        System.out.println("Inserisci il tuo nome: ");
        String name = scanner2.nextLine();
        TestScore testScore = new TestScore(nPlayers, name);
        testScore.test(10,"ALL");  
        scanner.close();
        scanner2.close();

    }   
}
