package issame.achievements_core.mixin;

import btw.inventory.container.PlayerContainer;
import issame.achievements_core.event.EventDispatcher;
import net.minecraft.src.Container;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Container.class)
public abstract class ContainerMixin {

    @Inject(method = "slotClick",
            at = @At(value = "TAIL"))
    private void onArmorEquipped(int slot, int par2, int par3, EntityPlayer player, CallbackInfoReturnable<ItemStack> cir){
        if ((Object)this instanceof PlayerContainer && slot >= 5 && slot <= 8) {
            if (slot == 5 && player.inventory.armorInventory[3] != null)
            {
                dispatchEvent(player);
            }
            else if (slot == 6 && player.inventory.armorInventory[2] != null)
            {
                dispatchEvent(player);
            }
            else if (slot == 7 && player.inventory.armorInventory[1] != null)
            {
                dispatchEvent(player);
            }
            else if (slot == 8 && player.inventory.armorInventory[0] != null)
            {
                dispatchEvent(player);
            }
        }
    }

    private static void dispatchEvent(EntityPlayer player) {
        //System.out.println("SLOT CLICKED: CHECKING ACHIEVEMENTS");

        EventDispatcher.onArmorEquipped(player,
                player.inventory.armorInventory[3],
                player.inventory.armorInventory[2],
                player.inventory.armorInventory[1],
                player.inventory.armorInventory[0]);
    }
}