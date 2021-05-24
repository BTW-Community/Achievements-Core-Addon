package net.minecraft.src.example;

import net.minecraft.src.Achievement;
import net.minecraft.src.EventDispatcher;
import net.minecraft.src.AchievementTab;
import net.minecraft.src.AddonExt;
import net.minecraft.src.Block;
import net.minecraft.src.CustomAchievementTab;
import net.minecraft.src.FCAddOnHandler;
import net.minecraft.src.FCBetterThanWolves;
import net.minecraft.src.Item;

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
	
	public static Achievement mineBark;
	public static Achievement mineStick;

	public ExampleAchievements() {
		super("Example Achievements", "1.0.0", "ExampleAchievements");
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
		
		/* DO NOT set any achievement to independent (don't call .setIndependent())
		 * as this may cause issues for some achievements.
		 * Setting the parent achievement to null works completely
		 * fine so there shouldn't be a reason to use it anyway.
		 */
		
		AchievementTab tabSimple = new AchievementTab("simple").setIcon(FCBetterThanWolves.fcCompanionCube);
		chiselWood = (new Achievement("chiselWood", 0, 0, FCBetterThanWolves.fcItemChiselWood, null)).registerAchievement(tabSimple).setIndependent();
		chiselStone = (new Achievement("chiselStone", 0, 2, FCBetterThanWolves.fcItemChiselStone, chiselWood)).registerAchievement(tabSimple);
		System.out.println("Simple Tab: " + tabSimple.size() + " achievements");
		
		AchievementTab tabCustom = new CustomAchievementTab("custom").setIcon(FCBetterThanWolves.fcItemWaterWheel);
		mineBark = (new Achievement("mineBark", 0, 0, FCBetterThanWolves.fcItemBark, null)).registerAchievement(tabCustom);
		mineStick = (new Achievement("mineStick", 10, 10, Item.stick, mineBark)).registerAchievement(tabCustom);
		System.out.println("Custom Tab: " + tabCustom.size() + " achievements");
		
		new AchievementTab("4").setIcon(Block.cloth);
		new AchievementTab("5").setIcon(Block.cloth);
		new AchievementTab("6").setIcon(Block.cloth);
		new AchievementTab("7").setIcon(Block.cloth);
		new AchievementTab("8").setIcon(Block.cloth);
		new AchievementTab("9").setIcon(Block.cloth);
		new AchievementTab("10").setIcon(Block.cloth);
		
		EventDispatcher.register(new ExampleEventHandler());
		
		FCAddOnHandler.LogMessage(this.getName() + " Initialized");
	}
	
	public String GetLanguageFilePrefix()
	{
		return "example";
	}
}