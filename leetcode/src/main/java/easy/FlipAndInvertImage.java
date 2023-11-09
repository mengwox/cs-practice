package easy;

/**
 * 832.翻转图像
 */
public class FlipAndInvertImage {
	public int[][] flipAndInvertImage(int[][] image) {
		int len = image.length;
		for (int i = 0; i < len; i++) {
			int left = 0, right = len - 1;
			while (left <= right) {
				flipAndInvert(image[i], left++, right--);
			}
		}
		return image;
	}

	//翻转的同时, 使用与1进行`异或`位运算 以实现反转
	//1与1 异或 --> 0, 0与1 异或--> 1
	private void flipAndInvert(int[] ele, int left, int right) {
		int temp = ele[left];
		ele[left] = ele[right] ^ 1;
		ele[right] = temp ^ 1;
	}
}
