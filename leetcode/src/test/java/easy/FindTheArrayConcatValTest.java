package easy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FindTheArrayConcatValTest {
	@Test
	void improveFindTheArrayConcVal() {
		FindTheArrayConcatVal test = new FindTheArrayConcatVal();

		Assertions.assertEquals(596, test.improveFindTheArrayConcVal(new int[]{7, 52, 2, 4}));
		Assertions.assertEquals(673, test.improveFindTheArrayConcVal(new int[]{5, 14, 13, 8, 12}));
	}
}