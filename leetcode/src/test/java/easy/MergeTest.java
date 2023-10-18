package easy;

import org.junit.jupiter.api.Test;

class MergeTest {
	private final Merge solution = new Merge();

	@Test
	void merge() {
		solution.merge(new int[]{1, 2, 3, 0, 0, 0}, 3, new int[]{2, 5, 6}, 3);
		solution.merge(new int[]{4, 5, 6, 0, 0, 0}, 3, new int[]{1, 2, 3}, 3);
	}
}