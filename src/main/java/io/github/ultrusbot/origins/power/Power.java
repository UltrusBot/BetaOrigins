package io.github.ultrusbot.origins.power;

import net.minecraft.entity.player.Player;

public abstract class Power {

    public Power() {

    }
    public abstract boolean isActive(Player player);

}
