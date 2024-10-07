package aed.search;

import java.util.ArrayList;
import java.util.List;

// import aed.search.SudokuState;

public class MEGAFUNCAODETESTE2 {
    public static void main(String[] args) {
        int[][] board = {
            {0,0,0, 0,0,0, 0,0,0},
            {0,0,0, 0,0,0, 0,0,0},
            {0,0,0, 0,0,0, 0,0,0},
            {0,0,0, 0,0,0, 0,0,0},
            {0,0,0, 0,0,0, 0,0,0},
            {0,0,0, 0,0,0, 0,0,0},
            {0,0,0, 0,0,0, 0,0,0},
            {0,0,0, 0,0,0, 0,0,0},
            {0,0,0, 0,0,0, 0,0,0}
        };

        SudokuState sudoku = new SudokuState(board);


        List list = sudoku.generateValidNextStates();

        for (Object s: list)
        {
            //System.out.println(">>>>>>>>>>>>>>>>>>>\n\n" + s.toString());
        } 

        //sudoku = sudoku.generateNextState(0, 3, 7);

        //System.out.println(sudoku.toString());
    }
}