package net.minecraft.src.example;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import net.minecraft.src.AchievementTab;
import net.minecraft.src.Block;
import net.minecraft.src.Icon;

/**
 * A custom tab that overrides the background generation to create a custom background.
 */
public class CustomAchievementTab extends AchievementTab {

	public CustomAchievementTab(String name) {
		super(name);
	}
	
	@Override
	protected Icon genAchievementIcon(int mapX, int mapY, int windowX, int windowY)
    {   
		// Honestly not sure how this works, but it works.
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
