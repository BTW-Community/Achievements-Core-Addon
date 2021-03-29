package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;

public class AchievementTab {
	
	/** Is the smallest column used to display a achievement on the GUI. */
    public static int minDisplayColumn;

    /** Is the smallest row used to display a achievement on the GUI. */
    public static int minDisplayRow;

    /** Is the biggest column used to display a achievement on the GUI. */
    public static int maxDisplayColumn;

    /** Is the biggest row used to display a achievement on the GUI. */
    public static int maxDisplayRow;
	
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
