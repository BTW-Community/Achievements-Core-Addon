package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;

public class AchievementTab {
	
	/** Holds a list of all registered achievements. */
    public static List achievementList = new ArrayList();
    
    private static String name;
    private static int index;
    private static int iconID = Item.paper.itemID;
	
	public AchievementTab(String name) {
		this.name = name;
		this.index = AchievementTabList.tabList.size();
		AchievementTabList.tabList.add(this);
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getIndex() {
		return this.index;
	}
	
	public AchievementTab setIconItemID(int iconID) {
        this.iconID = iconID;
        return this;
    }
	
	public int getIconItemID() {
        return iconID;
    }
	
	public Item getIconItem() {
        return Item.itemsList[this.getIconItemID()];
    }

	public void printSize() {
        System.out.println(this.name + ": " + achievementList.size() + " achievements");
    }
}
