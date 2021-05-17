package net.minecraft.src;

/**
 * @author IssaMe (@issame)
 *
 * An example addon showcasing how to use the Achievements Core Addon.
 */
public class AchievementExampleAddon extends AddonExt {
	private static AchievementExampleAddon instance;

	public AchievementExampleAddon() {
		super("Achievement Example Addon", "1.0.0", "AchExAddon");
	}

	public static AchievementExampleAddon getInstance() {
		if (instance == null) {
			instance = new AchievementExampleAddon();
		}
		return instance;
	}

	@Override
	public void Initialize() {
		FCAddOnHandler.LogMessage(this.getName() + " Version " + this.getVersionString() + " Initializing...");
		
		AchievementTab tabSimple1 = new AchievementTab("simple1").setIcon(FCBetterThanWolves.fcCompanionCube);
		Achievement simple1 = (new Achievement("simple1", 0, 0, FCBetterThanWolves.fcCompanionCube, null)).setIndependent().registerAchievement(tabSimple1);
		Achievement simple2 = (new Achievement("simple2", 0, 2, FCBetterThanWolves.fcBlockDispenser, simple1)).registerAchievement(tabSimple1);
		tabSimple1.printSize();
		
		AchievementTab tabCustom = new CustomAchievementTab("custom").setIcon(FCBetterThanWolves.fcItemWaterWheel);
		Achievement custom1 = (new Achievement("custom1", 0, 0, FCBetterThanWolves.fcAnvil, null)).setIndependent().registerAchievement(tabCustom);
		Achievement custom2 = (new Achievement("custom2", 10, 10, FCBetterThanWolves.fcItemBloodMossSpores, custom1)).registerAchievement(tabCustom);
		tabCustom.printSize();
		
		new AchievementTab("simple2").setIcon(Block.cloth);
		new AchievementTab("simple3").setIcon(Block.cloth);
		new AchievementTab("simple4").setIcon(Block.cloth);
		new AchievementTab("simple5").setIcon(Block.cloth);
		new AchievementTab("simple6").setIcon(Block.cloth);
		new AchievementTab("simple7").setIcon(Block.cloth);
		new AchievementTab("simple8").setIcon(Block.cloth);
		
		FCAddOnHandler.LogMessage(this.getName() + " Initialized");
	}
	
	public String GetLanguageFilePrefix()
	{
		return "ach_example";
	}
} 