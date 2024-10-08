package aed.search;


public class MEGAFUNCAODETESTE2
{
    public static void main(String[] args)
    {
        int[][] board =
        {
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0}};

        /*
         * int[][] board = { {0,3,0, 0,0,6, 0,1,8}, {0,6,0, 2,0,0, 0,4,7}, {9,1,7, 0,0,0, 0,0,0},
         * {4,0,0, 0,7,9, 0,0,1}, {0,5,0, 0,2,0, 0,9,0}, {6,0,0, 4,1,0, 0,0,2}, {0,0,0, 0,0,0,
         * 6,2,9}, {3,7,0, 0,0,2, 0,8,0}, {5,2,0, 9,0,0, 0,7,0} };
         */

        SudokuState sudoku = new SudokuState(board);


        SudokuState solucao = SudokuState.backtrackingSearch(sudoku);

        if (solucao == null)
        {
            System.out.println("nao deu para resolver");
        }
        else
        {
            System.out.println(solucao.toString());
        }
    }
}
