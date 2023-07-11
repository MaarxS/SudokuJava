package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FieldGenerator {

	private final int FIELDS_IN_ROW = 9;
	private final int FIELDS_IN_COLUMN = 9;
	private final int MAX_GRPOUP_SIZE = 5;

	private Random random = new Random();

	public Field generateSolvable(Field field) {
		generateRecursive(field, 0);
		for (int i = 0; i < 80; i++) {
			int x = random.nextInt(FIELDS_IN_ROW);
			int y = random.nextInt(FIELDS_IN_COLUMN);
			Position pos = new Position(x, y);
			field.set(pos, 0);
		}
		return field;
	}

	public Field generateSimple(Field field) {
		for (int number = 1; number <= 9; number++) {
			Position pos;
			do {
				int x = random.nextInt(FIELDS_IN_ROW);
				int y = random.nextInt(FIELDS_IN_COLUMN);
				pos = new Position(x, y);
			} while (field.get(pos) != 0);
			field.set(pos, number);
		}
		new Solver().solve(field);
		return field;
	}

	public boolean generateRecursive(Field field, int iteration) {
		final int BACKTRACK_STEP_SIZE = 4; // skips this amount of backtracks
		if (iteration == 81)
			return true; // field finished
		int number = (iteration / 9) + 1;

		List<Position> possible = possibleFields(field, number);
		if (possible.size() == 0)
			return false; // if no field is possible, backtrack
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
		for (int x = 0; x < FIELDS_IN_ROW; x++) {
			for (int y = 0; y < FIELDS_IN_COLUMN; y++) {
				Position pos = new Position(x, y);
				if (field.get(pos) != 0)
					continue;
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

	/* Sudoku mit vom Benutzer vorgegebener Schwierigkeit erstellen */
	public Pair<Field, Field> generate(int difficulty) {

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
			int row = random.nextInt(FIELDS_IN_ROW);
			int col = random.nextInt(FIELDS_IN_COLUMN);
			Position pos = new Position(row, col);
			if (unsolvedSudoku.get(pos) != 0) {
				unsolvedSudoku.set(pos, 0);
			}
			count++;
		}

		return new Pair<Field, Field>(unsolvedSudoku, solvedSudoku);
	}

	public Pair<Field, Field> generateKiller(int difficulty) {
		KillerField unsolvedKiller = new KillerField();
		generateRecursive(unsolvedKiller, 0);

		int[][] groups = createFieldGroups();
		for (Position pos : Position.iterateAll()) {
			unsolvedKiller.setGroup(pos, groups[pos.x][pos.y]);
		}

		List<Integer> sums = calculateSums(unsolvedKiller);
		for (int i = 1; i < sums.size(); i++) {
			unsolvedKiller.setSum(i, sums.get(i));
		}

		KillerField solvedKiller = unsolvedKiller.copy();

		int count = 0;
		int limit = 0;
		switch (difficulty) {
		case 1:
			limit = 60;
			break;
		case 2:
			limit = 70;
			break;
		default:
			limit = 50;
			break;
		}
		while (count < limit) {
			int row = random.nextInt(FIELDS_IN_ROW);
			int col = random.nextInt(FIELDS_IN_COLUMN);
			Position pos = new Position(row, col);
			if (unsolvedKiller.get(pos) != 0) {
				unsolvedKiller.set(pos, 0);
				count++;
			}
		}
		return new Pair<Field, Field>(unsolvedKiller, solvedKiller);
	}

	// generiert die Gruppen für Killer, fügt jeder Zelle einer Gruppe hinzu
	private int[][] createFieldGroups() {

		int y = 0;
		int maxgroupSize;
		int fieldgroup = 1;
		int fieldgroupsize = 0;
		int[][] grid = new int[FIELDS_IN_ROW][FIELDS_IN_COLUMN];

		for (int x = 0; x < FIELDS_IN_ROW; x++) {

			if (grid[x][y] == 0) {
				maxgroupSize = random.nextInt(MAX_GRPOUP_SIZE) + 1;
				fieldgroupsize = 0;
				generateFieldGroup(grid, fieldgroup, x, y, fieldgroupsize, maxgroupSize);
				fieldgroup++;
			}
			if (x >= FIELDS_IN_ROW - 1) {
				y++;
				x = -1;
				if (y > FIELDS_IN_COLUMN - 1) {
					break;
				}
			}
		}
		return grid;
	}

	private int generateFieldGroup(int[][] grid, int fieldgroup, int x, int y, int fieldgroupsize, int maxgroupSize) {
		if (x < 0 || x >= FIELDS_IN_ROW || y < 0 || y >= FIELDS_IN_COLUMN || fieldgroupsize >= maxgroupSize) {
			// Die Rekursion wird beendet, wenn die Grenzen des Feldes erreicht sind oder
			// die MaximamGruppengröße erreicht ist
			return fieldgroupsize;
		}
		if (grid[x][y] != 0) {
			return fieldgroupsize;
		}

		grid[x][y] = fieldgroup;
		fieldgroupsize++;

		// Generiere die benachbarten Zellen zufällig
		int[][] neighbors = { { x - 1, y }, { x + 1, y }, { x, y - 1 }, { x, y + 1 } };
		shuffleArray(neighbors, random);

		for (int[] neighbor : neighbors) {
			int neighbor_x = neighbor[0];
			int neighbor_y = neighbor[1];
			fieldgroupsize = generateFieldGroup(grid, fieldgroup, neighbor_x, neighbor_y, fieldgroupsize, maxgroupSize);
		}
		return fieldgroupsize;
	}

	// Hilfsmethode zum Mischen eines Arrays
	private void shuffleArray(int[][] array, Random random) {
		for (int i = array.length - 1; i > 0; i--) {
			int index = random.nextInt(i + 1);
			int[] temp = array[index];
			array[index] = array[i];
			array[i] = temp;
		}
	}

	private List<Integer> calculateSums(KillerField field) {
		List<Integer> sums = new ArrayList<>();
		for (Position pos : Position.iterateAll()) {
			int groupNr = field.getGroup(pos);
			while (sums.size() <= groupNr)
				sums.add(0);
			sums.set(groupNr, sums.get(groupNr) + field.get(pos));
		}
		return sums;
	}

}
