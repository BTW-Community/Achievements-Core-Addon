package net.minecraft.src.example;

import net.minecraft.src.EventListener;
import net.minecraft.src.EventType;
import net.minecraft.src.AchievementList;
import net.minecraft.src.Block;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.FCBetterThanWolves;
import net.minecraft.src.ItemStack;

public class ExampleEventHandler {

	@EventListener(EventType.CRAFTED)
	public void onCrafted(EntityPlayer player, ItemStack itemstack) {
		if (itemstack.itemID == FCBetterThanWolves.fcItemChiselWood.itemID) {
            player.triggerAchievement(ExampleAchievements.chiselWood);
        }
		else if (itemstack.itemID == FCBetterThanWolves.fcItemChiselStone.itemID) {
            player.triggerAchievement(ExampleAchievements.chiselStone);
        }
	}
	
	@EventListener(EventType.PICKUP)
	public void onPickup(EntityPlayer player, ItemStack itemstack) {
		if (itemstack.itemID == FCBetterThanWolves.fcItemBark.itemID) {
            player.triggerAchievement(ExampleAchievements.mineBark);  // This gets called, but the achievement doesn't trigger.
        }
	}
}
