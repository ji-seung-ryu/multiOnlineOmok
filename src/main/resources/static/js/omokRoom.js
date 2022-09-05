const BLACK = 0;
const WHITE = 1;
const NONE = 2;
const WIDTH = 19, HEIGHT = 19;
var turn = BLACK;
var board = createBoard(HEIGHT, WIDTH);

function createBoard(rows, columns) {
	var arr = new Array(rows);
	for (var i = 0; i < rows; i++) {
		arr[i] = new Array(columns);
		for (var j = 0; j < columns; j++) {
			arr[i][j] = omokRoom.board[i][j];
			
		}
	}
	return arr;
}

function makeBoard() {
	var board = document.querySelector('.board');
	for (var r = 1; r < HEIGHT; r++) {
		var row = document.createElement("div");
		row.className = "row";
		row.id = r;
		for (var c = 1; c < WIDTH; c++) {
			var box = document.createElement("div");
			box.className = "box";
			box.id = row.id + " " + c;

			if (canPutStone(r, c)) {
				var stone = document.createElement("div");
				stone.className = "stone";
				stone.id = box.id;
				box.appendChild(stone);
			}
			row.appendChild(box);

		}
		board.appendChild(row);
	}
	initBoard();
}

function canPutStone(r, c) {
	if (r >= 1 && r < HEIGHT-1 && c >= 1 && c < WIDTH-1) return true;
	else return false;
}

function initBoard(){
	for (var r=1 ;r<HEIGHT-1; r++){
		for (var c=1;c<WIDTH-1 ;c++){
			if (board[r][c] != NONE){
				var stone = returnStone(r, c);
				drawStone(stone, board[r][c]);
			} 
		}
	}
}

function addClickEvent() {
	var stones = document.querySelectorAll(".stone");
	stones.forEach((stone) => {
		stone.addEventListener('click', () => {
			putStone(stone);

		});
	});
}

function putStone(stone) {
	var { r, c } = getRowColumn(stone);
	if (!isMyTurn()) {
		console.log("not your turn!");
		return;	
	}
	if (board[r][c] != NONE) {
		console.log("already stone exist!")
		return;	
	}
	board[r][c] = turn;

	drawStone(stone, turn);
	checkIsFinished(r, c);

	informStone (r,c,turn);
	turnChange();
}

function isMyTurn(){
	if (turn == BLACK && omokRoom.creator === username) return true;
	else if (turn == WHITE && omokRoom.opposite === username) return true;
	else return false;	
}
function getRowColumn(stone) {
	var pos = stone.id;
	var posToArray = pos.split(" ");
	var r = parseInt(posToArray[0]), c = parseInt(posToArray[1]);
	return { r, c };
}

function drawStone(stone, prevTurn) {
	stone.style.border = "1px solid black";
	console.log(prevTurn);
	if (prevTurn == BLACK) stone.style.backgroundColor = "black";
	else stone.style.backgroundColor = "white";
}

function checkIsFinished(r, c) {
	if (isFinished(r, c)) {
		if (turn == BLACK) console.log("BLACK WIN!");
		else console.log("WHITE WIN!");
	}
}

function turnChange() {
	if (turn == BLACK) turn = WHITE;
	else turn = BLACK;
}
function isFinished(r, c) {
	var rightTop = connectedStones(r, c, -1, 1);
	var rightBot = connectedStones(r, c, 1, 1);
	var leftTop = connectedStones(r, c, -1, -1);
	var leftBot = connectedStones(r, c, 1, -1);
	var right = connectedStones(r, c, 0, 1);
	var left = connectedStones(r, c, 0, -1);
	var top = connectedStones(r, c, -1, 0);
	var bot = connectedStones(r, c, 1, 0);
	if (rightTop + leftBot > 5) return true;
	if (leftTop + rightBot > 5) return true;
	if (left + right > 5) return true;
	if (top + bot > 5) return true;

	return false;

}

function connectedStones(r, c, dy, dx) {
	if (!canPutStone(r, c)) return 0;
	if (board[r][c] != turn) return 0;

	return 1 + connectedStones(r + dy, c + dx, dy, dx);
}

function updateBoard(stoneInfo){
	var r = stoneInfo.r, c= stoneInfo.c, prevTurn = stoneInfo.turn;
	var stone = returnStone(r,c);
	board[r][c] = prevTurn;

	drawStone(stone, board[r][c]);
	checkIsFinished(r, c);

	turnChange();
}

function returnStone(r, c){
	var stones = document.querySelectorAll(".stone");
	var returnStone;
	stones.forEach((stone) => {
		if (stone.id === r+" "+c) returnStone = stone;
	});
	
	return returnStone;
}

makeBoard();
addClickEvent();