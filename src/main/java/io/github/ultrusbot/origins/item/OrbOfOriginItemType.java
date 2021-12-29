package io.github.ultrusbot.origins.item;

import io.github.ultrusbot.origins.duck.PlayerEntityOriginsDuck;
import io.github.ultrusbot.origins.origin.Origin;
import net.minecraft.entity.player.Player;
import net.minecraft.item.ItemInstance;
import net.minecraft.item.ItemType;
import net.minecraft.level.Level;

public class OrbOfOriginItemType extends ItemType {
    public OrbOfOriginItemType(int id) {
        super(id);
    }

    @Override
    public ItemInstance use(ItemInstance arg, Level arg1, Player arg2) {
        Origin randOrigin = Origin.values()[rand.nextInt(Origin.values().length-1) + 1];
        arg2.sendTranslatedMessage("You are now a: " + randOrigin.name);
        ((PlayerEntityOriginsDuck)arg2).setOrigin(randOrigin);
        return super.use(arg, arg1, arg2);

    }
}
