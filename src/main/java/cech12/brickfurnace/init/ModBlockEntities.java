package cech12.brickfurnace.init;

import cech12.brickfurnace.BrickFurnaceMod;
import cech12.brickfurnace.api.block.BrickFurnaceBlocks;
import cech12.brickfurnace.api.blockentity.BrickFurnaceBlockEntities;
import cech12.brickfurnace.blockentity.BrickBlastFurnaceBlockEntity;
import cech12.brickfurnace.blockentity.BrickFurnaceBlockEntity;
import cech12.brickfurnace.blockentity.BrickSmokerBlockEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid= BrickFurnaceMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ModBlockEntities {

    @SubscribeEvent
    public static void registerBlockEntities(RegistryEvent.Register<BlockEntityType<?>> event) {
        BrickFurnaceBlockEntities.BRICK_FURNACE = register(BrickFurnaceBlockEntity::new, "brick_furnace", BrickFurnaceBlocks.BRICK_FURNACE, event);
        BrickFurnaceBlockEntities.BRICK_BLAST_FURNACE = register(BrickBlastFurnaceBlockEntity::new, "brick_blast_furnace", BrickFurnaceBlocks.BRICK_BLAST_FURNACE, event);
        BrickFurnaceBlockEntities.BRICK_SMOKER = register(BrickSmokerBlockEntity::new, "brick_smoker", BrickFurnaceBlocks.BRICK_SMOKER, event);
    }

    private static <T extends BlockEntity> BlockEntityType<T> register(BlockEntityType.BlockEntitySupplier<T> supplier, String registryName, Block block, RegistryEvent.Register<BlockEntityType<?>> registryEvent) {
        BlockEntityType<T> blockEntityType = BlockEntityType.Builder.of(supplier, block).build(null);
        blockEntityType.setRegistryName(registryName);
        registryEvent.getRegistry().register(blockEntityType);
        return blockEntityType;
    }

}
