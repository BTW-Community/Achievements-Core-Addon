package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;

public class AchievementTab {
	
	/** Holds a list of all registered achievements. */
    public List<Achievement> achievementList;
    
    /** Is the smallest column used to display a achievement on the GUI. */
    public static int minDisplayColumn;

    /** Is the smallest row used to display a achievement on the GUI. */
    public static int minDisplayRow;

    /** Is the biggest column used to display a achievement on the GUI. */
    public static int maxDisplayColumn;

    /** Is the biggest row used to display a achievement on the GUI. */
    public static int maxDisplayRow;
    
    private String name;
    private int index;
    private int iconID = Item.paper.itemID;
	
	public AchievementTab(String name) {
		this.achievementList = new ArrayList<Achievement>();
		this.name = "achievementtab." + name;
		this.index = AchievementTabList.tabList.size();
		AchievementTabList.add(this);
	}
	
	public String getName() {
		return name;
	}
	
	public int getIndex() {
		return index;
	}
	
	public AchievementTab setIcon(int iconID) {
        this.iconID = iconID;
        return this;
    }
	
	public void add(Achievement achievement) {
		this.achievementList.add(achievement);
	}
	
	public int getIconItemID() {
        return iconID;
    }
	
	public Item getIconItem() {
        return Item.itemsList[this.getIconItemID()];
    }

	public void printSize() {
        System.out.println(StatCollector.translateToLocal(name) + ": " + achievementList.size() + " achievements");
    }
}
