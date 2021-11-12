import java.util.Scanner;
import reference.*;


/**
 * Clase principal del juego
 * @author Reyes Ramos Luz María
 * @author Julieta Vargas Gutiérrez 318341945
 * @version 1.0 Noviembre 2021
 * @since EDD-2022-1
 */
public class Main {
    private final static String red="\033[31m"; 
	private final static String blue="\033[34m"; 
	private final static String reset="\u001B[0m";
	private final static String green="\033[32m"; 
	private final static String yellow="\033[33m"; 
	private final static String purple="\033[35m"; 
	private final static String cyan="\033[36m"; 
	private final static String white = "\u001B[37m";
       
        private void sleep(int time){
        try {
			System.out.print("\n\t......\n\n");
			Thread.sleep(time);
		} catch (InterruptedException e) {
			System.out.println("\nSomething failed D:");
		}
    }		
    
     
       
    public static void main(String[] args) {
          
         System.out.println("\t"+yellow + "\t★ ★ 「" + white + " W E L C O M E  - TO - O L D - M A I D - G A M E "+ yellow+ "」★ ★ " + reset);
          
         Scanner scanner = new Scanner(System.in);
         Scanner scanner1 = new Scanner(System.in);
           

         do{
           System.out.println(blue+ "[1] How to play \n"+
              blue+ "[2] Start the game \n"+
              blue+ "[3] Close \n"  
              //"[5]Jugar de nuevo ");
                  );

            int opcion = scanner.nextInt(); //ME FALTA SI ES STRING

            switch(opcion){
                case 1:
                    System.out.println("\t" + green + "\t"+ "🃏      DESCRIPTION       🃏 " );
                     System.out.println("\t" + yellow + " The goal is to form and discard pairs of cards, \n"+
                           "\t" + yellow + "and not to be left with the odd card (a joker) at the end. \n"+
                           "\t" + yellow + "In this game you don't look for a winner,\n" +
                           "\t" + yellow + "you just look to avoid to avoid being the loser\n");
                     
                    System.out.println("\t" + green + "\t"+ " INSTRUCTIONS ");   
                     System.out.println("\t" + yellow + "  The deck of cards is shuffled and one card is removed\n"+
                           "\t" + yellow + "The deck will be distributed among the players and the pairs will be withdrawn \n"+
                           "\t" + yellow + "In the following turns you will steal a card from the player before you\n" +
                           "\t" + yellow + "and if you have pairs they will be removed if you stay whith the joyer" );                    
                        System.out.println("\t" + "\t" +  "\t" + red + "YOU LOSE" );
                    
                    break;
                    
                case 2:
                    int players = 0;
		boolean repetir = true;
                       DoubleLinkedList<Player> player = new DoubleLinkedList<>();
               System.out.println("\t" + purple + "What´s your name?");
               
                    String name = scanner1.nextLine();
               
                while(repetir){
			System.out.println("\t" + purple + "Enter the number of players");
			   try{
				players = scanner.nextInt();
				if(players>=2 && players<10){
                                     repetir = false;
				}else{
					System.out.println( red + "Enter a number from 2 to 10");
				}
			}catch(Exception e){
				System.out.println( red + "You must enter a number");
				scanner.next();
			}

		}
		 repetir = true;
		OldMaidGame g = new OldMaidGame();
		
		g.game(players, name);
		
                 break;       
                case 3:
                    return;
                    
                default:
                    System.out.println("Invalid option");
            }
         
        
          } while(true);        
                 
         
    }
      
}
