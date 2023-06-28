package model;

import java.util.Random;

public class Str8tsGenerator {
	
	private Solver solver = new Solver();
	private Random random = new Random();
	
	public Str8tsField generate(int difficulty) {
		Str8tsField field = new Str8tsField();
		for (int i = 0; i < 40; i++) {
			int x = random.nextInt(9);
			int y = random.nextInt(9);
			field.setBlack(new Position(x, y), true);
		}
		System.out.println(field);
		removeShortStr8ts(field);
		System.out.println(field);
		field = (Str8tsField) solver.solve(field, 3000000);
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				Position pos = new Position(x, y);
				if (field.isBlack(pos) && random.nextInt(3) == 0) {
					int i;
					for (i = 1; i <= 9 && field.isPossible(pos, i); i++);
					// field.set(pos, i);
				}
			}
		}
		System.out.println(field);
		return field;
	}
	
	private void removeShortStr8ts(Str8tsField field) {
		for (int x = 0; x < 9; x++) {
			int length = 0;
			for (int y = 0; y < 9; y++) {
				Position pos = new Position(x, y);
				if (field.isBlack(pos)) {
					// if a str8t with length 1 is found
					if (length == 1) {
						field.setBlack(pos, false);
						length++;
					} else {
						length = 0;
					}
				} else if (y == 8 && length == 0) {
					field.setBlack(new Position(x, y - 1), false);
				} else {
					length++;
				}
			}
		}
		for (int y = 0; y < 9; y++) {
			int length = 0;
			for (int x = 0; x < 9; x++) {
				Position pos = new Position(x, y);
				if (field.isBlack(pos)) {
					// if a str8t with length 1 is found
					if (length == 1) {
						field.setBlack(pos, false);
						length++;
					} else {
						length = 0;
					}
				} else if (x == 8 && length == 0) {
					field.setBlack(new Position(x - 1, y), false);
				} else {
					length++;
				}
			}
		}
	}
}
