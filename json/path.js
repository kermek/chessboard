function b_path(sSelector) {
    var p = this;
    var CHESSBOARD_WIDTH  = 1000;
    var CHESSBOARD_HEIGHT = 1000;
    
    p.path        = $(sSelector);
    p.board       = p.path.get(0).getContext('2d')
    p.boardWidth  = p.path.get(0).width;
    p.boardHeight = p.path.get(0).height;
    p.cellWidth   = p.boardWidth/CHESSBOARD_WIDTH;
    p.cellHeight  = p.boardHeight/CHESSBOARD_HEIGHT;
    p.jsonPath    = 'path.json'
    p.jsonBoard   = 'board.json'
    
    p.drawBoard = function() {
        //draw board
        p.board.beginPath();
        for (var i = 0; i < p.boardWidth; i += p.cellWidth) {
            p.board.moveTo(i, 0);
            p.board.lineTo(i, p.boardHeight);

        }
        for (var i = 0; i < p.boardHeight; i += p.cellHeight) {
            p.board.moveTo(0, i);
            p.board.lineTo(p.boardWidth, i);
        }
        p.board.closePath();
        p.board.strokeStyle='gray';
        p.board.stroke();
        // draw states
        $.getJSON(p.jsonBoard, function(data) {
            $.each(data, function(key, val) {
                //console.log(key + " : " + val);
                for (var i = 0; i < val.length; i++) {
                    var array = JSON.parse(val[i]);
                    var x = array[0]*p.cellWidth;
                    var y = array[1]*p.cellHeight;
                    var state = array[2];
                    if (state == -1) {
                        p.board.fillStyle = 'gray';
                        p.board.fillRect(x, y, p.cellWidth, p.cellHeight);
                    }
                }
            });
        });
    }
    
    p.showPath = function() {
        p.board.fillStyle = 'black';
        $.getJSON(p.jsonPath, function(data) {
            $.each(data, function(key, val) {
            //console.log(key + " : " + val);
            p.board.fillRect(val[0]*p.cellWidth, val[1]*p.cellHeight, p.cellWidth, p.cellHeight);
            });
        });
    }
    p.draw = function() {
        p.drawBoard();
        p.showPath();
    }
}