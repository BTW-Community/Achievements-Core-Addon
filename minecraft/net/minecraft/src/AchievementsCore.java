package net.minecraft.src;

import java.awt.List;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.minecraft.src.Achievement;
import net.minecraft.src.EventDispatcher;
import net.minecraft.src.AchievementTab;
import net.minecraft.src.Block;
import net.minecraft.src.FCAddOnHandler;
import net.minecraft.src.FCBetterThanWolves;
import net.minecraft.src.Item;

/**
 * Achievements Core Addon.
 * This only exists so I can add a language file prefix,
 * which is used for achievement messages.
 */
public class AchievementsCore extends FCAddOn {
	private static AchievementsCore instance;
	private Map<String, byte[]> achievementsMap = new HashMap();
	
	public int maxSize;
	
	public AchievementsCore() {
		super("Achievements Core", "2.1.0", "AC");
	}

	public static AchievementsCore getInstance() {
		if (instance == null) {
			instance = new AchievementsCore();
		}
		return instance;
	}

	@Override
	public void Initialize() {
		FCAddOnHandler.LogMessage(this.getName() + " Version " + this.getVersionString() + " Initializing...");
		maxSize = AchievementTabList.getMaxSize();
		FCAddOnHandler.LogMessage(this.getName() + " Initialized");
	}
	
	public void triggerAchievement(EntityPlayer player, Achievement achievement) {
		if (!achievementsMap.containsKey(player.username)) {
			createBlankAchievements(player.username);
		}
		byte[] achievementStats = achievementsMap.get(player.username);
		achievementStats[achievement.statId] += 1;
	}
	
	private void createBlankAchievements(String name) {
		byte[] achievementStats = new byte[maxSize];
		
		for (AchievementTab tab : AchievementTabList.tabList) {
			for (Achievement achievement : tab.achievementList) {
				achievementStats[achievement.statId] = 0;
			}
		}
		achievementsMap.put(name, achievementStats);
	}
	
	public NBTTagCompound saveAchievementsToNBT() {
		NBTTagCompound playersTag = new NBTTagCompound("Players");
		
		Iterator<String> playerNames = achievementsMap.keySet().iterator();
		while (playerNames.hasNext()) {
			String name = playerNames.next();
			playersTag.setByteArray(name, achievementsMap.get(name));
		}
		
        return playersTag;
    }
	
	public void loadAchievementsFromNBT(NBTTagCompound achievementsTag) {
		achievementsMap.clear();
		
		Iterator<String> playerNames = NBTTagCompound.getTagMap(achievementsTag).keySet().iterator();
		
		while (playerNames.hasNext()) {
			String name = playerNames.next();
			
			this.createBlankAchievements(name);
			byte[] achievementStats = achievementsMap.get(name);
			byte[] tempStats = achievementsTag.getByteArray(name);
			
			for (int i = 0; i < maxSize; i++) {
				// Break early in-case there are more.
				if (tempStats.length == i) { break; }
				achievementStats[i] = tempStats[i];
			}
		}
	}
	
	@Override
	public FCAddOnUtilsWorldData createWorldData() {
		return new AchievementsCoreWorldData();
	}
	
	@Override
	public String GetLanguageFilePrefix() {
		return "achievementscore";
	}

}