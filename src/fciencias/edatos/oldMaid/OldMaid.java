import reference.*;
import java.util.Random;
/**
 * Clase que representa la modalidad de juego "Old Maid".
 * @author Reyes Ramos Luz María. 318211073
 * @author Julieta Vargas Gutiérrez 318341945
 * @version 1.0 Noviembre 2021
 * @since EDD 2022-1
 */
public class OldMaid {

    /**Contiene el mazo de las 51 cartas */
    private DoubleLinkedList<Card> allCards;


    /**Contiene la cantidad total de jugadores */
    private static DoubleLinkedList<Player> players = new DoubleLinkedList<>();

    DoubleLinkedList<Queue<Card>> registros = new DoubleLinkedList<>();

    Queue<Card> stolen = new Queue<>();
    

    //creando en mazo de todas las cartas
    /**
     * Genera un deck con las 51 cartas de inicio del juego.
     * @return
     */
    public Deck generateCards(){
       
        allCards = new DoubleLinkedList<>();
        String[] cardName = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
        String[] suit={"♠", "♦", "♥", "♣"};
        for(int i=0;i<13;i++){
            if(!cardName[i].equals("Queen")){
                allCards.add(allCards.size(),new Card(cardName[i], suit[0]));
            }
            allCards.add(allCards.size() ,new Card(cardName[i], suit[1]));
            allCards.add(allCards.size(),new Card(cardName[i], suit[2]));
            allCards.add(allCards.size(),new Card(cardName[i], suit[3]));
        }

        
        Deck cards = new Deck(auxCreateCards(allCards));
        asignValue(cards.getDecks());
        return cards ;
    }

    private DoubleLinkedList<Card>  auxCreateCards(DoubleLinkedList<Card> allCards ){
        String[] cards = {"🂡","🃁", "🂱", "🃑",     "🂢", "🃂", "🂲", "🃒"   
                         
                         ,"🂣","🃃", "🂳", "🃓",     "🂤" ,"🃄" , "🂴", "🃔", 
                         
                          "🂥","🃅", "🂵", "🃕",     "🂦",  "🃆" ,"🂶",  "🃖",

                          "🂧","🃇", "🂷", "🃗",     "🂨",  "🃈", "🂸",  "🃘" , 
                        
                          "🂩","🃉", "🂹", "🃙",     "🂪",  "🃊", "🂺",  "🃚", 
                          
                          "🂫","🃋", "🂻", "🃛",     "🃍",  "🂽", "🃏",  "🂮", 
                          
                          "🃎", "🂾", "🃞" };
        for (int n = 0; n < cards.length; n++) 
            allCards.get(n).setDraw(cards[n]);
        
        return allCards;

    }

    /*
	 * Asigna el valor al que pertenece cada carta.
	 * 1 - Ace de  "♠", "♦", "♥", "♣"
	 * 2 - Two
	 * 3 - Three
	 * 4 - Four
	 * 5 - Five 
	 * 6 - Six
	 * 7 - Seven
	 * 8 - Eight
	 * 9 - Nine
	 * 10 -Ten
	 * 11 -Jack
	 * 12 -Queen
	 * 13 -KIng
	 * @param cards  Lista de cartas
	 */
	private void asignValue(DoubleLinkedList<Card> cards){
		for(int n =0; n<cards.size(); n++){
            if(cards.get(n).getDrawCard().equals("🃏")){
                cards.get(n).setValue(0);
                cards.get(n).setNombreCarta("Joker");
                cards.get(n).setSuit("✮");
                continue;
                
            }
			cards.get(n).auxAsinngValue(cards.get(n));
		}
	}

    /**
     * Genera una lista de jugadores: Total de participantes en el juego.
     * A lo maximo son 10 jugadores. Rango 1-10
     * @param n Número de jugadores que participarán en el juego.
     * @return Lista con n jugadores.
     */
    public void generatePlayers(int n){
        if(n<1 || n>10){
            System.out.println("Número de jugadores fuera de rango 🚩🚩🚩");
            return;
        }
        players = new DoubleLinkedList<>();
        for (int k = 0; k<n; k++)
            players.add(players.size(), new Player("\tPlayer " + (k+2) + " ٩(●̮̮̃•̃)۶",k+1) );

    }
    //Revolver/ Barajear cartas
    public Deck shuffle(Deck allCards){
        Queue<Integer> index = auxShuffle();
        int i =0;
        DoubleLinkedList<Card> copy = allCards.getDecks();
        DoubleLinkedList<Card> shuffle = new DoubleLinkedList<>();
        for(int n = 0; n<copy.size(); n++){
            i= index.first();
            index.dequeue();
            shuffle.add(n, copy.get(i));
        }
        //System.out.println("+++++++++"+shuffle);



        return new Deck(shuffle);
    }

