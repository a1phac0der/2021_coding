package misc;

import java.util.Stack;

public class ExpressionMatch {
	public static boolean isMatched(String expression) {
		final String opening = "[{(";
		final String closing = "]})";

		Stack<Character> buffer = new LinkedStack<Character>();

		for (char ch : expression.toCharArray()) {
			if (opening.indexOf(ch) != -1) {
				buffer.push(ch);
			}
			if (closing.indexOf(ch) != -1) {
				int closing_index = closing.indexOf(ch);
				Character opening_char = buffer.pop();
				if (opening_char == null) {
					return false;
				}
				int opening_index = opening.indexOf(opening_char);
				if (opening_index != closing_index)
					return false;
			}
		}
		return buffer.empty();
	}

	public static void main(String[] args) {
		System.out.println(isMatched("(dsffd)[dsff][[]]{[()]}"));
	}
}
