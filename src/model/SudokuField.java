package model;

public class SudokuField implements Field {
	private int[][] grid = new int[9][9];

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
		int num = grid[x][y];
		if (num == 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isCorrect(int x, int y) {

		int num = grid[x][y];
		if (num == 0) {
			return true;
		}
		grid[x][y] = 0;
		// check rows
		for (int i = 0; i < 9; i++) {
			if (num == grid[x][i]) {
				grid[x][y] = num;
				return false;
			}
		}
		// check columns
		for (int i = 0; i < 9; i++) {
			if (num == grid[i][y]) {
				grid[x][y] = num;
				return false;
			}
		}

		// check cells
		int cellX = (x / 3) * 3;
		int cellY = (y / 3) * 3;
		for (int i = cellX; i < cellX + 3; i++) {
			for (int j = cellY; j < cellY + 3; j++) {
				if (grid[i][j] == num) {
					grid[x][y] = num;
					return false;
				}
			}
		}
		grid[x][y] = num;
		return true;

		// return false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int y = 0; y < grid.length; y++) {
			for (int x = 0; x < grid.length; x++) {
				builder.append(grid[x][y]);
				builder.append(" ");
			}
			builder.append("\n");
		}
		return builder.toString();
	}
}
