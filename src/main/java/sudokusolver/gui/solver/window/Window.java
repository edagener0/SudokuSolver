package main.java.sudokusolver.gui.solver.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import main.java.sudokusolver.gui.solver.exception.InvalidBoard;


public class Window extends JFrame {
    private final int WIDTH;
    private final int HEIGHT;

    private final Dimension WINDOW_SIZE;

    private final Color CELL_BACKGROUND = Color.WHITE;
    private final Color CELL_BACKGROUND2 = Color.LIGHT_GRAY;

    private Board board;
    private Header header;
    private Footer footer;
    private Button checkButton;
    private Button resetButton;

    public Window(int width) {
        this.WIDTH = width;
        this.HEIGHT = this.WIDTH + (this.WIDTH / 10);

        this.WINDOW_SIZE = new Dimension(this.WIDTH, this.HEIGHT);

        // TODO change the window size to keep the board square

        setTitle("Sudoku Solver");
        setSize(this.WINDOW_SIZE);
        setMinimumSize(this.WINDOW_SIZE);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        this.board = new Board(width, CELL_BACKGROUND, CELL_BACKGROUND2);
        this.board.setVisible(true);

        add(this.board, BorderLayout.CENTER);

        this.footer = new Footer();
        this.header = new Header("Sudoku Solver");

        this.checkButton = new Button("Solve Sudoku");
        this.resetButton = new Button("Reset board");

        this.footer.add(this.checkButton, BorderLayout.EAST);
        this.footer.add(this.resetButton, BorderLayout.WEST);

        add(this.footer, BorderLayout.SOUTH);
        add(this.header, BorderLayout.NORTH);
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

    public void resetBoard() {
        this.board.resetBoard();
    }

    public void setCheckButtonFunction(ActionListener listener) {
        this.checkButton.addActionListener(listener);
    }

    public void setResetButtonFunction(ActionListener listener) {
        this.resetButton.addActionListener(listener);
    }
}
