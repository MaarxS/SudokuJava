package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Str8tsField implements Field {
	// Single digits are white fields
	// Double digits are black fields
	// Value of the black fields is calculated with mod 10
	// 0 means blank fields
	private final int FIELDS_IN_ROW = 9;
	private final int FIELDS_IN_COLUMN = 9;
	private final int FIELDS_IN_GRID = 81;
	private int[][] fields = new int[FIELDS_IN_ROW][FIELDS_IN_COLUMN];
		
	public Str8tsField() {
		for (int i = 0; i < fields.length; i++) {
			for (int j = 0; j < fields[i].length; j++) {
				fields[i][j] = 0;
			}
		}
	}
	
	/** Constructor for copying this object.*/
	private Str8tsField(int[][] fields) {
		for (int x = 0; x < FIELDS_IN_ROW; x++) {
			for (int y = 0; y < FIELDS_IN_COLUMN; y++) {
				this.fields[x][y] = fields[x][y];
			}
		}
	}
	
	@Override
	public void set(Position pos, int value) {
		fields[pos.x][pos.y] = value + (fields[pos.x][pos.y] / 10) * 10;
	}

	@Override
	public int get(Position pos) {
		return fields[pos.x][pos.y] % 10;
	}

	@Override
	public boolean isEditable(Position pos) {
		return fields[pos.x][pos.y] <= 9; // TODO
	}
	
	public void setBlack(Position pos, boolean isBlack) {
		fields[pos.x][pos.y] = fields[pos.x][pos.y] % 10;
		if (isBlack) {
			fields[pos.x][pos.y] += 10;
		}
	}
	
	public boolean isBlack(Position pos) {
		if (fields[pos.x][pos.y] > 9) {
			return true;
		}
		return false;
	}
	

	@Override
	public boolean isCorrect(Position pos) {
		int digit = fields[pos.x][pos.y];
		// checking row
		for (int i = 0; i < FIELDS_IN_ROW; i++) {
			if (i != pos.y && digit != 0 && digit == (fields[pos.x][i] % 10)) {
				return false;
			}
		}
		// checking column
		for (int j = 0; j < FIELDS_IN_COLUMN; j++) {
				if (j != pos.x && digit != 0 && digit == (fields[j][pos.y] % 10)) {
					return false;
				}
		}
		// creating straights 
		List<Integer> verticalStraight = new ArrayList<Integer>();
		List<Integer> horizontalStraight = new ArrayList<Integer>();
		
		int i = pos.y;
		int j = pos.x;
		if (!isBlack(pos)) {
			horizontalStraight.add(fields[pos.x][pos.y]);
			while (i < FIELDS_IN_ROW - 1) {
				if (!isBlack(new Position(pos.x, i+1))) {
					horizontalStraight.add(fields[pos.x][i+1]);
					i++;
				} else {
					break;
				}
			}
			i = pos.y;
			while (i > 0){
				if (!isBlack(new Position(pos.x, i-1))) {
				horizontalStraight.add(fields[pos.x][i-1]);
				i--;
				} else {
					break;
				}
			}
			verticalStraight.add(fields[pos.x][pos.y]);
			while (j < FIELDS_IN_COLUMN -1){
				if (!isBlack(new Position(j+1, pos.y))) {
				verticalStraight.add(fields[j+1][pos.y]);
				j++;
				} else {
					break;
				}
			}
			j = pos.x;
			while (j > 0) {
				if (!isBlack(new Position(j-1, pos.y))) {
				verticalStraight.add(fields[j-1][pos.y]);
				j--;
				} else {
					break;
				}
			}
		}
		horizontalStraight.sort(null);
		verticalStraight.sort(null);
		int countH = Collections.frequency(horizontalStraight, 0);
		int countV = Collections.frequency(verticalStraight, 0);
		int filledFieldsH = horizontalStraight.size() - countH;
		int filledFieldsV = verticalStraight.size() - countV;
		
		
		// checking horizontal straight
		if(countH == 0) {
			for (int n = 1; n < horizontalStraight.size(); n++) {
	            if (horizontalStraight.get(n) - horizontalStraight.get(n - 1) != 1) {
	                return false;
	            }
	        }
		} else if (filledFieldsH > 1 ) {
			int min = horizontalStraight.get(countH);
			int max = Collections.max(horizontalStraight);
			if (max - min > horizontalStraight.size() - 1) {
				return false;
			}
		}
		// checking vertical straight
		if (countV == 0) {
			for (int m = 1; m < verticalStraight.size(); m++) {
	            if (verticalStraight.get(m) - verticalStraight.get(m - 1) != 1) {
	                return false;
	            }
	        }
		} else if (filledFieldsV > 1 ) {
			int min = verticalStraight.get(countV);
			int max = Collections.max(verticalStraight);
			if (max - min > verticalStraight.size() - 1) {
				return false;
			}
		}
		return true;
}

	@Override
	public boolean isPossible(Position pos, int value) {
		int temp = fields[pos.x][pos.y];
		fields[pos.x][pos.y] = value;
		boolean result = isCorrect(pos);
		fields[pos.x][pos.y] = temp;
		return result;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int y = 0; y < fields.length; y++) {
			for (int x = 0; x < fields.length; x++) {
				Position pos = new Position(x, y);
				builder.append(fields[x][y] % 10);
				if (!isEditable(pos)) builder.append("!");
				else if (!isCorrect(pos)) builder.append("?");
				else builder.append(" ");
			}
			builder.append("\n");
		}
		return builder.toString();
	}
	
	@Override
	public Str8tsField copy() {
		return new Str8tsField(fields);
	}
	
	@Override
	public boolean isSolved() {
		for(int i = 0; i < FIELDS_IN_GRID; i++) {
			Position pos = new Position(i % 9, i / 9);
			if(fields[pos.x][pos.y] == 0 && !isBlack(pos)) {
				return false;
			}
		}
		return true;
	}
}