package issame.achievements_core.mixin;

import issame.achievements_core.event.EventDispatcher;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.ICrafting;
import net.minecraft.src.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayerMP.class)
public abstract class EntityPlayerMPMixin extends EntityPlayer implements ICrafting {
    public EntityPlayerMPMixin(World par1World) {
        super(par1World);
    }

    @Inject(method = "travelToDimension(I)V", at = @At("TAIL"))
    private void travelToDimension(int dimensionId, CallbackInfo ci) {
        EventDispatcher.onTraveledDimension(this, dimensionId);
    }
}
