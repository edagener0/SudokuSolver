package sudokusolver.gui.solver.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import sudokusolver.gui.solver.exception.InvalidBoard;


public class Window extends JFrame {
    private final int WIDTH;
    private final int HEIGHT;

    private final Dimension WINDOW_SIZE;

    private final Color CELL_BACKGROUND = Color.WHITE;
    private final Color CELL_BACKGROUND2 = Color.LIGHT_GRAY;
    private final Color CELL_BORDER = Color.GRAY;

    private Board board;
    private Header header;
    private Footer footer;
    private Dimension boardDimension, headerDimension, footerDimension;

    private Button checkButton;
    private Button resetButton;
    private Dimension checkButtonDimension, resetButtonDimension;

    public Window(int width) {
        this.WIDTH = width;
        
        this.boardDimension = new Dimension(this.WIDTH, this.WIDTH);
        this.headerDimension = new Dimension(this.WIDTH, this.WIDTH / 10);
        this.footerDimension = new Dimension(this.WIDTH, this.WIDTH / 10);

        this.checkButtonDimension = new Dimension((int) (this.WIDTH / 2), (int) (this.footerDimension.getHeight()));
        // FIXME using the line below the program takes longer to load idk why :(    comment the line to see the change
        //this.resetButtonDimension = new Dimension((int) (this.WIDTH / 2), (int) (this.footerDimension.getHeight()));
        this.resetButtonDimension = new Dimension((int) (this.WIDTH / 2), (int) (this.footerDimension.getHeight()));

        this.HEIGHT = (int) (this.headerDimension.getHeight() + this.boardDimension.getHeight() + this.footerDimension.getHeight());
        this.WINDOW_SIZE = new Dimension(this.WIDTH, this.HEIGHT + (int) (this.boardDimension.getWidth() / 16));
        
        setTitle("Sudoku Solver");
        setSize(this.WINDOW_SIZE);
        setMinimumSize(this.WINDOW_SIZE);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        this.board = new Board(this.boardDimension, CELL_BACKGROUND, CELL_BACKGROUND2, CELL_BORDER);
        this.board.setVisible(true);

        add(this.board, BorderLayout.CENTER);

        this.footer = new Footer(this.footerDimension);
        this.header = new Header(this.headerDimension, "Sudoku Solver");

        this.checkButton = new Button(this.checkButtonDimension, "Solve Sudoku");
        this.resetButton = new Button(this.resetButtonDimension, "Reset board");

        this.footer.add(this.checkButton, BorderLayout.EAST);
        this.footer.add(this.resetButton, BorderLayout.WEST);

        add(this.footer, BorderLayout.SOUTH);
        add(this.header, BorderLayout.NORTH);

        pack();
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

    public Dimension getDimension() {
        return this.WINDOW_SIZE;
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
