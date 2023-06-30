package model;

public interface Field {
	
	public void set(Position pos, int value);
	
	public int get(Position pos);
	
	/** Return whether the solver should edit this Position. */
	public boolean isEditable(Position pos);
	
	/** Checks if the value at this position complies to the rules of this Field. */
	public boolean isCorrect(Position pos);

	/** Checks if the value would comply to the rules of this Field on this Position. */
	public boolean isPossible(Position pos, int value);
	
	/** Create an independent copy of this object.*/
	public Field copy();
	/** Checks if Field is already solved.*/
	public boolean isSolved();
	
}
