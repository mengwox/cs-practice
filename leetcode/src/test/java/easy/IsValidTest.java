package easy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsValidTest {

	@Test
	void isValid() {
		IsValid solution = new IsValid();
		assertTrue(solution.isValid("()"));
		assertTrue(solution.isValid("()[]{}"));
		assertTrue(solution.isValid("{()}"));

		assertFalse(solution.isValid("(]"));
		assertFalse(solution.isValid("{]"));
		assertFalse(solution.isValid("{)"));
	}
}