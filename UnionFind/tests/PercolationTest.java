import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import edu.princeton.cs.algs4.In;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.junit.jupiter.api.extension.TestExtensionContext;
import org.junit.rules.ExpectedException;

public class PercolationTest {

	public static final String INPUT1 = "input1.txt";
	public static final String INPUT1_NO = "input1-no.txt";
	public static final String INPUT50 = "input50.txt";
	public static final String INPUT5 = "input5.txt";

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void zeroSitesTest() {
		In in = new In(getFilePath(INPUT1));
		int n = in.readInt();
		Percolation perc = new Percolation(n);
		assertFalse(perc.percolates());
	}

	@Test
	public void zeroNoSitesTest() {
		In in = new In(getFilePath(INPUT1_NO));
		int n = in.readInt();
		Percolation perc = new Percolation(n);
		assertFalse(perc.percolates());
	}

	@Test
	public void numberOfOpenSites50Test() {
		In in = new In(getFilePath(INPUT5));
		int n = in.readInt();
		Percolation perc = new Percolation(n);
		for (int i = 0; i < 3; i++) {
			openSiteByDataFromInput(in, perc);
		}
		Assert.assertThat(perc.numberOfOpenSites(), is(3));
	}

	@Ignore
	@Test
	public void checkThrowingException() {
		exception.expect(ArrayIndexOutOfBoundsException.class);

		In in = new In(getFilePath(INPUT5));
		int n = in.readInt();
		Percolation perc = new Percolation(n);
		for (int i = 0; i < 4; i++) {
			openSiteByDataFromInput(in, perc);
		}
		perc.open(6, 2);
	}

	private void openSiteByDataFromInput(In in, Percolation perc) {
		int i = in.readInt();
		int j = in.readInt();
		perc.open(i, j);
	}

	private String getFilePath(String fileName) {
		return "C:\\Users\\Svyatoslav_Yakovlev\\Downloads\\percolation-testing\\percolation\\" + fileName;
	}


}
