package model;

import java.util.Random;

public class Str8tsGenerator {
	
	public static final int DIFFICULTY_EASY = 0;
	public static final int DIFFICULTY_NORMAL = 1;
	public static final int DIFFICULTY_DIFFICULT = 2;
	private Solver solver = new Solver();
	private Random random = new Random();
	
	
	public Str8tsField generate(int difficulty) {
		Str8tsField field = new Str8tsField();
		for (int i = 0; i < 12; i++) {
			int x = random.nextInt(9);
			int y = random.nextInt(9);
			field.setBlack(new Position(x, y), true);
		}
		solver.solve(field);
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				Position pos = new Position(x, y);
				if (field.isBlack(pos) && random.nextInt(3) == 0) {
					int i;
					for (i = 1; i <= 9 && field.isPossible(pos, i); i++);
					field.set(pos, i);
				}
			}
		}
		return field;
	}
}
