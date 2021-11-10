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
	
	/*
     * Guarda los turnos jugadores en un queue.
     * Turnos de 1 a n jugadores
     */
    private Queue<Integer> asignTurn(int num){
        Queue<Integer> turnos = new Queue<>();
        for(int n= 0; n<num;n++)
            turnos.enqueue(n+1);
        
		return turnos;

    }

	@Override
	public String toString(){
		return "\n\nName:" +name+"" + "\n\nCards:\t\n" + "\t"+deck.toString()+"\n\n" ;
	}


	
}
