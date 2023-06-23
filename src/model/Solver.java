package model;

public class Solver {
	
	private UpdateListener updateListener = (x, y, value) -> {};
	
	/** Updatelistener is called on every solving step.*/
	public void setUpdateListener(UpdateListener listener) {
		updateListener = listener;
	}
	
	public Field solve(Field field) {
		field = field.copy();
		solveBacktracking(field);
		return field;
	}
	
	public boolean solveBacktracking(Field field) {
		int[] r = fieldWithLeastPossibilities(field);
		int x = r[0];
		int y = r[1];
		if (x == -1) { // finished solving
			return true;
		}
		for (int i = 1; i <= 9; i++) {
			field.set(x, y, i);
			updateListener.onUpdate(x, y, i);
			if (field.isCorrect(x, y)) {
				if (solveBacktracking(field)) {
					return true;
				}
			}
		}
		field.set(x, y, 0);
		return false;
	}
	
	public int[] fieldWithLeastPossibilities(Field field) {
		int x = 0;
		int y = 0;
		int minCount = 10;
		int[] result = new int[] {-1, -1};
		while (y < 9) {
			int[] coordinates = findNextEditable(field, x, y);
			x = coordinates[0];
			y = coordinates[1];
			if (!(y < 9)) break;
			int count = countPossibilities(field, x, y);
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
	private int[] findNextEditable(Field field, int x, int y) {
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
	
	/** Returns the amount of possible solutions for the given position.*/
	private int countPossibilities(Field field, int x, int y) {
		int count = 0;
		for (int i = 1; i <= 9; i++) {
			if (field.isPossible(x, y, i)) {
				count++;
			}
		}
		return count;
	}
}
