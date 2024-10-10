package gui.solver.window;

import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JPanel;

import gui.solver.exception.InvalidBoard;
import gui.solver.exception.InvalidDigit;


public class Board extends JPanel {
    private Cell[][] cells;

    public Board(int width, Color cellBackground, Color cellBackground2) {
        super(new GridLayout(9, 9));

        this.cells = new Cell[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cells[i][j] = new Cell(cellBackground);

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
}
