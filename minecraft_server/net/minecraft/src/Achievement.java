package net.minecraft.src;

import net.minecraft.src.AchievementsCore;

public class Achievement extends StatBase
{
	static {
		AchievementsCore.getInstance();
	}
	
    /**
     * Is the column (related to center of achievement gui, in 24 pixels unit) that the achievement will be displayed.
     */
    public final int displayColumn;

    /**
     * Is the row (related to center of achievement gui, in 24 pixels unit) that the achievement will be displayed.
     */
    public final int displayRow;

    /**
     * Holds the parent achievements, that must be taken before this achievement is avaiable.
     */
    public final Achievement parentAchievement;
    public final Achievement[] parentAchievements;

    /**
     * Holds the description of the achievement, ready to be formatted and/or displayed.
     */
    private final String achievementDescription;

    /**
     * Holds the ItemStack that will be used to draw the achievement into the GUI.
     */
    public final ItemStack theItemStack;

    /**
     * Special achievements have a 'spiked' (on normal texture pack) frame, special achievements are the hardest ones to
     * achieve.
     */
    private boolean isSpecial;
    
    public Achievement(String name, int displayColumn, int displayRow, Item item, Achievement... parentAchievements)
    {
        this(0, name, displayColumn, displayRow, new ItemStack(item), parentAchievements);
    }

    public Achievement(String name, int displayColumn, int displayRow, Block block, Achievement... parentAchievements)
    {
        this(0, name, displayColumn, displayRow, new ItemStack(block), parentAchievements);
    }

    public Achievement(int par1, String par2Str, int par3, int par4, Item par5Item, Achievement... parentAchievements)
    {
        this(par1, par2Str, par3, par4, new ItemStack(par5Item), parentAchievements);
    }

    public Achievement(int par1, String par2Str, int par3, int par4, Block par5Block, Achievement... parentAchievements)
    {
        this(par1, par2Str, par3, par4, new ItemStack(par5Block), parentAchievements);
    }

    public Achievement(int id, String name, int displayColumn, int displayRow, ItemStack theItemStack, Achievement... parentAchievements)
    {
        super(AchievementTabList.counter++, "achievement." + name);
        this.theItemStack = theItemStack;
        this.achievementDescription = "achievement." + name + ".desc";
        this.displayColumn = displayColumn;
        this.displayRow = displayRow;

        this.parentAchievements = parentAchievements;
        this.parentAchievement = parentAchievements[0];
    }

    /**
     * Indicates whether or not the given achievement or statistic is independent (i.e., lacks prerequisites for being
     * update).
     */
    public Achievement setIndependent()
    {
        this.isIndependent = true;
        return this;
    }

    /**
     * Special achievements have a 'spiked' (on normal texture pack) frame, special achievements are the hardest ones to
     * achieve.
     */
    public Achievement setSpecial()
    {
        this.isSpecial = true;
        return this;
    }
    
    /**
     * Adds the achievement on the internal list of registered achievements, also, it's check for duplicated id's.
     */
    public Achievement registerAchievement()
    {
        super.registerStat();
        AchievementList.achievementList.add(this);
        return this;
    }
    
    /**
     * Registers an achievement to a custom tab.
     * 
     * @param tab is the custom tab to register to
     * @return this
     */
    public Achievement registerAchievement(AchievementTab tab)
    {
        super.registerStat();
        tab.add(this);
        
        if (this.displayColumn < tab.minDisplayColumn)
        {
        	tab.minDisplayColumn = this.displayColumn;
        }

        if (this.displayRow < tab.minDisplayRow)
        {
        	tab.minDisplayRow = this.displayRow;
        }

        if (this.displayColumn > tab.maxDisplayColumn)
        {
        	tab.maxDisplayColumn = this.displayColumn;
        }

        if (this.displayRow > tab.maxDisplayRow)
        {
        	tab.maxDisplayRow = this.displayRow;
        }
        return this;
    }

    /**
     * Register the stat into StatList.
     */
    public StatBase registerStat()
    {
        return this.registerAchievement();
    }
    
    /**
     * Register the stat into StatList.
     */
    public StatBase registerStat(AchievementTab tab)
    {
        return this.registerAchievement(tab);
    }

    /**
     * Initializes the current stat as independent (i.e., lacking prerequisites for being updated) and returns the
     * current instance.
     */
    public StatBase initIndependentStat()
    {
        return this.setIndependent();
    }
}
