`use-strict`;

const id = document.querySelector("#updatelistId");
const title = document.querySelector("#updatelistTitle");
const description = document.querySelector("#taskTitleUpdate");
const dueDate = document.querySelector("#taskDueDateUpdate");
const idOfListForTask = document.querySelector("#IdOfListUpdate");
const taskID = document.querySelector("#taskIDUpdate");

const updateListButton = document.querySelector("#updateListButton");
const updateTaskButton = document.querySelector("#updateButton");

const showNewTasks = document.querySelector("#updatedTaskList");
const showNewListTitle = document.querySelector("#showUpdate");

const printNewTitle = (title) => {
    let element = document.createElement("h3");
    let text = document.createTextNode(`${title}`);
    element.appendChild(text);
    showNewListTitle.appendChild(element);
}

const printNewTask = (task) => {
    let element = document.createElement("li");
    let text = document.createTextNode(`${task}`);
    element.appendChild(text);
    showNewTasks.appendChild(element);
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
const updateTask = () => {
    const taskId = taskID.value;
    const taskDescription = description.value;
    const taskDueDate = dueDate.value;
    const taskListId = idOfListForTask.value;

    let data = {
        id: taskId,
        description: taskDescription,
        dueDate: taskDueDate,
        myList: { id: taskListId }
    }
    fetch("http://localhost:8080/entries/update/" + id, {
        method: "PUT",
        body: JSON.stringify(data),
        headers: {
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*"
        }
    }).then(response => response.json())
        .then(info => {
            console.log(info);
            printNewTask(`task: ${info.description}, complete by: ${info.dueDate}`);
        })
        .catch(err => console.error('ERROR!' + err));
}

//event listeners
updateListButton.addEventListener("click", updateList);
updateTaskButton.addEventListener("click", updateTask);