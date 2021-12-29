package io.github.ultrusbot.origins.mixin;

import io.github.ultrusbot.origins.duck.PlayerEntityOriginsDuck;
import io.github.ultrusbot.origins.origin.Origin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.Player;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.level.Level;
import net.minecraft.util.io.CompoundTag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerEntityMixin extends LivingEntity implements PlayerEntityOriginsDuck {

    @Shadow public abstract boolean damage(Entity arg, int i);

    @Shadow public PlayerInventory inventory;
    private Origin origin = Origin.EMPTY;

    public PlayerEntityMixin(Level arg) {
        super(arg);
    }

    @Inject(method = "readCustomDataFromTag", at = @At("TAIL"))
    public void readCustomData$Origins(CompoundTag compoundTag, CallbackInfo ci) {
        if (compoundTag.containsKey("Origin")) {
            origin = Origin.values()[compoundTag.getInt("Origin")];
        } else {
            origin = Origin.EMPTY;
        }
        if (origin == Origin.BLAZEBORN) {
            this.immuneToFire = true;
        }
    }

    @Inject(method = "writeCustomDataToTag", at = @At("TAIL"))
    public void writeCustomData$Origins(CompoundTag compoundTag, CallbackInfo ci) {
        compoundTag.put("Origin", origin.id);
    }

    @Override
    public Origin getOrigin() {
        return origin;
    }

    @Override
    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    @Inject(method = "tick", at = @At("TAIL"))
    void tick$Origins(CallbackInfo ci) {
        if (this.isTouchingWater() && origin == Origin.BLAZEBORN) {
            damage(this, 1);
        }
    }

    @Inject(method = "travel", at = @At("TAIL"))
    void travel$Origins(CallbackInfo ci) {
        if (origin == Origin.AVIAN) {
            if (this.velocityY < 0 && !this.isOnLadder()) {
                this.velocityY /= 2.0f;
            }
        }
    }

    @Inject(method = "attack", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/Entity;damage(Lnet/minecraft/entity/Entity;I)Z"))
    void afterAttaack$Origins(Entity par1, CallbackInfo ci) {
        if (origin == Origin.BLAZEBORN) {
            par1.fire = 100;
        }
    }

    @Inject(method = "handleFallDamage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;handleFallDamage(F)V"), cancellable = true)
    void cancelFallDamage$Origins(float par1, CallbackInfo ci) {
        if (origin == Origin.AVIAN) {
            ci.cancel();
        }
    }

    @Inject(method = "applyDamage", at = @At("HEAD"))
    void applyDamage$Origins(int par1, CallbackInfo ci) {
        System.out.println(this.inventory.method_687());
    }

}
