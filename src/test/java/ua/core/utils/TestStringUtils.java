package ua.core.utils;

import static org.junit.Assert.*;

import org.junit.*;

public class TestStringUtils {
	
	@Test
	public void isEqualIgnoreCase() {
		
		assertTrue (StringUtils.isEqualIgnoreCase ("ABC", "ABC"));
		assertTrue (StringUtils.isEqualIgnoreCase ("ABC", "abc"));
		assertTrue (StringUtils.isEqualIgnoreCase ("abcd", "ABcd"));
		assertTrue (StringUtils.isEqualIgnoreCase ("AbCd", "aBcD"));
		assertTrue (StringUtils.isEqualIgnoreCase ("", ""));
		assertTrue (StringUtils.isEqualIgnoreCase (null, null));
		
		assertFalse (StringUtils.isEqualIgnoreCase ("ABC", "ABCD"));
		assertFalse (StringUtils.isEqualIgnoreCase ("ABC", "abc "));
		assertFalse (StringUtils.isEqualIgnoreCase ("", null));
	}
}
