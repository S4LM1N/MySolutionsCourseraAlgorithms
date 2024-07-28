package Module4;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int N;
    private Item[] queue;

    // construct an empty randomized queue
    public RandomizedQueue(){
        this.N = 0;
        queue = (Item[]) new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty(){
        return N==0;
    }

    // return the number of items on the randomized queue
    public int size(){
        return N;
    }

    private void resize(int n){
        Item[] copy = (Item[]) new Object[n];
        for (int i = 0; i < N; i++) {
            copy[i] = queue[i];
        }
        queue = copy;
    }

    // add the item
    public void enqueue(Item item){
        if (item==null) throw new IllegalArgumentException();

        if(N == queue.length){
            resize(2* queue.length);
        }
        queue[N] = item;
        N++;
    }

    // remove and return a random item
    public Item dequeue(){
        if(isEmpty()) throw new NoSuchElementException();

        int rand = StdRandom.uniformInt(0,N); //we chose a random number
        Item pop = queue[rand];

        if((N-1) == rand){ // if rand and the last item in the queue is the same
            queue[rand] = null;
        } else { //its not the last then we have to swap the random item on the list with the last so we can still call it a queue
            queue[rand] = queue[N-1];
            queue[N-1] = null;
        }

        if(N>0 && N == queue.length/4){
            resize(queue.length/2);
        }

        N--;
        return pop;
    }

    // return a random item (but do not remove it)
    public Item sample(){
        if(isEmpty()) throw new NoSuchElementException();

        int rand = StdRandom.uniformInt(0,N);

        return queue[rand];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator(){
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item>{
        int counter = N;
        public boolean hasNext(){
            return counter!=0;
        }

        public Item next() {
            if(!hasNext()) throw new NoSuchElementException();

            int rand = StdRandom.uniformInt(0,counter);
            Item item = queue[rand];

            if(rand == counter-1){
                counter--;
            } else {
                queue[rand] = queue[counter-1];
                queue[counter-1] = item;
                counter--;
            }

            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args){

    }
}
