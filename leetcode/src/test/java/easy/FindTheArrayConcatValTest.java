package easy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FindTheArrayConcatValTest {
	private final FindTheArrayConcatVal solution = new FindTheArrayConcatVal();

	@Test
	void improveFindTheArrayConcVal() {
		Assertions.assertEquals(596, solution.improveFindTheArrayConcVal(new int[]{7, 52, 2, 4}));
		Assertions.assertEquals(673, solution.improveFindTheArrayConcVal(new int[]{5, 14, 13, 8, 12}));
	}
}