package dev.simongreen.runescape;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;

import static dev.simongreen.runescape.RuneScapeMod.*;

public class RuneScapeModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        for (Block runeAltarBlock : RUNE_ALTARS) {
            BlockRenderLayerMap.INSTANCE.putBlock(runeAltarBlock, RenderLayer.getCutout());
        }
    }
}
