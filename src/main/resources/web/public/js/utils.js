function sendRequest(value, callback) {
    fetch(location.protocol + "//" + location.hostname + ":" + location.port + "/requests/" + value, { method: 'GET' }).then(response => response.text("Result"))
    .then((result) => { 
        if (callback != null) {
            callback(result.toString());
        }
    });
}