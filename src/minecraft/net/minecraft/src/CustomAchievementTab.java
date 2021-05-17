package net.minecraft.src;

import java.util.Random;

import org.lwjgl.opengl.GL11;

public class CustomAchievementTab extends AchievementTab {

	public CustomAchievementTab(String name) {
		super(name);
	}
	
	protected Icon genAchievementIcon(int mapX, int mapY, int windowX, int windowY)
    {   
		int xPos = (windowX + 288 >> 4) + mapX;
        int yPos = (windowY + 288 >> 4) + mapY;
        
		Icon icon = Block.planks.getIcon(0, 0);
    	if (xPos % 4 == 0) {
    		icon = Block.wood.getIcon(2, 0);
    	}
    	else if (xPos % 2 == 0 && yPos % 4 == 0) {
    		icon = Block.glass.getIcon(0, 0);
    	}
    	if (yPos >= 36) {
    		icon = Block.cobblestone.getIcon(0, 0);
    	}
        return icon;
    }
}
