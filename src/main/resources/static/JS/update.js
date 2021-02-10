`use-strict`;

const id = document.querySelector("#updatelistId");
const title = document.querySelector("#updatelistTitle");
const description = document.querySelector("#taskTitleUpdate");
const dueDateForTask = document.querySelector("#taskDueDateUpdate");
const idOfListForTask = document.querySelector("#IdOfListUpdate");
const taskID = document.querySelector("#taskIDUpdate");

const updateListButton = document.querySelector("#updateListButton");
const updateTaskButton = document.querySelector("#updateButton");
const finishedUpdateButton = document.querySelector("#finishUpdateButton");

const showNewTasks = document.querySelector("#updatedTaskList");
const showNewListTitle = document.querySelector("#showUpdate");

//print new list title to screen
const printNewTitle = (title) => {
    let element2 = document.createElement("h6");
    let text2 = document.createTextNode("new list title:")
    element2.appendChild(text2);
    let element = document.createElement("h3");
    let text = document.createTextNode(`${title}`);
    element.appendChild(text);
    showNewListTitle.appendChild(element2);
    showNewListTitle.appendChild(element);
}

//print new task to screen
const printNewTask = (task) => {
    let element = document.createElement("li");
    let text = document.createTextNode(`${task}`);
    element.appendChild(text);
    showNewTasks.appendChild(element);
}

//clear update details from screen
const clearUpdateDetails = () => {
    showNewTasks.innerHTML = "";
    showNewListTitle.innerHTML = "";
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
    const taskDueDate = dueDateForTask.value;
    const taskListId = idOfListForTask.value;

    let data = {
        id: taskId,
        description: taskDescription,
        dueDate: taskDueDate,
        myList: { id: taskListId }
    }
    fetch(`http://localhost:8080/entries/update/${taskId}`, {
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
finishedUpdateButton.addEventListener("click", clearUpdateDetails);