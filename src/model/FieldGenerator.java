package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FieldGenerator {


	private Random random = new Random();

	public Field generateSolvable(Field field) {
		generateRecursive(field, 0);
		for (int i = 0; i < 80; i++) {
			int x = random.nextInt(9);
			int y = random.nextInt(9);
			Position pos = new Position(x, y);
			field.set(pos, 0);
		}
		return field;
	}

	public Field generateSimple(Field field) {
		for (int number = 1; number <= 9; number++) {
			Position pos;
			do {
				int x = random.nextInt(9);
				int y = random.nextInt(9);
				pos = new Position(x, y);
			} while (field.get(pos) != 0);
			field.set(pos, number);
		}
		new Solver().solve(field);
		return field;
	}

	public boolean generateRecursive(Field field, int iteration) {
		final int BACKTRACK_STEP_SIZE = 4; // skips this amount of backtracks
		if (iteration == 81) return true; // field finished
		int number = (iteration / 9) + 1;

		List<Position> possible = possibleFields(field, number);
		if (possible.size() == 0) return false; // if no field is possible, backtrack
		// choose random field and set it
		Position pos = removeRandom(possible);
		field.set(pos, number);

		while (!generateRecursive(field, iteration + 1)) {
			field.set(pos, 0); // remove last number
			if (possible.size() == 0 || iteration % BACKTRACK_STEP_SIZE != 0) {
				return false;
			}
			// choose random field and set it
			pos = removeRandom(possible);
			field.set(pos, number);
		}
		return true;
	}

	private List<Position> possibleFields(Field field, int value) {
		List<Position> list = new ArrayList<>();
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				Position pos = new Position(x, y);
				if (field.get(pos) != 0) continue;
				field.set(pos, value);
				if (field.isCorrect(pos)) {
					list.add(pos);
				}
				field.set(new Position(x, y), 0);
			}
		}
		return list;
	}

	private <T> T removeRandom(List<T> list) {
		int i = random.nextInt(list.size());
		return list.remove(i);
	}
	public Pair<SudokuField,SudokuField> generate(int difficulty) {
		SudokuField unsolvedSudoku = new SudokuField();
		generateRecursive(unsolvedSudoku, 0);
		SudokuField solvedSudoku = unsolvedSudoku.copy();

		int count = 0;
		int limit = 0;
		switch (difficulty) {
		case 1:
			
			limit = 50;
			break;
		case 2:
			limit = 60;
			break;
		default:
			limit = 40;
			break;
		}
		while (count < limit) {
			int row = random.nextInt(9);
			int col = random.nextInt(9);
			Position pos = new Position(row, col);
			if (unsolvedSudoku.get(pos) != 0) {
				unsolvedSudoku.set(pos, 0);
			}
			count++;
		}

		return new Pair<SudokuField, SudokuField>(unsolvedSudoku, solvedSudoku);
	}
}
