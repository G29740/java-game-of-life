# java-gameoflife
Game of Life written in Java
![GoL](https://user-images.githubusercontent.com/48676469/97662900-31afb900-1ab3-11eb-995c-68339d0128e0.png)

## Description
The Game of Life is a famous cellular automaton designed by the mathematician John Conway. This game plays by itself using a model of computation in which the state of the cells in a grid evolves based on a predefined set of rules.

## Rules
- A two-dimentional grid of infinite size (but limited to 50x50 in this project) is used and each cell can have two distinct states: alive or dead.
- A cell has 8 neighbouring cells: 2 horizontally, 2 vertically and 4 diagonally.
- At each step of the automation, the state of the cells are determined by the state of their neighbours using the following criteria:
  - A dead cell becomes alive if it has exactly 3 live neighbouring cells.
  - A cell's state does not change if it has exactly 2 live neighbouring cells.
  - A cell dies if it has less than 2 live neighbouring cells.
  - A cell dies if it has more than 3 live neighbouring cells.
  
## Curiosities
Among the infinite number of possible configuration of the cells in the grid, are some particular structures that can appear after a certain generation. The most notable ones are:
Structure | Description | Examples
--------- | ----------- | ------------
Still life | A group of cells that does not vary from generation to generation | Block: ![Block](https://user-images.githubusercontent.com/48676469/97673686-249cc500-1ac7-11eb-8c51-cde8d953a64f.png)<br>Tub: ![Tub](https://user-images.githubusercontent.com/48676469/97673718-31211d80-1ac7-11eb-9ce7-a87b7c4592af.png)
Oscillator | A pattern that loops for a finite number of generations | Blinker ![Blinker](https://user-images.githubusercontent.com/48676469/97673773-48600b00-1ac7-11eb-99bd-ef8dd7b7ea0e.png)<br>Frog ![Frog](https://user-images.githubusercontent.com/48676469/97673819-6168bc00-1ac7-11eb-822a-594d225f1782.png)
Spaceship | An oscillating and moving structure | Glider ![Glider](https://user-images.githubusercontent.com/48676469/97673874-77767c80-1ac7-11eb-8424-96e6df021cb2.png)
Mathuselah | An active structure taking a large number of generations to stabilise | Pentomino R (1103 generations) ![Pentomino R](https://user-images.githubusercontent.com/48676469/97673939-95dc7800-1ac7-11eb-9a9e-75de8a142f59.png)

## How to use this project
Ensure JDK and JRE are installed. Refer to this link to know how to: [Overview of JDK 9 and JRE 9 Installation](https://docs.oracle.com/javase/9/install/overview-jdk-9-and-jre-9-installation.htm).

Create a project in your favorite java IDE (ex: Netbeans, IntelliJ, Eclipse, etc), transfer the `.java` files to the sources folder and run the project.

An empty grid will be displayed upon starting up. Click on any cell to create a live cell (default colour is blue) and click `Start` for autoplay or `Step` to advance step by step. Click `Stop` to stop the autoplay and click `Reset` to remove all cells from the grid.

The colour of the cells can be changed by clicking on the top menu `Options > Colours`. The size of the grid can be changed by choosing `Options > Size` and the timer delay (for autoplay) can be customised with `Options > Timer`.

Note: this automaton is limited to a grid size of 50x50.
