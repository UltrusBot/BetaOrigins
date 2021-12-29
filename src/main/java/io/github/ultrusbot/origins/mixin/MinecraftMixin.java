package io.github.ultrusbot.origins.mixin;

import io.github.ultrusbot.origins.duck.OptionsDuck;
import io.github.ultrusbot.origins.duck.PlayerEntityOriginsDuck;
import io.github.ultrusbot.origins.origin.Origin;
import net.minecraft.client.Minecraft;
import net.minecraft.client.options.GameOptions;
import net.minecraft.entity.player.ClientPlayer;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {


    @Shadow public GameOptions options;

    @Shadow public ClientPlayer player;

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Keyboard;getEventKey()I", ordinal = 2, shift = At.Shift.BY, by = -1))
    public void MinecrafTest(CallbackInfo ci) {
        if (Keyboard.getEventKey() == ((OptionsDuck)this.options).getPrimaryActiveKey().key) {
            this.player.sendTranslatedMessage("You pressed the primary active key!");
            if (((PlayerEntityOriginsDuck)this.player).getOrigin() == Origin.ELYTRIAN) {
                player.velocityY += 1.5f;
            }
        }
        if (Keyboard.getEventKey() == ((OptionsDuck)this.options).getSecondaryActiveKey().key) {
            this.player.sendTranslatedMessage("You pressed the secondary active key!");
        }
    }
}
