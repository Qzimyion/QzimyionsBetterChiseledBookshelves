package com.qzimyion.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class QzimyionsBetterChiseledBookshelvesClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockEntityRendererFactories.register(BlockEntityType.CHISELED_BOOKSHELF, QBCBBlockEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(QBCBBlockEntityRenderer.MODEL_LAYER, QBCBBlockEntityRenderer::getTexturedModelData);
    }
}
