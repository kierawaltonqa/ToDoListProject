`use-strict`;

const id = document.querySelector("#updatelistId");
const title = document.querySelector("#updatelistTitle");
const description = document.querySelector("#taskTitleUpdate");
const dueDate = document.querySelector("#taskDueDateUpdate");
const idOfListForTask = document.querySelector("#IdOfListUpdate");

const updateListButton = document.querySelector("#updateListButton");
const updateTaskButton = document.querySelector("#updateButton");

const showNewTasks = document.querySelector("#updatedTaskList");
const showNewListTitle = document.querySelector("#showUpdate");

const printNewTitle = (title) => {
    let element = document.createElement("h3");
    let text = document.createTextNode(`updated title: ${title}`);
    element.appendChild(text);
    showNewListTitle.appendChild(element);
}

//update list
const updateList = () => {
    const listTitle = title.value;
    const ID = id.value;
    let data = {
        id: ID,
        title: listTitle
    }
    fetch("http://localhost:8080/lists/update/" + ID, {
        method: "PUT",
        body: JSON.stringify(data),
        headers: {
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*"
        }
    }).then(response => response.json())
        .then(info => {
            console.log(info);
            printNewTitle(info.title);
        })
        .catch(err => console.error('ERROR!' + err));
}

//update tasks in list


//event listeners
updateListButton.addEventListener("click", updateList)