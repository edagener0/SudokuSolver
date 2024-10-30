package sudokusolver.web.server;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sudokusolver.aed.search.SudokuState;

@RestController
public class SudokuSolverAPIController
{
    private final int N = 9;
    public int[][] getBoardFromString(String board_s)
    {
        String[] rows = board_s.split(";");
        int[][] board = new int[N][N];

        try
        {
            for (int row = 0; row < N; row++)
            {
                if (rows.length != N) throw new Exception("Invalid board length.");
                String[] cols = rows[row].split(",");
                if (cols.length != N) throw new Exception("Invalid row length in board.");

                for (int col = 0; col < N; col++)
                {
                    board[row][col] = Integer.parseInt(cols[col]);
                    if (board[row][col] < 0 || board[row][col] > N) throw new Exception("Invalid digits on board."); 
                }
            }
            if (!SudokuState.isBoardValid(board)) throw new Exception("Invalid digits on board.");
            return board;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @RequestMapping(path = "/api/solveBoard/", method = RequestMethod.GET)
    public SudokuState getSudokuSolution(@RequestParam(name = "board") String board_s)
    {
        int[][] board = getBoardFromString(board_s);

        if (board == null)
        {
            return null;
        }

        SudokuState sudokuState = new SudokuState(board);

        return SudokuState.backtrackingSearch(sudokuState);
    }

    @RequestMapping(path = "/api/viewSolveBoard/", method = RequestMethod.GET)
    public List<SudokuState> getSodokuStatesForSolution(
            @RequestParam(name = "board") String board_s)
    {
        int[][] board = getBoardFromString(board_s);

        if (board == null)
        {
            return null;
        }

        SudokuState initialState = new SudokuState(board);

        ArrayList<SudokuState> sudokuStates =
                SudokuState.getBacktrackingSearchSudokuStates(initialState);

        return sudokuStates;
    }
}
