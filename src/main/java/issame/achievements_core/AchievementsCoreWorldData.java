package issame.achievements_core;

import btw.world.util.WorldData;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.WorldInfo;
import net.minecraft.src.WorldServer;

public class AchievementsCoreWorldData implements WorldData {
    // TODO: Figure out how to save data per world.
    //
    // The WorldData methods assume you are loading from and saving to
    // WorldServer and WorldInfo, but as an Addon those classes cannot be modified.
    //
    // Trying to keep track of what world is loaded using a reference to WorldInfo
    // does not seem to work, as the same world doesn't keep the same reference.
    //
    // Trying to store the String of the world name only partially works,
    // if two worlds have the same name it cannot distinguish the two.

    private static String currentWorldName;

    @Override
    public void saveWorldDataToNBT(WorldServer world, NBTTagCompound tag) {}

    @Override
    public void loadWorldDataFromNBT(WorldServer world, NBTTagCompound tag) {}

    @Override
    public void saveGlobalDataToNBT(WorldInfo info, NBTTagCompound tag) {
        System.out.println("OLD: " + currentWorldName);
        if (info.isInitialized()) {
            System.out.println("INITIALIZED");
        }
        if (currentWorldName.equals(info.getWorldName())) {
            System.out.println("SAVE: " + info.getWorldName());
            AchievementsCore.saveDataToNBT();
        } else {
            System.out.println("NEW: " + info.getWorldName());
        }
    }

    @Override
    public void loadGlobalDataFromNBT(WorldInfo info, NBTTagCompound tag) {
        System.out.println("OLD: " + currentWorldName);
        if (info.isInitialized()) {
            System.out.println("INITIALIZED");
        }
        System.out.println("LOAD: " + info.getWorldName());
        currentWorldName = info.getWorldName();
        AchievementsCore.loadDataFromNBT(tag);
    }

    @Override
    public void copyGlobalData(WorldInfo oldInfo, WorldInfo newInfo) {}

    @Override
    public String getFilename() {
        return null;
    }
}
