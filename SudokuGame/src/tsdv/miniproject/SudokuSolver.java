package tsdv.miniproject;

public class SudokuSolver {
	private SudokuModel sudokuModel;

	public SudokuSolver() {
		sudokuModel = new SudokuModel();
	}

	public void solve() {
		sudokuModel.init();
		sudokuModel.tryValue(0, 0);

	}
}
