package model;

import java.util.Random;

public class FieldGenerator {
	
	private Random random = new Random();
	
	public Field generate(Field field) {
		for (int number = 1; number <= 9; number++) {
			int x;
			int y;
			do {
				x = random.nextInt(9);
				y = random.nextInt(9);
			} while (field.get(x, y) != 0);
			field.set(x, y, number);
		}
		new Solver(field).solve();
		return field;
	}
}
