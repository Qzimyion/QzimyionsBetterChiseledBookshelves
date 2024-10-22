package com.qzimyion.client;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.qzimyion.QzimyionsBetterChiseledBookshelves;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.entry.RegistryElementCodec;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.dynamic.Codecs;

public record QCBItemTexturesUtil(Identifier itemId, Identifier texturePath, String translationKey) {

    public static final Codec<QCBItemTexturesUtil> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            Identifier.CODEC.fieldOf("item").forGetter(QCBItemTexturesUtil::itemId),
            Identifier.CODEC.fieldOf("texture_path").forGetter(QCBItemTexturesUtil::texturePath),
            Codecs.NON_EMPTY_STRING.fieldOf("translation_key").forGetter(QCBItemTexturesUtil::translationKey)
    ).apply(instance, QCBItemTexturesUtil::new));

    public static final PacketCodec<ByteBuf, QCBItemTexturesUtil> PACKET_CODEC;
    public static final Codec<RegistryEntry<QCBItemTexturesUtil>> ENTRY_CODEC;
    public static final PacketCodec<RegistryByteBuf, RegistryEntry<QCBItemTexturesUtil>> ENTRY_PACKET_CODEC;

    public QCBItemTexturesUtil(Identifier itemId, Identifier texturePath, String translationKey){
        this.itemId = itemId;
        this.texturePath = texturePath;
        this.translationKey = translationKey;
    }

    public Identifier texturePath() {
        return this.texturePath;
    }

    public Identifier itemId() {
        return this.itemId;
    }

    static {
        PACKET_CODEC = PacketCodec.tuple(Identifier.PACKET_CODEC, QCBItemTexturesUtil::itemId, Identifier.PACKET_CODEC, QCBItemTexturesUtil::texturePath, PacketCodecs.STRING, QCBItemTexturesUtil::translationKey, QCBItemTexturesUtil::new);
        ENTRY_CODEC = RegistryElementCodec.of(QzimyionsBetterChiseledBookshelves.PIZZA_TOPPING_VARIANT, CODEC);
        ENTRY_PACKET_CODEC = PacketCodecs.registryEntry(QzimyionsBetterChiseledBookshelves.PIZZA_TOPPING_VARIANT, PACKET_CODEC);
    }
}
