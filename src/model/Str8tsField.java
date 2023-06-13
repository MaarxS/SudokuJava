package model;

public class Str8tsField implements Field {

	private int[][] grid = new int[9][9];
	private boolean[][] editable = new boolean[9][9];
	private boolean[][] black = new boolean[9][9];
	
	public Str8tsField() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				grid[i][j] = 0;
				editable[i][j] = false;
				black[i][j] = false;
			}
		}
	}
	
	@Override
	public void set(int x, int y, int value) {
		grid[x][y] = value;
	}

	@Override
	public int get(int x, int y) {
		return grid[x][y];
	}

	@Override
	public boolean isEditable(int x, int y) {
		return editable[x][y];
	}

	@Override
	public boolean isCorrect(int x, int y) {
		int value = grid[x][y];
		if (value == 0) return true;
		grid[x][y] = 0;
		// check rows
		for (int i = 0; i < grid.length; i++) {
			if (grid[i][y] == value) {
				grid[x][y] = value;
				return false;
			}
		}
		// check columns
		for (int i = 0; i < grid.length; i++) {
			if (grid[x][i] == value) {
				grid[x][y] = value;
				return false;
			}
		}
		// check str8t
		int[] numbers = getHorizontalStr8tNumbers(x, y);
		for (int number : numbers) {
			if (number == 0) {
				continue;
			}
			if (Math.abs(value - number) >= numbers.length) {
				return false;
			}
		}
		numbers = getVerticalStr8tNumbers(x, y);
		for (int number : numbers) {
			if (number == 0) {
				continue;
			}
			if (Math.abs(value - number) >= numbers.length) {
				return false;
			}
		}
		return true;
	}
	
	private int[] getHorizontalStr8tNumbers(int x, int y) {
		int start = 0;
		for (int i = x; i >= 0; i--) {
			if (black[i][y]) {
				start = x + 1;
				break;
			}
		}
		int length = 9;
		for (int i = start; i < grid.length; i++) {
			if (black[i][y]) {
				length = i - start;
				break;
			}
		}
		int[] numbers = new int[length];
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = grid[i + start][y];
		}
		return numbers;
	}

	private int[] getVerticalStr8tNumbers(int x, int y) {
		int start = 0;
		for (int i = y; i >= 0; i--) {
			if (black[x][i]) {
				start = x + 1;
				break;
			}
		}
		int length = 9;
		for (int i = start; i < grid.length; i++) {
			if (black[x][i]) {
				length = i - start;
				break;
			}
		}
		int[] numbers = new int[length];
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = grid[x][i + start];
		}
		return numbers;
	}
}
