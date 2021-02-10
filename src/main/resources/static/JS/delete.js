`use-strict`;

const listid = document.querySelector("#deletelistId");

const deleteButton = document.querySelector("#deleteListButton");

//delete method for lists
const deleteList = () => {
    const id = listid.value;

    fetch("http://localhost:8080/lists/delete/" + id, {
        method: "DELETE",
    })
        // .then(response => response.json())
        .then(response => console.log(`list with ID ${id} deleted`)
        )
        .catch(err => console.error(`Stop!! ${err}`));
}

//event listeners
deleteButton.addEventListener("click", deleteList);