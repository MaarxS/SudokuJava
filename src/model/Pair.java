package model;

public class Pair<X, Y> {
	public final X unsolved;
	public final Y solved;
	
	public Pair(X unsolved, Y solved) {
		this.unsolved = unsolved;
		this.solved = solved;
	}
}
