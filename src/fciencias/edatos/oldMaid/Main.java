import java.util.Random;
import reference.*;


/**
 *
 * @author julie
 */
public class Main {
    
   static DoubleLinkedList<Card> allCards;
    static DoubleLinkedList<Player> players;
       
      /***********************************************
      **              MÉTODOS                       **
      ************************************************/
    
    
    /**
	*  Crea las cartas
	*/
    
    public static void crearCartas(){
		allCards = new DoubleLinkedList<>();
		String[] cardName = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Joker", "Queen", "King"};
		String[] cardSign = {"♠", "♦", "♥", "♣"};
		for(int i=0;i<13;i++){
			if(!cardName[i].equals("Queen")){
		        allCards.add(0,new Card(cardName[i], cardSign[0], 1));
			}
			allCards.add(0,new Card(cardName[i], cardSign[1], 2));
			allCards.add(0,new Card(cardName[i], cardSign[2], 2));
			allCards.add(0,new Card(cardName[i], cardSign[3], 1));
		}
    }
    
    /**
    * Genera los jugadores
    */
                
    
   public static void generaPlayer(){
		players = new DoubleLinkedList<>();
		players.add(0,new Player("Player 1"));
		players.add(0,new Player("Player 2"));
		players.add(0,new Player("Player 3"));
		players.add(0,new Player("Player 4"));
                players.add(0,new Player("Player 5"));
		players.add(0,new Player("Player 6"));
		players.add(0,new Player("Player 7"));
		players.add(0,new Player("Player 8"));
	} 


   
    /**
    * Distribuye las cartas
    */
   private static void distribuyeCartas() {
		DoubleLinkedList<Card> 
                        tempCards = null; //Aquí pasa algo mal pero no sé que es):
                
		for(int i=0,k=0;i<allCards.size();i++,k++){
			if(k==4)k=0;
			Random r = new Random();
			int randint = (Math.abs(r.nextInt()) % (allCards.size()-i));
			players.get(k).getDeck().addToDeck(tempCards.get(randint));
			tempCards.remove(randint);
		}
	}
   
    
  
    
    
    public static void main(String[] args) {
                crearCartas();
                generaPlayer();
                distribuyeCartas();
		
                
        
        
        
       
        
       
       
       
        
       
    }
    
}
