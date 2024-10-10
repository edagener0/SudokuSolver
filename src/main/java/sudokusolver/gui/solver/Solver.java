package main.java.sudokusolver.gui.solver;

import main.java.sudokusolver.aed.search.SudokuState;
import main.java.sudokusolver.gui.solver.exception.InvalidBoard;
import main.java.sudokusolver.gui.solver.window.MsgBox;
import main.java.sudokusolver.gui.solver.window.Window;

public class Solver {
    private int[][] sudokuBoard;
    private Window window;
    private SudokuState sudokuState;

    public Solver() {
        this.sudokuBoard = new int[9][9];
    }

    private int[][] getBoardFromWindow(Window window) throws InvalidBoard {
        try {
            return window.getBoard();
        } catch (InvalidBoard e) {
            throw e;
        }
    }

    /*
     * private void printActualBoard() {
     * for (int i = 0; i < 9; i++) {
     * for (int j = 0; j < 9; j++) {
     * System.out.printf("%d ", this.sudokuBoard[i][j]);
     * }
     * System.out.printf("\n");
     * }
     * }
     */

    public void solve() {
        try {
            this.sudokuBoard = this.getBoardFromWindow(this.window);
            this.sudokuState = new SudokuState(this.sudokuBoard);

            long startTime, endTime;

            startTime = System.nanoTime();

            this.sudokuState = SudokuState.backtrackingSearch(this.sudokuState);

            if (this.sudokuState == null) {
                endTime = System.nanoTime();
                MsgBox.error("No solution found. 🐳");
                System.err.println("No solution found. Elapsed time: " + (endTime - startTime) / 1000000000);
                return;
            }

            endTime = System.nanoTime();

            System.out.println("Solution found! Elapsed time: " + (endTime - startTime) / 1000000000 +
                    "\nSolution: \n" + this.sudokuState.toString());

            this.window.setBoard(this.sudokuState.getBoard());
        } catch (InvalidBoard e) {
            e.printStackTrace();
            MsgBox.error("Invalid digits in the board. 🍎");
        }
    }

    public void reset() {
        this.window.resetBoard();
    }

    public void run(String[] args) {
        this.window = new Window(500);

        window.setCheckButtonFunction(e -> {
            this.solve();
        });

        window.setResetButtonFunction(e -> {
            this.reset();
        });

        window.setVisible(true);
    }
}