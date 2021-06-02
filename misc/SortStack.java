import java.util.Stack;

public class SortStack {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(9);
        stack.push(3);
        stack.push(2);
        stack.push(6);
        System.out.println(stack);
        sort(stack);
        System.out.println(stack);
    }

    public static void sort(Stack<Integer> stack) {
        if (!stack.isEmpty()){
            int elem = stack.pop();
            sort(stack);
            sortedInsert(stack, elem);
        }
    }

    private static void sortedInsert(Stack<Integer> stack, int elem){
        if (stack.isEmpty() || elem > stack.peek())
            stack.push(elem);
        else {
            int current = stack.pop();
            sortedInsert(stack, elem);
            stack.push(current);
        }
    }
}
