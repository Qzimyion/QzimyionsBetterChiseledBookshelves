package com.qzimyion.client;

import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.entity.ChiseledBookshelfBlockEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

import java.util.Objects;

import static com.qzimyion.QzimyionsBetterChiseledBookshelves.MOD_ID;

public class QBCBBlockEntityRenderer implements BlockEntityRenderer<ChiseledBookshelfBlockEntity> {

    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }

    public static final EntityModelLayer MODEL_LAYER = new EntityModelLayer(id("/neo_chiseled_bookshelf"), "neo_chiseled_bookshelf");
    public static final Identifier ATLAS_ID = id("textures/atlas/neo_chiseled_bookshelf.png");
    private final ModelPart[] modelParts = new ModelPart[2 * 3];

    public static TexturedModelData getTexturedModelData(){
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        for (int i = 0; i < 3*2; i++){
            var x = i % 3;
            var y = i / 2;
            modelPartData.addChild("item_variant_" + i,
                    ModelPartBuilder.create()
                            .uv(0, 0)
                            .cuboid(15f/3 * x + 1.5f, 15f/2 * y + 1f, 16f, 4f, 6f, 0f,
                                    new Dilation(0.0F)),
                    ModelTransform.NONE);
        }
        return TexturedModelData.of(modelData, 16, 16);
    }

    public QBCBBlockEntityRenderer(BlockEntityRendererFactory.Context ctx){
        var model = ctx.getLayerModelPart(MODEL_LAYER);
        for (int i = 0; i < 3*2; i++){
            modelParts[i] = model.getChild("item_variant_" + i);
        }
    }

    @Override
    public void render(ChiseledBookshelfBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        var facing = entity.getCachedState().get(HorizontalFacingBlock.FACING);
        light = WorldRenderer.getLightmapCoordinates(Objects.requireNonNull(entity.getWorld()), entity.getPos().offset(facing));
        matrices.push();
        matrices.translate(0.5f, 0, 0.5f);
        matrices.multiply(RotationAxis.NEGATIVE_Y.rotationDegrees(facing.asRotation()));
        matrices.translate(-0.5f, 0, -0.5f);

        for (int i = 0; i <  3 * 2 ; i++){
            var stack = entity.getStack(i);
            if (!stack.isEmpty()){
                var textureId = Registries.ITEM.getId(stack.getItem()).withPrefixedPath("entity/neo_chiseled_bookshelf/");
                var spriteId = new SpriteIdentifier(ATLAS_ID, textureId);
                var vertexConsumer = spriteId.getVertexConsumer(vertexConsumers, RenderLayer::getEntityCutout);
                modelParts[i].render(matrices, vertexConsumer, light, overlay);
            }
        }
        matrices.pop();
    }
}
