package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FieldGenerator {
	
	public static void main(String[] args) {
		Field field = new SudokuField();
		long nanos = System.nanoTime();
		
		new FieldGenerator().generateRecursive(field, 0);
		long elapsed = System.nanoTime() - nanos;
		System.out.printf("%.3f%n", elapsed / 1000000f);
		System.out.println(field);
	}
	
	private Random random = new Random();
	
	public Field generateSolvable(Field field) {
		generateRecursive(field, 0);
		for (int i = 0; i < 80; i++) {
			int x = random.nextInt(9);
			int y = random.nextInt(9);
			field.set(x, y, 0);
		}
		return field;
	}
	
	public Field generateSimple(Field field) {
		for (int number = 1; number <= 9; number++) {
			int x;
			int y;
			do {
				x = random.nextInt(9);
				y = random.nextInt(9);
			} while (field.get(x, y) != 0);
			field.set(x, y, number);
		}
		new Solver(field).solve();
		return field;
	}
	
	public boolean generateRecursive(Field field, int iteration) {
		final int BACKTRACK_STEP_SIZE = 4; // skips this amount of backtracks
		if (iteration == 81) return true; // field finished
		int number = (iteration / 9) + 1;
		
		List<int[]> possible = possibleFields(field, number);
		if (possible.size() == 0) return false; // if no field is possible, backtrack
		// choose random field and set it
		int[] coordinates = removeRandom(possible);
		field.set(coordinates[0], coordinates[1], number);
		
		while (!generateRecursive(field, iteration + 1)) {
			field.set(coordinates[0], coordinates[1], 0); // remove last number
			if (possible.size() == 0 || iteration % BACKTRACK_STEP_SIZE != 0) {
				return false;
			}
			// choose random field and set it
			coordinates = removeRandom(possible);
			field.set(coordinates[0], coordinates[1], number);
		}
		return true;
	}
	
	private List<int[]> possibleFields(Field field, int value) {
		List<int[]> list = new ArrayList<>();
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				if (field.get(x, y) != 0) continue;
				field.set(x, y, value);
				if (field.isCorrect(x, y)) {
					list.add(new int[] {x, y});
				}
				field.set(x, y, 0);
			}
		}
		return list;
	}
	
	private <T> T removeRandom(List<T> list) {
		int i = random.nextInt(list.size());
		return list.remove(i);
	}
}
