package misc;

public class NQueensSolution {
	public static int[] placeQueens(int queens) {
		int[] positions = new int[queens];
		tryPositions(positions, 0, new Trial());
		return positions;
	}
	
	static class Trial{
		int index=1;
	}

	private static boolean tryPositions(int[] positions, int current, Trial trial) {
		if (current >= positions.length)
			return true;
		for (int value = 0; value < positions.length; value++) {
			if (!hasConflict(positions, current, value)) {
				positions[current] = value;
				System.out.print("\nTrial: " + (trial.index++)  + "\n");
				for (int i = 0; i < positions.length; i++)
					System.out.print(positions[i] + "  ");
				boolean done = tryPositions(positions, current + 1, trial);
				if (done)
					return true;
			}
		}
		return false;
	}

	private static boolean hasConflict(int[] positions, int current, int value) {
		for (int i = 0; i < current; i++) {
			if (positions[i] == value)
				return true;
			if (current - i == Math.abs(value - positions[i]))
				return true;
		}
		return false;

	}

	public static void main(String[] args) {
		int[] positions = placeQueens(30);
		System.out.println("\nFinal result:");
		for (int i = 0; i < positions.length; i++)
			System.out.print(positions[i] + "  ");
	}
}
