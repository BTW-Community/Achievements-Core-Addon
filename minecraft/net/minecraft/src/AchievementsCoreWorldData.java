package net.minecraft.src;

public class AchievementsCoreWorldData implements FCAddOnUtilsWorldData {
	private static final AchievementsCore ac = AchievementsCore.getInstance();
	private static String currentWorld;

	@Override
	public void saveWorldDataToNBT(WorldServer world, NBTTagCompound tag) {
	}

	@Override
	public void loadWorldDataFromNBT(WorldServer world, NBTTagCompound tag) {
	}

	@Override
	public void saveGlobalDataToNBT(WorldInfo info, NBTTagCompound tag) {
		if (info.getWorldName().equals(currentWorld)) {
			tag.setTag("Achievements", ac.saveAchievementsToNBT());
		}
	}

	@Override
	public void loadGlobalDataFromNBT(WorldInfo info, NBTTagCompound tag) {
		currentWorld = info.getWorldName();
		if (tag.hasKey("Achievements")) {
			ac.loadAchievementsFromNBT(tag.getCompoundTag("Achievements"));
	    }
	}

	@Override
	public void copyGlobalData(WorldInfo oldInfo, WorldInfo newInfo) {
	}

	@Override
	public String getFilename() {
		return null;
	}
}
