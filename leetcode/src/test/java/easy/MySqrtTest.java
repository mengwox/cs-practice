package easy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MySqrtTest {
	private static final MySqrt SOLUTION = new MySqrt();

	@Test
	void mySqrt() {
		System.out.println(SOLUTION.mySqrt(5));
		System.out.println(SOLUTION.mySqrt(9));
		System.out.println(SOLUTION.mySqrt(10));
		System.out.println(SOLUTION.mySqrt(12));
		System.out.println(SOLUTION.mySqrt(17));
	}
}