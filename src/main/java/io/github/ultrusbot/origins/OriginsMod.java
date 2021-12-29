package io.github.ultrusbot.origins;

import java.io.IOException;

import io.github.minecraftcursedlegacy.api.config.Configs;
import io.github.minecraftcursedlegacy.api.recipe.Recipes;
import io.github.minecraftcursedlegacy.api.registry.Id;
import io.github.minecraftcursedlegacy.api.registry.Registries;
import io.github.minecraftcursedlegacy.api.registry.Translations;
import io.github.ultrusbot.origins.item.OrbOfOriginItemType;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.ItemInstance;
import net.minecraft.item.ItemType;
import net.minecraft.tile.Tile;
import tk.valoeghese.zoesteriaconfig.api.container.WritableConfig;
import tk.valoeghese.zoesteriaconfig.api.template.ConfigTemplate;

public class OriginsMod implements ModInitializer {
	public static ItemType ORB_OF_ORIGIN = Registries.ITEM_TYPE.register(new Id("origins:orb_of_origin"), id -> new OrbOfOriginItemType(id).setName("orbofOrigins"));;
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		System.out.println("Origins is loading!");
		Translations.addItemTranslation(ORB_OF_ORIGIN, "Orb of Origin");
		Recipes.addShapelessRecipe(new ItemInstance(ORB_OF_ORIGIN), Tile.DIRT);
		Recipes.addShapelessRecipe(new ItemInstance(ItemType.chestplateDiamond), Tile.SAND);

		// example config
		try {
			config = Configs.loadOrCreate(new Id("origins", "example"),
					ConfigTemplate.builder()
					.addContainer("exampleContainer", container -> container.addDataEntry("someData", "0.5"))
					.build());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		System.out.println(config.getDoubleValue("exampleContainer.someData"));
	}

	private static WritableConfig config;
}
