`use-strict`;

const listTitle = document.querySelector("#listTitle");

const createList = () => {
    const listTitle = listTitle.nodeValue;

    let data = {
        title: listTitle
    }

    fetch("http://localhost:8080/lists/create", {
        method: "POST",
        body: JSON.stringify(data),
        headers: { "Content-Type": "application/json" }
    })
        .then(response => response.json())
        .then(info => {
            console.log(info);
        })
        .catch(err => console.error(`Stopppppp! ${err}`));
}


