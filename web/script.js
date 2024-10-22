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

addEventListenerToInputs();