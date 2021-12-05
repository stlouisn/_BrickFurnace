package cech12.brickfurnace.init;


import cech12.brickfurnace.BrickFurnaceMod;
import cech12.brickfurnace.api.block.BrickFurnaceBlocks;
import cech12.brickfurnace.block.BrickBlastFurnaceBlock;
import cech12.brickfurnace.block.BrickFurnaceBlock;
import cech12.brickfurnace.block.BrickSmokerBlock;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.ToIntFunction;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

@Mod.EventBusSubscriber(modid= BrickFurnaceMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ModBlocks {

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        BrickFurnaceBlocks.BRICK_FURNACE = registerBlock("brick_furnace", CreativeModeTab.TAB_DECORATIONS, new BrickFurnaceBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_RED).requiresCorrectToolForDrops().strength(2.0F, 6.0F).lightLevel(getLightLevelWhenLit(13))));
        BrickFurnaceBlocks.BRICK_BLAST_FURNACE = registerBlock("brick_blast_furnace", CreativeModeTab.TAB_DECORATIONS, new BrickBlastFurnaceBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_RED).requiresCorrectToolForDrops().strength(2.0F, 6.0F).lightLevel(getLightLevelWhenLit(13))));
        BrickFurnaceBlocks.BRICK_SMOKER = registerBlock("brick_smoker", CreativeModeTab.TAB_DECORATIONS, new BrickSmokerBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_RED).requiresCorrectToolForDrops().strength(2.0F, 6.0F).lightLevel(getLightLevelWhenLit(13))));
    }

    private static ToIntFunction<BlockState> getLightLevelWhenLit(final int lightLevel) {
        return (blockState) -> blockState.getValue(BlockStateProperties.LIT) ? lightLevel : 0;
    }

    public static Block registerBlock(String name, CreativeModeTab itemGroup, Block block) {
        BlockItem itemBlock = new BlockItem(block, new Item.Properties().tab(itemGroup));
        block.setRegistryName(name);
        itemBlock.setRegistryName(name);
        ForgeRegistries.BLOCKS.register(block);
        ForgeRegistries.ITEMS.register(itemBlock);
        return block;
    }

}