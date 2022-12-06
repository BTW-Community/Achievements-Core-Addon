package issame.achievements_core;

import net.minecraft.src.FCAddOn;
import net.minecraft.src.FCAddOnHandler;

public class AchievementsCore extends FCAddOn {
    private static AchievementsCore instance;

    private AchievementsCore() {
        super("Achievements Core", "3.0.0", "AC");
    }

    @Override
    public void Initialize() {
        FCAddOnHandler.LogMessage(this.getName() + " Version " + this.getVersionString() + " Initializing...");
    }

    public static AchievementsCore getInstance() {
        if (instance == null)
            instance = new AchievementsCore();
        return instance;
    }
}
