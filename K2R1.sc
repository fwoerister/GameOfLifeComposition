/*

Author:   Florian Wörister
Date:     30.09.2018

Description:
This class represens a K2R1 celular automata. This means, that the automat can have
two different states (False, True). Every neighborhood consists of 3 cells.

The gameboard is initialized with random values. By using the setRule member function, the
transition rules can be defined. In case no rule exists for a certain neighborhood, the cell is going
to be "dead" in the next population.

*/
K2R1Automat : Stream {
	var gameBoard;
	var activeCells;
	var gameSize;
	var rules;
	var p;
	var possible_vals;

	init {
		|size, probability, possibleVals|
		gameBoard = Array.fill(size,{probability.coin});
		activeCells = [];
		gameSize = size;
		rules = Dictionary.new;
		possible_vals = possibleVals;
		p = probability;
	}

	next {

		activeCells.postln;

		if(activeCells.size > 0){
			^possible_vals[activeCells.pop];
		} {
			"new generation!".postln;

			gameBoard=gameBoard.collect({
				|cell, idx|
				var env;
				if(idx==0) {
					env=[gameBoard.at(gameBoard.size()-1),gameBoard.at(idx),gameBoard.at(idx+1)];
				} {
					if(idx==(gameSize-1)) {
						env=[gameBoard.at(idx-1),gameBoard.at(idx),gameBoard.at(0)];
					} {
						env=[gameBoard.at(idx-1),gameBoard.at(idx),gameBoard.at(idx+1)];
					};
				};

				if(rules.keys().includes(env)){
					rules.at(env);
				} {
					false;
				};
			});

			activeCells = gameBoard.collect({
				|item,idx|
				if(item){
					idx;
				}
			}).select({
				|item,idx|
				item.notNil;
			});

			if(activeCells.size > 0){
				^possible_vals[activeCells.pop];
			} {
				this.init(gameSize,p);
				^possible_vals[0];
			};
		};
	}


	setRule {
		|env,state|
		rules.add(env->state);
	}

}
