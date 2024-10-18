package sudokusolver.aed.search;

import java.util.List;
// import java.util.function.Consumer;
// import java.util.function.Function;

import sudokusolver.aed.collections.IStack;
import sudokusolver.aed.collections.ShittyStack;
import sudokusolver.aed.collections.StackList;
import sudokusolver.aed.utils.TemporalAnalysisUtils;

import java.util.ArrayList;


public class SudokuState
{
    private static final int N = 9;
    private int[][] board;


    /**
     * Construtor do objeto {@code SudokuState}.
     * 
     * @param board - {@code int[][]} que indica os números e as posições de cada dígito de um
     *        tabuleiro Sudoku.
     */
    public SudokuState(int[][] board)
    {
        this.board = board;
    }


    /**
     * 
     * @return Retorna um array bidimensional contendo o tabuleiro de Sudoku
     */
    public int[][] getBoard()
    {
        return this.board;
    }


    public void setCell(int row, int column, int value)
    {
        this.board[row][column] = value;
    }


    public int getCell(int row, int column)
    {
        return this.board[row][column];
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
        int subGridRow = row - row % 3, subGridCol = col - col % 3;

        for (int i = subGridRow; i <= subGridRow + 2; i++)
        {
            for (int j = subGridCol; j <= subGridCol + 2; j++)
            {
                if (this.board[i][j] == value)
                {
                    return true;
                }
            }
        }

        return false;
    }


