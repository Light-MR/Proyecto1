import reference.*;

public class Deck {
        
      
	DoubleLinkedList<Card> decks;

	public Deck(){
		this.decks = new DoubleLinkedList<>();
	}

	public void addToDeck (Card card){
		this.decks.add(0,card);
	}
   
       public void removeFromDeck (Card card){
		this.decks.remove(0);
	}
       
	public int getSize (){
		return this.decks.size();
	}

	public 	DoubleLinkedList<Card> getDecks() {
		return decks;
	}

	public void setDecks(DoubleLinkedList<Card> decks) {
		this.decks = decks;
	}

 
}
