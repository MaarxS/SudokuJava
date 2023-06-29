package model;

import java.util.Optional;

public class Solver {
	
	private UpdateListener updateListener = (pos, value) -> {};
	
	/** Updatelistener is called on every solving step.*/
	public void setUpdateListener(UpdateListener listener) {
		updateListener = listener;
	}
	
	public Field solve(Field field) {
		return solve(field, 0).orElse(field);
	}
	
	/** 
	 * Solve the Field within maxSteps.
	 * 
	 * @param field the Field to be solved.
	 * @param maxSteps stops execution after maxSteps (0 to never stop).
	 * @return an empty Optional container if it could not be solved otherwise returns 
	 * an Optional container with the solved Field.
	 */
	public Optional<Field> solve(Field field, int maxSteps) {
		field = field.copy();
		long startTime = System.nanoTime();
		ValueHolder<Integer> step = new ValueHolder<>(0);
		solveBacktracking(field, maxSteps, step);
		if (findNextEditable(field, new Position(0, 0)).y < 9) return Optional.empty(); 
		System.out.printf("Solver.solve: solved with %d steps in %.3fms\n", step.value, (System.nanoTime() - startTime) / 1000000f);
		return Optional.of(field);
	}
	
	/**
	 * Solves the field using {@link Field#isCorrect(Position)} with backtracking.
	 * 
	 * @param field the field to be solved
	 * @param maxSteps stop the algorithm once this {@link #steps} exceeds maxSteps (0 to never stop).
	 * {@link #steps} is incremented every time a number is entered
	 * @return true if its finished or stopped, false if its not solvable
	 */
	private boolean solveBacktracking(Field field, int maxSteps, ValueHolder<Integer> step) {
		Optional<Position> optionalPos = fieldWithLeastPossibilities(field);

		if (optionalPos.isEmpty()) { // finished solving
			return true;
		}
		Position pos = optionalPos.get();
		for (int i = 1; i <= 9; i++) {
			field.set(pos, i);
			step.value++;
			updateListener.onUpdate(pos, i);
			if (field.isCorrect(pos)) {
				if (maxSteps > 0 && step.value >= maxSteps) return true;
				if (solveBacktracking(field, maxSteps, step)) {
					return true;
				}
			}
		}
		field.set(pos, 0);
		return false;
	}
	
	public Optional<Position> fieldWithLeastPossibilities(Field field) {
		Position pos = new Position(0, 0);
		int minCount = 10;
		Optional<Position> result = Optional.empty();
		
		while (pos.y < 9) {
			pos = findNextEditable(field, pos);
			if (!(pos.y < 9)) break;
			int count = countPossibilities(field, pos);
			if (count < minCount) {
				result = Optional.of(pos);
				minCount = count;
			}
			pos = nextIndex(pos.x, pos.y);
		}
		return result;
	}

	/** Calls nextIndex until a Field is editable and empty */
	private Position findNextEditable(Field field, Position pos) {
	while (pos.y < 9 && !(field.get(pos) == 0 && field.isEditable(pos))) {
			pos = nextIndex(pos.x, pos.y);
		}
		return pos;
	}
	
	/** Get the next number, if x is at the end of the row, get the first item of the next row.*/
	private Position nextIndex(int x, int y) {
		x++;
		y += x / 9;
		x = x % 9;
		return new Position(x, y);
	}
	
	/** Returns the amount of possible solutions for the given position.*/
	private int countPossibilities(Field field, Position pos) {
		int count = 0;
		for (int i = 1; i <= 9; i++) {
			if (field.isPossible(pos, i)) {
				count++;
			}
		}
		return count;
	}
}
