package misc;

public class NQueens {
	public static int[] getQueensPositions(int size) {
		int[] queenPosition = new int[size];
		for (int i = 0; i < size; i++) {
			queenPosition[i] = -1;
		}
		System.out.println("\nQueens arrangement successful?: " + safelyArrangeQueensOnBoard(0, size, queenPosition));
		return queenPosition;
	}

	public static boolean safelyArrangeQueensOnBoard(int current, int size, int[] queenPosition) {
		if (current == size)
			return true;
		else {
			for (int i = 0; i < size; i++) {
				System.out.println("\nTrying " + i + " at position: " + current);
				queenPosition[current] = i;
				for(int j=0; j<queenPosition.length; j++)
					System.out.print(queenPosition[j] + " ");
				if (!hasconflict(queenPosition, current)) {
					boolean done = safelyArrangeQueensOnBoard(current + 1, size, queenPosition);
					if (done)
						return true;
				}
			}
//			for(int i=0; i<queenPosition.length; i++)
//				System.out.print(queenPosition[i] + " ");
//			System.out.println("\n" + current + " : " + queenPosition[current]);
			return false;
		}

	}

	public static boolean hasconflict(int[] queenPosition, int current) {
		for (int i = 0; i < current; i++) {
			if (queenPosition[i] == queenPosition[current])
				return true;
			if ((current - i) == Math.abs(queenPosition[current] - queenPosition[i]))
				return true;
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println("Queens positions so that they can't kill each other:-\n");
		int[] positions = getQueensPositions(8);
		for (int i=0; i< positions.length; i++)
			System.out.println(positions[i] + " ");
	}
}
