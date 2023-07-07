package model;


public class KillerField implements Field {
	// Value of the grids is calculated with mod 10
	// Groups is calculated with div 10
	private int[][] grid = new int[9][9];
	private boolean[][] editable = new boolean[9][9];
	private int[] sums = new int[100];
	
	
	
	
	
	public KillerField() {
			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid[i].length; j++) {
					grid[i][j] = 0;
					editable[i][j] = true;
				}
		}
	}
	
	public void set(int x, int y, int value) {
		grid[x][y] = value + (grid[x][y] / 10) * 10;
	}
	
	public int get(int x, int y) {
		return grid[x][y];
	}

	@Override
	public void setEditable(int x, int y, boolean isEditable) {
		// TODO Auto-generated method stub
		editable[x][y] = isEditable;
	}

	@Override
	public boolean isEditable(int x, int y) {
		// TODO Auto-generated method stub
		return editable[x][y];
	}

	@Override
	public boolean isCorrect(int x, int y) {
		// TODO Auto-generated method stub
		int value = grid[x][y] % 10;
		if (value == 0) {
			return true;
		}
		// checking row
		for (int i = 0; i < 9; i++) {
			if (i != y && value != 0 && value == (grid[x][i] % 10)) {
				return false;
			}
		}
		// checking column
		for (int j = 0; j < 9; j++) {
				if (j != x && value != 0 && value == (grid[j][y] % 10)) {
					return false;
				}
		}
		// checking cells
		int cellX = (x / 3) * 3;
			int cellY = (y / 3) * 3;
			for (int i = cellX; i < cellX + 3; i++) {
				for (int j = cellY; j < cellY + 3; j++) {
					if (i != x && j != y && (grid[i][j] % 10) == value) {
						return false;
						}
				}
			}
		
		//checking group 
		int group = getGroup(x,y);
		int sum = 0;
		boolean complete = true;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (group == getGroup(i,j)) {
					if ((grid[i][j] % 10) != 0) {
						sum += grid[i][j] % 10;
					} else {
						complete = false;
					}
				}
			}
		}
		if (complete && sum != getSum(group)) {
			return false;
		} else if (sum > getSum(group)) {
			return false;
		}
		return true;
		}

	@Override
	public boolean isPossible(int x, int y, int value) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public int getGroup(int x, int y) {
		return grid[x][y] / 10;
	}
	
	public void setGroup(int x, int y, int group) {
		grid[x][y] = Integer.valueOf(String.valueOf(group) + String.valueOf(grid[x][y] % 10));
	}
	
	public int getSum(int group) {
		return sums[group];
	}
	
	public void setSum(int group, int sum) {
		sums[group] = sum;
	}
}
	