package Module2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int n;
    private Boolean[][] opened;
    private WeightedQuickUnionUF tuf;
    private WeightedQuickUnionUF luf;
    private int top;
    private int bot;
    private int countOpen;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n){
        if(n<=0){
            throw new IllegalArgumentException();
        }

        top = 0;
        bot = n*n + 1;
        this.n = n;
        this.opened = new Boolean[n][n];
        this.tuf = new WeightedQuickUnionUF(n*n + 1);
        this.luf = new WeightedQuickUnionUF(n*n + 2);
        this.countOpen = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.opened[i][j] = false;
            }

        }
    }

    private void isValid(int row, int col){
        if(row<1 || row>n || col<1 || col>n){
            throw new IllegalArgumentException();
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
        isValid(row, col);

        if(isOpen(row,col)){
            return;
        }

        this.opened[row -1][col - 1] = true;
        this.countOpen += 1;

        if(row == 1){
            tuf.union(getIndexTo1D(row,col),top);
            luf.union(getIndexTo1D(row,col),top);
        }
        if(row == n){
            luf.union(getIndexTo1D(row,col),bot);
        }
        if(row>1 && isOpen(row-1,col)){ // check if top site is open
            tuf.union(getIndexTo1D(row,col), getIndexTo1D(row-1,col));
            luf.union(getIndexTo1D(row,col), getIndexTo1D(row-1,col));
        }
        if(row<n && isOpen(row+1,col)){ // check if bottom site is open
            tuf.union(getIndexTo1D(row, col),getIndexTo1D(row+1,col));
            luf.union(getIndexTo1D(row, col),getIndexTo1D(row+1,col));
        }
        if(col>1 && isOpen(row,col-1)){ // check if left site is open
            tuf.union(getIndexTo1D(row,col),getIndexTo1D(row,col-1));
            luf.union(getIndexTo1D(row,col),getIndexTo1D(row,col-1));
        }
        if(col<n && isOpen(row,col+1)){ // check if right site is open
            tuf.union(getIndexTo1D(row,col),getIndexTo1D(row,col+1));
            luf.union(getIndexTo1D(row,col),getIndexTo1D(row,col+1));
        }

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        isValid(row, col);

        return this.opened[row-1][col-1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){ //site is full if connected to the top
        isValid(row,col);

        return tuf.connected(getIndexTo1D(row,col),this.top);
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        return this.countOpen;
    }

    private int getIndexTo1D(int row, int col){
        return n*(row-1)+col;
    }

    // does the system percolate?
    public boolean percolates(){
        return luf.connected(top,bot);
    }

    // test client (optional)
    public static void main(String[] args){
        Percolation p = new Percolation(3);
        p.open(1,1);
        p.open(2,1);
        p.open(3,1);
        p.open(3,1);
        p.open(3,1);


        Percolation p2 = new Percolation(5);
        p2.open(5,5);
        p2.open(4,5);
        p2.open(4,4);
        p2.open(3,4);
        p2.open(3,3);
        p2.open(2,3);
        p2.open(2,2);
        p2.open(1,2);

    }
}
