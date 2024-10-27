
function getCells()
{
    return document.getElementsByClassName('cell-input');
}

function validateCellInput(event)
{
    const value = event.target.value;

    if (!/^[1-9]+$/.test(value))
    {
        event.target.value = "";
    }
    else
    {
        event.target.value = value.slice(-1);
    }
}

function addEventListenerToInputs()
{
    const input_elements = document.querySelectorAll(".cell-input");

    for (input_element of input_elements)
    {
        input_element.addEventListener("input", validateCellInput);
    }
}


async function getBoardSolution(board)
{
    const boardString = board.map(row => row.join(',')).join(';');
    const url = `http://127.0.0.1:8080/api/solveBoard/?board=${encodeURIComponent(boardString)}`;
    try 
    {
        const response = await fetch(url,
            {
                method: "GET",
                headers:
                {
                    "Content-Type": "application/json",
                },
            }
        )
        if (!response.ok)
        {
            throw new Error(`Couldn't get board solution: ${response.statusText}`);
        }
        const data = await response.json();
        return data.board
    }   
    catch (error) 
    {
        console.error("Error:", error)
    }
}

async function getBoardViewSolution(board)
{
    const boardString = board.map(row => row.join(',')).join(';');
    const url = `http://127.0.0.1:8080/api/viewSolveBoard/?board=${encodeURIComponent(boardString)}`;
    try 
    {
        const response = await fetch(url,
            {
                method: "GET",
                headers:
                {
                    "Content-Type": "application/json",
                },
            }
        )
        if (!response.ok)
        {
            throw new Error(`Couldn't get board solution: ${response.statusText}`);
        }
        const data = await response.json();
        return data;
    }   
    catch (error) 
    {
        console.error("Error:", error)
        return null;
    }
}




function getBoardInput()
{
    let board = [];
    let row = [];
    let column_counter = 0;
    const items = getCells()


    for (item of items)
    {
        value = (item.value) ? parseInt(item.value): 0;
        row.push(value);
        column_counter += 1;
        if (column_counter % 9 == 0)
        {
            column_counter = 0;
            board.push(row);
            row = [];
        }
    }

    return board
    
}


function resetBoardInput()
{
    
    const cells = getCells()
    for (cell of cells)
    {
        cell.value = "";
    }
    
    activateCells()
}

function deactivateCells()
{
    const cells = getCells()
    for (cell of cells)
    {
        cell.readOnly=true;
    }
}

function activateCells()
{
    const cells = getCells()
    for (cell of cells)
    {
        cell.readOnly=false;
    }
}

function putValuesOnBoard(cells, board)
{
    for (let rowIndex = 0; rowIndex < 9; rowIndex++) 
        {
            for (let colIndex = 0; colIndex < 9; colIndex++) 
            {
    
                let cellIndex = rowIndex * 9 + colIndex;
    
                cells[cellIndex].value = board[rowIndex][colIndex].toString();
    
            }
        }
}

async function solveBoard()
{
    let boardInput = getBoardInput()
    if (isBoardValid(boardInput) == false)
    {
        alert("Invalid Input Board");
        return;
    }
    let boardSolution = await getBoardSolution(boardInput)
    const cells = getCells()
    putValuesOnBoard(cells, boardSolution);
    deactivateCells();
}





function validateCellInput(event)
{
    const value = event.target.value;

    if (!/^[1-9]+$/.test(value))
    {
        event.target.value = "";
    }
    else
    {
        event.target.value = value.slice(-1);
    }
}

function addEventListenerToInputs()
{
    const input_elements = document.querySelectorAll(".cell-input");

    for (input_element of input_elements)
    {
        input_element.addEventListener("input", validateCellInput);
    }
}


async function getBoardSolution(board)
{
    const boardString = board.map(row => row.join(',')).join(';');
    const url = `http://127.0.0.1:8080/api/solveBoard/?board=${encodeURIComponent(boardString)}`;
    try 
    {
        const response = await fetch(url,
            {
                method: "GET",
                headers:
                {
                    "Content-Type": "application/json",
                },
            }
        )
        if (!response.ok)
        {
            throw new Error(`Couldn't get board solution: ${response.statusText}`);
        }
        const data = await response.json();
        return data.board
    }   
    catch (error) 
    {
        console.error("Error:", error)
    }
}

