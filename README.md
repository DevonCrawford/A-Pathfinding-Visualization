# A-Pathfinding

Pathfinding has always interested me. This is my first attempt at A* pathfinding. My algorithm has slight modifications to increase efficiency. I came up with the idea to use sine and cosine functions to search for open nodes when the pathfinding is
set to non-diagonal mode. Making it about twice as fast in those conditions.

I made this visualization to show the beauty of pathfinding. There are two major modes for this algorithm: 
  - showSteps [True == visualization, False ignores visuals until the algorithm has processed,
     allowing it to output a timed result of the algorithm. Useful to test efficiency]
  - setDiagonal [True == diagonal creation (max 8), False != diagonal creation (max 4)]
  
Both of these settings may be changed in the "Frame.java" class. 
  - showSteps is simply a boolean variable initialized in the constructor
  - setDiagonal is a method in the APathfinding object
   
  a project by Devon Crawford.
