import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] grid;
    private int openCount; 
    private WeightedQuickUnionUF unionFindPercolates; 
    private WeightedQuickUnionUF unionFindIsFull;
    private int virtualTopNode;
    private int virtualBottomNode; 


    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("gird value must be more than 0");        
        }

        grid = new int[n][n];
        this.openCount = 0;
        this.virtualTopNode = 0; 
        this.virtualBottomNode = n * n + 1; 
        this.unionFindPercolates = new WeightedQuickUnionUF(n*n+2);
        this.unionFindIsFull = new WeightedQuickUnionUF(n * n + 1);

    }   

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {

        if (row < 1 || row > grid.length || col < 1 || col > grid[0].length) {
            throw new IllegalArgumentException("row or col index out of bounds");
        }

        if (grid[row-1][col-1] == 1) return;

        grid[row-1][col-1] = 1;
        openCount++;

        if (isOpen(row, col) && (row == 1)) {
            unionFindPercolates.union(col, virtualTopNode);
            unionFindIsFull.union(col, virtualTopNode);
        }

        if (isOpen(row, col) && row == grid.length) {
            unionFindPercolates.union((row-1) * grid.length + col, virtualBottomNode);
        }

        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        for (int[] dir : directions) {
            int dx = (row-1) + dir[0];
            int dy = (col-1) + dir[1];

            if (dx >= 0 && dx < grid.length && dy >= 0 && dy < grid.length) {
                if (grid[dx][dy] == 1) {
                    int dydx = dx * grid[0].length + (dy + 1);
                    int xy = (row-1) * grid[0].length + col;                    
                    unionFindPercolates.union(dydx, xy);
                    unionFindIsFull.union(dydx, xy);
                }
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 1 || row > grid.length || col < 1 || col > grid[0].length) {
            throw new IllegalArgumentException("row or col index out of bounds");
        }
        return grid[row-1][col-1] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 1 || row > grid.length || col < 1 || col > grid[0].length) {
            throw new IllegalArgumentException("row or col index out of bounds");
        }
        return isOpen(row, col) && (unionFindIsFull.find(virtualTopNode) == unionFindIsFull.find((row-1) * grid[0].length + col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return this.openCount;
    }

    // does the system percolate?
    public boolean percolates() {
        return (unionFindPercolates.find(virtualTopNode) == unionFindPercolates.find(virtualBottomNode));
    }

    // test client (optional)
    public static void main(String[] args) {
        int n = 3; 
        Percolation perc = new Percolation(n);
        perc.open(1, 1);
        perc.open(1, 2);
        perc.open(2, 2);
        perc.open(3, 2);

        // System.out.println(Arrays.deepToString(grid));
        System.out.println("open sites: " + perc.numberOfOpenSites());
        System.out.println("IsFull: " + perc.isFull(2, 2));
        System.out.println("percolation: " + perc.percolates());

    }
}