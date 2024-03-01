package easy;

/**
 * 14.最长公共前缀
 */
public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        String result = strs[0];
        for (int i = 1; i < strs.length; i++) {
            String tmp = getCommonPrefix(result, strs[i]);
            if (tmp.length() < result.length()) {
                if (tmp.isEmpty()) {
                    return tmp;
                }
                result = tmp;
            }
        }
        return result;
    }

    private String getCommonPrefix(String str1, String str2) {
        int size = Math.min(str1.length(), str2.length());
        if (size == 0) {
            return "";
        }
        int i;
        for (i = 0; i < size; i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                break;
            }
        }
        return str1.substring(0, i);
    }
}
