package model;

public class Solver {
	
	private Field field;
	private UpdateListener updateListener = field -> {};
	
	public Solver(Field field) {
		this.field = field;
	}
	
	public void setUpdateListener(UpdateListener listener) {
		updateListener = listener;
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
			updateListener.onUpdate(field);
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
		while (y < 9) {
			int[] coordinates = findNextEditable(x, y);
			x = coordinates[0];
			y = coordinates[1];
			if (!(y < 9)) break;
			int count = countPossibilities(x, y);
			if (count < minCount) {
				result = new int[] {x, y};
				minCount = count;
			}
			coordinates = nextIndex(x, y);
			x = coordinates[0];
			y = coordinates[1];
		}
		return result;
	}

	/** Calls nextIndex until a Field is editable and empty */
	private int[] findNextEditable(int x, int y) {
	while (y < 9 && !(field.get(x, y) == 0 && field.isEditable(x, y))) {
			int[] r = nextIndex(x, y);
			x = r[0];
			y = r[1];
		}
		return new int[] {x, y};
	}
	
	/** Get the next number, if x is at the end of the row, get the first item of the next row.*/
	private int[] nextIndex(int x, int y) {
		x++;
		y += x / 9;
		x = x % 9;
		return new int[] {x, y};
	}
	
	private int countPossibilities(int x, int y) {
		int count = 0;
		for (int i = 1; i <= 9; i++) {
			if (field.isPossible(x, y, i)) {
				count++;
			}
		}
		return count;
	}
}
