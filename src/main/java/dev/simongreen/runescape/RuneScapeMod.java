package dev.simongreen.runescape;

import dev.simongreen.runescape.block.RuneAltar;
import dev.simongreen.runescape.item.RuneAltarItem;
import dev.simongreen.runescape.item.Talisman;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
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
	public static final Item AIR_TALISMAN = registerBasic("air_talisman", new Talisman(new FabricItemSettings()));
	public static final Block RUNE_ALTAR = Registry.register(Registry.BLOCK, new Identifier(NAMESPACE, "rune_altar"), new RuneAltar(FabricBlockSettings.of(Material.STONE).nonOpaque()));
	public static final Item RUNE_ALTAR_ITEM = registerBasic("rune_altar", new RuneAltarItem(RUNE_ALTAR, new FabricItemSettings()));

	private static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.create(new Identifier(NAMESPACE, "all_items"))
			.icon(() -> new ItemStack(AIR_RUNE))
			.appendItems(itemStacks -> {
				itemStacks.add(new ItemStack(AIR_RUNE));
				itemStacks.add(new ItemStack(FIRE_RUNE));
				itemStacks.add(new ItemStack(EARTH_RUNE));
				itemStacks.add(new ItemStack(WATER_RUNE));
				itemStacks.add(new ItemStack(AIR_TALISMAN));
				itemStacks.add(new ItemStack(RUNE_ALTAR_ITEM));
			})
			.build();

	public static Item registerBasic(String path) {
		return registerBasic(path, new Item(new FabricItemSettings()));
	}
	public static Item registerBasic(String path, Item item) {
		return Registry.register(Registry.ITEM, new Identifier(NAMESPACE, path), item);
	}
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");
	}

}
