`use-strict`;


readAllToDos = document.querySelector("#showLists");
listID = document.querySelector("#listId");

const readAllButton = document.querySelector("#readLists");
const clearListsButton = document.querySelector("#clearLists");
const viewByIdButton = document.querySelector("#readListById")

const printToScreen = (list) => {
    let element = document.createElement("p");
    let text = document.createTextNode(`${list}`);
    element.appendChild(text);
    readAllToDos.appendChild(element);
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
                    console.log(infofromserver.data);
                    for (let each of infofromserver) {
                        printToScreen(each.id);
                        printToScreen(each.title);
                        let objJSON = JSON.stringify(each.toDoList);
                        printToScreen(objJSON);
                    }
                })
            }
        }).catch((err) => {
            console.error(err);
        })
}

//read all to do entries method 
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
    fetch("http://localhost:8080/lists/read/" + 1, {
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
                    console.log(infofromserver.data); // key - return array(6)
                    printToScreen(infofromserver.id);
                    printToScreen(infofromserver.title);
                    let myJSON = JSON.stringify(infofromserver.toDoList);
                    printToScreen(myJSON)
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