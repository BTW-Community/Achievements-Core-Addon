package issame.achievements_core.mixin;

import issame.achievements_core.event.EventDispatcher;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityPlayer.class)
public abstract class EntityPlayerMixin extends EntityLiving implements ICommandSender {
    public EntityPlayerMixin(World par1World) {
        super(par1World);
    }

    @Inject(method = "onKillEntity(Lnet/minecraft/src/EntityLiving;)V", at = @At(value = "HEAD"))
    private void onKillEntity(EntityLiving entityLiving, CallbackInfo ci) {
        EntityPlayer player = (EntityPlayer) ((Object) this);
        EventDispatcher.onKilled(player, entityLiving);
    }

    @Inject(method = "interactWith(Lnet/minecraft/src/Entity;)Z", at = @At(value = "HEAD"))
    private void interactWith(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        EntityPlayer player = (EntityPlayer) ((Object) this);
        EventDispatcher.onEntityInteraction(player, entity, player.getCurrentEquippedItem());
    }
}
