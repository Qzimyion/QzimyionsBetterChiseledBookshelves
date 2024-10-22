package com.qzimyion;

import com.qzimyion.client.QCBItemTexturesUtil;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.function.UnaryOperator;

public class QCBDataComponents {

    public static final ComponentType<List<QCBItemTexturesUtil>> ITEM_TEXTURES = register("item_textures", (builder) -> {
        return builder.codec(QCBItemTexturesUtil.CODEC.listOf()).cache();
    });

    private static <T> ComponentType<T> register(String id, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return (ComponentType) Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(QzimyionsBetterChiseledBookshelves.MOD_ID, id), ((ComponentType.Builder) builderOperator.apply(ComponentType.builder())).build());
    }

    public static void init(){

    }
}
