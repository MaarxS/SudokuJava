package model;

import java.util.Optional;

public class Solver {
	
	private UpdateListener updateListener = (pos, value) -> {};
	private long startTime = 0;
	private int steps = 0;
	
	/** Updatelistener is called on every solving step.*/
	public void setUpdateListener(UpdateListener listener) {
		updateListener = listener;
	}
	
	public Field solve(Field field) {
		field = field.copy();
		startTime = System.nanoTime();
		solveBacktracking(field, 0);
		System.out.printf("solved in %.3fms\n", (System.nanoTime() - startTime) / 1000000f);
		return field;
	}
	
	public Field solve(Field field, int maxSteps) {
		field = field.copy();
		startTime = System.nanoTime();
		steps = 0;
		solveBacktracking(field, maxSteps);
		System.out.printf("solved in %.3fms\n", (System.nanoTime() - startTime) / 1000000f);
		return field;
	}
	
	private void stepsUpdate(int steps) {
		if (steps % 1000000 == 0) {
			System.out.printf("executed %dM steps in %.3fs\n", steps / 1000000, (System.nanoTime() - startTime) / 1000000000f);
		}
	}
	
	private boolean solveBacktracking(Field field, int maxSteps) {
		Optional<Position> optionalPos = fieldWithLeastPossibilities(field);

		if (optionalPos.isEmpty()) { // finished solving
			return true;
		}
		Position pos = optionalPos.get();
		for (int i = 1; i <= 9; i++) {
			field.set(pos, i);
			stepsUpdate(++steps);
			updateListener.onUpdate(pos, i);
			if (field.isCorrect(pos)) {
				if (maxSteps > 0 && steps >= maxSteps) return true;
				if (solveBacktracking(field, maxSteps)) {
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
