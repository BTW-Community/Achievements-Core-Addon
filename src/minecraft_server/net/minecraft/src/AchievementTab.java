package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AchievementTab {
	
	/** Holds a list of all registered achievements. */
    public List<Achievement> achievementList;
    
    /** Is the smallest column used to display a achievement on the GUI. */
    public int minDisplayColumn;

    /** Is the smallest row used to display a achievement on the GUI. */
    public int minDisplayRow;

    /** Is the biggest column used to display a achievement on the GUI. */
    public int maxDisplayColumn;

    /** Is the biggest row used to display a achievement on the GUI. */
    public int maxDisplayRow;
    
    private String name;
    private int index;
    private int iconID = Item.paper.itemID;
	
    /**
     * @param name is the name of the tab
     */
	public AchievementTab(String name) {
		this.achievementList = new ArrayList<Achievement>();
		this.name = "achievementtab." + name;
		this.index = AchievementTabList.tabList.size();
		AchievementTabList.add(this);
	}
	
	/**
	 * @return untranslated name of the tab
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return index of the tab
	 */
	public int getIndex() {
		return index;
	}
	
	/**
	 * Sets the icon for this tab to the specified item/block id.
	 * 
	 * @param id is the item/block id
	 * @return this
	 */
	public AchievementTab setIcon(int id) {
        this.iconID = id;
        return this;
    }
	
	/**
	 * Sets the icon for this tab to the specified item.
	 * 
	 * @param item is the item to set as the icon
	 * @return this
	 */
	public AchievementTab setIcon(Item item) {
		setIcon(item.itemID);
        return this;
    }
	
	/**
	 * Sets the icon for this tab to the specified block.
	 * 
	 * @param block is the block to set as the block
	 * @return this
	 */
	public AchievementTab setIcon(Block block) {
		setIcon(block.blockID);
        return this;
    }
	
	/**
	 * Adds an Achievement to this tab.
	 * 
	 * @param achievement to add
	 */
	public void add(Achievement achievement) {
		this.achievementList.add(achievement);
	}
	
	public int getIconItemID() {
        return iconID;
    }
	
	public Item getIconItem() {
        return Item.itemsList[this.getIconItemID()];
    }

	public int size() {
        return achievementList.size();
    }
}
