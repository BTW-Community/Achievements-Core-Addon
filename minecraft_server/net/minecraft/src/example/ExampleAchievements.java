package net.minecraft.src.example;

import net.minecraft.src.Achievement;
import net.minecraft.src.AchievementTab;
import net.minecraft.src.Block;
import net.minecraft.src.EventDispatcher;
import net.minecraft.src.FCAddOn;
import net.minecraft.src.FCAddOnHandler;
import net.minecraft.src.FCBetterThanWolves;
import net.minecraft.src.Item;

public class ExampleAchievements extends FCAddOn {

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
		 * to (Achievement)null works completely fine so there shouldn't be a reason to use it anyway.
		 */
		
		AchievementTab tabSimple = new AchievementTab("simple").setIcon(FCBetterThanWolves.fcCompanionCube);
		mineBark = (new Achievement("mineBark", 0, 0, FCBetterThanWolves.fcItemBark, (Achievement)null)).registerAchievement(tabSimple);
		mineStick = (new Achievement("mineStick", 1, 0, Item.stick, mineBark)).registerAchievement(tabSimple);
		chiselWood = (new Achievement("chiselWood", 2, 0, FCBetterThanWolves.fcItemChiselWood, mineStick)).registerAchievement(tabSimple);
		chiselStone = (new Achievement("chiselStone", 3, 0, FCBetterThanWolves.fcItemChiselStone, chiselWood)).registerAchievement(tabSimple);
		killPig = (new Achievement("killPig", 3, -1, Item.porkRaw, chiselStone)).registerAchievement(tabSimple);
		cookPork = (new Achievement("cookPork", 4, -1, Item.porkCooked, killPig)).registerAchievement(tabSimple);
		cookIron = (new Achievement("cookIron", 4, 0, FCBetterThanWolves.fcItemNuggetIron, chiselStone)).registerAchievement(tabSimple);
		neuterCreeper = (new Achievement("neuterCreeper", 4, 1, FCBetterThanWolves.fcItemCreeperOysters, cookIron)).registerAchievement(tabSimple);
		System.out.println("Simple Tab: " + tabSimple.size() + " achievements");
		
		AchievementTab tabCustom = new AchievementTab("custom").setIcon(FCBetterThanWolves.fcItemWaterWheel);
		netherPortal = (new Achievement("netherPortal", 0, 0, Block.obsidian, cookIron)).registerAchievement(tabCustom);
		firePotion = (new Achievement("firePotion", 0, 1, Item.potion, netherPortal)).setSpecial().registerAchievement(tabCustom);
		System.out.println("Custom Tab: " + tabCustom.size() + " achievements");
		
		EventDispatcher.register(new ExampleEventHandler());
		
		FCAddOnHandler.LogMessage(this.getName() + " Initialized");
	}
	
	public String GetLanguageFilePrefix()
	{
		return "example";
	}
}
