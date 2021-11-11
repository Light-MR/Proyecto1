import java.util.Random;
import java.util.Scanner;
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

    Scanner sc  = new Scanner(System.in);
    

    /**Jugador de la derecha, izquierda y jugador actual */
    Player nextPlayer, prevPlayer, actual, user;

    Queue<Queue<Card>> historialCard = new Queue<>();
    Queue<Player> playersRemoved= new Queue<>();

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
        try {
            //Crear cartas
            System.out.println("\t"+yellow + "\t★ ★ 「" + white + " W E L C O M E  - TO - O L D - M A I D - G A M E "+ 
            yellow+ "」★ ★ " + reset);
            Deck allCards =  game.generateCards();
            sleep(1000);
            System.out.println("\n\t ★ CARDS:\t\n"+ allCards); //Mostrar cartas
            sleep(3000);
            //crear jugadores
            // ---Jugador usuario
            user= new Player("\t"+userName,0);
            //Demas jugadores
            players = game.generatePlayers(playersAmount-1);
            players.add(0, user);
            game.setPlayers(players);
    
            System.out.println("\n\t ★ PLAYERS: \n" + game.printPlayersName(players));
            sleep(3000);
            System.out.print("\033\143"); //limpiar consola

            System.out.println("\n\t--------"+ red+ " Shuffling cards"+ reset);
            allCards = game.shuffle(allCards);//revolver cartas
            //System.out.println(allCards);
            sleep(1000);

            System.out.println("\n\t--------"+ green+ " Dealing cards"+ reset);
            game.distributeCards(allCards);

            sleep(600);
            //Mostrar cartas de jugadores
            Player aux= new Player();
            for(int n = 0;n<players.size(); n++){
                aux= players.get(n);
                if(n==0){
                    System.out.println("\tYour cards"+players.get(n));
                    sleep(3000);
                    continue;
                }
                game.showBackCard(aux.getDeck(), aux);
            }

            //System.out.println(players);
            sleep(5000);
            System.out.print("\033\143"); //limpiar consola
            System.out.println( purple+ "\n\tDiscard your pairs..."+reset);
            Queue<Card> firstUserReoved =players.get(0).getDeck().discardPairs(players.get(0));
            if(!firstUserReoved.isEmpty()){
                game.getRegistros().add(game.getRegistros().size(), firstUserReoved);
            }
            System.out.println( blue+ "\n\tPlayers are discarding their cards too..."+reset);
            sleep(700);

            game.discardPairsMachine(players);

           

            sleep(2000);
            System.out.print("\033\143"); //limpiar consola
            System.out.println(green+"\n\tWho's going to start?٩(͡๏̯͡๏)۶   "+reset+ 
            cyan + "\n\n\t\tpicking player----"+ reset);
            sleep(200);
            //Decidir quien comienza la partida
            Random rn  = new Random();
            int starts= rn.nextInt(players.size());
            actual = players.get(starts);
       
            System.out.println(white+ "\n\t★"+ actual.getName()+ " starts the game!!"+ reset);
            //Asignando referencias.
            players.get(starts).setTurn(1);
            System.out.println("\n\tAcual player number:"+ actual.getNumPlayer());
            if(actual.getNumPlayer() ==0){
                nextPlayer = players.get(starts+1);
                prevPlayer = players.get(players.size()-1);
            }else if(actual.getNumPlayer() == players.size()-1){
                prevPlayer = players.get(starts-1);
                nextPlayer = players.get(0);
            }else{
                nextPlayer = players.get(starts+1);
                prevPlayer= players.get(starts-1);
            }
            System.out.println("\n\tIndex prev"+ players.getIndex(prevPlayer)+ "current"+ players.getIndex(actual)
            + "Next:"+ players.getIndex(nextPlayer));
            //System.out.println("PrevPlayer"+ prevPlayer+ "\t\tNextPlayer"+ nextPlayer);
        } catch (IndexOutOfBoundsException |  NullPointerException e) {
            System.out.println("\n\t Unexpected error");
            return;
        }



    }

    private void sleep(int time){
        try {
			System.out.print("\n\t......\n\n");
			Thread.sleep(time);
		} catch (InterruptedException e) {
			System.out.println("\nSomething failed D:");
		}
    }
    
    public Player game(int amountPlayers, String username){
        startGame(amountPlayers, username);
        Player loser = new Player();
        Player temp=new Player();
        int aux=0 ,cont =0;
        int index=0, numCard=0;
        try {
            while (players.size()!= 1) {
                
                
                System.out.println("\n\tPrev"+prevPlayer);
                System.out.println("\n\tActual"+actual);
                System.out.println("\n\tNext"+nextPlayer);
                aux = players.getIndex(prevPlayer);
                System.out.println("Aux: "+aux);
    
                if(nextPlayer.getDeck()==null){
                    //Eliminando jugador que se quedó sin cartas
                    index = players.getIndex(nextPlayer);
                    temp = players.remove(index);
                    System.out.println("uuuu");
                    nextPlayer=players.get(index+1);
                    playersRemoved.enqueue(temp);
                    System.out.println(red+ "\n\t"+temp.getName()+ "has left the game 🚩 ");
                    
                    
                    
                   /////
                }

                

                //robar carta jugador derecha
                if(actual.getNumPlayer()==0){//si es el usuario
                    System.out.println("\n\tPick a card from the player on your right!\tThe player is:"
                    + nextPlayer.getName()+ "\n\n\t"+ nextPlayer.getName()+"cards:\n\n");
                    game.showBackCard(nextPlayer.getDeck(), nextPlayer);
                    numCard = askCard(nextPlayer.getDeck().getSize());
                    pickRightUser(numCard);
                    

                }else{
                    System.out.println("\n\tPicking a card from the player on the right!\n\tThe player is:"
                    + nextPlayer.getName()+ " and the current player is:"+actual.getName()+"\n\n\t"+ nextPlayer.getName()+"cards:\n\n");
                    game.showBackCard(nextPlayer.getDeck(), nextPlayer);
                    pickRightMachine();
                }

               

                //picks cards from players from the right and switch turns with players from the left
               
                //Asignando referencias, indices para prev estan al revés 0_0
                if(!hasNextPlayer(actual)){
                    System.out.println("*****");
                    nextPlayer = players.get(0);
                    actual= prevPlayer;
                    prevPlayer= players.get(aux-cont);
                }else if(!hasPrevPlayer(actual)){
                    System.out.println("++++++");
                    nextPlayer= actual;
                    actual=prevPlayer;
                    prevPlayer = players.get(players.size()-1);
                }else{
                    nextPlayer= actual;
                    actual = prevPlayer;
                    prevPlayer = players.get(players.size()-aux-1);
                }
                cont++;
                System.out.println("Prev1:"+prevPlayer);
                System.out.println("Actual1:"+actual);
                System.out.println("Next:"+nextPlayer);


                


                
            }
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            System.out.println("\n\tSomething is wrong  with me :(");
            System.out.println("\n\tPlayer List size:"+ players.size());
            System.out.println(e);
            e.printStackTrace();
            return loser;
        }
        System.out.println(loser = players.get(0));
        return loser;
    }

    private void pickRightUser(int numCard){
        numCard = numCard-1;
        Queue<Card> auxR = new Queue<>();
        Card aux = game.pickCard(nextPlayer, numCard);
        System.out.println("\n\tYou picked card :"+ numCard+ aux + "from"+ nextPlayer.getName());
        actual.getDeck().addToDeck(aux);
        System.out.println("\n\tNow your cards are:\n\n"+ actual);
        nextPlayer.getDeck().removeFromDeck(numCard);

        System.out.println(red+"\n\tDicarding your pairs, if there's pairs..."+reset);
        auxR=actual.getDeck().discardPairs(actual);


        //ACOMODO DE CARTAS
        System.out.println(yellow + "\n\tSort your cards?"+reset);
    

        
        
        if(!auxR.isEmpty()){
            game.getRegistros().add(game.getRegistros().size(), auxR);
        }
    }

    public void pickRightMachine(){
        Random rn = new Random();
       
        try {
            int option = rn.nextInt(nextPlayer.getDeck().getSize());
            if(option==nextPlayer.getDeck().getSize() && option != 0){
                option= option-1;
            }
            Card aux= game.pickCard(nextPlayer, option);
            System.out.println("\n\t"+actual.getName()+"  picked card "+option+": "+ aux.getBack() + " from  "+ nextPlayer.getName());
            actual.getDeck().addToDeck(aux);
            System.out.println("\n\tNext Deck size:"+nextPlayer.getDeck().getSize() + "\tIndex:"+ option);
            nextPlayer.getDeck().removeFromDeck(option);
            System.out.println(blue+"\n\tDicarding" + actual.getName()+ " pairs, if there's pairs..."+reset);
            Queue<Card> auxR=actual.getDeck().discardPairs(actual);
            if(!auxR.isEmpty()){
                game.getRegistros().add(game.getRegistros().size(), auxR);
            }
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            //TODO: handle exception
        }

    }

    private boolean hasNextPlayer(Player player){
        try {
            return players.getIndex(players.get(players.getIndex(player)+1)) !=-1;
        } catch (IndexOutOfBoundsException | NullPointerException  e) {
            return false;
            //TODO: handle exception
        }
        
    }
    private Boolean hasPrevPlayer(Player player){
        try {
            return players.getIndex(players.get(players.getIndex(player)+1)) !=-1;
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            return false;
            //TODO: handle exception
        }
    }

    public int askCard(int range){
        int number=-1;
        String userInput ="k";
        while (!userInput.equals("")) {
            System.out.println(blue+"/////////////////////"+reset);
            try {
                while (!(number>=1 && number<=range)) {
                    System.out.println("\n\tType your option:");
                    number= Integer.parseInt(sc.nextLine());  
                }
                System.out.println("\n\tPress enter to continue");
                    userInput= sc.nextLine();
                    if(userInput.equals("")){
                        return number;
                    }
            } catch (Exception e) {
                System.out.println("\n\tInvalid input, try typing a valid card option 🚩");
                //sc.nextLine();
            }
        }
    

        return 0;
    }

    
}
