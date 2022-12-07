package issame.achievements_core;

import btw.AddonHandler;
import btw.BTWAddon;

public class AchievementsCore extends BTWAddon {
    private static AchievementsCore instance;

    private AchievementsCore() {
        super("Achievements Core", "3.0.0", "AC");
    }

    @Override
    public void initialize() {
        AddonHandler.logMessage(this.getName() + " Version " + this.getVersionString() + " Initializing...");
    }
}
