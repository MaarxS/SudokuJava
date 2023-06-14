package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class FieldGenerator {
	
	public static void main(String[] args) {
		Field field = new SudokuField();
		new FieldGenerator().generateSimple(field);
		System.out.println(field);
	}
	
	private Random random = new Random();
	
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
	
	public boolean generateRecursive(Field field, int number, int placed) {
		if (number > 9) return true; // field finished
		
		List<int[]> possible = possibleFields(field, number);
		if (possible.size() == 0) return false; // if no field is possible, backtrack
		// choose random field and set it
		int[] coordinates = removeRandom(possible);
		field.set(coordinates[0], coordinates[1], number);
		
		// calculate next parameters
		placed++;
		int nextNumber = number + placed / 9;
		int nextPlaced = placed % 9;
		
		while (!generateRecursive(field, nextNumber, nextPlaced)) {
			field.set(coordinates[0], coordinates[1], 0); // remove last number
			if (possible.size() == 0 || placed != 1) { // backtrack 10 at once for performance
				return false;
			}
			coordinates = removeRandom(possible);
			field.set(coordinates[0], coordinates[1], number);
		}
		return true;
	}
	
	private List<int[]> possibleFields(Field field, int value) {
		List<int[]> list = new LinkedList<>();
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
