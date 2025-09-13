import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Match {
    
    private int n_hands;
    private ArrayList<Player> players;
    private int startFiches;

    public Match(){

        this.players = new ArrayList<Player>(0);
        this.n_hands = 0;

    }

    private void setMatch(String name, int n_players, int startFiches){

        this.n_hands = 0;
        this.startFiches = startFiches;
        setPlayers(name, n_players);  

    }

    private void setPlayers(String name, int n_players){

        Random random = new Random();
        players.add(new Player(name, this.startFiches,0));
        for(int i = 2; i <= n_players; i++){
            String botName = "bot" + (i-1);
            int risky = random.nextInt(5) + 1;
            players.add(new Player(botName, this.startFiches, risky));
        }

    }

    private void clearPlayers(){

        for(int i = 0; i < players.size(); i++){
            players.set(i, new Player(players.get(i).getName(), players.get(i).getFiches(), players.get(i).getRisky()));
        }

    }

    public void startMatch(int startFiches){

        Scanner scanner = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        int nPlayers = 0;
        do{
            System.out.print("Inserisci numero giocatori (minimo 2, massimo 20): ");
            nPlayers = scanner.nextInt();
        }while(nPlayers < 2 || nPlayers > 20);
        System.out.println("Inserisci il tuo nome: ");
        String name = scanner2.nextLine();
        scanner.close();
        scanner2.close();
        setMatch(name, nPlayers, startFiches);

    }


    public void playHands(int n_hands){

        for(int i = 0; i < n_hands; i++){
            this.n_hands++;
            System.out.print("\n\n\nNUOVA MANO [ "+this.n_hands+" ]\n\n\n");
            clearPlayers();
            Hand hand = new Hand(players);
            hand.playHand();
        }

    }


    public static void main(String [] args){

        Match match = new Match();
        match.startMatch(1000);
        match.playHands(10);
    }

}
