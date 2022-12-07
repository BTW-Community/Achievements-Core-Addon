package issame.example;

import issame.achievements_core.achievements.AchievementTab;
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
	protected Icon genAchievementIcon(int x, int y) {
        
		Icon icon = Block.planks.getIcon(0, 0);
    	if (x % 4 == 0) {
    		icon = Block.wood.getIcon(2, 0);
    	}
    	else if (x % 2 == 0 && y % 4 == 0) {
    		icon = Block.glass.getIcon(0, 0);
    	}
    	if (y >= 36) {
    		icon = Block.cobblestone.getIcon(0, 0);
    	}
        return icon;
    }
}