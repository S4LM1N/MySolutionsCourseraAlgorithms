import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item>{
    private Node first,last;
    private int n;


    private class Node{
        Item item;
        Node next;
    }

    private class ListIterator implements Iterator<Item>{
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
        public Item next()
        {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    // construct an empty deque
    public Deque(){

    }

    // is the deque empty?
    public boolean isEmpty(){
        return first == null;
    }

    // return the number of items on the deque
    public int size(){
        return this.n;
    }

    // add the item to the front
    public void addFirst(Item item){
        if(item == null){
            throw new IllegalArgumentException();
        }
    }

    // add the item to the back
    public void addLast(Item item){
        if(item == null){
            throw new IllegalArgumentException();
        }
    }

    // remove and return the item from the front
    public Item removeFirst(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
    }

    // remove and return the item from the back
    public Item removeLast(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){
        return new ListIterator();
    }



    // unit testing (required)
    public static void main(String[] args){

    }
}
