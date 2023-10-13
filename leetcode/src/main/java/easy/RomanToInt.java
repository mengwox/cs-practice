package easy;

import java.util.HashMap;
import java.util.Map;

/**
 * 13.罗马数字转整数
 */
public class RomanToInt {

    public int romanToInt(String s) {
        Map<Character, Integer> container = container();
        int len = s.length();
        int result = 0;
        int preInt = 0;
        for (int i = len - 1; i >= 0; i--) {
            Integer curInt = container.get(s.charAt(i));
            if (preInt > curInt) {
                result -= curInt;
            } else {
                result += curInt;
            }
            preInt = curInt;
        }
        return result;
    }

    private Map<Character, Integer> container() {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        return map;
    }
}
