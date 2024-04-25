function on_button_click(id) {
    const headers = {
        'Accept': 'application/json', 'Content-Type': 'application/json'
    };
    let fun = "on_click";
    const body = {
        'id': id,
        'function': fun
    };
    fetch(`http://127.0.0.1:2001/api`, {
        method: 'POST', headers: headers, body: JSON.stringify(body)
    })
        .then(response => response.json())
        .then(response => console.log(response)).catch(function () {
        console.log("Unhandled promise rejection")
    });
}
