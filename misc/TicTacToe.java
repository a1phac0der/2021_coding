package misc;

public class TicTacToe {
	int[][] board = new int[3][3];
	int X = -1;
	int O = 1;
	int EMPTY = 0;
	int player;

	public TicTacToe() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = EMPTY;
			}
		}
		player = X;
	}

	public void putMark(int x, int y) {
		if (x < 0 || x > 2 || y < 0 || y > 2) {
			throw new IllegalArgumentException("Invalid cell");
		}
		if (board[x][y] != EMPTY) {
			throw new IllegalArgumentException("Cell is already filled");
		}
		board[x][y] = player;
		player = -player;
	}

	public boolean isWinner(int mark) {
		if (board[0][0] + board[0][1] + board[1][2] == mark * 3 || board[1][0] + board[1][1] + board[1][2] == mark * 3
				|| board[2][0] + board[2][1] + board[2][2] == mark * 3
				|| board[0][0] + board[1][1] + board[2][2] == mark * 3
				|| board[0][2] + board[1][1] + board[2][0] == mark * 3)
			return true;
		else
			return false;
	}

	public String winner() {
		if (isWinner(X))
			return "X";
		else if (isWinner(O))
			return "O";
		else
			return "Tie";
	}

	public void printBoard() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == X)
					System.out.print("X");
				else if (board[i][j] == O)
					System.out.print("O");
				else
					System.out.println(" ");
					if (j < 2)
						System.out.print(" | ");
			}
			if (i < 2)
				System.out.print("\n-------------------\n");
		}
	}

	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();
		game.putMark(0, 0);
		game.putMark(0, 1);
		game.putMark(0, 2);
		game.putMark(1, 0);
		game.putMark(1, 1);
		game.putMark(1, 2);
		game.putMark(2, 0);
		game.putMark(2, 1);
		game.putMark(2, 2);
		System.out.println(game.winner());
		game.printBoard();
	}
}
