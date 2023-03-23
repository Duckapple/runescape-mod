package dev.simongreen.runescape;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RuneScapeMod implements ModInitializer {
	public static final String NAMESPACE = "runescape";
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(NAMESPACE);

	public static final Item AIR_RUNE = registerBasic("air_rune");
	public static final Item FIRE_RUNE = registerBasic("fire_rune");
	public static final Item EARTH_RUNE = registerBasic("earth_rune");
	public static final Item WATER_RUNE = registerBasic("water_rune");

	private static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.create(new Identifier(NAMESPACE, "all_items"))
			.icon(() -> new ItemStack(AIR_RUNE))
			.appendItems(itemStacks -> {
				itemStacks.add(new ItemStack(AIR_RUNE));
				itemStacks.add(new ItemStack(FIRE_RUNE));
				itemStacks.add(new ItemStack(EARTH_RUNE));
				itemStacks.add(new ItemStack(WATER_RUNE));
			})
			.build();

	public static final Item registerBasic(String path) {
		return Registry.register(Registry.ITEM, new Identifier(NAMESPACE, path), new Item(new FabricItemSettings()));
	}
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");
	}
}
