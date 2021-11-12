import java.util.Random;
import java.util.Scanner;

import javax.swing.text.AsyncBoxView;

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
    //DoubleLinkedList<Player> players;

    Scanner sc  = new Scanner(System.in);
    
    String historial ="";

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
            game.generatePlayers(playersAmount-1);
            game.getPlayers().add(0, user);
            //game.setPlayers(players);
    
            System.out.println("\n\t ★ PLAYERS: \n" + game.printPlayersName(game.getPlayers()));
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
            int size = game.getPlayers().size();
            for(int n = 0;n<size; n++){
                aux= game.getPlayers().get(n);
                if(n==0){
                    System.out.println("\tYour cards"+game.getPlayers().get(n));
                    sleep(3000);
                    continue;
                }
                game.showBackCard(aux.getDeck(), aux);
            }

            //System.out.println(players);
            sleep(5000);
            System.out.print("\033\143"); //limpiar consola
            System.out.println( purple+ "\n\tDiscard your pairs..."+reset);
            game.getPlayers().get(0).getDeck().discardPairs(game.getPlayers().get(0));
            
            System.out.println( blue+ "\n\tPlayers are discarding their cards too..."+reset);
            sleep(700);

            game.discardPairsMachine(game.getPlayers());

           

            sleep(2000);
            System.out.print("\033\143"); //limpiar consola
            System.out.println(green+"\n\tWho's going to start?٩(͡๏̯͡๏)۶   "+reset+ 
            cyan + "\n\n\t\tpicking player----"+ reset);
            sleep(200);
            //Decidir quien comienza la partida
            Random rn  = new Random();
            int starts= rn.nextInt(game.getPlayers().size()); //elección al azar 
            actual = game.getPlayers().get(starts); //el actual al inciar es el elegido al azar

            historial+= white+ "\n\t★"+ actual.getName()+ " starts the game!!"+ reset; //Lo primero que guarda el historial es quien inicia.
            System.out.println(white+ "\n\t★"+ actual.getName()+ " starts the game!!"+ reset);

            //Sin sólo son dos jugadores 
            if(game.getPlayers().size()==2){
                if(!hasPrevPlayer(actual)){ //el que se eligió al azar es el primero
                    nextPlayer= game.getPlayers().get(1);//Su siguiente es el otro elemento
                    prevPlayer = actual; //previo y actual seran lo mismo 
                }
                //Si es el segundo
                nextPlayer=actual;
                actual=game.getPlayers().get(0);
                prevPlayer=actual;
                System.out.println("\n\tTwo players:\t"+ actual + "\tNext: "+ nextPlayer + "\tprev: "+ prevPlayer);
                System.out.println(game.getPlayers());
                return;
                
            }
            //Asignando referencias. Para más de dos juagadores
            if(actual.getNumPlayer() ==0){ //Para el usuario, su identificador siempre será el 0
                nextPlayer = game.getPlayers().get(starts+1);
                prevPlayer = game.getPlayers().get(game.getPlayers().size()-1);
            }else if(actual.getNumPlayer() == game.getPlayers().size()-1){//Si el elegido al azar es el ultimo de la lista
                prevPlayer = game.getPlayers().get(starts-1);
                nextPlayer = game.getPlayers().get(0); //si suguiente es la cabeza de la lista
            }else{
                nextPlayer = game.getPlayers().get(starts+1);
                prevPlayer= game.getPlayers().get(starts-1);
            }
            System.out.println("\n\tIndex prev: "+ game.getPlayers().getIndex(prevPlayer)+ "\tcurrent: "+ game.getPlayers().getIndex(actual)
            + "\tNext: "+ game.getPlayers().getIndex(nextPlayer));
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
            while (true) {
            
                   /* 
                if(nextPlayer.getDeck().isEmpty() && players.size() >2){


                    System.out.println(red+"\n\t"+ nextPlayer.getName()+" ran out of cards!!"+
                    "\n\n\tNow "+ nextPlayer.getName() + " is leaving the game :3"+reset);
                    //if(hasNextPlayer(actual)){
                        //actual = players.get(players.getIndex2(nextPlayer)+1);
                    //}else{
                        nextPlayer= players.get(players.getIndex2(actual));
                        actual = prevPlayer;
                        prevPlayer = players.get(players.getIndex2(actual)-1);
                       
                        //prevPlayer= players.get(players.getIndex2(prevPlayer)-1);
                    //}
                    System.out.println(green+"\n\t"+players.remove(players.getIndex2(actual))+"\n\t------ IS OUT!!"+reset);
                    System.out.println("\n\tPlayers size"+ players );
                    
                    //playersRemoved.enqueue(winner);
                    if(players.size()==1){
                        System.out.println("\n\n\tSIZE:  "+ players.size() + players);
                        break;
                    }
                }*/
                         
                
              
                /*
                System.out.println("\n\tPrev"+prevPlayer);
                System.out.println("\n\tActual"+actual);
                System.out.println("\n\tNext"+nextPlayer);
                aux = players.getIndex2(prevPlayer);
                System.out.println("Aux: "+aux);*/
                aux = game.getPlayers().getIndex2(prevPlayer);
    
                //robar carta jugador derecha
                if(actual.getNumPlayer()==0 && !actual.getDeck().isEmpty()){//si es el usuario
                    System.out.println("\n\tPick a card from the player on your right!\tThe player is:"
                    + nextPlayer.getName()+"\n");
                    game.showBackCard(nextPlayer.getDeck(), nextPlayer);
                    numCard = askCard(nextPlayer.getDeck().getSize());
                    pickRightUser(numCard);
                    

                }else{
                    System.out.println("\n\tPicking a card from the player on the right!\n\tThe player is:"
                    + nextPlayer.getName()+ " and the current player is:"+actual.getName()+"\n");
                    game.showBackCard(nextPlayer.getDeck(), nextPlayer);
                    pickRightMachine();
                }

                if(game.getPlayers().size()==2){
                    Player tem = actual;
                    actual= nextPlayer;
                    nextPlayer= tem;
                    prevPlayer=actual=prevPlayer;
                    System.out.println("\n\tHAY DOS JUGADORES\n"+game.getPlayers());
                }
               

                //picks cards from players from the right and switch turns with players from the left
               
                //Asignando referencias, indices para prev estan al revés 0_0
                if(!hasNextPlayer(actual) && aux-1<game.getPlayers().size() && aux-1>2 && cont==1){
                    System.out.println("\n\t*****");
                    nextPlayer = game.getPlayers().get(0);
                    actual= prevPlayer;
                    prevPlayer= game.getPlayers().get(aux-1);
                }else if(!hasPrevPlayer(actual) && game.getPlayers().size()-1>2 &&(game.getPlayers().size()-2>2)){
                    System.out.println("\n\t++++++");
                    nextPlayer= actual;
                    actual=game.getPlayers().get(game.getPlayers().size()-1);
                    prevPlayer = game.getPlayers().get(game.getPlayers().size()-2);
                }else if(!hasPrevPlayer(prevPlayer) && hasPrevPlayer(actual)){
                    System.out.println("\n\t---------");
                    nextPlayer= actual;
                    actual=prevPlayer;
                    prevPlayer= game.getPlayers().get(0);
                }else if(aux-1<game.getPlayers().size() && aux-1>=0){
                    nextPlayer= actual;
                    actual = prevPlayer;
                    prevPlayer = game.getPlayers().get(aux-1);
                }
                
                /*
                System.out.println("Prev1:"+prevPlayer);
                System.out.println("Actual1:"+actual);
                System.out.println("Next:"+nextPlayer);
                System.out.println("\n\tHAS PREV?"+ hasPrevPlayer(actual));*/
                
                System.out.println("\n\t*********FIN ************\n\tPLAYERS:\n"+ game.getPlayers() );
                cont++;
                if(game.getPlayers().size()==1){
                    break;
                }

              
            }
            
          
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            System.out.println("\n\tSomething is wrong  with me :(");
            System.out.println("\n\tPlayer List size:"+ game.getPlayers().size());
            System.out.println(game.getPlayers());
            System.out.println(e);
            e.printStackTrace();
            return loser;
        }
        System.out.println(game.getPlayers());
        return loser;
    }

    private String pickRightUser(int numCard){
        
        numCard = numCard-1;
        int nextIndex=  game.getPlayers().getIndex2(nextPlayer);
        
        Card aux = game.pickCard(nextPlayer, numCard);
        String registro ="\n\tYou picked card :"+(numCard+1)+ aux + "from"+ nextPlayer.getName();
        System.out.println(registro);
        //Aplicar directo a los jugadores de la
        game.getPlayers().get(game.getPlayers().getIndex2(actual)).getDeck().addToDeck(aux);
        //actual.getDeck().addToDeck(aux);
        

        System.out.println("\n\tNow your cards are:\n\n"+ actual);
        nextPlayer.getDeck();
        //Quitar de next
        int cardIndex= game.getPlayers().get(nextIndex).getDeck().getDecks().getIndex2(aux);
        game.getPlayers().get(nextIndex).getDeck().removeFromDeck(cardIndex);

                
        if(nextPlayer.getDeck().isEmpty()){
            registro+=leave();
            
        }       
        System.out.println(red+"\n\tDicarding your pairs, if there's pairs..."+reset);
        int actualIndex = game.getPlayers().getIndex2(actual);

        registro+=game.getPlayers().get(actualIndex).getDeck().discardPairs(game.getPlayers().get(actualIndex)); //Descarando pares de actual



        //ACOMODO DE CARTAS
        System.out.println(yellow + "\n\tSort your cards?"+reset);

        return registro;

    }

    public String pickRightMachine(){
        Random rn = new Random();
        String registro="";
        
       
        try {
            int option = rn.nextInt(nextPlayer.getDeck().getSize());//azar
            Card aux= game.pickCard(nextPlayer, option);
            int nextIndex = game.getPlayers().getIndex2(nextPlayer);
            int indexAct = game.getPlayers().getIndex2(actual);
            registro= "\n\n\t"+actual.getName()+"  picked card "+(option+1)+": "+ aux.getBack() + " from  "+ nextPlayer.getName();
            System.out.println(registro);
            game.getPlayers().get(indexAct).getDeck().addToDeck(aux);//Añadir a actual
            
            //Quitar de next

            System.out.println("\n\tNext Deck size:"+nextPlayer.getDeck().getSize() + "\tIndex:"+ option);
            System.out.println(blue+"\n\tDicarding" + actual.getName()+ " pairs, if there's pairs..."+reset);
            int cardIndex= game.getPlayers().get(nextIndex).getDeck().getDecks().getIndex2(aux);
            game.getPlayers().get(nextIndex).getDeck().removeFromDeck(cardIndex);
                    
            if(nextPlayer.getDeck().isEmpty()){
                registro+=leave();
                
            }
            registro+=game.getPlayers().get(nextIndex).getDeck().discardPairs(game.getPlayers().get(nextIndex)); //Descarando pares de next
            
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            //TODO: handle exception
        }
        return registro;

    }

    private boolean hasNextPlayer(Player player){
        try {
            return game.getPlayers().getIndex2(game.getPlayers().get(game.getPlayers().getIndex2(player)+1)) !=-1;
        } catch (IndexOutOfBoundsException | NullPointerException  e) {
            return false;
            //TODO: handle exception
        }
        
    }
    private Boolean hasPrevPlayer(Player player){
        try {
            return game.getPlayers().getIndex2(game.getPlayers().get(game.getPlayers().getIndex2(player)-1))>=0;
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

    private String  leave(){
        

        System.out.println(red+"\n\t"+ nextPlayer.getName()+" ran out of cards!!"+
        "\n\n\tNow "+ nextPlayer.getName() + " is leaving the game :3"+reset);
        String registro =red+"\n\t"+ nextPlayer.getName()+" left the game.";
        Player removed = game.getPlayers().remove(game.getPlayers().getIndex2(nextPlayer));
        System.out.println(green+"\n\t"+ removed +"\n\t------ IS OUT!!"+reset);
        System.out.println("\n\tPlayers size"+ game.getPlayers() );
        return registro;
        
    }




    /// E M P E Z A N D O  ---- A G A I N

    public void gameOM(int numPlayers, String username){
        //Iniciar Juego
        startGame(numPlayers, username);
        int  cont =0;
        //"Caso base cuando don 2 juagdores"
        int numCard=0, range;//El numero de carta a descartar
        
        try {
            do {
                int indexActList = game.getPlayers().getIndex(actual);
                int indNext =game.getPlayers().getIndex(nextPlayer);
                if(game.getPlayers().size()== 2){ //Si son dos , solo es necesario actual y next?
                    System.out.println("\n\t//////////////TWO////");
                    while (!actual.getDeck().isEmpty() || !nextPlayer.getDeck().isEmpty()){
                        if(game.getPlayers().size()==1){
                            System.out.println("\n\tSIZE 1"+ game.getPlayers());
                            return;
                        }

                        //Robar carta al otro jugador
                        //SI el usuario aun esta dentro de estos dos jugadores
                        if(!nextPlayer.getDeck().isEmpty()){
                            if(actual.getNumPlayer()==0 && !actual.getDeck().isEmpty()){
                                System.out.println("\n\tPick a card from the player on your right!\t Next player is:"+
                                blue + nextPlayer.getName()+"\n"+reset);
                                game.showBackCard(nextPlayer.getDeck(), nextPlayer);
                                range= nextPlayer.getDeck().getSize();
                                numCard= askCard(range);
                                historial += pickRightUser(numCard);
                            }else{
                                System.out.println("\n\tPicking a card from the player on the right!\n\tThe player is:"+
                                nextPlayer.getName()+ " and the current player is:"+actual.getName()+"\n");
                                game.showBackCard(nextPlayer.getDeck(), nextPlayer);
                                historial+=pickRightMachine();
                            }//Si se trata del otro jugador

                        }
                        //referencias
                        
                        nextPlayer= game.getPlayers().get(indexActList);
                        actual = game.getPlayers().get(indNext);
                        System.out.println(game.getPlayers());
                        
                        
                    }
                }

                //Si son más de dos jugadores
                int  aux = game.getPlayers().getIndex2(prevPlayer); //Indice de jugador a laizquierda
                
                 //robar carta jugador derecha
                if(!nextPlayer.getDeck().isEmpty()){
                    if(actual.getNumPlayer()==0 && !actual.getDeck().isEmpty()){//si es el usuario
                        System.out.println("\n\tPick a card from the player on your right!\tThe player is:"
                        + nextPlayer.getName()+"\n");
                        game.showBackCard(nextPlayer.getDeck(), nextPlayer);
                        numCard = askCard(nextPlayer.getDeck().getSize());
                        historial+=pickRightUser(numCard);
                    }else{
                        System.out.println("\n\tPicking a card from the player on the right!\n\tNext player is:"
                        + nextPlayer.getName()+ " and the current player is:"+actual.getName()+"\n");
                        game.showBackCard(nextPlayer.getDeck(), nextPlayer);
                        historial+=pickRightMachine();
                    }
                }

                //Refrencias para mas de un jugador

                /*Si el actual no tiene jugador a la derecha y el indice para el jugador de 
                *la izquierda es >2 pero < a la longitud de la lista .
                * Solo se cumple cuando el jugador que empieza es el ultimo en la lista*/ 
                if(!hasNextPlayer(actual) && aux-1<game.getPlayers().size() && aux-1>2 && cont==1){
                    System.out.println("\n\t*****");
                    nextPlayer = game.getPlayers().get(0);
                    actual = game.getPlayers().get(aux);
                    prevPlayer= game.getPlayers().get(aux-1);
                }else if(!hasPrevPlayer(actual) && game.getPlayers().size()-1>2 &&(game.getPlayers().size()-2>2)){
                    System.out.println("\n\t++++++");
                    
                    nextPlayer= game.getPlayers().get(indexActList); //next = actual
                    actual=game.getPlayers().get(game.getPlayers().size()-1); //actual = ultimo en la lista
                    prevPlayer = game.getPlayers().get(game.getPlayers().size()-2);//prev = penultimo en la lista

                }else if(!hasPrevPlayer(prevPlayer) && hasPrevPlayer(actual)){
                    System.out.println("\n\t---------");
                    nextPlayer=game.getPlayers().get(indexActList) ;
                    actual= game.getPlayers().get(aux);
                    prevPlayer= game.getPlayers().get(0);
                    /* Si no se puede avanzar dos jugadores antes del actual, pero el jugador actual
                    si tiene un aterior, ejemplo el jugador 2*/ 
                }else if(aux-1<game.getPlayers().size() && aux-1>=0){ //Si no entra en los casos especiales
                    nextPlayer= actual;
                    actual = prevPlayer;
                    prevPlayer = game.getPlayers().get(aux-1);
                }

                System.out.println("\n\tPlayers List size:"+ game.getPlayers().size()+ "\n\n\t***P L A Y E R S\n\n"
                + game.getPlayers());


            } while (game.getPlayers().size()>1);
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            System.out.println("\n\tSomething is wrong with me! :(.\n\n\t"+
            "Players List size:"+ game.getPlayers().size());

          //   System.out.println(e);
          //    e.printStackTrace();
        }




    }

    public String getHistorial(){
        return historial;
    }

    

    
}
