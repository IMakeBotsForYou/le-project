package game;


import java.util.Random;

public class Game {
	
	final static int SIZE = 4;
	Grid gameGrid = new Grid(SIZE);
	Random random = new Random();
	
	public boolean move(Direction direction){
		// move given grid in given direction, returns if the Grid changed
		Grid gridCopy = gameGrid.clone();
		for (int [] point : genStartPoints(direction)){
			int[] line = gameGrid.getLine(point, direction.oppositeDirection());
			gameGrid.setLine(point, direction.oppositeDirection(), merge(line));
		}
		return !gameGrid.equals(gridCopy);
	}
	
	private int[][] genStartPoints(Direction direction){
		// generate all start points to generate the merged lines 

		int[] startPoint = new int[2];
		int gridEnd = gameGrid.getSize() - 1;
		startPoint[0] = direction.x == 1 ? gridEnd : 0;
		startPoint[1] = direction.y == 1 ? gridEnd : 0;
		return gameGrid.genPositions(startPoint, direction.otherDimension());
		
	}
	
	private int[] merge(int[] line) {
		// merges the given array to the left side
		int valueBuffer = 0;
		int[] output = {0, 0, 0, 0};
		int outputInd = 0;
		for (int number : line) {
			if (number != 0) {
				if (number == valueBuffer) {
					output[outputInd] = number + valueBuffer;
					outputInd++;
					valueBuffer = 0;
				} else if (valueBuffer != 0) {
					output[outputInd] = valueBuffer;
					outputInd++;
					valueBuffer = number;
				} else {
					valueBuffer = number;
				}
			}
		}
		if (valueBuffer != 0) {output[outputInd] = valueBuffer;}
		return output;
	}
	
	public void spawnBlock(){
		// spawns new number at random location
		int rand1;
		int rand2;
		if (!gameGrid.isFull()){
			while(true){
				rand1 = random.nextInt(SIZE);
				rand2 = random.nextInt(SIZE);
				if (gameGrid.getValue(rand1, rand2) == 0){
					// there is a 75% percent chance for a two:
					int number = random.nextInt(5) != 2 ? 4 : 2;
					gameGrid.setValue(rand1, rand2, number);
					break;
				}
			}
		}
	}
	public boolean isLost(){
		// checks if the game is lost
		// make new game object with same grid:
		Game gameCopy = new Game();
		gameCopy.setGrid(gameGrid.clone());
		// move in each direction, if the grid changes game is not lost
		for (Direction direction : Direction.values()){
			if (gameCopy.move(direction)) {
				return false;
			}
		}
		return true;
	}
        
       
	public void setGrid(Grid grid){
		this.gameGrid = grid;
	}
	public int getGridValue(int x, int y){
		// used by the GUI to display the game
		return gameGrid.getValue(x, y);
	}
}
