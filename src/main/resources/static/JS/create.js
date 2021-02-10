`use-strict`;

const listTitle = document.querySelector("#listTitle");
const taskTitle = document.querySelector("#taskTitle");
const dueDate = document.querySelector("#taskDueDate");
const ID = document.querySelector("#IdOfList")

const createListButton = document.querySelector("#createButton");
const addTaskButton = document.querySelector("#addButton");
const finishedButton = document.querySelector("#finishAddButton");

const showList = document.querySelector("#createList");
const showTasks = document.querySelector("#taskList");

//clear creation details from the screen
const clearDetails = () => {
    showTasks.innerHTML = "";
    showList.innerHTML = "";
}

const printScreen = (title) => {
    let element = document.createElement("h3");
    let text = document.createTextNode(`new list: ${title.title}`);
    let element2 = document.createElement("p");
    let text2 = document.createTextNode(`with id: ${title.id}`);
    element2.appendChild(text2);
    element.appendChild(text);
    showList.appendChild(element);
    showList.appendChild(element2);
}

const printTasks = (tasks) => {
    let element = document.createElement("li");
    let text = document.createTextNode(`${tasks}`);
    element.appendChild(text);
    showTasks.appendChild(element);
}

const createList = () => {
    const listTitle2 = listTitle.value;
    let data = {
        title: listTitle2
    }
    fetch("http://localhost:8080/lists/create", {
        method: "POST",
        body: JSON.stringify(data),
        headers: {
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*"
        }
    })
        .then(response => response.json())
        .then(info => {
            console.log(info);
            //print title to screen
            printScreen(info);
        })
        .catch(err => console.error('ERROR!' + err));
}

const createTask = () => {
    const taskTitle2 = taskTitle.value;
    const dueDate2 = dueDate.value;
    const listId = ID.value;

    let data = {
        description: taskTitle2,
        dueDate: dueDate2,
        completed: false,
        myList: { id: listId }
    }

    fetch("http://localhost:8080/entries/create", {
        method: "POST",
        body: JSON.stringify(data),
        headers: {
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*"
        }
    })
        .then(response => response.json())
        .then(info => {
            console.log(info);
            printTasks(`task: ${info.description}, complete by: ${info.dueDate}`);
        })
        .catch(err => console.error('ERROR!' + err));
}

//event listeners
createListButton.addEventListener("click", createList);
addTaskButton.addEventListener("click", createTask);
finishedButton.addEventListener("click", clearDetails);