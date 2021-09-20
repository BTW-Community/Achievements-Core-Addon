package net.minecraft.src;

import net.minecraft.src.Achievement;
import net.minecraft.src.EventDispatcher;
import net.minecraft.src.AchievementTab;
import net.minecraft.src.Block;
import net.minecraft.src.FCAddOnHandler;
import net.minecraft.src.FCBetterThanWolves;
import net.minecraft.src.Item;

/**
 * Achievements Core Addon.
 * This only exists so I can add a language file prefix,
 * which is used for achievement messages.
 */
public class AchievementsCore extends FCAddOn {
	private static AchievementsCore instance;
	
	public AchievementsCore() {
		super("Achievements Core", "2.0.0", "AC");
	}

	public static AchievementsCore getInstance() {
		if (instance == null) {
			instance = new AchievementsCore();
		}
		return instance;
	}

	@Override
	public void Initialize() {
		FCAddOnHandler.LogMessage(this.getName() + " Version " + this.getVersionString() + " Initializing...");
		FCAddOnHandler.LogMessage(this.getName() + " Initialized");
	}
	
	public String GetLanguageFilePrefix()
	{
		return "achievementscore";
	}
}