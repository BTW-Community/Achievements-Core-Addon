package issame.example;

import btw.AddonHandler;
import btw.BTWAddon;
import btw.block.BTWBlocks;
import btw.item.BTWItems;
import issame.achievements_core.achievements.Achievement;
import issame.achievements_core.achievements.AchievementTab;
import issame.achievements_core.event.EventDispatcher;
import net.minecraft.src.*;

public class ExampleAchievements extends BTWAddon {

private static ExampleAchievements instance;
	
	/* List of custom achievements */
	public static Achievement mineBark;
	public static Achievement mineStick;
	public static Achievement chiselWood;
	public static Achievement chiselStone;
	public static Achievement killPig;
	public static Achievement cookPork;
	public static Achievement drinkMilk;
	public static Achievement cookIron;
	public static Achievement neuterCreeper;
		
	public static Achievement netherPortal;
	public static Achievement firePotion;
	public static Achievement cureVillager;
	public static Achievement levelUpFarmer;

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
	public void initialize() {
		AddonHandler.logMessage(this.getName() + " Version " + this.getVersionString() + " Initializing...");
		
		AchievementTab tabSimple = new AchievementTab("simple").setIcon(BTWBlocks.companionCube);
		mineBark = new Achievement("mineBark", 0, 0, BTWItems.bark, tabSimple);

		mineStick = new Achievement("mineStick", 0, 1, Item.stick, tabSimple);
		chiselWood = new Achievement("chiselWood", 1, 0, BTWItems.pointyStick, tabSimple)
				.setParents(mineBark, mineStick);
		chiselStone = new Achievement("chiselStone", 2, 0, BTWItems.sharpStone, tabSimple)
				.setParents(chiselWood);
		killPig = (new Achievement("killPig", 2, -1, Item.porkRaw, tabSimple))
				.setParents(chiselStone).setFrame(2, 0);
		cookPork = (new Achievement("cookPork", 2, -2, Item.porkCooked, tabSimple))
				.setParents(killPig).setFrame(3, 0);
		drinkMilk = (new Achievement("drinkMilk", 2, -3, Item.bucketMilk, tabSimple))
				.setParents(cookPork).setFrame(3, 1);
		cookIron = (new Achievement("cookIron", 3, 0, BTWBlocks.ironOreChunk, tabSimple))
				.setParents(chiselStone);
		neuterCreeper = (new Achievement("neuterCreeper", 3, 1, BTWItems.creeperOysters, tabSimple))
				.setParents(cookIron).setHidden();

		System.out.println("Simple Tab: " + tabSimple.size() + " achievements");

		AchievementTab tabCustom = new CustomAchievementTab("custom").setIcon(BTWItems.waterWheel);
		netherPortal = (new Achievement("netherPortal", 0, 0, Block.obsidian, tabCustom))
				.setParents(neuterCreeper);
		firePotion = (new Achievement("firePotion", 0, 1, Item.potion, tabCustom))
				.setParents(netherPortal);
		cureVillager = (new Achievement("cureVillager", 1, 0, Item.emerald, tabCustom))
				.setParents(netherPortal).setFrame(2, 0);
		levelUpFarmer = (new Achievement("levelUpFarmer", 2, 0, Item.emerald, tabCustom))
				.setParents(cureVillager).setFrame(1, 1);
		System.out.println("Custom Tab: " + tabCustom.size() + " achievements");

		EventDispatcher.register(new ExampleEventHandler());
		AddonHandler.logMessage(this.getName() + " Initialized");
	}
}
