package model;

import java.util.Optional;
import java.util.Random;

public class Str8tsGenerator {
	
	private Solver solver = new Solver();
	private Random random = new Random();
	
	public Pair<Str8tsField, Str8tsField> generate(int difficulty) {
		Str8tsField field = generateSolved();
		Str8tsField unsolvedField = field.copy();

		int count = 0;
		int limit = 0;
		switch (difficulty) {
		case 1:
			limit = 40;
			break;
		case 2:
			limit = 50;
			break;
		default:
			limit = 30;
			break;
		}
		while (count < limit) {
			int row = random.nextInt(9);
			int col = random.nextInt(9);
			Position pos = new Position(row, col);
			if (unsolvedField.get(pos) != 0) {
				unsolvedField.set(pos, 0);
				count++;
			}
		}
		
		return new Pair<>(field, unsolvedField);
	}
	
	/**
	 * Generates a solved Field by randomly placing black squares and trying to solve it.
	 * 
	 * If the solver fails after MAX_STEPS it starts again.
	 * @returns a solved {@link Str8tsField}
	 */
	public Str8tsField generateSolved() {
		final int MAX_STEPS = 10000;
		final int AMOUNT_BLACKS = 40;
		int tries = 0;
		long startTime = System.nanoTime();

		Str8tsField field;
		Optional<Str8tsField> optField;
		field = new Str8tsField();
		do {
			tries++;
			field = new Str8tsField();
			for (int i = 0; i < AMOUNT_BLACKS; i++) {
				int x = random.nextInt(9);
				int y = random.nextInt(9);
				field.setBlack(new Position(x, y), true);
			}
			removeShortStr8ts(field);
			optField = solver.solve(field, MAX_STEPS);
		} while (optField.isEmpty());
		field = optField.get();
		
		float time = (System.nanoTime() - startTime) / 1000000000f;
		System.out.printf("Str8tsGenerator.generate: generated with %d " + (tries == 1 ? "try" : "tries") + " in %.3fs\n", tries, time);

		for (Position pos : Position.iterateAll()) {
			if (field.isBlack(pos)) {
				for (int i = 1; i <= 9; i++) {
					if (field.isPossible(pos, i)) {
						field.set(pos, i);
						break;
					}
				}
			}
		}
		return field;
	}

	/** Removes neighboring black squares for every str8t with length 1. */
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
