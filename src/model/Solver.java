package model;

public class Solver {
	
	private Field field;
	
	public Solver(Field field) {
		this.field = field;
	}
	
	/** Backtracking algorithm */
	public boolean solve() {
		int[] r = fieldWithLeastPossibilities();
		int x = r[0];
		int y = r[1];
		if (x == -1) { // finished solving
			return true;
		}
		for (int i = 1; i <= 9; i++) {
			field.set(x, y, i);
			if (field.isCorrect(x, y)) {
				if (solve()) {
					return true;
				}
			}
		}
		field.set(x, y, 0);
		return false;
	}
	
	public int[] fieldWithLeastPossibilities() {
		int x = 0;
		int y = 0;
		int minCount = 10;
		int[] result = new int[] {-1, -1};
		while (x < 9) {
			findNextEditable(x, y);
			if (x > 8) break;
			int count = countPossibilities(x, y);
			if (count < minCount) {
				result = new int[] {x, y};
				minCount = count;
			}
			nextIndex(x, y);
		}
		return result;
	}

	/** Calls nextIndex until a Field is editable and empty */
	private int[] findNextEditable(int x, int y) {
		while (x < 9 && !(field.get(x, y) == 0 && field.isEditable(x, y))) {
			int[] r = nextIndex(x, y);
			x = r[0];
			y = r[1];
		}
		return new int[] {x, y};
	}
	
	/** Get the next number, if x is at the end of the row, get the first item of the next row.*/
	private int[] nextIndex(int x, int y) {
		y += 1;
		x += y % 9;
		y = y / 9;
		return new int[] {x, y};
	}
	
	private int countPossibilities(int x, int y) {
		int temp = field.get(x, y);
		int count = 0;
		for (int i = 1; i <= 9; i++) {
			if (field.isCorrect(x, y)) break;
		}
		field.set(x, y, temp);
		return count;
	}
}
