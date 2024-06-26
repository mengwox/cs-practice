package easy;

import java.util.Stack;

/**
 * 20.有效的括号
 */
public class IsValid {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (isRight(ch)) {
                if (stack.isEmpty()) {
                    return false;
                }
                char popCh = stack.pop();
                if (unpair(popCh, ch)) return false;
            } else {
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }

    static boolean unpair(Character pop, Character c) {
        if (pop == '(' && c != ')') {
            return true;
        }
        if (pop == '[' && c != ']') {
            return true;
        }
        return pop == '{' && c != '}';
    }


    private boolean isRight(Character ch) {
        return ch == ')' || ch == ']' || ch == '}';
    }
}
