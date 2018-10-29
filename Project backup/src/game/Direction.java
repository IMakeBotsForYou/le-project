package game;

public enum Direction {
	LEFT (-1, 0),
	RIGHT (1,0),
	UP (0, -1),
	DOWN(0, 1);
	
	public final int x;
	public final int y;
	
	Direction(int x, int y){
		this.x = x;
		this.y = y;
	}
	public Direction oppositeDirection(){
		if (this == Direction.UP) {return Direction.DOWN;}
		if (this == Direction.DOWN) {return Direction.UP;}
		if (this == Direction.RIGHT) {return Direction.LEFT;}
		else {return Direction.RIGHT;}
	}
	public Direction otherDimension(){
		//used to get start points for merging
		return this.x == 0 ? RIGHT : DOWN;
	}
}
