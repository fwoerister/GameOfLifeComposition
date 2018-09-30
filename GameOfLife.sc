GameOfLife : Stream {
	var gameBoard;
	var activeCells;
	var rules;
	var r;
	var c;
	var p;
	var return_val;

	init_gol{
		|rows, columns, prob|
		r=rows;
		c=columns;
		p=prob;
		gameBoard = Array.fill2D(r,c,{prob.coin});
	}

	reset {
		this.init_gol(r,c,p);
	}

	next{
		var newGameBoard = Array.fill2D(r,c,False);
		var base_note;

		if(activeCells.size > 0) {
			"active:".postln;
			activeCells.postln;

			return_val = activeCells.pop;
		} {
			"new generation!".postln;
			gameBoard.collect({
				|row, ri|
				row.collect({
					|cell, ci|

					var env = [
						gameBoard[(ri-1)%r][(ci-1)%c],
						gameBoard[(ri-1)%r][ci],
						gameBoard[(ri-1)%r][(ci+1)%c],
						gameBoard[ri][(ci-1)%c],
						gameBoard[ri][(ci+1)%c],
						gameBoard[(ri+1)%r][(ci-1)%c],
						gameBoard[(ri+1)%r][ci],
						gameBoard[(ri+1)%r][(ci+1)%c]
					];

					var count = env.count({|item| item;});

					if (gameBoard[ri][ci]) {
						if ((count==2) || (count==3)) {
							newGameBoard[ri][ci]=true;
						} {
							newGameBoard[ri][ci]=false;
						}
					} {
						if (count==3){
							newGameBoard[ri][ci] = true;
						} {
							newGameBoard[ri][ci] = false;
						}
					}
				});
			});

			gameBoard = newGameBoard;

			activeCells = gameBoard.collect({
				|row, ri|
				row.collect({
					|cell, ci|
					if (gameBoard[ri][ci]){
						[ri,ci];
					}
				})
			}).flatten.select({
				|item|
				item.notNil;
			});

			"active:".postln;
			activeCells.postln;

			return_val = activeCells.pop;
		};

		base_note = rrand(40,70);

		if (return_val.isNil){
			this.init_gol(r,c,p);
			[base_note];
		} {

			return_val.insert(0,base_note);

			return_val.integrate.postln;
			^return_val.integrate;
		}

	}
}