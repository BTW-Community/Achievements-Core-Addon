package issame.achievements_core.mixin;

import issame.achievements_core.event.EventDispatcher;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityItem.class)
public abstract class EntityItemMixin extends Entity {
    public EntityItemMixin(World par1World) {
        super(par1World);
    }

    @Inject(method = "onCollideWithPlayer(Lnet/minecraft/src/EntityPlayer;)V",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/src/EntityPlayer;onItemPickup(Lnet/minecraft/src/Entity;I)V"))
    private void onPickup(EntityPlayer player, CallbackInfo ci) {
        ItemStack itemStack = ((EntityItem) ((Object) this)).getEntityItem();
        EventDispatcher.onPickup(player, itemStack);
    }
}
