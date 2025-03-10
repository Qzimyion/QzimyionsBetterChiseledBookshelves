package com.qzimyion.client;

import com.qzimyion.QzimyionsBetterChiseledBookshelves;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.entity.ChiseledBookshelfBlockEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

import java.util.Objects;

public class QBCBBlockEntityRenderer implements BlockEntityRenderer<ChiseledBookshelfBlockEntity> {
    public static final EntityModelLayer MODEL_LAYER = new EntityModelLayer(QzimyionsBetterChiseledBookshelves.id("chiseled_bookshelf"), "chiseled_bookshelf");
    public static final Identifier ATLAS_ID = QzimyionsBetterChiseledBookshelves.id("textures/atlas/chiseled_bookshelf.png");
    private final ModelPart[] bookModels = new ModelPart[6];

    public static TexturedModelData getTexturedModelData(){
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        for (int i = 0; i < 6; i++) {
            float x = (i % 3) * 5.3f + 1.5f;
            float y = ((float) i / 3) * 6f + 2f;
            modelPartData.addChild("book_" + i, ModelPartBuilder.create().uv(0, 0).cuboid(x, y, 15f, 4f, 5f, 1f), ModelTransform.NONE);
        }
        return TexturedModelData.of(modelData, 16, 16);
    }

    public QBCBBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
        var model = ctx.getLayerModelPart(MODEL_LAYER);
        for (int i = 0; i < 6; i++) {
            bookModels[i] = model.getChild("book_" + i);
        }
    }

    @Override
    public void render(ChiseledBookshelfBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        BlockState state = entity.getCachedState();
        var facing = state.get(HorizontalFacingBlock.FACING);
        light = WorldRenderer.getLightmapCoordinates(Objects.requireNonNull(entity.getWorld()), entity.getPos().offset(facing));

        matrices.push();
        matrices.translate(0.5f, 0, 0.5f);
        matrices.multiply(RotationAxis.NEGATIVE_Y.rotationDegrees(facing.asRotation()));
        matrices.translate(-0.5f, 0, -0.5f);

        for (int i = 0; i < 6; i++) {
            var stack = entity.getStack(i);
            if (!stack.isEmpty()) {
                var textureId = Registries.ITEM.getId(stack.getItem()).withPrefixedPath("entity/chiseled_bookshelf/");
                var spriteId = new SpriteIdentifier(ATLAS_ID, textureId);
                var vertexConsumer = spriteId.getVertexConsumer(vertexConsumers, RenderLayer::getEntityCutout);
                bookModels[i].render(matrices, vertexConsumer, light, overlay);
            }
        }
        matrices.pop();
    }
}
