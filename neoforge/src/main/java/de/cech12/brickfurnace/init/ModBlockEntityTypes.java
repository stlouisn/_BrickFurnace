package de.cech12.brickfurnace.init;

import de.cech12.brickfurnace.BrickFurnaceMod;
import de.cech12.brickfurnace.Constants;
import de.cech12.brickfurnace.blockentity.BrickBlastFurnaceBlockEntity;
import de.cech12.brickfurnace.blockentity.BrickFurnaceBlockEntity;
import de.cech12.brickfurnace.blockentity.BrickSmokerBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModBlockEntityTypes {

    public static DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, BrickFurnaceMod.MOD_ID);

    static {
        Constants.BRICK_FURNACE_BLOCK_ENTITY_TYPE = BLOCK_ENTITY_TYPES.register(Constants.BRICK_FURNACE_NAME, () -> BlockEntityType.Builder.of(BrickFurnaceBlockEntity::new, Constants.BRICK_FURNACE_BLOCK.get()).build(null));
        Constants.BRICK_BLAST_FURNACE_BLOCK_ENTITY_TYPE = BLOCK_ENTITY_TYPES.register(Constants.BRICK_BLAST_FURNACE_NAME, () -> BlockEntityType.Builder.of(BrickBlastFurnaceBlockEntity::new, Constants.BRICK_BLAST_FURNACE_BLOCK.get()).build(null));
        Constants.BRICK_SMOKER_BLOCK_ENTITY_TYPE = BLOCK_ENTITY_TYPES.register(Constants.BRICK_SMOKER_NAME, () -> BlockEntityType.Builder.of(BrickSmokerBlockEntity::new, Constants.BRICK_SMOKER_BLOCK.get()).build(null));
    }

}
