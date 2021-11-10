import reference.*;
/**
 * Clase que representa la partidfa de Old Maid
 * @author Reyes Ramos Luz Maria . 318211073
 * @author Julieta Vargas Gutiérrez 318341945
 * @version 1.0 Noviembre 2021
 * @since EDD 2022-1
 */
public class OldMaidGame {

    /**Old maid stuff */
    OldMaid game = new OldMaid();

    /**Players */
    DoubleLinkedList<Player> players;
    

    /**Jugador de la derecha, izquierda y jugador actual */
    Player nextPlayer, prevPlayer, actual, user;

    Queue<Queue<Card>> historialCard = new Queue<>();

    //Card color
	private final static String red="\033[31m"; 
	private final static String blue="\033[34m"; 
	private final static String reset="\u001B[0m";
	private final static String green="\033[32m"; 
	private final static String yellow="\033[33m"; 
	private final static String purple="\033[35m"; 
	private final static String cyan="\033[36m"; 
	private final static String white = "\u001B[37m";
    


    //Iniciar partida 
    public void startGame(int playersAmount, String userName){
        Queue<Queue<Card>> registroCard= new Queue<>();
        //Crear cartas
        System.out.println("\t"+yellow + "\t★ ★ 「" + white + " W E L C O M E  - TO - O L D - M A I D - G A M E "+ 
        yellow+ "」★ ★ " + reset);
        Deck allCards =  game.generateCards();
        sleep(1000);
        System.out.println("\n\t ★ CARDS:\t\n"+ allCards); //Mostrar cartas
        sleep(4000);
        //crear jugadores
        // ---Jugador usuario
        user= new Player("\t"+userName,0);
        //Demas jugadores
        players = game.generatePlayers(playersAmount-1);
        players.add(0, user);
        game.setPlayers(players);
    
        sleep(4000);
        System.out.println("\n\t ★ PLAYERS: \n" + game.printPlayersName(players));
        sleep(3000);
        System.out.print("\033\143"); //limpiar consola

        System.out.println("\n\t--------"+ red+ " Preparing cards"+ reset);
        game.shuffle(allCards); //revolver cartas
        sleep(1000);

        System.out.println("\n\t--------"+ green+ " Dealing cards"+ reset);
        game.distributeCards(allCards);

        sleep(1000);
        //Mostrar cartas de jugadores
        Player aux= new Player();
        for(int n = 0;n<players.size(); n++){
            aux= players.get(n);
            if(n==0){
                System.out.println("\n\tYour cards\n"+players.get(n));
                sleep(3000);
                continue;
            }
            game.showBackCard(aux.getDeck(), aux);
            
        }
        sleep(5000);
        System.out.print("\033\143"); //limpiar consola
        System.out.println( purple+ "\n\tDiscard your pairs..."+reset);
        Queue<Card> firstUserReoved =players.get(0).getDeck().discardPairs(userName);
        if(!firstUserReoved.isEmpty()){
            registroCard.enqueue(firstUserReoved);
        }
        System.out.println( blue+ "\n\tPlayers are discarding their cards too..."+reset);
        sleep(700);
        






    }

    private void sleep(int time){
        try {
			System.out.print("\n\t......\n\n");
			Thread.sleep(time);
		} catch (InterruptedException e) {
			System.out.println("\nSomething failed D:");
		}
    }
    
}
