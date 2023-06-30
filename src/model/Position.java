package model;

import java.util.Iterator;

/** Immutable class to hold x and y position. */
public class Position implements Iterable<Position> {

	/** The horizontal position starting from 0 from left to right. */
	public final int x;
	/** The vertical position starting from 0 from top to bottom. */
	public final int y;
	
	public static final int WIDTH = 9;
	public static final int HEIGHT = 9;
	
	/** Create a Position with x and y values. */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/** Create a Position using a one dimensional input. */
	public Position(int i) {
		x = i % WIDTH;
		y = i / WIDTH;
	}

	/** 
	 * Returns an Iterable which goes over all Positions with from
	 * left to right and top to bottom. Usage:
	 * <pre>
	 * for (Position pos : Position.iterateAll()) {
	 * 
	 * }
	 * </pre>
	 */
	public static Position iterateAll() {
		return new Position(-1, 0);
	}
	
	/**
	 * Iterator to be able to use this notation:
	 * <pre>
	 * for (Position pos : new Position(-1, 0)) {
	 * 
	 * }
	 * </pre>
	 */
	@Override
	public Iterator<Position> iterator() {
		return new Iterator<Position>() {
			Position current = new Position(x, y);
			
			@Override
			public boolean hasNext() {
				return current.hasNext();
			}

			@Override
			public Position next() {
				current = current.next();
				return current;
			}
			
		};
	}

	public boolean hasNext() {
		return !(x + 1 >= WIDTH && y + 1 >= HEIGHT);
	}

	public Position next() {
		int i = x + 1 + y * 9;
		return new Position(i);
	}
	
	@Override
	public String toString() {
		return "Position(%d, %d)".formatted(x, y);
	}

	/** Return value is Index for an 1D Array. */
	public int position1Dimensional() {	  
		return x + y * 9;
	}
	
}
