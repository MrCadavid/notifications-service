const stompClient = new StompJs.Client({
    brokerURL: 'wss://notifications-service-fq4c.onrender.com/websocket'
});

stompClient.onConnect = (frame) => {
    setConnected(true);
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/notifications', ({body:notification}) => {

        console.log(notification)
        showNotification(JSON.parse(notification));
    });
};

stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

stompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#table-notifications").show();
    }
    else {
        $("#table-notifications").hide();
    }
    $("#notifications").html("");
}

function connect() {
    stompClient.activate();
}

function disconnect() {
    stompClient.deactivate();
    setConnected(false);
    console.log("Disconnected");
}

function sendNotification() {
    stompClient.publish({
        destination: "/app/notify",
        body: JSON.stringify({
            type: "alert",
            message: $("#notification").val(),
            timestamp: new Date().toISOString() 
        })
    });
}


function showNotification(notification) {
    $("#notifications").append("<tr><td>" + notification.message + "</td></tr>");
}

$(function () {
    $("form").on('submit', (e) => e.preventDefault());
    $( "#connect" ).click(() => connect());
    $( "#disconnect" ).click(() => disconnect());
    $( "#send" ).click(() => sendNotification());
});