    //auxiliar para barajear cartas
    /*
     * Regresa una cola con numeros random sin repeticiones 
     * del 0 al 50, esto será de ayuda para utilizarse como 
     * índices.
     */
    private Queue<Integer> auxShuffle(){
        Queue <Integer> indices = new Queue<>();
        DoubleLinkedList<Integer> repeated = new DoubleLinkedList<>();
        
        Random rn = new Random();
        int index = rn.nextInt(51);
        for(int k = 0; k<51; k++){
            while (repeated.contains(index)) {
                index = rn.nextInt(51);
            }
            repeated.add(0, index);
            indices.enqueue(index);
        }
        return indices;
    }

    //----------------------Repartir cartas
    
    public void distributeCards(Deck allCards){
        Card temp = new Card();
        try {
            while(allCards != null) {
                for(int n =0; n<players.size();n++){
                    temp = allCards.getDecks().get(0);
                    players.get(n).getDeck().addToDeck(temp);
                    allCards.removeFromDeck(0);
                }
            }
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            return;
        }
      
    }

    // Descartar pares
    /**
     * Elimina los pares de cartas de todos los jugadores de una lista. Version machine
     * @param players Lista de jugadores
     * @return Una lista de queues, donde cada queue contiene las cartas que fueron eliminadas
     * por jugador, una queue representa las cartas eliminadas de un jugador.
     */
    public void discardPairsMachine(DoubleLinkedList<Player> players){
        for(int n=0; n<players.size();n++){
            if(n==0)
                continue;
            
            players.get(n).getDeck().discardPairs(players.get(n));
        }
       

    }

    //Turno, este debe ser en sentido de las agujas del reloj ....Elegir carta de otro jugador a su derecha.

    /**
     * Elige una carta de las cartas del siguiente jugador.
     * @param nextPLayer El jugador al que se le quitará una carta.
     * @param card Numero de carta a descartar, debe estar dentro del rango de cartas
     * @return La carta que fue descartada
     */
    public Card pickCard(Player nextPLayer,int card){ //version user
        //Suponiendo que ya se dioel numero de carta a quitar
        Card picked = new Card();

        if(card==0){
            return picked = nextPLayer.getDeck().getDecks().get(0);
        }
        try {
            
            for(int n = 0; n<nextPLayer.getDeck().getSize(); n++)
                if(card == n){
                    //No eliminar
                    return picked = nextPLayer.getDeck().getDecks().get(n);
                    
                }
                    
                
            
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            System.out.println("\n\tIndex out of range, you have not such amount of cards, please "+
            "try again with a valid number. (¬_¬)");
            return picked;
        }
        

        return picked;
       
    }

    /**
     * Muestra las cartas del siguiente jugador a quitar cartas, sólo se muestra la cara 
     * trasera del la carta.
     * @param namePlayer El nombre del siguiente jugador (a quien se le quitará una carta).
     * @param playerDeck Las cartas del otro jugador.
     */
    public  void showBackCard( Deck playerDeck, Player player){
        //System.out.println("Pick one card from "+ namePlayer + " cards\n");
        try {
            System.out.println("\n\n\t"+player.getName() + " cards\n");
            for(int n=0; n<playerDeck.getSize();n++)
                System.out.print("\t"+playerDeck.getDecks().get(n).getBack()+ " "+(n+1));
            
            
        } catch (NullPointerException| IndexOutOfBoundsException e) {
            //TODO: handle exception
            System.out.print("*");
        }
        
    }

    public DoubleLinkedList<Card> getAllCards(){
        return allCards;
    }
    public void setAllCards(DoubleLinkedList<Card> cards){
        this.allCards = cards;
    }
    public DoubleLinkedList<Player> getPlayers(){
        return players;
    }

   

    public DoubleLinkedList<Queue<Card>> getRegistros(){
        return registros;
    }

    public void setRegistros(DoubleLinkedList<Queue<Card>> registros){
        this.registros = registros;
    }

    /**
	 * Imprime los nombres de una lista de jugadores.
	 * @param players Lista de jugadores.
	 * @return Cadena con nombres de los jugadores.
	 */
	public String printPlayersName(DoubleLinkedList<Player> players){
		String names="";
		for(int n =0; n<players.size(); n++){
			names+="\t"+players.get(n).getName()+ "\n\n";
		}
		return "\n"+names;
	}



    
    
    

}
