package com.anikhil.scrumsphere.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntegerSumTest {

	@Test
	void sum() {
		IntegerSum integerSum = new IntegerSum();
		assertEquals(5, integerSum.sum(2, 3));
		assertEquals(0, integerSum.sum(-2, 2));
		assertEquals(-5, integerSum.sum(-2, -3));
	}
}