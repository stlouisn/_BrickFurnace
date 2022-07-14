package cech12.brickfurnace.init;

import cech12.brickfurnace.BrickFurnaceMod;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BrickFurnaceMod.MOD_ID);

    public static final RegistryObject<Item> BRICK_FURNACE = fromBlock(ModBlocks.BRICK_FURNACE, CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Item> BRICK_BLAST_FURNACE = fromBlock(ModBlocks.BRICK_BLAST_FURNACE, CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Item> BRICK_SMOKER = fromBlock(ModBlocks.BRICK_SMOKER, CreativeModeTab.TAB_DECORATIONS);

    private static RegistryObject<Item> fromBlock(RegistryObject<Block> block, CreativeModeTab tab) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

}