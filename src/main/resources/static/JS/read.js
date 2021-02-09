`use-strict`;
//read all lists method
const readLists = () => {
    fetch("http://localhost:8080/lists/readAll", {
        mode: "no-cors"
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
                })
            }
        }).catch((err) => {
            console.error(err);
        })
}

readLists();