    // XXX used at line 246
    private static boolean isBoardValid(int[][] board)
    {
        SudokuState tempState = new SudokuState(board);
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
            {
                int digit = tempState.getCell(i, j);
                if (digit == 0)
                {
                    continue;
                }

                if (!tempState.isValidAction(i, j, digit))
                {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * Verifica se o tabuleiro atual é uma solução possível para o jogo Sudoku.
     * 
     * @return Retorna {@code true} caso o tabuleiro atual seja uma solução possível para o jogo
     *         Sudoku, caso contrário é retornado {@code false}.
     */
    public boolean isSolution()
    {
        boolean[][] rows = new boolean[N][9];
        boolean[][] cols = new boolean[N][9];
        boolean[][] subGrids = new boolean[N][9];

        for (int row = 0; row < N; row++)
        {
            for (int col = 0; col < N; col++)
            {
                int digit = this.board[row][col];

                // Caso existe alguma célula vazia então não é solução.
                if (digit-- == 0)
                    return false;

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


    /**
     * Método que verifica se uma posição do tabuleiro de Sudoku é válida.
     *
     * @param row - Linha do tabuleiro.
     * @param column - Coluna do tabuleiro.
     * @param value - Valor a ser colocado no tabuleiro.
     * @return Retorna {@code true} caso a posição seja válida, caso contrário retorna
     *         {@code false}.
     */
    public boolean isValidAction(int row, int column, int value)
    {
        return !isValueInRow(value, row) && !isValueInCol(value, column)
                && !isValueInSubGrid(value, row, column);
    }


    /**
     * Método que tenta colocar um valor numa determinada coordenada do tabuleiro de Sudoku.
     *
     * @param row - Linha do tabuleiro.
     * @param column - Coluna do tabuleiro.
     * @param value - Valor a ser colocado no tabuleiro.
     * @return Retorna um novo objeto com o valor colocado nas coordenadas especificadas. Retorna
     *         null caso não seja possível colocar o valor no tabuleiro de Sudoku.
     */
    public SudokuState generateNextState(int row, int column, int value)
    {
        SudokuState nextState = this.clone().generateNextStateRetuningNull(row, column, value);

        if (nextState == null)
        {
            return this;
        }

        return nextState;
    }


    private SudokuState generateNextStateRetuningNull(int row, int column, int value)
    {
        if (isValidAction(row, column, value))
        {
            this.setCell(row, column, value);

            return this;
        }

        return null;
    }


    /**
     * Método que tenta preencher a próxima casa vazia com um dígito. A próxima casa vazia é
     * determinada percorrendo o tabuleiro da esquerda para a direita, e de cima para baixo.
     *
     * @return Retorna uma lista com todos os estados válidos resultantes de se escolher a próxima
     *         casa vazia. Caso não existam ações válidas, será retornada uma lista vazia.
     */
    public List<SudokuState> generateValidNextStates()
    {
        List<SudokuState> sudokuStates = new ArrayList<>();

        for (int row = 0; row < N; row++)
        {
            for (int col = 0; col < N; col++)
            {
                int digit = this.board[row][col];
                if (digit != 0)
                {
                    continue;
                }

                for (int d = 1; d <= 9; d++)
                {
                    SudokuState nextState = generateNextStateRetuningNull(row, col, d);
                    if (nextState == null)
                    {
                        continue;
                    }
                    sudokuStates.add(nextState.clone());
                }
                return sudokuStates;
            }
        }

        return sudokuStates;
    }


    private static SudokuState backtrackingSearch(SudokuState initialState,
            IStack<SudokuState> stack)
    {

        stack.push(initialState);

        while (!stack.isEmpty())
        {
            SudokuState lastState = stack.peek();

            if (lastState.isSolution()) {
                return lastState.clone();
            }

            List<SudokuState> nextStages = stack.pop().generateValidNextStates();

            if (nextStages.size() > 0)
            {
                for (SudokuState i : nextStages)
                {
                    stack.push(i);
                }
            }
        }
        return null;
    }


    /**
     * Método estático para realizar a busca por retrocesso (Backtracking) no estado do Sudoku.
     *
     * @param initialState - Estado atual do Sudoku.
     * @return Retorna a solução encontrada. Null caso não seja encontrada uma solução.
     */
    public static SudokuState backtrackingSearch(SudokuState initialState)
    {
        return SudokuState.backtrackingSearch(initialState, new StackList<SudokuState>());
    }


    /**
     * Método usado para criar cópias profundas.
     *
     * @return Retorna uma cópia profunda do objeto SudokuState.
     */
    public SudokuState clone()
    {
        int[][] newBoard = this.board.clone();
        for (int i = 0; i < N; i++)
        {
            newBoard[i] = this.board[i].clone();
        }
        SudokuState newState = new SudokuState(newBoard);

        return newState;
    }


    /**
     * Método usado para transformar {@code SudokuState} em uma {@code String}.
     *
     * @return Retorna uma {@code String} do tabuleiro de Sudoku.
     */
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


    public static void main(String[] args)
    {
        // NOTA: RAZAO NA BASE 2 e o expoente elevado a a ao expoente da razao na base 2.
        int iterations = 6;
        System.out.println("Backtracking Search Stacklist");
        TemporalAnalysisUtils.runDoublingRatioTest((complexity) -> generateSudokuExample(complexity), 
        (state) -> SudokuState.backtrackingSearch(state, new StackList<SudokuState>()), 
        iterations, 1);

        System.out.println("Backtracking Search ShittyStack");
        TemporalAnalysisUtils.runDoublingRatioTest((complexity) -> generateSudokuExample(complexity), 
        (state) -> SudokuState.backtrackingSearch(state, new ShittyStack<SudokuState>()), 
        iterations, 1);
    }

    public static SudokuState generateSudokuExample(int complexity) {
        int[][] board  = {
            {1, 2, 3, 4, 5, 6, 7, 8, 9},
            {7, 8, 9, 1, 2, 3, 4, 5, 6},
            {4, 5, 6, 7, 8, 9, 1, 2, 3},
            {3, 1, 2, 8, 4, 5, 9, 6, 7},
            {6, 9, 7, 3, 1, 2, 8, 4, 5},
            {8, 4, 5, 6, 9, 7, 3, 1, 2},
            {2, 3, 1, 5, 7, 4, 6, 9, 8},
            {9, 6, 8, 2, 3, 1, 5, 7, 4},
            {5, 7, 4, 9, 6, 8, 2, 3, 1}
        };

        int zerosCount = 0;
        
        for (int i = 0; zerosCount < complexity && i < 9; i++) {
            for (int j = 0; zerosCount < complexity && j < 9; j++) {
                if (i % 2 == 0 || j % 2 == 0) {
                    board[i][j] = 0;
                    zerosCount++;
                }
            }
        }

        SudokuState state = new SudokuState(board);
        return state;
    }
}

