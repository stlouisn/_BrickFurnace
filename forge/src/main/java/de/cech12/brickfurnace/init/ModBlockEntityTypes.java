package de.cech12.brickfurnace.init;

import de.cech12.brickfurnace.BrickFurnaceMod;
import de.cech12.brickfurnace.Constants;
import de.cech12.brickfurnace.blockentity.BrickBlastFurnaceBlockEntity;
import de.cech12.brickfurnace.blockentity.BrickFurnaceBlockEntity;
import de.cech12.brickfurnace.blockentity.BrickSmokerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class ModBlockEntityTypes {

    public static DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, BrickFurnaceMod.MOD_ID);

    static {
        Constants.BRICK_FURNACE_BLOCK_ENTITY_TYPE = BLOCK_ENTITY_TYPES.register(Constants.BRICK_FURNACE_NAME, () -> BlockEntityType.Builder.of(BrickFurnaceBlockEntity::new, Constants.BRICK_FURNACE_BLOCK.get()).build(null));
        Constants.BRICK_BLAST_FURNACE_BLOCK_ENTITY_TYPE = BLOCK_ENTITY_TYPES.register(Constants.BRICK_BLAST_FURNACE_NAME, () -> BlockEntityType.Builder.of(BrickBlastFurnaceBlockEntity::new, Constants.BRICK_BLAST_FURNACE_BLOCK.get()).build(null));
        Constants.BRICK_SMOKER_BLOCK_ENTITY_TYPE = BLOCK_ENTITY_TYPES.register(Constants.BRICK_SMOKER_NAME, () -> BlockEntityType.Builder.of(BrickSmokerBlockEntity::new, Constants.BRICK_SMOKER_BLOCK.get()).build(null));
    }

}
