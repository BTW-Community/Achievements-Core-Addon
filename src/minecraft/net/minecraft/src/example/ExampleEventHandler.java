package net.minecraft.src.example;

import net.minecraft.src.AchievementListener;
import net.minecraft.src.AchievementType;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.FCBetterThanWolves;
import net.minecraft.src.ItemStack;

public class ExampleEventHandler {

	@AchievementListener(AchievementType.CRAFTED)
	public void onCrafted(EntityPlayer player, ItemStack itemstack) {
		if (itemstack.itemID == FCBetterThanWolves.fcItemChiselWood.itemID) {
            player.triggerAchievement(ExampleAchievements.chiselWood);;
        }
		else if (itemstack.itemID == FCBetterThanWolves.fcItemChiselStone.itemID) {
            player.triggerAchievement(ExampleAchievements.chiselStone);;
        }
	}
}
