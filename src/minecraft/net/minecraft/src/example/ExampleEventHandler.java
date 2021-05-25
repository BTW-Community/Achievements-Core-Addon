package net.minecraft.src.example;

import net.minecraft.src.EventListener;
import net.minecraft.src.EventType;
import net.minecraft.src.AchievementList;
import net.minecraft.src.Block;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPig;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.FCBetterThanWolves;
import net.minecraft.src.Item;
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
            player.triggerAchievement(ExampleAchievements.mineBark);
        }
		else if (itemstack.itemID == Item.stick.itemID) {
            player.triggerAchievement(ExampleAchievements.mineStick);
        }
	}
	
	@EventListener(EventType.COOKED)
	public void onCooked(EntityPlayer player, ItemStack itemstack) {
		if (itemstack.itemID == Item.porkCooked.itemID) {
            player.triggerAchievement(ExampleAchievements.cookPork);
        }
		else if (itemstack.itemID == FCBetterThanWolves.fcItemNuggetIron.itemID) {
            player.triggerAchievement(ExampleAchievements.cookIron);
        }
	}
	
	@EventListener(EventType.KILLED)
	public void onKilled(EntityPlayer player, EntityLiving entity) {
		if (entity instanceof EntityPig) {
            player.triggerAchievement(ExampleAchievements.killPig);
        }
	}
}
