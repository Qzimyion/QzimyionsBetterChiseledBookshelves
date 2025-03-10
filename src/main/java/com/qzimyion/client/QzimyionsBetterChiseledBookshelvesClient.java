package com.qzimyion.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;

public class QzimyionsBetterChiseledBookshelvesClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(QBCBBlockEntityRenderer.MODEL_LAYER, QBCBBlockEntityRenderer::getTexturedModelData);
    }
}
