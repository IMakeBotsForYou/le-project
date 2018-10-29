package game;

import java.util.Arrays;
import java.awt.Color;
public class Grid {
	private int[][] grid;
	private final int size;
	
	public Grid(int inputSize){
		size = inputSize;
		grid = new int[size][size];	
	}
	
	public void setgrid(int[][]grid){
		this.grid = grid;
	}
	
	public int getValue(int x, int y){
		return grid[y][x];
	}
	
	public void setValue(int x, int y, int value){
		grid[y][x] = value;
	}

        
	public int getSize(){
		return size;
	}
        @Override
	public Grid clone(){
		int [][] newgrid = new int[size][size];
		for (int i = 0; i < grid.length; i++){
			newgrid[i] = Arrays.copyOf(grid[i], grid[i].length);
		}
		Grid copyGrid = new Grid(size);
		copyGrid.grid = newgrid;
		return copyGrid;
	}
	
	public int[] getLine(int[] point, Direction direction){
		
		int[] output = new int[size];
		int i = 0;
		for (int[] pos : genPositions(point, direction)){
			output[i] = grid[pos[1]][pos[0]];
			i++;
		}
		return output;
	}
	
	public void setLine(int[] point, Direction direction, int[] line){
		int linePos = 0;
		for (int[] pos : genPositions(point, direction)){
			grid[pos[1]][pos[0]] = line[linePos];
			linePos++;
		}
	}
	
	public boolean isFull(){
		//checks if grid is full
		for(int[] row : grid){
			for (int value : row){
				if (value == 0){
					return false;
				}
			}
		}
		return true;
        }
	public int[][] genPositions(int[] point, Direction direction){
		/*generate all positions passed, going from the given point in the given direction
		 * returns array of positions
		 */
		int[][] output = new int[size][2];
		int x = point[0];
		int y = point[1];
		for (int i = 0; i < size; i++){
			output[i][0] = x;
			output[i][1] = y;
			x += direction.x;
			y += direction.y;
		}
		return output;
	}
	
	public boolean equals( Object obj ){
		if (!(obj instanceof Grid)){
			return false;
		}
		Grid otherGrid = (Grid) obj;
		return Arrays.deepEquals(grid, otherGrid.grid);
	}
	
	public int hashCode(){
		return size;
	}
}
