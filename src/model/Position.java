package model;

/** Immutable class to hold x and y position. */
public class Position {
	/** The horizontal position starting from 0 from left to right. */
	public final int x;
	/** The vertical position starting from 0 from top to bottom. */
	public final int y;
	
	/** Create a Position with x and y values. */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
}
