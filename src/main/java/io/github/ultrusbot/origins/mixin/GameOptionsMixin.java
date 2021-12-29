package io.github.ultrusbot.origins.mixin;

import io.github.ultrusbot.origins.duck.OptionsDuck;
import net.minecraft.client.options.GameOptions;
import net.minecraft.client.options.KeyBinding;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameOptions.class)
public class GameOptionsMixin implements OptionsDuck {
    @Shadow public KeyBinding forwardKey;
    @Shadow public KeyBinding leftKey;
    @Shadow public KeyBinding backKey;
    @Shadow public KeyBinding rightKey;
    @Shadow public KeyBinding jumpKey;
    @Shadow public KeyBinding sneakKey;
    @Shadow public KeyBinding dropKey;
    @Shadow public KeyBinding inventoryKey;
    @Shadow public KeyBinding chatKey;
    @Shadow public KeyBinding fogKey;
    @Shadow public KeyBinding[] keyBindings;
    public KeyBinding activeKey = new KeyBinding("key.primary", 34);
    public KeyBinding secondaryKey = new KeyBinding("key.secondary", 19);


//    this.keyBindings = new KeyBinding[];
    @Inject(method = "load", at = @At("HEAD"))
    private void initMix$Origins(CallbackInfo ci) {
        this.keyBindings = new KeyBinding[]{this.forwardKey, this.leftKey, this.backKey, this.rightKey, this.jumpKey, this.sneakKey, this.dropKey, this.inventoryKey, this.chatKey, this.fogKey, this.activeKey, this.secondaryKey};
    }

    @Override
    public KeyBinding getPrimaryActiveKey() {
        return activeKey;
    }

    @Override
    public KeyBinding getSecondaryActiveKey() {
        return secondaryKey;
    }
}
