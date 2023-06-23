package model;

public interface Field {
	
	public void set(int x, int y, int value);
	
	public int get(int x, int y);
	
	public boolean isEditable(int x, int y);
	
	public boolean isCorrect(int x, int y);

	public boolean isPossible(int x, int y, int value);
	
	/** Create an independent copy of this object.*/
	public Field copy();
	
}
