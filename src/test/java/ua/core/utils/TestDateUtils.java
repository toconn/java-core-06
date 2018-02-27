package ua.core.utils;

import static org.junit.Assert.*;
import java.util.Date;
import org.junit.Test;


public class TestDateUtils {

	@Test
	public void isWithin() {
	
		assertTrue (DateUtils.isWithin (new Date (1357020000000L), new Date (1357020000000L), new Date (1357020000000L)));		// Equals
		assertTrue (DateUtils.isWithin (new Date (1357020000000L), new Date (1357020000000L), new Date (1357030000000L)));		// Equals Start
		assertTrue (DateUtils.isWithin (new Date (1357030000000L), new Date (1357020000000L), new Date (1357030000000L)));		// Equals End
		assertTrue (DateUtils.isWithin (new Date (1357025000000L), new Date (1357020000000L), new Date (1357030000000L)));		// Between

		assertFalse (DateUtils.isWithin (new Date (1357010000000L), new Date (1357020000000L), new Date (1357030000000L)));		// Before
		assertFalse (DateUtils.isWithin (new Date (1357040000000L), new Date (1357020000000L), new Date (1357030000000L)));		// After
	}
}
