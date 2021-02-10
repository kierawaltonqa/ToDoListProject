`use-strict`;

const listTitle = document.querySelector("#listTitle");
const taskTitle = document.querySelector("#taskTitle");
const dueDate = document.querySelector("#taskDueDate");
const ID = document.querySelector("#IdOfList")

const createListButton = document.querySelector("#createButton");
const addTaskButton = document.querySelector("#addButton");

const showList = document.querySelector("#createList");
const showTasks = document.querySelector("#taskList");

const printScreen = (title) => {
    let element = document.createElement("h3");
    let text = document.createTextNode(`${title}`);
    element.appendChild(text);
    showList.appendChild(element);
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
    console.log(data);

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
            printScreen(info.title);
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
            printTasks(`${info.description} + ${info.dueDate}`);
        })
        .catch(err => console.error('ERROR!' + err));
}

//event listeners
createListButton.addEventListener("click", createList);
addTaskButton.addEventListener("click", createTask);