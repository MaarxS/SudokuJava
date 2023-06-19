package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Str8tsField implements Field {
	// Single digits are white fields
	// Double digits are black fields
	// Value of the black fields is calculated with mod 10
	// 0 means blank fields
		private int[][] fields=
		{{10,10,1,2,10,7,6,10,18},
		{4,3,2,1,10,6,8,7,5},
		{5,4,10,3,2,19,7,8,6},
		{10,5,4,10,3,2,10,16,7},
		{10,10,5,6,10,1,2,3,4},
		{6,7,3,5,4,10,1,2,10},
		{7,8,10,10,6,5,3,4,10},
		{13,9,8,17,5,4,10,1,2},
		{10,6,9,8,7,10,4,5,3}};
		
		private int[][] fieldsEmpty=
			{{10,10,0,2,10,7,0,10,18},
				{4,0,0,0,10,0,8,0,0},
				{0,0,10,0,2,19,0,0,0},
				{10,0,0,10,3,2,10,16,7},
				{10,10,0,0,10,1,0,3,4},
				{0,0,3,0,0,10,0,2,10},
				{7,0,10,10,0,0,0,0,10},
				{13,9,0,17,0,0,10,1,0},
				{10,0,9,0,0,10,4,0,0}};

//	private int[][] fields = new int[9][9];
//	public Str8tsField() {
//		for (int i = 0; i < fields.length; i++) {
//			for (int j = 0; j < fields[i].length; j++) {
//				fields[i][j] = 0;
//			}
//		}
//	}
	
	@Override
	public void set(int x, int y, int value) {
		fields[x][y] = value;
	}

	@Override
	public int get(int x, int y) {
		return fields[x][y];
	}

	@Override
	public boolean isEditable(int x, int y) {
		if (fields[x][y] == 0) {
			return true;
		} 
		return false;
	}
	
	public boolean isBlack(int x, int y) {
		if (fields[x][y] > 9) {
			return true;
		}
		return false;
	}
	

	@Override
	public boolean isCorrect(int x, int y) {
		int digit = fields[x][y];
		// checking row
		for (int i = 0; i < 9; i++) {
			if (i != y && digit != 0 && digit == (fields[x][i] % 10)) {
				return false;
			}
		}
		// checking column
		for (int j = 0; j < 9; j++) {
				if (j != x && digit != 0 && digit == (fields[j][y] % 10)) {
					return false;
				}
		}
		// creating straights 
		List<Integer> verticalStraight = new ArrayList<Integer>();
		List<Integer> horizontalStraight = new ArrayList<Integer>();

		int i = y;
		int j = x;
		if (!isBlack(x,y)) {
			horizontalStraight.add(fields[x][y]);
			while (i < 8) {
				if (!isBlack(x, i+1)) {
					horizontalStraight.add(fields[x][i+1]);
					i++;
				} else {
					break;
				}
			}
			i = y;
			while (i > 0){
				if (!isBlack(x, i-1)) {
				horizontalStraight.add(fields[x][i-1]);
				i--;
				} else {
					break;
				}
			}
			verticalStraight.add(fields[x][y]);
			while (j < 8){
				if (!isBlack(j+1, y)) {
				verticalStraight.add(fields[j+1][y]);
				j++;
				} else {
					break;
				}
			}
			j = x;
			while (j > 0) {
				if (!isBlack(j-1, y)) {
				verticalStraight.add(fields[j-1][y]);
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

}