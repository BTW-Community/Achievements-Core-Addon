package issame.achievements_core.mixin;

import btw.inventory.container.PlayerContainer;
import issame.achievements_core.event.EventDispatcher;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(Container.class)
public abstract class ContainerMixin {

    @Inject(method = "slotClick",
            at = @At(value = "TAIL"))
    private void onArmorEquipped(int slot, int par2, int par3, EntityPlayer player, CallbackInfoReturnable<ItemStack> cir){
        if ((Object)this instanceof PlayerContainer && slot >= 5 && slot <= 8) {
            EventDispatcher.onArmorEquipped(player,
                    player.inventory.armorInventory[3],
                    player.inventory.armorInventory[2],
                    player.inventory.armorInventory[1],
                    player.inventory.armorInventory[0]);
        }
    }
}