public class PercolationStats {

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        
    }

    // sample mean of percolation threshold
    public double mean() {
        return 0.0;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return 0.0;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return 0.0;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return 0.0;
    }

   // test client (see below)
   public static void main(String[] args) {

        int n = Integer.parseInt(args[0]);
        int trails = Integer.parseInt(args[1]);
        
        PercolationStats stats = new PercolationStats(n, trails);

        System.out.println("mean       = " + stats.mean());
        System.out.println("stdev      = " + stats.stddev());
        System.out.println("95% confidence interval = [" 
                    + stats.confidenceLo() + ", " 
                    + stats.confidenceHi() + "]");
   }

}