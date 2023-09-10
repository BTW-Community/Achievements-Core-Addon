package issame.achievements_core.mixin;

import btw.entity.mob.villager.VillagerEntity;
import issame.achievements_core.event.EventDispatcher;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityVillager;
import net.minecraft.src.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(VillagerEntity.class)
public class MilkTossMixin extends EntityVillager {
    public MilkTossMixin(World par1World) {
        super(par1World);
    }

    @Inject(method = "checkForLooseMilk",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/src/World;playAuxSFX(IIIII)V"))
    public void onMilkTossed(CallbackInfo ci) {
        EntityPlayer player = worldObj.getClosestPlayerToEntity(this, 8);

        if (player != null) {
            EventDispatcher.onMilkTossed(player);
        }
    }
}
