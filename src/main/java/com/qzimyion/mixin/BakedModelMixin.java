package com.qzimyion.mixin;

import com.qzimyion.client.QBCBBlockEntityRenderer;
import net.minecraft.client.render.model.BakedModelManager;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.Map;

@Mixin(BakedModelManager.class)
public class BakedModelMixin {

    @Mutable
    @Shadow @Final private static Map<Identifier, Identifier> LAYERS_TO_LOADERS;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void appendToAtlases(CallbackInfo ci) {
        LAYERS_TO_LOADERS = new HashMap<>(LAYERS_TO_LOADERS);
        LAYERS_TO_LOADERS.put(QBCBBlockEntityRenderer.ATLAS_ID, QBCBBlockEntityRenderer.id("scroll_shelf"));
    }
}
