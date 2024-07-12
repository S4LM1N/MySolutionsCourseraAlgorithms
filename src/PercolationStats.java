import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int n;
    private double[] treshold;
    private int trials;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials){
        if(n<1 || trials<1){
            throw new IllegalArgumentException();
        }

        this.n = n;
        treshold = new double[trials];
        this.trials = trials;

        for (int i = 0; i < treshold.length; i++)
        {
            treshold[i] = calcTreshold(n);
        }
    }

    private double calcTreshold(int n)
    {
        int i, j;
        Percolation p = new Percolation(n);
        double counter = 0;
        while (!p.percolates())
        {
            i = StdRandom.uniformInt(n) + 1;
            j = StdRandom.uniformInt(n) +1;
            if (!p.isOpen(i, j))
            {
                counter++;
                p.open(i, j);
            }
        }
        return counter / (n*n);
    }

    public double stddev(){
        return StdStats.stddev(treshold);
    }

    public double mean(){
        return StdStats.mean(treshold);
    }

    public double confidenceLo(){
        return mean() - (1.96*stddev())/Math.sqrt(trials);
    }

    public double confidenceHi(){
        return mean() + (1.96*stddev())/Math.sqrt(trials);
    }

    public static void main(String[] args) {
        PercolationStats stats = new PercolationStats(200, 100);
        System.out.println("mean                    = " + stats.mean());
        System.out.println("stddev                  = " + stats.stddev());
        System.out.println("95% confidence interval = " + stats.confidenceLo() +
                ", " + stats.confidenceHi());
    }

}
