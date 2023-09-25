package issame.achievements_core.mixin;

import issame.achievements_core.event.EventDispatcher;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityGhast.class)
public abstract class EntityGhastMixin extends EntityFlying {
    public EntityGhastMixin(World par1World) {
        super(par1World);
    }

    @Redirect(method = "attackEntityFrom(Lnet/minecraft/src/DamageSource;I)Z",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/src/EntityPlayer;triggerAchievement(Lnet/minecraft/src/StatBase;)V"))
    private void killedByReturn(EntityPlayer player, StatBase par1StatBase) {
        EventDispatcher.onReturnedToGhast(player);
    }
}