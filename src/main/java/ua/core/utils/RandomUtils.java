package ua.core.utils;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {
	
	/**
	 * Returns a random number between 0 and max inclusive.
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static int randomInt (int max) {
		return ThreadLocalRandom.current().nextInt (max + 1);
	}
	
	/**
	 * Returns a random number between min and max inclusive.
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static int randomInt (int min, int max) {
		return ThreadLocalRandom.current().nextInt (min, max + 1);
		// return min + (int) (Math.random() * ((max - min) + 1));
	}
	
	public static long randomLong (long max) {
		return (long) (new Random().nextDouble() * (max + 1));
	}

}