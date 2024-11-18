const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8080/websocket'
});

stompClient.onConnect = (frame) => {
    
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/notifications', (notification) => {
        console.log(notification)
    });
};

stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

stompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};



function connect() {
    stompClient.activate();
}

function disconnect() {
    stompClient.deactivate();
    console.log("Disconnected");
}

function sendName() {
    stompClient.publish({
        destination: "/app/notify",
        body: JSON.stringify({'message': "hola te estoy enviando una notificacion"})
    });
}

connect();




