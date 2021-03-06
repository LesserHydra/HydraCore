package com.lesserhydra.bukkitutil;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;


public final class ExpUtils {
	
	/**
	 * Calculates the amount of exp needed to reach the given level.
	 * @param currentLevel Current level
	 * @return See above
	 */
	public static int calculateXpFromLevel(int currentLevel) {
		if (currentLevel < 17) return currentLevel*currentLevel + 6*currentLevel;
		if (currentLevel < 32) return (int) (2.5*currentLevel*currentLevel - 40.5*currentLevel + 360);
		return (int) (4.5*currentLevel*currentLevel - 162.5*currentLevel + 2220);
	}
	
	/**
	 * Calculates the amount of exp needed to move from the current level to the next.
	 * @param currentLevel Current level
	 * @return See above
	 */
	public static int calculateXpForNextLevel(int currentLevel) {
		if (currentLevel < 16) return 2*currentLevel + 7;
		if (currentLevel < 31) return 5*currentLevel - 38;
		return 9*currentLevel - 158;
	}
	
	/**
	 * Calculates the amount of exp needed to reach the given level and progress.
	 * @param currentLevel Current level
	 * @param progress Progress towards the next level, 0-1 inclusive
	 * @return See above
	 */
	public static int calculateXpFromProgress(int currentLevel, double progress) {
		return (int) (progress*calculateXpForNextLevel(currentLevel));
	}
	
	public static Pair<Integer, Float> getLevelAndProgress(int totalExp) {
		int level = 0;
		int remaining = totalExp;
		float progress;
		while (true) {
			int forNext = calculateXpForNextLevel(level);
			if (remaining < forNext) {
				progress = (float)remaining / (float)forNext;
				break;
			}
			++level;
			remaining -= forNext;
		}
		
		return new ImmutablePair<>(level, progress);
	}
	
}
