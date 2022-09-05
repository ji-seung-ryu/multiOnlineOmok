var stompClient = null;
var receiver = null;

function getReceiver(){
	if (username === omokRoom.creator && username !== omokRoom.opposite) receiver = omokRoom.opposite;
	else receiver = omokRoom.creator;
}

function connect() {
	if (username) {
		var socket = new SockJS('/javatechie');
		stompClient = Stomp.over(socket);
		stompClient.connect({}, onConnected, onError);
	}
}

function onConnected() {
	stompClient.subscribe('/queue/'+username, onMessageReceived);

/*
	stompClient.send("/app/message.register",
		{},
		JSON.stringify({ sender: username, receiver: username, type: 'JOIN' })
	)


	stompClient.send("/app/message.fight",
		{},
		JSON.stringify({ sender: username, receiver: username, type: 'JOIN' })
	)
*/
}

function onError(error) {
	console.log(error);
}

function onMessageReceived(payload) {
	var message = JSON.parse(payload.body);
	
	if (message.type === 'PUT_STONE') {
		var stoneInfo = JSON.parse(message.content);
		updateBoard(stoneInfo);
	} 

}

function informStone (r, c, turn){
	var c = JSON.stringify({r:r, c:c, turn:turn, roomId: omokRoom.roomId});
	stompClient.send("/app/message.put",
		{},
		JSON.stringify({ sender: username, receiver: receiver, type: 'PUT_STONE', content: c})
	)
}

getReceiver();
connect(); 