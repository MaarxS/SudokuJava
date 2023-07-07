package model;

import java.util.ArrayList;
import java.util.List;

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
	/** Constructor for copying this object.*/
	private KillerField(int[][] fields) {
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				this.grid[x][y] = fields[x][y];
			}
		}
	}
	
	public void set(Position pos, int value) {
		grid[pos.x][pos.y] = value + (grid[pos.x][pos.y] / 10) * 10;
	}
	
	public int get(Position pos) {
		return grid[pos.x][pos.y];
	}

	@Override
	public boolean isEditable(Position pos) {
		// TODO Auto-generated method stub
		return editable[pos.x][pos.y];
	}

	@Override
	public boolean isCorrect(Position pos) {
		// TODO Auto-generated method stub
		int value = grid[pos.x][pos.y] % 10;
		if (value == 0) {
			return true;
		}
		// checking row
		for (int i = 0; i < 9; i++) {
			if (i != pos.y && value != 0 && value == (grid[pos.x][i] % 10)) {
				return false;
			}
		}
		// checking column
		for (int j = 0; j < 9; j++) {
				if (j != pos.x && value != 0 && value == (grid[j][pos.y] % 10)) {
					return false;
				}
		}
		// checking cells
		int cellX = (pos.x / 3) * 3;
			int cellY = (pos.y / 3) * 3;
			for (int i = cellX; i < cellX + 3; i++) {
				for (int j = cellY; j < cellY + 3; j++) {
					if (i != pos.x && j != pos.y && (grid[i][j] % 10) == value) {
						return false;
						}
				}
			}
		
		//checking group 
		int group = getGroup(pos);
		if (group == 0) {
			return true;
		}
		int sum = 0;
		boolean complete = true;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				Position groupPosition = new Position(i, j);
				if (group == getGroup(groupPosition)) {
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
	public boolean isPossible(Position pos, int value) {
		int temp = grid[pos.x][pos.y];
		grid[pos.x][pos.y] = value;
		boolean result = isCorrect(pos);
		grid[pos.x][pos.y] = temp;
		System.out.println(result);
		return result;
	}
	
	public int getGroup(Position pos) {
		return grid[pos.x][pos.y] / 10;
	}
	
	public void setGroup(Position pos, int group) {
		grid[pos.x][pos.y] = Integer.valueOf(String.valueOf(group) + String.valueOf(grid[pos.x][pos.y] % 10));
	}
	
	public int getSum(int group) {
		return sums[group - 1];
	}
	
	public void setSum(int group, int sum) {
		sums[group - 1] = sum;
	}
	
	public int getGroupCount() {
		for (int i = 0; i < sums.length; i++) {
			if (sums[i] == 0) return i;
		}
		return sums.length;
	}
	
	public List<Position> getMembers(int group) {
		List<Position> list = new ArrayList<>();
		for (Position pos : Position.iterateAll()) {
			if (getGroup(pos) == group) {
				list.add(pos);
			}
		}
		return list;
	}
	@Override
	public KillerField copy() {
		return new KillerField(grid);
	}
	
	@Override
	public boolean isSolved(){
		return false;
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
	