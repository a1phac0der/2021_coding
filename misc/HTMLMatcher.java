package misc;

import java.util.Stack;

public class HTMLMatcher {
	public static boolean isHTMLMatched(String html) {
		Stack<String> buffer = new LinkedStack<String>();
		int j = html.indexOf("<");
		while (j != -1) {
			int k = html.indexOf(">", j + 1);
			if (k == -1)
				return false;
			String tag = html.substring(j + 1, k);
			if (!tag.startsWith("/")){
				System.out.println("Pushing: " + tag);
				buffer.push(tag);
			}
			else {
				if (buffer.empty())
					return false;
				String pop = buffer.pop();
				System.out.println("Popping: " + pop);
				if (!tag.substring(1).equals(pop))
					return false;
			}
			j = html.indexOf("<", k+1);
		}
		return buffer.empty();
	}

	public static void main(String[] args) {
		System.out.println(isHTMLMatched("<html><body>sdsf<body></html>"));
		
	}
}
