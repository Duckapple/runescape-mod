package dev.simongreen.runescape;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

import static dev.simongreen.runescape.RuneScapeMod.*;

public class RuneScapeModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(RUNE_ALTAR, RenderLayer.getCutout());
    }
}
