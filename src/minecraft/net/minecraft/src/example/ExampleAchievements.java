package net.minecraft.src.example;

import net.minecraft.src.Achievement;
import net.minecraft.src.AchievementEventDispatcher;
import net.minecraft.src.AchievementTab;
import net.minecraft.src.AddonExt;
import net.minecraft.src.Block;
import net.minecraft.src.CustomAchievementTab;
import net.minecraft.src.FCAddOnHandler;
import net.minecraft.src.FCBetterThanWolves;

/**
 * @author IssaMe (@issame)
 *
 * An example addon showcasing how to use the Achievements Core Addon.
 */
public class ExampleAchievements extends AddonExt {
	private static ExampleAchievements instance;
	private ExampleEventHandler listener;
	
	public static Achievement chiselWood;
	public static Achievement chiselStone;
	
	public static Achievement custom1;
	public static Achievement custom2;

	public ExampleAchievements() {
		super("Achievement Example Addon", "1.0.0", "AchExAddon");
	}

	public static ExampleAchievements getInstance() {
		if (instance == null) {
			instance = new ExampleAchievements();
		}
		return instance;
	}

	@Override
	public void Initialize() {
		FCAddOnHandler.LogMessage(this.getName() + " Version " + this.getVersionString() + " Initializing...");
		
		AchievementTab tabSimple = new AchievementTab("simple1").setIcon(FCBetterThanWolves.fcCompanionCube);
		chiselWood = (new Achievement("simple1", 0, 0, FCBetterThanWolves.fcItemChiselWood, null)).setIndependent().registerAchievement(tabSimple);
		chiselStone = (new Achievement("simple2", 0, 2, FCBetterThanWolves.fcItemChiselStone, chiselWood)).registerAchievement(tabSimple);
		System.out.println("Simple Tab: " + tabSimple.size() + " achievements");
		
		AchievementTab tabCustom = new CustomAchievementTab("custom").setIcon(FCBetterThanWolves.fcItemWaterWheel);
		custom1 = (new Achievement("custom1", 0, 0, FCBetterThanWolves.fcAnvil, null)).setIndependent().registerAchievement(tabCustom);
		custom2 = (new Achievement("custom2", 10, 10, FCBetterThanWolves.fcItemBloodMossSpores, custom1)).registerAchievement(tabCustom);
		System.out.println("Custom Tab: " + tabCustom.size() + " achievements");
		
		new AchievementTab("4").setIcon(Block.cloth);
		new AchievementTab("5").setIcon(Block.cloth);
		new AchievementTab("6").setIcon(Block.cloth);
		new AchievementTab("7").setIcon(Block.cloth);
		new AchievementTab("8").setIcon(Block.cloth);
		new AchievementTab("9").setIcon(Block.cloth);
		new AchievementTab("10").setIcon(Block.cloth);
		
		AchievementEventDispatcher.register(new ExampleEventHandler());
		
		FCAddOnHandler.LogMessage(this.getName() + " Initialized");
	}
	
	public String GetLanguageFilePrefix()
	{
		return "ach_example";
	}
}