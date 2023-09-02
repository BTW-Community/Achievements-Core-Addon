package issame.achievements_core.mixin;

import issame.achievements_core.event.EventDispatcher;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ICommandSender;
import net.minecraft.src.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

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
}
