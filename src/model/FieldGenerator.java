package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FieldGenerator {

	private static final int[] grp = new int[81];

	private int[][] grid = new int[9][9];

	private static Random random = new Random();

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
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
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
			int row = random.nextInt(9);
			int col = random.nextInt(9);
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
		KillerField solvedKiller = unsolvedKiller.copy();
		return new Pair<Field, Field>(unsolvedKiller, solvedKiller);
	}

	//generiert die Gruppen für Killer, fügt jeder Zelle einer Gruppe hinzu
	public void FieldGroup() {
		int y = 0;
		int maxgroupSize;
		int fieldgroup = 1;
		int fieldgroupsize = 0;

		for (int x = 0; x < 9; x++) {

			if (grid[x][y] == 0) {
				maxgroupSize = random.nextInt(5) + 1;
				fieldgroupsize = 0;
				generateFieldGroup(fieldgroup, x, y, fieldgroupsize, maxgroupSize);
				fieldgroup++;
			}
			if (x >= 8) {
				y++;
				x = -1;
				if (y > 8) {
					x = 10;
				}
			}
		}

	}

	public int generateFieldGroup(int fieldgroup, int x, int y, int fieldgroupsize, int maxgroupSize) {
		if (x < 0 || x >= 9 || y < 0 || y >= 9 || fieldgroupsize >= maxgroupSize) {
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
			fieldgroupsize = generateFieldGroup(fieldgroup, neighbor_x, neighbor_y, fieldgroupsize, maxgroupSize);
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

	// erzeugt die summe der einzellnen Gruppen und speichert sie in ein Array
	// index des Array entspricht der Grupp, beachte Gruppe 1 begint im Index 1
	public void getsumgrp() {

		int y = 0;

		for (int grpNr = 1; grpNr < 80; grpNr++) {
			y = 0;
			for (int x = 0; x < 9; x++) {

				if (grid[x][y] == grpNr) {
					grp[grpNr] += grid[x][y];
				}

				if (x >= 8) {
					y++;
					x = -1;
					if (y >= 9) {
						x = 10;
					}
				}

			}
		}

	}

}
