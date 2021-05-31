package net.minecraft.src.example;

import java.util.Random;
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
}
