	import reference.*;

/**
 * Clase que implementa el mazo
 * @author Julieta Vargas Gutiérrez 318341945
 * @author Reyes Ramos Luz Marìa  318211073
 * @version 1.0 Noviembre 2021.
 * @since Estructuras de datos 2022-1.
 */


public class Deck {
        
     /** Listas doblemente ligadas para cartas */ //	
	DoubleLinkedList<Card> decks;


	//Card color
	private final static String red="\033[31m"; 
	private final static String blue="\033[34m"; 
	private final static String reset="\u001B[0m";
	private final static String green="\033[32m"; 
	private final static String yellow="\033[33m"; 
	private final static String purple="\033[35m"; 
	private final static String cyan="\033[36m"; 
	private final static String white = "\u001B[37m";
	 

	Queue<Player> auxRegistros;

     /**Constuctor a partir de listas doblemente ligadas*/     
	public Deck(){
		this.decks = new DoubleLinkedList<>();
	}

	public Deck(DoubleLinkedList<Card> cards){
		this.decks = cards;
	}

        
        /**
	* Permite añadir carta
        * @param card la carta a agregar
	*/
        
	public void addToDeck (Card card){
		this.decks.add(getSize(),card);
	}
        
    /**
	* Permite remover una carta
    * @param i indice de la carta que quieres remover
	*/
   
       public Card removeFromDeck (int i){
		return this.decks.remove(i);
	}
       
    /**
	* Regresa el tamaño de la baraja 
	* @return el tamaño de la baraja
	*/   
	public int getSize (){
		return decks.size();
	}

    
	/**
	* Regresa apartir de listas doblemente ligadas un mazo con cartas
	* @return decks
	*/          
	public DoubleLinkedList<Card> getDecks() {
		return decks;
	}
        
    /**
	* Modifca el mazo apartir de una lista de cartas.
    * @param decks Lista de cartas.
	*/
	public void setDecks(DoubleLinkedList<Card> decks) {
		this.decks = decks;
	}

	/**
	 * Determina si en una lista de cartas hay elementos duplicados, es decir 
	 * determina si existe un par de cartas iguales en valor.
	 * @return true si hay par, false en otro caso.
	 */
	public boolean duplicatedCards(){
		int value1 = 0, value2 = 0;
		for(int n = 0; n<getSize()-1; n++){
			for(int k = n+1; k<getSize(); k++){
				value1 = getDecks().get(n).getValue();
				value2 = getDecks().get(k).getValue();
				if(value1 == value2)
					return true;
				
			}
		}
		return false;
	}

	/**
	 * Remueve los pares de una lista de cartas (si los hay) version.
	 * @return Queue con los elementos eliminados
	 */
	public Queue<Card> discardPairs(Player player){
		Queue<Card> removed = new Queue<>();
		if(!duplicatedCards()){
			System.out.println(yellow+"\n\t There isn´t any pairs to discard!! (/◔ ◡ ◔)/"+player.getName()+reset);
			return removed;
		}
		try {
			DoubleLinkedList<Card> cards = getDecks();
			int value = 0, value1=0;
				Card card1 = new Card(), card2 = new Card();
				System.out.print("\n\tDiscarded cards from "+player.getName()+":");
				for(int n = 0; n<getSize(); n++){
					for(int k=n+1; k<getSize(); k++){
						value = cards.get(n).getValue();
						value1 = cards.get(k).getValue();
						if(value == value1){
							card1= cards.remove(n);
							card2 = cards.remove(k-1);
							removed.enqueue(card1);
							removed.enqueue(card2);
							n=0;
							k= n+1;
							System.out.print("\t"+card1 + "\n\t"+ card2+"\n\n");
						}
					}
				}
				auxRegistros.enqueue(player);
		
		} catch (IndexOutOfBoundsException | NullPointerException e) {
			System.out.println("\n\tSomething went wrong! D:");
			return removed;
		}
		return removed; //será de utilidad para el historial
	}



	@Override
	public String toString(){
		return getDecks().toString();
	}


 
}

