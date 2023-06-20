package model;

public interface Field {
	
	public void set(int x, int y, int value);
	
	public int get(int x, int y);
	
	public void setEditable(int x, int y, boolean isEditable);
	
	public boolean isEditable(int x, int y);
	
	public boolean isCorrect(int x, int y);

	boolean isPossible(int x, int y, int value);
	
}
