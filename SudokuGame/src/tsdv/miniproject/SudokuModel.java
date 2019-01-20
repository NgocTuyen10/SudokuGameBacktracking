package tsdv.miniproject;

public class SudokuModel {
	private int[][] x;

	public void init() {
		x = new int[][] { { 3, 0, 6, 5, 0, 8, 4, 0, 0 }, { 5, 2, 0, 0, 0, 0, 0, 0, 0 }, { 0, 8, 7, 0, 0, 0, 0, 3, 1 },
				{ 0, 0, 3, 0, 1, 0, 0, 8, 0 }, { 9, 0, 0, 8, 6, 3, 0, 0, 5 }, { 0, 5, 0, 0, 9, 0, 6, 0, 0 },
				{ 1, 3, 0, 0, 0, 0, 2, 5, 0 }, { 0, 0, 0, 0, 0, 0, 0, 7, 4 }, { 0, 0, 5, 2, 0, 6, 3, 0, 0 } };
	}

	private boolean isRowClash(int v, int r) {
		for (int i = 0; i < 9; i++) {
			if (x[r][i] == v)
				return true;
		}
		return false;
	}

	private boolean isColumnClash(int v, int c) {
		for (int i = 0; i < 9; i++) {
			if (x[i][c] == v)
				return true;
		}
		return false;
	}

	private boolean isBoxClash(int v, int r, int c) {
		int boxRowStart = r - r % 3;
		int boxColStart = c - c % 3;

		for (int row = boxRowStart; row < boxRowStart + 3; row++) {
			for (int col = boxColStart; col < boxColStart + 3; col++) {
				if (x[row][col] == v) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean check(int v, int r, int c) {

		if (isRowClash(v, r))
			return false;

		if (isColumnClash(v, c))
			return false;

		if (isBoxClash(v, r, c))
			return false;

		// return !(isColumnClash(v, c) || isRowClash(v, r) || isBoxClash(v, r, c));
		return true;
	}

	public void tryValue(int r, int c) {

		if (c == 9) {
			r += 1;
			c %= 9;
		}
		if (x[r][c] == 0) {
			for (int v = 1; v <= 9; v++) {
				if (check(v, r, c)) {
					x[r][c] = v;
					if (r == 8 & c == 8) {
						printSolution();
					} else {
						tryValue(r, c + 1);
					}
					x[r][c] = 0;
				}
			}
		} else {
			tryValue(r, c + 1);
		}
	}

	public void printSolution() {
		for (int r = 0; r < 9; r++) {
			for (int d = 0; d < 9; d++) {
				System.out.print(x[r][d]);
				System.out.print(" ");
			}
			System.out.print("\n");

			if ((r + 1) % (int) Math.sqrt(9) == 0) {
				System.out.print("");
			}
		}
	}
}
