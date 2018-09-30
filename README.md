# GameOfLifeComposition
## Introduction
This is a generative composition that is generated by two K3R1 and a GameOfLife cellular automata. The GoL is used generate chords. The approach for generating the chords was inspired by CAMUS. For every chord a random basenote is chosen and the coordinates of the cell are interpreted as the intervals of the upper structure of the chord. The K3R1 automats are used to control the duration of the chords and the panning position. This is done by defining a parameter value for every cell. The sequence is generated by creating a sequence of the 'alive' parameter values. Once all values are consumed, the next generation is generated. Again, only the 'alive' parameter values are conisdered for generating the sequence.

## Setup Guide
For running this script, you first need to find out the extension directory of your supercollider installation by running following command:
```
Platform.userExtensionDir
```
Now copy `GameOfLife.sc` and `K2R1.sc` into this directory or create a link to the original file.  
Once this is done, you can run the `GameOfLifeComposition.sc` (but don't forget to boot the server :wink:)

## Demo
A demo of this music piece can be found in the folder `demo/`
