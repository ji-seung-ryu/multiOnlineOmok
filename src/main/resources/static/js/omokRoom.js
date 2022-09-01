const BLACK = 0;
const WHITE = 1;
const NONE = 2;
const WIDTH = 19, HEIGHT = 19;
var turn = BLACK;
var board = create2DArray(HEIGHT, WIDTH);

function create2DArray(rows, columns) {
    var arr = new Array(rows);
    for (var i = 0; i < rows; i++) {
        arr[i] = new Array(columns);
        for (var j = 0;j<columns;j++){
          arr[i][j] = NONE;
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
}

function canPutStone(r, c) {
  if (r >= 1 && r < 18 && c >= 1 && c < 18) return true;
  else return false;
}

function addClickEvent() {
  var stones = document.querySelectorAll(".stone");
  stones.forEach((stone) => {
    stone.addEventListener('click', (event) => {

      var {r, c} = getRowColumn(stone);
      board[r][c] = turn;

      drawStone(stone);
      checkIsFinished (r,c);

      turnChange();
    },{ once : true});
  });
}

function getRowColumn (stone){
  var pos = stone.id;
  var posToArray = pos.split(" ");
  var r = parseInt(posToArray[0]), c = parseInt(posToArray[1]);
  return {r,c};
}

function drawStone(stone){
  stone.style.border = "1px solid black";
  if (turn == BLACK) stone.style.backgroundColor = "black";
  else stone.style.backgroundColor = "white";
}

function checkIsFinished(r, c){
  if (isFinished(r,c)){
    if (turn == BLACK) console.log("BLACK WIN!");
    else console.log("WHITE WIN!");
  }
}

function turnChange(){
  if (turn == BLACK) turn = WHITE;
  else turn = BLACK;
}
function isFinished(r, c) {
  var rightTop = connectedStones(r,c,-1,1);
  var rightBot = connectedStones(r,c,1,1);
  var leftTop = connectedStones(r,c,-1,-1);
  var leftBot = connectedStones(r,c,1,-1);
  var right = connectedStones(r,c,0,1);
  var left = connectedStones(r,c,0,-1);
  var top = connectedStones(r,c,-1,0);
  var bot = connectedStones(r,c,1,0);
  if (rightTop+leftBot>5) return true;
  if (leftTop+rightBot>5) return true;
  if (left+right > 5) return true;
  if (top+bot > 5) return true;

  return false;

}

function connectedStones (r,c,dy,dx){
  if (!canPutStone(r,c)) return 0;
  if (board[r][c] != turn) return 0;

  return 1+ connectedStones(r+dy,c+dx,dy,dx);
}



makeBoard();
addClickEvent();