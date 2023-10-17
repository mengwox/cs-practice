package easy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsValidTest {
	private final IsValid solution = new IsValid();

	@Test
	void isValid() {
		assertTrue(solution.isValid("()"));
		assertTrue(solution.isValid("()[]{}"));
		assertTrue(solution.isValid("{()}"));

		assertFalse(solution.isValid("(]"));
		assertFalse(solution.isValid("{]"));
		assertFalse(solution.isValid("{)"));
	}
}