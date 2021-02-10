`use-strict`;

const listTitle = document.querySelector("#listTitle");

const createListButton = document.querySelector("#createButton");

const showList = document.querySelector("#createList");

const printScreen = (title) => {
    let element = document.createElement("h3");
    let text = document.createTextNode(`${title}`);
    element.appendChild(text);
    showList.appendChild(element);
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
        .catch(err => console.error(`Stopppppp! ${err}`));
}

createListButton.addEventListener("click", createList);


