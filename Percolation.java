import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] Grid;
    private int openCount; 
    private WeightedQuickUnionUF unionFind; 
    private int virtualTopNode;
    private int virtualBottomNode; 


    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("gird value must be more than 0");        
        }

        this.Grid = new int[n][n];
        this.openCount = 0;
        this.virtualTopNode = 0; 
        this.virtualBottomNode = n * n + 1; 
        this.unionFind = new WeightedQuickUnionUF(n*n + 2);
       
        for (int col = 1; col <= n; col++) {
            unionFind.union(virtualTopNode, col);
        }

        for (int col = 1; col <= n; col++) {
            int bottomRowIndex = (n-1) * n + col;
            unionFind.union(virtualBottomNode, bottomRowIndex);
        }

    }   

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if ((row-1) < 0 || (col-1) < 0) {
            throw new IllegalArgumentException("row and col must start from 1");        
        }
        if (Grid[row-1][col-1] == 1) return;
        
        if ((row-1) > this.Grid.length || (col-1) > this.Grid[0].length) {
            throw new IllegalArgumentException("row and col must be valid!");        
        }

        Grid[row-1][col-1] = 1;
        this.openCount++;
        
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        for (int[] dir : directions) {
            int dx = (row-1) + dir[0];
            int dy = (col-1) + dir[1];

            if (dx >= 0 && dx < Grid.length && dy >= 0 && dy < Grid[0].length) {
                if (Grid[dx][dy] == 1) {
                    int dydx = dx * Grid[0].length + dy;
                    int xy = row * Grid[0].length + col;                    
                    unionFind.union(dydx, xy);
                }
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if ((row-1) < 0 || (col-1) < 0) {
            throw new IllegalArgumentException("row and col must start from 1");        
        }
        if (row > this.Grid.length || col > this.Grid[0].length) {
            throw new IllegalArgumentException("row and col must be valid!");
        }
        return Grid[row-1][col-1] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if ((row-1) < 0 && (col-1) < 0) {
            throw new IllegalArgumentException("row and col must be valid!");
        }
        return isOpen(row, col) && (unionFind.find(virtualTopNode) == unionFind.find(row * Grid[0].length + col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return this.openCount;
    }

    // does the system percolate?
    public boolean percolates() {
        return (unionFind.find(virtualTopNode) == unionFind.find(virtualBottomNode));
    }

    // test client (optional)
    public static void main(String[] args) {
        int n = 5; 
        Percolation perc = new Percolation(n);
        perc.open(5,5);

        System.out.println("percolation: " + perc.isOpen(10, 10));

    }
}