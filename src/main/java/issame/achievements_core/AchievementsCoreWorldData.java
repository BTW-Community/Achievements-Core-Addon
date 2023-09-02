package issame.achievements_core;

import btw.world.util.WorldData;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.WorldInfo;
import net.minecraft.src.WorldServer;

public class AchievementsCoreWorldData implements WorldData {

    @Override
    public void saveWorldDataToNBT(WorldServer world, NBTTagCompound tag) {}

    @Override
    public void loadWorldDataFromNBT(WorldServer world, NBTTagCompound tag) {}

    @Override
    public void saveGlobalDataToNBT(WorldInfo info, NBTTagCompound tag) {
        System.out.println("Save");
        tag.setTag("Achievements", AchievementsCore.saveDataToNBT());
    }

    @Override
    public void loadGlobalDataFromNBT(WorldInfo info, NBTTagCompound tag) {
        System.out.println("Load");
        AchievementsCore.loadDataFromNBT(tag.getCompoundTag("Achievements"));
    }

    @Override
    public void createDefaultGlobalData(WorldInfo info) {
        System.out.println("Create");
        AchievementsCore.loadBlankData();
    }

    @Override
    public void copyGlobalData(WorldInfo oldInfo, WorldInfo newInfo) {}

    @Override
    public String getFilename() {
        return null;
    }
}
