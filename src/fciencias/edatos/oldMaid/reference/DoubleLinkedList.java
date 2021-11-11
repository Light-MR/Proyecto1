
package reference;
import java.util.Iterator;


/**
 * Practica 2: Double Linked List.
 * @author Reyes Ramos Luz María 318211073
 * @author Vargas Gutiérrez Julieta 318341945
 * @version 1.0 Octubre 2021
 * @since Estructuras de datos lab 2022-1
 */
public class DoubleLinkedList<T> implements TDAList<T> {

    /**Nodo de la lista */
    private class Node{
        /**Node element */
        private T element;
        /**Next Node */
        private Node next;
        /**Prev node */
        private Node prev;
        
        //Atributos para swap
       private int data;  
        private Node(int d){               
            data = d;
            next = null;
        }
        
        /**
         * Create Node
         * @param element Node´s element
         */
        public Node(T element){this.element = element;}
        /**
         * Modifies next node
         * @param newNode New next node.
         */
        public void setNext(Node newNode){this.next = newNode;}
        /**
         * Modifies prev Node
         * @param newPrev New prev Node
         */
        public void setPrev(Node newPrev){this.prev = newPrev;}
        /**
         * Access to node's data
         * @return element
         */
        public T getElement(){return element;}
        /**
         * Modifies/changes a Node's element
         * @param newElement New element
         */
        public void setElement(T newElement){this.element = newElement;}
        /**
         * Access to the next node
         * @return next
         */
        public Node getNext(){return next;}
        /**
         * Access to the prev node.
         * @return prev
         */
        public Node getPrev(){return prev;}
      }

 //---------------------------- swap ----------------
    
    class DoubleLinkedLista{
        Node head;
    }
    
     public void swap(int n, int m){
        if (n == m)   // No sé pueden intercambiar si son el mismo elemento
            return;
        
        Node prevN = null, currN = head; //Busca a n
        while (currN != null && currN.data != n) {
            prevN = currN;
            currN = currN.next;
        }
 
       
        Node prevM = null, currM = head; //Busca a m
        while (currM != null && currM.data != m) {
            prevM = currM;
            currM = currM.next;
        }

        if (currN == null || currM == null) //Si no está ninguno de los dos
            return;
        
        if (prevN != null) // Si n no es la cabeza de la lista se hace a m la cabeza
            prevN.next = currM;
        else 
            head = currM;
 
       // Si n no es la cabeza de la lista se hace a n la cabeza
        if (prevM != null)
            prevM.next = currN;
        else // make x the new head
            head = currN;
 
        // Swap next pointers
        Node temp = currN.next;
        currN.next = currM.next;
        currM.next = temp;
    }
    
    
    
    

  //--------------------------- Metodos auxiliares -------------------------------------------------------------
    class Iterador implements Iterator<T>{
        Node nuevoNodo;
        /**
         *  Constructor por omision
         */
        public Iterador(){
            nuevoNodo = head;
        }
        /**
         * Ve si hay mas elementos
         */
        
        @Override
        public boolean hasNext() {
            if(nuevoNodo.getNext().getElement() != null)
                return true;
            
            return false;
        }
        
        /**
        * Regresa el siguiente elemento 
        */

        @Override
        public T next() {
            return nuevoNodo.getElement();
        }
    }




    //--------------------------- L I S T -------------------------------------------------------------
    /**List's head */
    private Node head;
    /**List's tail */
    private Node tail;
    /** List's size */
    private int size;

    @Override
    public void add(int i, T e) throws IndexOutOfBoundsException{
        //Indice fuera de rango definido
        if(i>=size()+1 || i<0)
            throw new IndexOutOfBoundsException();

        Node newNode = new Node(e);
        //Si es vacia
        if(isEmpty() && i==0){
            head= tail = newNode;
            size++;
            return;
        }

        //Añadir al incio
        if(i==0){
            newNode.setNext(head);
            head.setPrev(newNode);
            head = newNode;
            size++;
            return;
        }
        //Añadir al final
        if(i == size){
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
            size++;
            return;
        }

        //Cualquier otro índice dentro del rango
        Node iterador1 = head;
        Node iterador2= tail;
        int limit = size()/2;
        for(int n =0 , m=size()-1; n<size(); n++,m--){
            //Indice cerca de head , posicionarse 1 antes
            if(n == i-1){
                newNode.setNext(iterador1.getNext());
                newNode.setPrev(iterador1);
                iterador1.getNext().setPrev(newNode);
                iterador1.setNext(newNode);
                size++;
                return;
            }
            iterador1 = iterador1.getNext();

            //Indice cerca de tail
            if(m ==i-1){
                newNode.setNext(iterador2.getNext());
                newNode.setPrev(iterador2);
                iterador2.getNext().setPrev(newNode);
                iterador2.setNext(newNode);
                size++;
                return;
            }
            iterador2 = iterador2.getPrev();

            if(n==m){
                return;
            }

        }


    }
    @Override
    public void clear(){
      head = null;
       tail = null;
       size = 0;
   }

