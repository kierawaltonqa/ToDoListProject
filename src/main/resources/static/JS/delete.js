`use-strict`;

const listid = document.querySelector("#deletelistId");
const taskid = document.querySelector("#deletetaskId");

const deleteButton = document.querySelector("#deleteListButton");
const deleteTaskButton = document.querySelector("#deleteTaskButton");

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

const deleteTask = () => {
    const Id = taskid.value;

    fetch("http://localhost:8080/entries/delete/" + Id, {
        method: "DELETE",
    }).then(response => console.log(`list with ID ${Id} deleted`)
    )
        .catch(err => console.error(`Stop!! ${err}`));
}

//event listeners
deleteButton.addEventListener("click", deleteList);
deleteTaskButton.addEventListener("click", deleteTask);