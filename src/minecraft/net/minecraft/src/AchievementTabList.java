package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;

public class AchievementTabList {
	/** Holds a list of all registered achievement categories. */
    public static List<AchievementTab> tabList = new ArrayList<AchievementTab>();
    /** Counts the number of id's */
    public static int counter = 0;
    
	public static void add(AchievementTab achievementTab) {
		tabList.add(achievementTab);
	}
}
