package net.minecraft.src;

public class AchievementsCoreWorldData implements FCAddOnUtilsWorldData {
	
	AchievementsCore ac = AchievementsCore.getInstance();

	@Override
	public void saveWorldDataToNBT(WorldServer world, NBTTagCompound tag) {
		// TODO Auto-generated method stub
	}

	@Override
	public void loadWorldDataFromNBT(WorldServer world, NBTTagCompound tag) {
		// TODO Auto-generated method stub
	}

	@Override
	public void saveGlobalDataToNBT(WorldInfo info, NBTTagCompound tag) {
		tag.setTag("Achievements", ac.saveAchievementsToNBT());
	}

	@Override
	public void loadGlobalDataFromNBT(WorldInfo info, NBTTagCompound tag) {
		if (tag.hasKey("Achievements")) {
			ac.loadAchievementsFromNBT(tag.getCompoundTag("Achievements"));
	    }
	}

	@Override
	public void copyGlobalData(WorldInfo oldInfo, WorldInfo newInfo) {
		// TODO Auto-generated method stub
	}

	@Override
	public String getFilename() {
		return "achievementscore";
	}

}
