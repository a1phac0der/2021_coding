package misc;

public class EnglishRuler {
	public static void drawLine(int dashes, int tick) {
		if (dashes >= 1) {
			for (int i = 1; i <= dashes; i++) {
				System.out.print("- ");
			}
		}
		if (tick >= 0) {
			System.out.print(tick);
		}
		System.out.println();
	}

	public static void drawInterval(int dashes) {
		if (dashes >= 1) {
			drawInterval(dashes - 1);
			drawLine(dashes, -1);
			drawInterval(dashes - 1);
		}
	}

	public static void drawRuler(int inches, int majorLength) {
		drawLine(majorLength, 0);
		for (int i = 1; i <= inches; i++) {
			drawInterval(majorLength-1);
			drawLine(majorLength, i);
		}
	}

	public static void main(String[] balls){
		drawRuler(3, 5);
	}
}
