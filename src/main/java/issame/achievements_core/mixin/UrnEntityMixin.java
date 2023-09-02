package issame.achievements_core.mixin;

import btw.entity.UrnEntity;
import btw.entity.mob.ZombieEntity;
import issame.achievements_core.event.EventDispatcher;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityThrowable;
import net.minecraft.src.MovingObjectPosition;
import net.minecraft.src.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(UrnEntity.class)
public abstract class UrnEntityMixin extends EntityThrowable {
    public UrnEntityMixin(World par1World) {
        super(par1World);
    }

    @Redirect(method = "onImpact(Lnet/minecraft/src/MovingObjectPosition;)V",
            at = @At(value = "INVOKE", target = "Lbtw/entity/mob/ZombieEntity;attemptToStartCure()Z"))
    private boolean attemptToStartCure(ZombieEntity zombie) {
        if (zombie.attemptToStartCure()) {
            EventDispatcher.onCured((EntityPlayer) getThrower(), zombie);
            return true;
        }
        return false;
    }
}
