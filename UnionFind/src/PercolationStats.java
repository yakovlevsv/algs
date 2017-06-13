import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * Created by Svyatoslav_Yakovlev on 6/8/2017.
 */
public class PercolationStats {

	private final int n;
	private final int trials;
	private int currentExam;
	private double[] results;

	public PercolationStats(int n, int trials) {
		this.n = n;
		this.trials = trials;
		this.results = new double[trials];

		for (int i = 0; i < trials; i++) {
			doPercolationExperiment();
		}
	}

	private void doPercolationExperiment() {
		Percolation percolation = new Percolation(n);
		do {
			int row = StdRandom.uniform(n) + 1;
			int col = StdRandom.uniform(n) + 1;
			if (!percolation.isOpen(row, col)) {
				percolation.open(row, col);
			}
		} while (!percolation.percolates());
		results[currentExam++] = 1.0 * percolation.numberOfOpenSites() / (n * n);
	}

	public double mean() {
		return StdStats.mean(results);
	}

	public double stddev() {
		double mean = mean();
		double sum = 0;

		for (double result : results) {
			sum += (result - mean) * (result - mean);
		}
		return Math.sqrt(sum / (trials - 1));
	}

	public double confidenceLo() {
		double stddev = stddev();
		return mean() - (1.96 * stddev) / Math.sqrt(trials);
	}

	public double confidenceHi() {
		double stddev = stddev();
		return mean() + (1.96 * stddev) / Math.sqrt(trials);
	}

	public static void main(String[] args) {
		int n = 200;
		int trails = 100;

		PercolationStats percolationStats = new PercolationStats(n, trails);

		double mean = percolationStats.mean();

		StdOut.printf("mean                    = %.16f\n", mean);
		StdOut.printf("stddev                  = %.16f\n", percolationStats.stddev());
		double v = percolationStats.confidenceLo();
		double v1 = percolationStats.confidenceHi();
		System.out.println("95% confidence interval = " + v + ", " + v1 + "]");

	}
}
