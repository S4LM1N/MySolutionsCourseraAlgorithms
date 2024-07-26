import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item>{
    private Node first,last;
    private int n;


    private class Node{
        Item item;
        Node next;
        Node previous;
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
            if(!hasNext()){
                throw new NoSuchElementException();
            }

            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    // construct an empty deque
    public Deque(){
        n =0;
        first = null;
        last = null;
    }

    // is the deque empty?
    public boolean isEmpty(){
        return n == 0;
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

        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        first.previous = null;
        if(isEmpty()){
            last = first;
        } else {
            oldFirst.previous = first;
        }
        n++;
    }

    // add the item to the back
    public void addLast(Item item){
        if(item == null){
            throw new IllegalArgumentException();
        }

        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.previous = oldLast;
        last.next = null;

        if(isEmpty()){
            first = last;
        } else {
            oldLast.next = last;
        }
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }

        Item item = first.item;
        first = first.next;

        if(isEmpty()){
            last = null;
        } else {
            first.previous = null;
        }

        n--;

        return item;

    }

    // remove and return the item from the back
    public Item removeLast(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }

        Item item = last.item;
        last = last.previous;

        if(isEmpty()){
            first = null;
        } else {
            last.next = null;
        }

        n--;

        return item;

    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){
        return new ListIterator();
    }



    public static void main(String[] args){
        Deque<Integer> d = new Deque<>();

        for (int i = 0; i < 10; i++) {
            d.addFirst(i);
        }

        for (int i = 10; i < 20; i++) {
            d.addLast(i);
        }

        System.out.println("N:" +d.size()); //20 correct!

        for (int i = 0; i < 5; i++) {
            System.out.println(d.removeFirst());
        }

        System.out.println("N:" + d.size()); //15

        for (int i = 0; i < 5; i++) {
            System.out.println(d.removeLast());
        }

        System.out.println("N:" +d.size()); //10


    }
}
