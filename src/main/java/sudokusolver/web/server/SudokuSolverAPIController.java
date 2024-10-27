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

    public int[][] getBoardFromString(String board_s)
    {
        String[] rows = board_s.split(";");
        int[][] board = new int[9][9];

        for (int row = 0; row < 9; row++)
        {
            String[] cols = rows[row].split(",");

            for (int col = 0; col < 9; col++)
            {
                board[row][col] = Integer.parseInt(cols[col]);
            }
        }

        return board;
    }

    @RequestMapping(path = "/api/solveBoard/", method = RequestMethod.GET)
    public SudokuState getSudokuSolution(@RequestParam(name = "board") String board_s)
    {

        int[][] board = getBoardFromString(board_s);


        SudokuState sudokuState = new SudokuState(board);

        return SudokuState.backtrackingSearch(sudokuState);
    }

    @RequestMapping(path = "/api/viewSolveBoard/", method = RequestMethod.GET)
    public List<SudokuState> getSodokuStatesForSolution(
            @RequestParam(name = "board") String board_s)
    {
        int[][] board = getBoardFromString(board_s);

        SudokuState initialState = new SudokuState(board);

        ArrayList<SudokuState> sudokuStates = SudokuState.getBacktrackingSearchSudokuStates(initialState);

        return sudokuStates;
    }

}
