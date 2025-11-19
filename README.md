# Percolation

Princeton Algorithm Programming Assignment 1 Write a program to estimate the value of the percolationthreshold via Monte Carlo simulation.

Percolation. Given a composite systems comprised ofrandomly distributed insulating and metallic materials: whatfraction of the materials need to be metallic so that the compositesystem is an electrical conductor? Given a porous landscape withwater on the surface (or oil below), under what conditions will thewater be able to drain through to the bottom (or the oil to gushthrough to the surface)? Scientists have defined an abstractprocess known aspercolation to model such situations.

The model. We model a percolation system using anN-by-N grid ofsites. Each site iseitheropen or blocked. A full site isan open site that can be connected to an open site in the top rowvia a chain of neighboring (left, right, up, down) open sites. Wesay the systempercolates if there is a full site in thebottom row. In other words, a system percolates if we fill all opensites connected to the top row and that process fills some opensite on the bottom row. (For the insulating/metallic materialsexample, the open sites correspond to metallic materials, so that asystem that percolates has a metallic path from top to bottom, withfull sites conducting. For the porous substance example, the opensites correspond to empty space through which water might flow, sothat a system that percolates lets water fill open sites, flowingfrom top to bottom.)

<img width="868" height="632" alt="image" src="https://github.com/user-attachments/assets/c4aa414a-326e-4fce-9e75-e42dc7095bde" />
<img width="870" height="682" alt="image" src="https://github.com/user-attachments/assets/5734d6d2-170b-4df9-b259-6dad8dd541c2" />
The problem. In a famous scientific problem, researchers are interested in the following question: if sites are independently set to be open with probability p (and therefore blocked with probability 1 − p), what is the probability that the system percolates? When p equals 0, the system does not percolate; when p equals 1, the system percolates. The plots below show the site vacancy probability p versus the percolation probability for 20-by-20 random grid (left) and 100-by-100 random grid (right).
<img width="892" height="660" alt="image" src="https://github.com/user-attachments/assets/83d734d6-5fb7-4c4b-9980-2410c2e278c0" />
<img width="974" height="660" alt="image" src="https://github.com/user-attachments/assets/c593fb10-baea-417b-97cf-95dfae6f0435" />
When n is sufficiently large, there is a threshold value p* such that when p < p* a random n-by-n grid almost never percolates, and when p > p*, a random n-by-n grid almost always percolates. No mathematical solution for determining the percolation threshold p* has yet been derived. Your task is to write a computer program to estimate p*.

Percolation data type. To model a percolation system, create a data type Percolation with the following API:
`public class Percolation {
   public Percolation(int n)                // create n-by-n grid, with all sites blocked
   public    void open(int row, int col)    // open site (row, col) if it is not open already
   public boolean isOpen(int row, int col)  // is site (row, col) open?
   public boolean isFull(int row, int col)  // is site (row, col) full?
   public     int numberOfOpenSites()       // number of open sites
   public boolean percolates()              // does the system percolate?

   public static void main(String[] args)   // test client (optional)
}`




