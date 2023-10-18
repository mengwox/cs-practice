package easy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsHappyTest {
	private final IsHappy solution = new IsHappy();

	@Test
	void isHappy() {
		assertTrue(solution.isHappy(19));
		assertFalse(solution.isHappy(2));
	}
}