import reference.*;  
/**
 * Clase que implementa el jugador
 * @author Julieta Vargas Gutiérrez 318341945
 * @author Reyes Ramos Luz María 318211073
 * @version 1.0 Noviembre 2021.
 * @since Estructuras de datos 2022-1.
 */

public class Player {

    //Atributos 
	
	
	/**Cartas del jugador  */
           Deck deck;  
         
        /** Crea la mano del jugador */
           
        private DoubleLinkedList<Card> hand;
	/**Nombre del jugador */ 
	String name;


        /**Turno del jugador */
	int turn;
 
	/**Number of player */
	int num;

        /***********************************************
        **           CONSTRUCTORES                    **
        ************************************************/
      
	/**
	* Crea una nuevo jugador apartir del mazo
	*/
	public Player(){
	      this.deck = new Deck();
	}
        
	/**
	* Crea una nuevo jugador
	* @param name nombre del jugador.
	*/
        
	public Player(String name,int num){
		this.name = name;
		this.num = num;
		this.deck = new Deck();
	}
        /**
      * Metodo que regresa la mano del jugador
      * @return mano del jugador
      */
       public DoubleLinkedList<Card> getHand(){
         return hand;
     }
        
        
        /***********************************************
        **         MÉTODOS DE ACCESO                  **
        ************************************************/
        
        /**
	* Regresa el mazo
	* @return deck.
	*/
        
	public Deck getDeck() {
		return deck;
	}
        
        /**
	* Modifica el mazo 
	* @param deck el nuevo nombre de la mascota.
	*/

	public void setDeck(Deck deck) {
		this.deck = deck;
	}
        
         /**
	* Regresa el nombre del jugador
	* @return name.
	*/

	public String getName() {
		return name;
	}
        
        /**
	* Modifica el nombre del jugador
	* @param name.
	*/

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Obtiene el turno actual en una partida de un jugador.
	 * @return el turno del jugador
	 */
	public int gerTurn(){
		return turn;
	}
	
	/**
	 * Modifica el turno de un jugador.
	 * @param turno Nuevo turno a asignar.
	 */
	public void setTurn(int turn){
		this.turn = turn;
	}
	
	/**
	 * Obtiene el numero del jugador
	 * @return el turno del jugador
	 */
	
	public int getNumPlayer(){
		return num;
	}

       /**
       * Método para imprimir la información del jugador
       * @return la cadena de información
        */
	@Override
	public String toString(){
		return "\n\nName:" +name+"" + "\n\nCards:\t\n" + "\t"+deck.toString()+"\n\n" ;
	}

      //Metodo que intercambia las posiciones de las cartas
     public void swap(int i, int j){
      if(hand.size()>1){
         if(i<j){
            Card temp = hand.remove(i);
            hand.add(i,hand.remove(j-1));
            hand.add(j,temp);
         }else{
            Card temp = hand.remove(j);
            hand.add(j,hand.remove(i-1));
            hand.add(i,temp);
         }
    
      
        }
     }
}
     


      
	

