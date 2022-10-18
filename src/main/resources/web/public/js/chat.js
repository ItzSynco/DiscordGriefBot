window.onload = function () {
    document.getElementById("sendBtn").addEventListener("click", () => {
        sendMessage();
    });
}

function sendMessage() {
    let channelID = document.getElementById("channelID").value;
    let message = document.getElementById("message").value;
    
    sendRequest("send?id=" + btoa(channelID) + "&message=" + btoa(message), null);
}