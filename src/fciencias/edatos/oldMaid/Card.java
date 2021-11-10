import java.security.DrbgParameters.Reseed;

import reference.*;  
/**
 * Clase que implementa las cartas
 * @author Julieta Vargas Gutiérrez 318341945
 * @author Reyes Ramos Luz María 318211073
 * @version 1.0 Noviembre 2021.
 * @since Estructuras de datos 2022-1.
 */


  public class Card {
      
    //Atributos

	private String nombreCarta;

	/** Card symbol : "♠", "♦", "♥", "♣" */
	private String suit;

	/**Card values: 1= As, 2, 3,...,13 */
	private int value;

	
	/**Representación de la carta */
	private String cardDraw;
       	



	 //Card color
	 private final static String red="\033[31m"; 
	 private final static String blue="\033[34m"; 
	 private final static String reset="\u001B[0m";
 
	 private final static String green="\033[32m"; 
	 private final static String yellow="\033[33m"; 
	 private final static String purple="\033[35m"; 
	 private final static String cyan="\033[36m"; 
	 private final static String white = "\u001B[37m";

	
	/**Representación de la cara trasera de una carta */
	private final  String  backCard = white+"🂠"+reset;


        /***********************************************
        **           CONSTRUCTORES                    **
        ************************************************/
    public Card(){
    }

    /**
	* Crea una nueva varta
	* @param nombreCarta el nombre de la carta.
	* @param suit el signo de la carta.
	* @param par si son pares.
	*/
	
	public Card(String nombreCarta, String suit){
		this.nombreCarta = nombreCarta;
		this.suit = suit;
	}

         /***********************************************
        **         MÉTODOS DE ACCESO                  **
        ************************************************/

       /**
	* Regresa el nombre de la carta
	* @return el nombre de la carta
	*/
        
	public String getNombreCarta() {
		return nombreCarta;
	}
        
        /**
	* Modifca el nombre de la carta
	* @param nombreCarta el nuevo nombre de la carta
	*/
            
	public void setNombreCarta(String nombreCarta) {
		this.nombreCarta = nombreCarta;
	}
        
        /**
	* Regresa el signo de la carta
	* @return el signo de la carta
	*/
        
	public String getSuit() {
		return suit;
	}
        
     /**
	* Modifca el signo de la carta
	* @param signoCarta el nuevo nombre de la carta
	*/
        
	public void setSuit(String signoCarta) {
		suit = signoCarta;
	}
        
    /**
	* Obtiene el valor de la carta: "1 = As. 2, , 3..."
	* @return  valor de la carta
	*/
        
	public int getValue() {
		return value;
	}
        
        /**
	* Modifca el valor de la carta
	* @param value el nuevo valor de la carta
	*/

	public void setValue(int value) {
		this.value = value;
	}


	/** Obtiene la epresentacion de la cara de una carta
	 * @return cara de la carta.
	 */
	public String getDrawCard(){
		return cardDraw;
	}

	/** Modifica la representación de la cara de una carta
	 * @param draw cara de la carta.
	 */
	public void setDraw(String draw){
		this.cardDraw = draw;
	}
	
	/**Obtiene la cara trasera de la carta 
	 * @return cara trasera de la carta
	*/
	public String getBack(){
		return backCard;
	}

	

	@Override 
	public String toString(){
		return  "\n"+cardDraw + "\t["+getNombreCarta() + "  "+ getSuit() + "]"+"";
	}

	/**
	 * Asigna valor a una carta dada:
	 * Card values: 1= As, 2, 3,...,10, 11=Jack, 12 =Queen, 13 = King.
	 * @param card La carta a asignar valor.
	 */
	public void auxAsinngValue(Card card){
		String name = card.getNombreCarta();
		switch (name) {
			case "Ace":
				card.setValue(1);
				card.setDraw(red+card.getDrawCard()+ reset);
				break;
			case "Two":
			    card.setValue(2);
				card.setDraw(blue+card.getDrawCard()+ reset);
				break;
			case "Three":
			    card.setValue(3);
				card.setDraw(green+card.getDrawCard()+ reset);
				break;
			case "Four":
			    card.setValue(4);
				card.setDraw(purple+card.getDrawCard()+ reset);
				break;
			case "Five":
			    card.setValue(5);
				card.setDraw(cyan+card.getDrawCard()+ reset);
				break;
			case "Six":
			    card.setValue(6);
				card.setDraw(yellow+card.getDrawCard()+ reset);
				break;
			case "Seven":
			    card.setValue(7);
				card.setDraw(blue+card.getDrawCard()+ reset);
				break;
			case "Eight":
			    card.setValue(8);
				card.setDraw(red+card.getDrawCard()+ reset);
				break;
			case "Nine":
			    card.setValue(9);
				card.setDraw(purple+card.getDrawCard()+ reset);
				break;
			case "Ten":
			    card.setValue(10);
				card.setDraw(green+card.getDrawCard()+ reset);
				break;
			case "Jack":
			    card.setValue(11);
				card.setDraw(yellow+card.getDrawCard()+ reset);
				break;
			case "Queen":
			    card.setValue(12);
				card.setDraw(cyan+card.getDrawCard()+ reset);
				break;
			case "King":
			    card.setValue(13);
				card.setDraw(yellow+card.getDrawCard()+ reset);
				break;
			default:
                System.out.println("Algo salió mal (;._,)");
				break;
		}
	}

	



}
