package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.GL11;

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
	
	public AchievementTab setIcon(int id) {
        this.iconID = id;
        return this;
    }
	
	public AchievementTab setIcon(Item item) {
		setIcon(item.itemID);
        return this;
    }
	
	public AchievementTab setIcon(Block block) {
		setIcon(block.blockID);
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

	public int size() {
        return achievementList.size();
    }
	
	protected Icon genAchievementIcon(int mapX, int mapY, int windowX, int windowY)
    {   
    	int var10 = windowX + 288 >> 4;
        int var11 = windowY + 288 >> 4;
        Random random = new Random();
        int y1;
        
        float brightness = 0.6F - (float)(var11 + mapY) / 25.0F * 0.3F;
        GL11.glColor4f(brightness, brightness, brightness, 1.0F);
    	
    	random.setSeed((long)(1234 + var10 + mapX));
        random.nextInt();
        y1 = random.nextInt(1 + var11 + mapY) + (var11 + mapY) / 2;
        Icon icon = Block.sand.getIcon(0, 0);

        if (y1 <= 37 && var11 + mapY != 35)
        {
            if (y1 == 22)
            {
                if (random.nextInt(2) == 0)
                {
                    icon = Block.oreDiamond.getIcon(0, 0);
                }
                else
                {
                    icon = Block.oreRedstone.getIcon(0, 0);
                }
            }
            else if (y1 == 10)
            {
                icon = Block.oreIron.getIcon(0, 0);
            }
            else if (y1 == 8)
            {
                icon = Block.oreCoal.getIcon(0, 0);
            }
            else if (y1 > 4)
            {
                icon = Block.stone.getIcon(0, 0);
            }
            else if (y1 > 0)
            {
                icon = Block.dirt.getIcon(0, 0);
            }
        }
        else
        {
            icon = Block.bedrock.getIcon(0, 0);
        }
        return icon;
    }
}
