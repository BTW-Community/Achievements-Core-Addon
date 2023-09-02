package issame.achievements_core.mixin;

import btw.entity.mob.villager.VillagerEntity;
import issame.achievements_core.event.EventDispatcher;
import net.minecraft.src.EntityVillager;
import net.minecraft.src.MerchantRecipe;
import net.minecraft.src.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(VillagerEntity.class)
public abstract class VillagerEntityMixin extends EntityVillager {
    public VillagerEntityMixin(World par1World) {
        super(par1World);
    }

    @Inject(method = "useRecipe(Lnet/minecraft/src/MerchantRecipe;)V", at = @At("TAIL"))
    private void useRecipe(MerchantRecipe recipe, CallbackInfo ci) {
        EventDispatcher.onTraded(this.getCustomer(), recipe);
    }
}
