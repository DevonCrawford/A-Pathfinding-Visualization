# A-Pathfinding

I always wanted to make a proper pathfinding algorithm. In grade 11, with only a couple months experience in coding, I remember
deciding to try the A* algorithm. Needless to say, I failed without the proper knowledge. Last year I decided
to come back to this concept. One week of daily coding and I had done it. However, I closely followed online tutorials. So
it was not enough. I figured I need to make this different, faster. 

Somehow I came up with the idea to use sine and cosine functions to search for the open nodes when the pathfinding is
set to non-diagonal mode. Making it 2.25x faster in those conditions :)

I made this visualization to show the beauty of pathfinding. There are two major modes for this algorithm: 
  - showSteps [True == visualization, False ignores visuals until the algorithm has processed,
     allowing it to output a timed result of the algorithm. Useful to test efficiency]
  - setDiagonal [True == diagonal creation (max 8), False != diagonal creation (max 4)]
  
Both of these settings may be changed in the "Frame.java" class. 
  - showSteps is simply a boolean variable initialized in the constructor
  - setDiagonal is a method in the APathfinding object
   
  a project by Devon Crawford.
