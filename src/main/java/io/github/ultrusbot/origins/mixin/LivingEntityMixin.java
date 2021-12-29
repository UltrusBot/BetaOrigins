package io.github.ultrusbot.origins.mixin;

import io.github.ultrusbot.origins.duck.PlayerEntityOriginsDuck;
import io.github.ultrusbot.origins.origin.Origin;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {


    @ModifyArg(
            method = "travel",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;movementInputToVelocity(FFF)V"),
            index = 2
    )
    public float changeSpeed(float par1) {
        if (((LivingEntity)(Object)this) instanceof Player) {
            if (((PlayerEntityOriginsDuck)this).getOrigin() == Origin.AVIAN) {
                return par1 * 2.0f;
            }
        }
        return par1;
    }
}
