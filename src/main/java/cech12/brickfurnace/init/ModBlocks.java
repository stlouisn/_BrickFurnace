package cech12.brickfurnace.init;

import cech12.brickfurnace.BrickFurnaceMod;
import cech12.brickfurnace.block.BrickBlastFurnaceBlock;
import cech12.brickfurnace.block.BrickFurnaceBlock;
import cech12.brickfurnace.block.BrickSmokerBlock;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.ToIntFunction;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.RegistryObject;

public final class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, BrickFurnaceMod.MOD_ID);

    public static final RegistryObject<Block> BRICK_FURNACE = BLOCKS.register("brick_furnace", () -> new BrickFurnaceBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).requiresCorrectToolForDrops().strength(2.0F, 6.0F).lightLevel(getLightLevelWhenLit(13))));
    public static final RegistryObject<Block> BRICK_BLAST_FURNACE = BLOCKS.register("brick_blast_furnace", () -> new BrickBlastFurnaceBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).requiresCorrectToolForDrops().strength(2.0F, 6.0F).lightLevel(getLightLevelWhenLit(13))));
    public static final RegistryObject<Block> BRICK_SMOKER = BLOCKS.register("brick_smoker", () -> new BrickSmokerBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).requiresCorrectToolForDrops().strength(2.0F, 6.0F).lightLevel(getLightLevelWhenLit(13))));

    private static ToIntFunction<BlockState> getLightLevelWhenLit(final int lightLevel) {
        return (blockState) -> blockState.getValue(BlockStateProperties.LIT) ? lightLevel : 0;
    }

}