package medium;

// 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
//
//
//
// 示例 1:
//
//
// 输入: s = "abcabcbb"
// 输出: 3
// 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
//
//
// 示例 2:
//
//
// 输入: s = "bbbbb"
// 输出: 1
// 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
//
//
// 示例 3:
//
//
// 输入: s = "pwwkew"
// 输出: 3
// 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
//
//
//
//
// 提示：
//
//
// 0 <= s.length <= 5 * 10⁴
// s 由英文字母、数字、符号和空格组成
//
// Related Topics 哈希表 字符串 滑动窗口 👍 7230 👎 0


import java.util.ArrayList;
import java.util.List;

// leetcode submit region begin(Prohibit modification and deletion)
public class LengthOfLongestSubstring {
    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println(new LengthOfLongestSubstring().lengthOfLongestSubstring(s));
    }

    public int lengthOfLongestSubstring(String s) {
        // 双指针,滑动窗口
        int head = 0;
        int end = 0;
        List<Character> list = new ArrayList<>();
        int size = 0;
        while (end < s.length()) {
            char val = s.charAt(end);
            if (list.contains(val)) {
                //list中已存在,head开始检索并去除list中的元素
                size = Math.max(list.size(), size);
                while (head < end) {
                    Character headVal = s.charAt(head++);
                    list.remove(headVal);
                    if (headVal == val) {
                        break;
                    }
                }
            }
            list.add(val);
            end++;
        }
        return Math.max(list.size(), size);
    }
}
// leetcode submit region end(Prohibit modification and deletion)
