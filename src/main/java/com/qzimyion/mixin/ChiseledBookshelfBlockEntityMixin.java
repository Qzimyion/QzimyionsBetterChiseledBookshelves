package com.qzimyion.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.ChiseledBookshelfBlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ChiseledBookshelfBlockEntity.class)
public class ChiseledBookshelfBlockEntityMixin extends BlockEntity {


    public ChiseledBookshelfBlockEntityMixin(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        return createNbt(registryLookup);
    }
}
