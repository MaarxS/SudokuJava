package model;


public class SudokuField implements Field {

	private int[][] grid = new int[9][9];
	private boolean[][] editable = new boolean[9][9];
			
	public SudokuField() {
		for (int x = 0; x < editable.length; x++) {
			for (int y = 0; y < editable.length; y++) {
				grid[x][y] = 0;
				editable[x][y] = false;
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
		// check 3x3 cells
		int cellX = (x / 3) * 3;
		int cellY = (y / 3) * 3;
		for (int i = cellX; i < cellX + 3; i++) {
			for (int j = cellY; i < cellY + 3; j++) {
				if (grid[i][j] == value) {
					grid[x][y] = value;
					return false;
				}
			}
		}
		return true;
	}

}
