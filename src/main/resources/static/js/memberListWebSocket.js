var stompClient = null;

function connect() {
	if (username) {
		var socket = new SockJS('/javatechie');
		stompClient = Stomp.over(socket);
		stompClient.connect({}, onConnected, onError);
	}
}

function onConnected() {
	stompClient.subscribe('/topic/public', onMessageReceived);
	stompClient.subscribe('/queue/'+username, onMessageReceived);
	// Tell your username to the server


	stompClient.send("/app/message.register",
		{},
		JSON.stringify({ sender: username, receiver: username, type: 'JOIN' })
	)

/*
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
	

	if (message.type === 'JOIN') {
		console.log(message.sender + " joined!");
	} else if (message.type === 'FIGHT') {	
		fightHandler (message);	
		console.log('get fight message!');
	}

}

function fightHandler (message){
	var content = JSON.parse(message.content);
	var opposite = message.sender;
	
	if (content.title === 'invite'){
		getInvite(opposite);
	} else if (content.title === 'refuse'){
		getRefuse(opposite);
	} else if (content.title === 'agree') {
		getAgree(opposite);
	} else if (content.title === 'informRoomId'){
		enterRoom(content.roomId);
	} else{
		console.error('NO CONTENT TITLE');
	}
}

function getInvite(opposite){
	if (confirm("can you play omok with "+ opposite+"?")) {
		agreeFight(opposite);		
	} else {
		refuseFight(opposite);
	}
}

function agreeFight(opposite){
	var c = JSON.stringify({ 'title': 'agree'});
	stompClient.send("/app/message.fight",
		{},
		JSON.stringify({ sender: username, receiver: opposite, type: 'FIGHT', content: c})
	)
}

function refuseFight(opposite){
	var c = JSON.stringify({ 'title': 'refuse'});
	stompClient.send("/app/message.fight",
		{},
		JSON.stringify({ sender: username, receiver: opposite, type: 'FIGHT', content: c})
	)
}

function getRefuse(opposite){
	alert (opposite + " refused!"); 
}

async function getAgree(opposite){	
	var roomId = await createOmokRoom(opposite);
	sendRoomId(opposite, roomId);
	enterRoom(roomId);
}

async function createOmokRoom(opposite){
	var params = returnParams(opposite);
	const response = await axios.post('/create', params);
	return response.data.roomId;
}

function returnParams(opposite){
	var params = new URLSearchParams();
	params.append("creator", username);
	params.append("opposite", opposite);
	return params;
}

function sendRoomId(opposite, roomId){
	var c = JSON.stringify({ 'title': 'informRoomId' , 'roomId' : roomId});
	stompClient.send("/app/message.fight",
		{},
		JSON.stringify({ sender: username, receiver: opposite, type: 'FIGHT', content: c})
	)
}

function enterRoom(roomId){
	location.href = `/enter/${roomId}`;
}


function inviteFight (receiver){
	var c = JSON.stringify({ 'title': 'invite'});
	stompClient.send("/app/message.fight",
		{},
		JSON.stringify({ sender: username, receiver: receiver, type: 'FIGHT', content: c})
	)
	alert("waiting for response..");
}

connect();