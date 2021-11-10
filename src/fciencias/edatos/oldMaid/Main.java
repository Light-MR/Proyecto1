import reference.*;


/**
 * Clase principal del juego
 * @author Reyes Ramos Luz María
 * @author Julieta Vargas Gutiérrez 318341945
 * @version 1.0 Noviembre 2021
 * @since EDD-2022-1
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
    
    
    
    /**
    * Genera los jugadores
    */
                
    

   
    /**
    * Distribuye las cartas
    */
   /*private static void distribuyeCartas() {
		DoubleLinkedList<Card> 
                        tempCards = null; //Aquí pasa algo mal pero no sé que es):
                
		for(int i=0,k=0;i<allCards.size();i++,k++){
			if(k==4)k=0;
			Random r = new Random();
			int randint = (Math.abs(r.nextInt()) % (allCards.size()-i));
			players.get(k).getDeck().addToDeck(tempCards.get(randint));
			tempCards.remove(randint);
		}
	} */
   
    
  
    
    
    public static void main(String[] args) {
		OldMaid p = new OldMaid();
		//allCards = p.generateCards().getDecks();
		Deck d =  p.generateCards();
		//System.out.println(d);
		DoubleLinkedList<Player> players = p.generatePlayers(5);
		d = p.shuffle(d);
		//System.out.println("All cards size:"+d.getSize());
		p.distributeCards(d);
		
		
		System.out.println(players);
		
		//System.out.println("\n\tREVUELTAS?\n"+p.shuffle(d));
		System.out.println("\n\t---DESCARTANDO PARES...");
		Player p1 = players.get(players.size()-1);
		System.out.println("\n "+p1);
		Queue<Card> deleted =p1.getDeck().discardPairs(p1.getName());
     
		System.out.println("\n"+p1);
		//p.discardPairsMachine(players);
		//System.out.println(players);

		System.out.println("\n\tShow player back cards\n");
		p.pickCard(p1,0);

		


                //generaPlayer();
                //distribuyeCartas();
		
                
        
        
        
       
        
       
       
       
        
       
    }
    
}
