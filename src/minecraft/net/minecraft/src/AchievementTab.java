package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;

public class AchievementTab {
	
	/** Holds a list of all registered achievements. */
    public static List achievementList = new ArrayList();
    
    public static String name;
	
	public AchievementTab(String name) {
		this.name = name;
		AchievementTabList.tabList.add(this);
	}

	public void printSize() {
        System.out.println(this.name + ": " + achievementList.size() + " achievements");
    }
}
