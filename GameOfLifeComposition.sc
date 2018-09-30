/*
   Author: Florian Wörister
   Date:   30.09.2018

   Description:
   This is a generative composition that is generated by two K3R1 and a
   GameOfLife cellular automata. The GoL is used generate chords. The
   approach for generating the chords was inspired by CAMUS.
   For every chord a random basenote is chosen and the coordinates of the cell
   are interpreted as the intervals of the upper structure of the chord.

   The K3R1 automats are used to control the duration of the chords and the panning position.
   This is done by defining a parameter value for every cell. The sequence is generated by
   creating a sequence of the 'alive' parameter values. Once all values are consumed, the
   next generation is generated. Again, only the 'alive' parameter values are conisdered for
   generating the sequence.

 */

(
p = K2R1Automat.new;
p.init(11, 0.6, [0,0.1,0.2,0.3,0.4,0.5,0.6,0.7,0.8,0.9,1]);
p.setRule([true,true,true],true);
p.setRule([true,true,false],true);
p.setRule([true,false,true],true);
p.setRule([true,false,false],true);

d = K2R1Automat.new;
d.init(7, 0.6, [0.3,0.2,0.7,1,1.5,2,2.5]);
d.setRule([true,true,true],true);
d.setRule([true,true,false],true);
d.setRule([true,false,true],true);
d.setRule([true,false,false],true);

g = GameOfLife.new;
g.init_gol(12,12, 0.5);

s.record();
Pbind(\midinote, g, \delta, d, \pan, p).play;
)
