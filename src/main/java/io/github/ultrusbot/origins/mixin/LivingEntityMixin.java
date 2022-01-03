package io.github.ultrusbot.origins.mixin;

import io.github.ultrusbot.origins.duck.PlayerEntityOriginsDuck;
import io.github.ultrusbot.origins.origin.Origin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.Player;
import net.minecraft.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {


    public LivingEntityMixin(Level arg) {
        super(arg);
    }

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

    @Inject(method = "isOnLadder", at = @At("TAIL"), locals = LocalCapture.CAPTURE_FAILEXCEPTION, cancellable = true)
    public void onLadder$Origins(CallbackInfoReturnable<Boolean> cir, int var1, int var2, int var3) {
        if (((LivingEntity)(Object)this) instanceof Player) {
            if (((PlayerEntityOriginsDuck)this).getOrigin() == Origin.ARACHNID) {
                boolean climbingBlock = this.level.getTileId(var1 + 1, var2 , var3 + 1) > 0 || this.level.getTileId(var1 - 1, var2, var3 - 1) > 0;
                cir.setReturnValue(climbingBlock);
            }
        }
    }

}
