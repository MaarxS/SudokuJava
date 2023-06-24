package model;

public class SudokuField implements Field {
	private int[][] grid = new int[9][9];

	public SudokuField() {
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				grid[x][y] = 0;
			}
		}
	}
	
	/** Constructor for copying this object.*/
	private SudokuField(int[][] grid) {
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				this.grid[x][y] = grid[x][y];
			}
		}
	}
	
	@Override
	public void set(Position pos, int value) {
		grid[pos.x][pos.y] = value;
	}

	@Override
	public int get(Position pos) {
		return grid[pos.x][pos.y];
	}

	@Override
	public boolean isEditable(Position pos) {
		return true;
	}

	@Override
	public boolean isCorrect(Position pos) {
		int value = grid[pos.x][pos.y];
		if (value == 0) {
			return true;
		}
		grid[pos.x][pos.y] = 0;
		boolean result = isPossible(pos, value);
		grid[pos.x][pos.y] = value;
		return result;
	}
	
	@Override
	public boolean isPossible(Position pos, int value) {
		// check rows
		for (int i = 0; i < 9; i++) {
			if (value == grid[pos.x][i]) return false;
		}
		// check columns
		for (int i = 0; i < 9; i++) {
			if (value == grid[i][pos.y]) return false;
		}

		// check cells
		int cellX = (pos.x / 3) * 3;
		int cellY = (pos.y / 3) * 3;
		for (int i = cellX; i < cellX + 3; i++) {
			for (int j = cellY; j < cellY + 3; j++) {
				if (grid[i][j] == value) return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
//		final String ANSI_RESET = "\u001B[0m";
//		final String ANSI_RED = "\u001B[31m";
//		final String ANSI_GREY = "\u001B[30m";
		StringBuilder builder = new StringBuilder();
		for (int y = 0; y < grid.length; y++) {
			for (int x = 0; x < grid.length; x++) {
//				if (isCorrect(x, y)) builder.append(ANSI_RESET);
//				else builder.append(ANSI_RED);
//				if (!isEditable(x, y)) builder.append(ANSI_GREY);
				builder.append(grid[x][y]);
				builder.append(" ");
			}
			builder.append("\n");
		}
		return builder.toString();
	}

	@Override
	public SudokuField copy() {
		return new SudokuField(grid);
	}
}
