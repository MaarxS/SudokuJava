package model;

public class SolverThread extends Thread {
	
	private boolean stopSolving = false;
	private boolean blockOtherThreads = false;
	private Solver solveField;
	private Field field;
	private Field solvedField;
	
	public SolverThread(Solver solver, Field playerField) {
		solveField = solver;
		field = playerField;
	}
	@Override
	public void start() {
		blockOtherThreads = true;
		solvedField = solveField.solve(field);
		blockOtherThreads = false;
	}
	
	public boolean getStopSolving() {
		return stopSolving;
	}
	
	public void setStopSolving(boolean value) {
		stopSolving = value;
	}
	
	public Field getSolvedField() {
		return solvedField;
	}
	
	public boolean hasFinishedSolving() {
		return solvedField != null;	
	}
	
	public boolean getBlockOtherThreads() {
		return blockOtherThreads;
	}
}
