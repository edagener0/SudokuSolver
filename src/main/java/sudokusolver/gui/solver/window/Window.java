package main.java.sudokusolver.gui.solver.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import main.java.sudokusolver.gui.solver.exception.InvalidBoard;


public class Window extends JFrame {
    private final int WIDTH;
    private final int HEIGHT;

    private final Color CELL_BACKGROUND = Color.WHITE;
    private final Color CELL_BACKGROUND2 = Color.LIGHT_GRAY;

    private Board board;
    private Button checkButton;

    public Window(int width) {
        this.WIDTH = width;
        this.HEIGHT = this.WIDTH + (this.WIDTH / 10);

        setTitle("Sudoku Solver");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        this.board = new Board(width, CELL_BACKGROUND, CELL_BACKGROUND2);
        this.board.setVisible(true);

        add(this.board, BorderLayout.CENTER);

        this.checkButton = new Button("check 🍎");

        add(this.checkButton, BorderLayout.SOUTH);
    }

    public void setBoard(int[][] newBoard) {
        this.board.setBoard(newBoard);
    }

    public int[][] getBoard() throws InvalidBoard{
        try {
            return this.board.getBoard();
        } catch (InvalidBoard e) {
            throw e;
        }
    }

    public void setCheckButtonFunction(ActionListener listener) {
        this.checkButton.addActionListener(listener);
    }
}
