package cech12.brickfurnace.init;

import cech12.brickfurnace.BrickFurnaceMod;
import cech12.brickfurnace.api.block.BrickFurnaceBlocks;
import cech12.brickfurnace.api.tileentity.BrickFurnaceTileEntities;
import cech12.brickfurnace.tileentity.BrickBlastFurnaceTileEntity;
import cech12.brickfurnace.tileentity.BrickFurnaceTileEntity;
import cech12.brickfurnace.tileentity.BrickSmokerTileEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid= BrickFurnaceMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ModTileEntities {

    @SubscribeEvent
    public static void registerTileEntities(RegistryEvent.Register<BlockEntityType<?>> event) {
        BrickFurnaceTileEntities.BRICK_FURNACE = register(BrickFurnaceTileEntity::new, "brick_furnace", BrickFurnaceBlocks.BRICK_FURNACE, event);
        BrickFurnaceTileEntities.BRICK_BLAST_FURNACE = register(BrickBlastFurnaceTileEntity::new, "brick_blast_furnace", BrickFurnaceBlocks.BRICK_BLAST_FURNACE, event);
        BrickFurnaceTileEntities.BRICK_SMOKER = register(BrickSmokerTileEntity::new, "brick_smoker", BrickFurnaceBlocks.BRICK_SMOKER, event);
    }

    private static <T extends BlockEntity> BlockEntityType<T> register(BlockEntityType.BlockEntitySupplier<T> supplier, String registryName, Block block, RegistryEvent.Register<BlockEntityType<?>> registryEvent) {
        BlockEntityType<T> tileEntityType = BlockEntityType.Builder.of(supplier, block).build(null);
        tileEntityType.setRegistryName(registryName);
        registryEvent.getRegistry().register(tileEntityType);
        return tileEntityType;
    }

}
