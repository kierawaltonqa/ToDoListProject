`use-strict`;

const readAllToDos = document.querySelector("#showLists");
const listID = document.querySelector("#listId");

const readAllButton = document.querySelector("#readLists");
const clearListsButton = document.querySelector("#clearLists");
const viewByIdButton = document.querySelector("#readListById");

const printToScreen3 = (index, description, dueDate, id, completed) => {
    let element = document.createElement("p");
    let text = document.createTextNode(`${index}: ${description}, due: ${dueDate}, completed: ${completed} (id: ${id})`);
    element.appendChild(text);
    readAllToDos.appendChild(element);
}

const printToScreen2 = (list) => {
    let element = document.createElement("h4");
    let text = document.createTextNode(`${list}`);
    element.appendChild(text);
    readAllToDos.appendChild(element);
}

//json converter
const jsonConverter = (tasks) => {
    let id = tasks.id;
    let title = tasks.title;
    //let toDoList = JSON.stringify(tasks.toDoList);
    let toDoList = tasks.toDoList;
    let printedString1 = `${id}: ${title}`;

    printToScreen2(printedString1);
    for (let task of toDoList) {
        let index = toDoList.indexOf(task) + 1;
        printToScreen3(index, task.description, task.dueDate, task.id, task.completed);
    }
}

//clear all lists from screen
const clearListsFromScreen = () => {
    readAllToDos.innerHTML = "";
}

//read all lists method
const readLists = () => {
    fetch("http://localhost:8080/lists/readAll", {
        method: "GET"
    })
        .then((response) => {
            if (response.status !== 200) {
                throw new Error("I don't have a status of 200");
            } else {
                console.log(response);
                console.log(`response is OK (200)`);
                //json-ify it (which returns a promise)
                response.json().then((infofromserver) => {
                    console.log(infofromserver);
                    for (let each of infofromserver) {
                        jsonConverter(each);
                    }
                })
            }
        }).catch((err) => {
            console.error(err);
        })
}

//read all to do entries method - NOT CURRENTLY USED - DON'T NEED??
const readEntries = () => {
    fetch("http://localhost:8080/entries/readAll", {
        method: "GET"
    })
        .then((response) => {
            if (response.status !== 200) {
                throw new Error("I don't have a status of 200");
            } else {
                console.log(response);
                console.log(`response is OK (200)`);
                //json-ify it (which returns a promise)
                response.json().then((infofromserver) => {
                    console.log(infofromserver);
                    //console.log(infofromserver.data);
                })
            }
        }).catch((err) => {
            console.error(err);
        })
}

//read by ID
const readById = () => {
    const Id = listID.value;
    fetch("http://localhost:8080/lists/read/" + Id, {
        method: "GET"
    })
        .then((response) => {
            if (response.status !== 200) {
                throw new Error("I don't have a status of 200");
            } else {
                console.log(response);
                console.log(`response is OK (200)`);
                //json-ify it (which returns a promise)
                response.json().then((infofromserver) => {
                    console.log(infofromserver);
                    jsonConverter(infofromserver);
                })
            }
        }).catch((err) => {
            console.error(err);
        })
}

//event listeners for buttons
readAllButton.addEventListener("click", readLists);
clearListsButton.addEventListener("click", clearListsFromScreen);
viewByIdButton.addEventListener("click", readById);
