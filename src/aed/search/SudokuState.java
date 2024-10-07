package aed.search;

// apple -> 🍎

// don't forget to place this file in the right folder according to the package definition
// it should be .../src/aed/search

import java.util.List;

import java.util.ArrayList;

import aed.collections.StackList;

public class SudokuState
{
    private static final int N = 9;
    private int[][] board;

    /* private boolean[][] rows;
    private boolean[][] cols;
    private boolean[][] subGrids; */

    // you can add additional parameters (including internal classes), if needed


    public SudokuState(int[][] board)
    {
        this.board = board;

        //this.rows = new boolean[N][9];
        //this.cols = new boolean[N][9];
        //this.subGrids = new boolean[N][9];
    }

    /* public SudokuState(int[][] board, boolean[][] rows, boolean[][] cols, boolean[][] subGrids) {
        this.board = board;
        
        this.rows = rows;
        this.cols = cols;
        this.subGrids = subGrids;
    } */

    // this method is here for testing purposes
    public int[][] getBoard()
    {
        return this.board;
    }

    private boolean isValueInRow(int value, int row)
    {
        for (int i = 0; i < N; i++)
        {
            if (this.board[row][i] == value)
            {
                return true;
            }
        }
        return false;
    }

    private boolean isValueInCol(int value, int col)
    {
        for (int i = 0; i < N; i++)
        {
            if (this.board[i][col] == value)
            {
                return true;
            }
        }
        return false;
    }

    private boolean isValueInSubGrid(int value, int row, int col) 
    {
        int subGridRow= row - row % 3,
            subGridCol = col - col % 3;

        for (int i = subGridRow; i <= subGridRow + 2; i++) 
        {
            for (int j = subGridCol; j <= subGridCol + 2; j++)
            {
                if (this.board[i][j] == value)
                {
                    System.out.println(subGridRow + " " + subGridCol);
                    return true;
                }
            }
        }
        
        return false;
    }

    private boolean isValidRow(int row)
    {
        boolean[] digits_present = new boolean[N];

        for (int col = 0; col < N; col++)
        {
            int digit = this.board[row][col] - 1;
            
            if (digits_present[digit])
            {
                return false;
            }

            digits_present[digit] = true;
        }

        return true;
    }

    private boolean isValidCol(int col)
    {
        boolean[] digitsPresent = new boolean[N];
        
        for (int row = 0; row < N; row++)
        {
            int digit = this.board[row][col] - 1;

            if (digitsPresent[digit]) return false;

            digitsPresent[digit] = true;
        }

        return true;
    }

    private boolean isValidSquare(int row, int col)
    {
        boolean[] digitsPresent = new boolean[9];
        int subGridRow = row / 3;
        int subGridCol = col / 3;

        for (int i = subGridRow; i <= subGridRow + 2; i++)
        {
            for (int j = subGridCol; j <= subGridCol  + 2; j++)
            {
                int digit = this.board[i][j] - 1;

                if (digitsPresent[digit])
                {
                    return false;
                }

                digitsPresent[digit] = true;
            }
        }

        return true;
    }

    public boolean isSolution()
    {
        boolean[][] rows = new boolean[N][9];
        boolean[][] cols = new boolean[N][9];
        boolean[][] subGrids = new boolean[N][9];

        for (int row = 0; row < N; row++)
        {
            for (int col = 0; col < N; col++)
            {
                int digit = this.board[row][col] - 1;

                // Caso existe alguma célula vazia então não é solução.
                if (digit == 0) return false;

                int subGridIndex = (row / 3) * 3 + col / 3;

                // Verifique se o número está presente na linha, coluna ou sub-grelha atual.
                // Se o número estiver presente, a solução é inválida.

                if (rows[row][digit] || cols[col][digit] || subGrids[subGridIndex][digit])
                {
                    return false;
                }

                // Marcar a presença do digito na linha, coluna e sub-grelha atuais.

                rows[row][digit] = true;
                cols[col][digit] = true;
                subGrids[subGridIndex][digit] = true;
            }
        }
        return true;
    }

    public boolean isValidAction(int row, int column, int value)
    {
        if (isValueInRow(value, row)) {
            System.out.println("FALHOU POR CAUSA DA ROW");
            return false;
        } else if (isValueInCol(value, column)) {
            System.out.println("FALHOU POR CAUSA DA COLUMN");
            return false;

        } else if (isValueInSubGrid(value, row, column)) {
            System.out.println("FALHOU POR CAUSA DA GRID");
            return false;
        }

        return true;

        /* return  !isValueInRow(value, row) &&
                !isValueInCol(value, column) &&
                !isValueInSubGrid(value, row, column); */
    }

    public SudokuState generateNextState(int row, int column, int value)
    {
        if (isValidAction(row, column, value))
        {
            int[][] newBoard = this.board;
            newBoard[row][column] = value;
            SudokuState newState = new SudokuState(newBoard);

            // HACK
            System.out.println("VALIDO -> " + value + " (" + row + "," + column + ")");
            System.out.println(this.toString());
            return newState;
        }

        // HACK
        System.out.println("INVALIDO -> " + value + " (" + row + "," + column + ")");
        System.out.println(this.toString());
        return this;
    }

    public List<SudokuState> generateValidNextStates()
    {
        // HACK 
        int iteracoes = 0;

        List<SudokuState> sudokuStates = new ArrayList<>();
        for (int row = 0; row < N; row++)
        {
            // HACK
            int limite = 10000;
            if (iteracoes >= limite) break;

            for (int col = 0; col < N; col++)
            {
                // HACK
                if (iteracoes >= limite) break;

                int digit = this.board[row][col];
                // Caso não seja um espaço vazio não há nada a fazer
                if (digit != 0) continue;

                for (int d = 1; d <= 9; d++)
                {
                    SudokuState nextState = generateNextState(row, col, d);
                    sudokuStates.add(nextState);

                    // HACK
                    //System.out.println("( " + row + " , " + col + " ) testando: " + d);
                    iteracoes++;
                    if (nextState.getBoard()[row][col] != 0) break;
                }
            }
        }
        // HACK
        System.out.println(iteracoes);

        return sudokuStates;
    }

    public static SudokuState backtrackingSearch(SudokuState initialState)
    {
        // TODO: implement this method
        return null;
    }

    public SudokuState clone()
    {
        // you can use this method if needed
        int[][] newBoard = this.board.clone();
        // we need to be careful when copying bidimentional arrays. We need to do this:
        for (int i = 0; i < N; i++)
        {
            newBoard[i] = this.board[i].clone();
        }
        SudokuState newState = new SudokuState(newBoard);

        // copy/initialize additional parameters, if needed

        return newState;
    }

    // method useful for debugging and testing purposes
    public String toString()
    {
        String s = "";
        for (int i = 0; i < N; i++)
        {
            if (i % 3 == 0)
            {
                s += "----------------------\n";
            }
            for (int j = 0; j < N; j++)
            {
                if (j % 3 == 0)
                {
                    s += "|";
                }
                if (this.board[i][j] == 0)
                {
                    s += "_ ";
                }
                else
                    s += this.board[i][j] + " ";
            }
            s += "|\n";
        }
        s += "----------------------\n";
        return s;
    }
    
}
