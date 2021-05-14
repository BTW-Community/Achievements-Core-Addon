package net.minecraft.src;

/**
 * @author Sockthing (@socklessthing)
 *
 */
public class AchievementsMod extends AddonExt {
	private static AchievementsMod instance;

	public AchievementsMod() {
		super("AchievementsMod", "1.0.0", "AchievementsMod");
	}

	public static AchievementsMod getInstance() {
		if (instance == null) {
			instance = new AchievementsMod();
		}

		return instance;
	}

	@Override
	public void Initialize() {
		FCAddOnHandler.LogMessage(this.getName() + " Version " + this.getVersionString() + " Initializing...");
		
		AchievementTab tab = new AchievementTab("Test Category");
		Achievement test = (new Achievement("test", 0, 0, FCBetterThanWolves.fcCompanionCube, null)).setIndependent().registerAchievement(tab);
		tab.printSize();
		
		FCAddOnHandler.LogMessage(this.getName() + " Initialized");
	}
	
	public String GetLanguageFilePrefix()
	{
		return "SocksCrops";
	}
} 