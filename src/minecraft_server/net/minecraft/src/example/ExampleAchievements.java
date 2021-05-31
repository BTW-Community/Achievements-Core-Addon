package net.minecraft.src.example;

import net.minecraft.src.Achievement;
import net.minecraft.src.EventDispatcher;
import net.minecraft.src.AchievementTab;
import net.minecraft.src.AddonExt;
import net.minecraft.src.Block;
import net.minecraft.src.FCAddOnHandler;
import net.minecraft.src.FCBetterThanWolves;
import net.minecraft.src.Item;

/**
 * An example addon showcasing how to use the Achievements Core Addon.
 */
public class ExampleAchievements extends AddonExt {
	private static ExampleAchievements instance;
	
	/* List of custom achievements */
	public static Achievement chiselWood;
	public static Achievement chiselStone;
	public static Achievement killPig;
	public static Achievement cookPork;
	public static Achievement cookIron;
	
	public static Achievement mineBark;
	public static Achievement mineStick;
	public static Achievement neuterCreeper;
	public static Achievement netherPortal;
	public static Achievement firePotion;

	public ExampleAchievements() {
		super("Example Achievements", "1.0.0", "EA");
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
		
		/* 
		 * DO NOT set any achievement to independent (i.e. don't call .setIndependent())
		 * as this may cause issues for some achievement triggers. Setting the parent achievement
		 * to null works completely fine so there shouldn't be a reason to use it anyway.
		 */
		
		AchievementTab tabSimple = new AchievementTab("simple").setIcon(FCBetterThanWolves.fcCompanionCube);
		chiselWood = (new Achievement("chiselWood", 0, 0, FCBetterThanWolves.fcItemChiselWood, null)).registerAchievement(tabSimple);
		chiselStone = (new Achievement("chiselStone", 0, 2, FCBetterThanWolves.fcItemChiselStone, chiselWood)).registerAchievement(tabSimple);
		killPig = (new Achievement("killPig", 3, 3, Item.porkRaw, chiselStone)).registerAchievement(tabSimple);
		cookPork = (new Achievement("cookPork", 2, 2, Item.porkCooked, killPig)).registerAchievement(tabSimple);
		cookIron = (new Achievement("cookIron", -2, 4, FCBetterThanWolves.fcItemNuggetIron, cookPork)).registerAchievement(tabSimple);
		System.out.println("Simple Tab: " + tabSimple.size() + " achievements");
		
		AchievementTab tabCustom = new CustomAchievementTab("custom").setIcon(FCBetterThanWolves.fcItemWaterWheel);
		mineBark = (new Achievement("mineBark", 0, 0, FCBetterThanWolves.fcItemBark, null)).registerAchievement(tabCustom);
		mineStick = (new Achievement("mineStick", 10, 10, Item.stick, mineBark)).registerAchievement(tabCustom);
		neuterCreeper = (new Achievement("neuterCreeper", 6, 9, FCBetterThanWolves.fcItemCreeperOysters, mineStick)).registerAchievement(tabCustom);
		netherPortal = (new Achievement("netherPortal", -5, -5, Block.obsidian, mineStick)).registerAchievement(tabCustom);
		firePotion = (new Achievement("firePotion", 4, 2, Item.potion, netherPortal)).setSpecial().registerAchievement(tabCustom);
		System.out.println("Custom Tab: " + tabCustom.size() + " achievements");
		
		EventDispatcher.register(new ExampleEventHandler());
		
		FCAddOnHandler.LogMessage(this.getName() + " Initialized");
	}
	
	public String GetLanguageFilePrefix()
	{
		return "example";
	}
}