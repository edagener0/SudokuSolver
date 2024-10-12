package sudokusolver.gui.solver.window;

import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

import sudokusolver.gui.solver.exception.InvalidBoard;
import sudokusolver.gui.solver.exception.InvalidDigit;


public class Board extends JPanel {
    private Cell[][] cells;

    public Board(Dimension size, Color cellBackground, Color cellBackground2, Color borderColor) {
        super(new GridLayout(9, 9));
        setPreferredSize(size);

        this.cells = new Cell[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cells[i][j] = new Cell(new Dimension((int) (size.getWidth() / 9), (int) (size.getHeight() / 9)), cellBackground, borderColor);

                if ((i/3 + j/3) % 2 == 0) {
                    cells[i][j].setColor(cellBackground2);
                }

                add(cells[i][j]);
            }
        }
    }

    public int[][] getBoard() throws InvalidBoard {
        int[][] board = new int[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                try {
                    board[i][j] = this.cells[i][j].getNumber();
                } catch (InvalidDigit e) {
                    throw new InvalidBoard();
                }
            }
        }

        return board;
    }

    public void setBoard(int[][] newBoard) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.cells[i][j].setNumber(newBoard[i][j]);
            }
        }
    }

    public void resetBoard() {
        setBoard(new int[9][9]);
    }
}
