import java.util.Arrays;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    static int[][] Grid;
    private int openCount; 
    private WeightedQuickUnionUF unionFind; 
    private int virtualTopNode;
    private int virtualBottomNode; 


    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("gird value must be more than 0");        
        }

        Grid = new int[n][n];
        this.openCount = 0;
        this.virtualTopNode = 0; 
        this.virtualBottomNode = n * n + 1; 
        this.unionFind = new WeightedQuickUnionUF(n*n+2);

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

        if (isOpen(row, col) && (row == 1)) {
            unionFind.union(col, virtualTopNode);
        }

        if (isOpen(row, col) && row == Grid.length) {
            unionFind.union((row-1) * Grid.length + col, virtualBottomNode);
        }

        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        for (int[] dir : directions) {
            int dx = (row-1) + dir[0];
            int dy = (col-1) + dir[1];

            if (dx >= 0 && dx < Grid.length && dy >= 0 && dy < Grid.length) {
                if (Grid[dx][dy] == 1) {
                    int dydx = dx * Grid[0].length + (dy + 1);
                    int xy = (row-1) * Grid[0].length + col;                    
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
        return isOpen(row, col) && (unionFind.find(virtualTopNode) == unionFind.find((row-1) * Grid[0].length + col));
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
        int n = 3; 
        Percolation perc = new Percolation(n);
        perc.open(1,1);
        perc.open(1,2);
        perc.open(2,2);
        perc.open(3,2);

        System.out.println(Arrays.deepToString(Grid));
        System.out.println("open sites: " + perc.numberOfOpenSites());
        System.out.println("IsFull: " + perc.isFull(2, 2));
        System.out.println("percolation: " + perc.percolates());

    }
}