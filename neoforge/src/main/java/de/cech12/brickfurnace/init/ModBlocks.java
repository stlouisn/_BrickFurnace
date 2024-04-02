package de.cech12.brickfurnace.init;

import de.cech12.brickfurnace.BrickFurnaceMod;
import de.cech12.brickfurnace.Constants;
import de.cech12.brickfurnace.block.BrickBlastFurnaceBlock;
import de.cech12.brickfurnace.block.BrickFurnaceBlock;
import de.cech12.brickfurnace.block.BrickSmokerBlock;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import java.util.function.ToIntFunction;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.createBlocks(BrickFurnaceMod.MOD_ID);

    static {
        Constants.BRICK_FURNACE_BLOCK = BLOCKS.register(Constants.BRICK_FURNACE_NAME, () -> new BrickFurnaceBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).requiresCorrectToolForDrops().strength(2.0F, 6.0F).lightLevel(getLightLevelWhenLit(13))));
        Constants.BRICK_BLAST_FURNACE_BLOCK = BLOCKS.register(Constants.BRICK_BLAST_FURNACE_NAME, () -> new BrickBlastFurnaceBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).requiresCorrectToolForDrops().strength(2.0F, 6.0F).lightLevel(getLightLevelWhenLit(13))));
        Constants.BRICK_SMOKER_BLOCK = BLOCKS.register(Constants.BRICK_SMOKER_NAME, () -> new BrickSmokerBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).requiresCorrectToolForDrops().strength(2.0F, 6.0F).lightLevel(getLightLevelWhenLit(13))));
    }

    private static ToIntFunction<BlockState> getLightLevelWhenLit(final int lightLevel) {
        return (blockState) -> blockState.getValue(BlockStateProperties.LIT) ? lightLevel : 0;
    }

}