async function getBoardViewSolution(board)
{
    const boardString = board.map(row => row.join(',')).join(';');
    const url = `http://127.0.0.1:8080/api/viewSolveBoard/?board=${encodeURIComponent(boardString)}`;
    try 
    {
        const response = await fetch(url,
            {
                method: "GET",
                headers:
                {
                    "Content-Type": "application/json",
                },
            }
        )
        if (!response.ok)
        {
            throw new Error(`Couldn't get board view solution: ${response.statusText}`);
        }
        const data = await response.json();
        return data;
    }   
    catch (error) 
    {
        console.error("Error:", error)
        return null;
    }
}




function getBoardInput()
{
    let board = [];
    let row = [];
    let column_counter = 0;
    const items = getCells()


    for (item of items)
    {
        value = (item.value) ? parseInt(item.value): 0;
        row.push(value);
        column_counter += 1;
        if (column_counter % 9 == 0)
        {
            column_counter = 0;
            board.push(row);
            row = [];
        }
    }

    return board
    
}


function resetBoardInput()
{
    
    const cells = getCells()
    for (cell of cells)
    {
        cell.value = "";
    }
    
    activateCells()
}

function deactivateCells()
{
    const cells = getCells()
    for (cell of cells)
    {
        cell.readOnly=true;
    }
}

function activateCells()
{
    const cells = getCells()
    for (cell of cells)
    {
        cell.readOnly=false;
    }
}

function putValuesOnBoard(cells, board)
{
    for (let rowIndex = 0; rowIndex < 9; rowIndex++) 
        {
            for (let colIndex = 0; colIndex < 9; colIndex++) 
            {
    
                let cellIndex = rowIndex * 9 + colIndex;
    
                cells[cellIndex].value = board[rowIndex][colIndex].toString();
    
            }
        }
}

async function solveBoard()
{
    let boardInput = getBoardInput()
    if (isBoardValid(boardInput) == false)
    {
        alert("Invalid Input Board!");
        return;
    }
    let boardSolution = await getBoardSolution(boardInput)
    const cells = getCells()
    putValuesOnBoard(cells, boardSolution);
    deactivateCells();
}

async function solveBoardWithView()
{
    deactivateCells()
    let boardInput = getBoardInput()
    if (isBoardValid(boardInput) == false)
    {
        alert("Invalid Input Board!");
        activateCells()
        return;
    }
    let boardStates = await getBoardViewSolution(boardInput)
    const cells = getCells()
    for (state of boardStates)
    {
        putValuesOnBoard(cells, state.board);
        await new Promise(r => setTimeout(r, 100));
    }
    activateCells()
}
function areRowsValid(board)
{
    
    for (let row = 0; row < 9; row++)
    {       
        let digits_presence = [
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
        ]
        for (let col = 0; col < 9; col++)
        {
            if (board[row][col] == 0) continue;
            if (digits_presence[board[row][col]] == true) return false;
            digits_presence[board[row][col]] = true
        }
    }
    return true;
}

function areColsValid(board)
{
    
    for (let col = 0; col < 9; col++)
    {
        let digits_presence = [
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
        ]
        for (let row = 0; row < 9; row++)
        {
            if (board[row][col] == 0) continue;

            if (digits_presence[board[row][col]] == true) return false;
            digits_presence[board[row][col]] = true;
        }
    }
    return true;
}

function isSubGridValid(board, row, col)
{
    let digits_presence = [
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
    ]
    let subGridRow = row - row % 3, subGridCol = col - col % 3;
    

    for (let i = subGridRow; i < subGridRow + 3; i++)
    {
        for (let j = subGridCol; j < subGridCol + 3; j++)
        {
            if (board[i][j] != 0)
            {
                if (digits_presence[board[i][j]] == true)
                {
                    return false;
                }
                digits_presence[board[i][j]] = true;

            }
        }
    }
    return true;    
}

function areSubGridsValid(board)
{
    for (let row = 0; row < 9; row += 3)
    {
        for (let col = 0; col < 9; col += 3)
        {
            if (isSubGridValid(board, row, col) == false) return false;
        }
    }
    return true;
}
function isBoardValid(board)
{
    return areSubGridsValid(board) && areColsValid(board) && areRowsValid(board);
}

addEventListenerToInputs();