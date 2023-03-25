package dev.simongreen.runescape;

import dev.simongreen.runescape.block.RuneAltar;
import dev.simongreen.runescape.common.RuneType;
import dev.simongreen.runescape.item.RuneAltarItem;
import dev.simongreen.runescape.item.Talisman;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
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

    public static final Item[] RUNES = new Item[RuneType.values().length];
    public static final Item RUNE_ESSENCE = registerBasic("rune_essence", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item AIR_TALISMAN = registerBasic("air_talisman", new Talisman(new FabricItemSettings()));
    public static final Block RUNE_ALTAR = Registry.register(Registry.BLOCK, new Identifier(NAMESPACE, "rune_altar"), new RuneAltar(FabricBlockSettings.of(Material.STONE).nonOpaque()));
    public static final Item RUNE_ALTAR_ITEM = registerBasic("rune_altar", new RuneAltarItem(RUNE_ALTAR, new FabricItemSettings()));

    private static ItemGroup ITEM_GROUP;

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
        var builder = FabricItemGroupBuilder.create(new Identifier(NAMESPACE, "all_items"));

        int i = 0;
        for (var runeType : RuneType.RUNE_TYPE.getValues()) {
            RUNES[i] = registerBasic(runeType.asString() + "_rune");
            var index = i++;
            builder.appendItems(list -> list.add(new ItemStack(RUNES[index])));
        }
        ITEM_GROUP = builder.appendItems(list -> {
            for (var rune : RUNES) {
                list.add(new ItemStack(rune));
            }
            list.add(new ItemStack(RUNE_ESSENCE));
            list.add(new ItemStack(AIR_TALISMAN));
            list.add(new ItemStack(RUNE_ALTAR_ITEM));
        }).icon(() -> new ItemStack(RUNES[0])).build();
        LOGGER.info("Hello Fabric world!");
    }
}
