package model;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Solver {
	
	private Consumer<Integer> updateListener = (s) -> {};
	private Supplier<Boolean> isCancelled = () -> {return false;};
	
	/** Updatelistener is called on every solving step.*/
	public void setUpdateListener(Consumer<Integer> listener) {
		updateListener = listener;
	}
	
	/** Set the function to check if the solver is cancelled. */
	public void setIsCancelled(Supplier<Boolean> function) {
		isCancelled = function;
	}
	
	public <T extends Field> T solve(T field) {
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
	@SuppressWarnings("unchecked")
	public <T extends Field> Optional<T> solve(T field, int maxSteps) {
		field = (T) field.copy();
		long startTime = System.nanoTime();
		ValueHolder<Integer> step = new ValueHolder<>(0);
		solveBacktracking(field, maxSteps, step);
		boolean isSolved = true;
		for (Position pos : Position.iterateAll()) {
			if (field.get(pos) == 0 && field.isEditable(pos)) {
				isSolved = false;
				break;
			}
		}
		if (!isSolved) return Optional.empty(); 
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
		if (isCancelled.get()) {
			return true;
		}
		Optional<Position> optionalPos = fieldWithLeastPossibilities(field);
		
		if (optionalPos.isEmpty()) { // finished solving
			return true;
		}
		Position pos = optionalPos.get();
		for (int i = 1; i <= 9; i++) {
			field.set(pos, i);
			step.value++;
			updateListener.accept(step.value);
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
		int minCount = 10;
		Optional<Position> result = Optional.empty();
		for (Position pos : Position.iterateAll()) {
			if (field.get(pos) != 0 || !field.isEditable(pos)) continue;
			int count = countPossibilities(field, pos);
			if (count < minCount) {
				result = Optional.of(pos);
			}
		}
		return result;
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