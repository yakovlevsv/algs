import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

	private int number;
	private boolean[] openness;
	private final WeightedQuickUnionUF uf;
	private final int lastIndex;
	private int open;

	public Percolation(int n) {
		this.number = n;
		uf = new WeightedQuickUnionUF(n * n + 2);
		openness = new boolean[n * n + 2];
		lastIndex = openness.length - 1;
		initUnionOfTopLevelWithFakeSite(n);
	}

	public void open(int row, int col) {
		checkCorectness(row, col);
		openItself(row, col);
		openNeighbor(row, col, row - 1, col);
		openNeighbor(row, col, row + 1, col);
		openNeighbor(row, col, row, col + 1);
		openNeighbor(row, col, row, col - 1);
		open++;
	}

	private void checkCorectness(int row, int col) {
		int i = row * col;
		if (i < 1 || i > lastIndex) {
			throw new IndexOutOfBoundsException("Index Out Of Bounds");
		}
	}

	public boolean isOpen(int row, int col) {
		checkCorectness(row, col);
		return openness[getIndex(row, col)];
	}

	public boolean isFull(int row, int col) {
		checkCorectness(row, col);
		int index = getIndex(row, col);
		return openness[index] && (uf.connected(0, index));
	}

	public int numberOfOpenSites() {
		return open;
	}

	public boolean percolates() {
		for (int i = lastIndex - 1; i > lastIndex - number - 1; i--) {
			if (open > 0 && uf.connected(0, i)) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
	}

	private void initUnionOfTopLevelWithFakeSite(int n) {
		for (int i = 1; i <= n; i++) {
			uf.union(i, 0);
		}
	}

	private void openItself(int row, int col) {
		int index = getIndex(row, col);
		openness[index] = true;
	}

	private void openNeighbor(int qRow, int qCol, int pRow, int pCol) {
		if (isPossessedToPercolation(pRow, pCol) && isOpen(pRow, pCol)) {
			uf.union(getIndex(pRow, pCol), getIndex(qRow, qCol));
		}
	}

	private boolean isPossessedToPercolation(int pRow, int pCol) {
		return pRow >= 1 && pCol >= 1 && pRow <= number && pCol <= number;
	}

	private int getIndex(int row, int col) {
		return (row - 1) * number + col;
	}
}