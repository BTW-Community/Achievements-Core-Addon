package issame.achievements_core;

import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;

public class ACPreLaunchInitializer implements PreLaunchEntrypoint {
    /**
     * Runs the PreLaunch entrypoint to register BTW-Addon.
     * Don't initialize anything else here, use
     * the method Initialize() in the Addon.
     */
    @Override
    public void onPreLaunch() {
        AchievementsCore.getInstance();
    }
}