    @Override
    public boolean contains(T e){
        //Si la lista es vacía
        if(isEmpty())
            return false;

        Node iterador1 = head;
        Node iterador2 = tail;
        int limit = (size()/2)+1;
        for(int n =0, m = size()-1; n<limit; n++,m--){
            //System.out.println("n = " + n + "m = "+ m);
            if(e.equals(iterador1.getElement())){
                //System.out.println("E1: "+ iterador1.getElement());
                return true;
            }
            iterador1 = iterador1.getNext();
            //Solo recorre la  mitad
            if(e.equals(iterador2.getElement())){
                //System.out.println("E2: "+ iterador2.getElement());
                return true;
            }
            iterador2 = iterador2.getPrev();
             //   A B C D 5
        }

        return false;
    }
    
    
   /* public Node getNode(int i){
		Node current = this.head;
		if (i > this.size|| i < 0)
		{	
			return null;
		}		
		for(int k = 0; k < i; k++)
		{
			current = current.next;
		}
		return current;
	}
    
       public void swap(int i, int j){
           Node n = getNode(i);
           Node m = getNode(j);
           T aux = n.getElement();
           n.setElement(m.getElement());
           m.setElement(aux);
       }
   */    
    
    
    
    
    
    
    @Override
    public boolean isEmpty(){
        boolean empty = (head == null) ? true : false;
        return empty;
    }
    public T remove(int i) throws IndexOutOfBoundsException{
        if(i>size()-1 || i<0){
            throw new IndexOutOfBoundsException();
        }
        //List vacia
        if(isEmpty()){
            System.out.println("\n\tLa lista está vacía");
            return  null;
        }
        //Si i es 0
        Node eliminado = null;
        if( i ==0){
            eliminado = head;
            head = head.getNext();
            head.setPrev(null); //añadí esta línea
            size--;
            return eliminado.getElement();
        }
        //  Si es el ultimo elemento
        if(i == size()-1){
            eliminado = tail;
            tail= tail.getPrev();
            tail.setNext(null);
            size--;
            return eliminado.getElement();
        }
        //Algun otro indice
        int limit = (size()/2)+1;
        Node iterador1 = head;
        Node iterador2 = tail;
        for(int n =0, m = size()-1; n<size(); n++, m--){
            if(n == i ){
                eliminado = iterador1;
                iterador1.getPrev().setNext(iterador1.getNext());
                iterador1.getNext().setPrev(iterador1.getPrev());
                System.out.println("------");
                size--;
                return  eliminado.getElement();
            }
            iterador1 = iterador1.getNext();

            if(m == i){
                eliminado = iterador2;
                iterador2.getPrev().setNext(iterador2.getNext());
                iterador2.getNext().setPrev(iterador2.getPrev());
                System.out.println("------");
                size--;
                return eliminado.getElement();

            }
            iterador2 = iterador2.getPrev();


        }
        return eliminado.getElement();
    }
    @Override
    public int size(){
        return size;
    }
    @Override
    public void revert(){
      if(isEmpty())
            System.out.println("\n\tLista sin elementos.");

        Node aux = null;
        Node iterador = head;
        while (iterador != null) {
            aux = iterador.getPrev();
            iterador.setPrev(iterador.getNext());
            iterador.setNext(aux);
            iterador = iterador.getPrev();
        }
        if(aux!= null)
            head = aux.getPrev();


        System.out.println(toString());
    }
    @Override
    public TDAList cut(boolean side){
      DoubleLinkedList doubleLinkedList = new DoubleLinkedList();

      if(side){
          Node aux = tail;
          for(int i = size/2; i < size; i++){
              doubleLinkedList.add(0, aux.getElement());
              aux = aux.getPrev();
          }
      }else{
          for(int i = 0; i < size/2; i++){
              doubleLinkedList.add(i, this.get(i));
          }
      }

      return doubleLinkedList;
  }

    @Override
    public String toString(){
        String formato = "";

        Node iterador = head;
        while(iterador != null){
            formato += iterador.getElement() + " \n";
            iterador = iterador.getNext();
        }

        return formato;
    }




    @Override
    public Iterator listIterador() {
       Iterador Iterador = new Iterador();
       return Iterador;

    }




}
