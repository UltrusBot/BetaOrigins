package io.github.ultrusbot.origins.mixin;

import io.github.ultrusbot.origins.duck.PlayerEntityOriginsDuck;
import io.github.ultrusbot.origins.origin.Origin;
import net.minecraft.entity.player.Player;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemInstance;
import net.minecraft.item.armour.ArmourItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(PlayerInventory.class)
public abstract class PlayerInventoryMixin implements Inventory {

    @Shadow public ItemInstance[] armour;

    @Shadow public Player player;


//    @Inject(method = "method_687", at = @At("TAIL"), locals = LocalCapture.CAPTURE_FAILHARD)
//    public void changeArmorReturn(CallbackInfoReturnable<Integer> cir, int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
//        if (((PlayerEntityOriginsDuck)player).getOrigin() == Origin.SHULK) {
//            cir.setReturnValue(Math.min((var3 == 0 ? 0 : (var1 - 1) * var2 / var3 + 1) + 2, 20));
//        } else {
//            cir.setReturnValue(cir.getReturnValueI());
//        }
//    }


    /**
     * @author UltrusBot
     */
    @Overwrite
    public int method_687() {
        int var1 = 0;
        int var2 = 0;
        int var3 = 0;

        for(int var4 = 0; var4 < this.armour.length; ++var4) {
            if (this.armour[var4] != null && this.armour[var4].getType() instanceof ArmourItem) {
                int var5 = this.armour[var4].method_723();
                int var6 = this.armour[var4].method_721();
                int var7 = var5 - var6;
                var2 += var7;
                var3 += var5;
                int var8 = ((ArmourItem)this.armour[var4].getType()).field_2084;
                var1 += var8;
            }
        }
        int returnValue = var3 == 0 ? 0 : (var1 - 1) * var2 / var3 + 1;
        if (((PlayerEntityOriginsDuck)player).getOrigin() == Origin.SHULK) {
            return Math.min(returnValue + 4, 20);
        }
        return returnValue;

    }

}
