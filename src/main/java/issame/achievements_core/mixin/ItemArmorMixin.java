package issame.achievements_core.mixin;

import issame.achievements_core.event.EventDispatcher;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemArmor.class)
public abstract class ItemArmorMixin extends Item {
    public ItemArmorMixin(int itemID) {
        super(itemID);
    }

    @Inject(method = "onItemRightClick",
            at = @At(value = "TAIL"))
    private void onArmorEquipped(ItemStack itemstack, World world, EntityPlayer player, CallbackInfoReturnable<ItemStack> cir){
        EventDispatcher.onArmorEquipped(player,
                player.inventory.armorInventory[3],
                player.inventory.armorInventory[2],
                player.inventory.armorInventory[1],
                player.inventory.armorInventory[0]);
    }
}
