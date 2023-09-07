package issame.achievements_core;

import btw.AddonHandler;
import btw.BTWAddon;
import btw.world.util.WorldData;
import issame.achievements_core.achievements.*;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.NBTTagInt;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AchievementsCore extends BTWAddon {
    // Key is the player name, value is a map of achievement names to values.
    private static final Map<String, Map<String, Integer>> achievementsMap = new HashMap<>();
    public static AchievementPopup popup;

    private AchievementsCore() {
        super("Achievements Core", "3.0.0", "AC");
    }

    @Override
    public void initialize() {
        AddonHandler.logMessage(this.getName() + " Version " + this.getVersionString() + " Initializing...");
        popup = new AchievementPopup(Minecraft.getMinecraft());
        registerAddonCommandClientOnly(new CommandAchievement());
        AddonHandler.logMessage(this.getName() + " Initialization Complete.");
    }

    @Override
    public void postInitialize() {
        super.postInitialize();

        for (Iterator<AchievementTab> it = AchievementTabList.iterator(); it.hasNext(); ) {
            AchievementTab tab = it.next();
            AddonHandler.logMessage(String.format("%s: %d achievements", tab.getName(), tab.size()));
        }
    }

    @Override
    public WorldData createWorldData() {
        return new AchievementsCoreWorldData();
    }

    public static NBTTagCompound saveDataToNBT() {
        NBTTagCompound tag = new NBTTagCompound("Achievements");
        for (String playerName : achievementsMap.keySet()) {
            NBTTagCompound playerTag = new NBTTagCompound(playerName);
            tag.setCompoundTag(playerName, playerTag);

            Map<String, Integer> playerAchievements = achievementsMap.get(playerName);
            for (String achievementName : playerAchievements.keySet()) {
                playerTag.setInteger(achievementName, playerAchievements.get(achievementName));
            }
        }
        return tag;
    }

    public static void loadDataFromNBT(NBTTagCompound tag) {
        achievementsMap.clear();
        for (Object playerObj : tag.getTags()) {
            NBTTagCompound playerTag = (NBTTagCompound) playerObj;
            Map<String, Integer> playerAchievements = new HashMap<>();
            achievementsMap.put(playerTag.getName(), playerAchievements);

            for (Object achievementObj : playerTag.getTags()) {
                NBTTagInt achievementTag = (NBTTagInt) achievementObj;
                playerAchievements.put(achievementTag.getName(), achievementTag.data);
            }
        }
    }

    public static void loadBlankData() {
        achievementsMap.clear();
    }

    /**
     * @param achievement
     * @param player
     * @param amount
     * @param set forces the achievement counter to be the value of amount
     * @return true when Achievement threshold is passed (triggered Achievement)
     */
    public static boolean trigger(Achievement achievement, EntityPlayer player, int amount, boolean set) {
        if (!set && achievement.getStatus(player) != AchievementStatus.CAN_UNLOCK) return false;

        if (!achievementsMap.containsKey(player.getEntityName())) {
            Map<String, Integer> playerAchievements = new HashMap<>();
            achievementsMap.put(player.getEntityName(), playerAchievements);
        }

        Map<String, Integer> playerAchievements = achievementsMap.get(player.getEntityName());
        int old_count = playerAchievements.getOrDefault(achievement.getUnlocalizedName(), 0);
        int new_count = set ? amount : old_count + amount;

        if (old_count < achievement.threshold && new_count >= achievement.threshold) {
            MinecraftServer.getServer().getConfigurationManager().sendChatMsg(achievement.getAnnounceMessage(player));
            popup.queueTakenAchievement(achievement);
        }

        new_count = Math.max(0, Math.min(achievement.threshold, new_count));
        playerAchievements.put(achievement.getUnlocalizedName(), new_count);

        return new_count == achievement.threshold;
    }

    public static boolean trigger(Achievement achievement, EntityPlayer player) {
        return trigger(achievement, player, 1, false);
    }

    public static boolean hasUnlocked(Achievement achievement, EntityPlayer player) {
        if (!achievementsMap.containsKey(player.getEntityName())) return false;
        Map<String, Integer> playerAchievements = achievementsMap.get(player.getEntityName());

        int count = playerAchievements.getOrDefault(achievement.getUnlocalizedName(), 0);
        return count >= achievement.threshold;
    }
}
