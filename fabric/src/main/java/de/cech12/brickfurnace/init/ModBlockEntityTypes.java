package de.cech12.brickfurnace.init;

import de.cech12.brickfurnace.CommonLoader;
import de.cech12.brickfurnace.Constants;
import de.cech12.brickfurnace.blockentity.AbstractBrickFurnaceBlockEntity;
import de.cech12.brickfurnace.blockentity.BrickBlastFurnaceBlockEntity;
import de.cech12.brickfurnace.blockentity.BrickFurnaceBlockEntity;
import de.cech12.brickfurnace.blockentity.BrickSmokerBlockEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;

public final class ModBlockEntityTypes {

    private static final BlockEntityType<? extends AbstractBrickFurnaceBlockEntity> BRICK_FURNACE_BLOCK_ENTITY_TYPE = register(Constants.BRICK_FURNACE_NAME, BlockEntityType.Builder.of(BrickFurnaceBlockEntity::new, Constants.BRICK_FURNACE_BLOCK.get()));
    private static final BlockEntityType<? extends AbstractBrickFurnaceBlockEntity> BRICK_BLAST_FURNACE_BLOCK_ENTITY_TYPE = register(Constants.BRICK_BLAST_FURNACE_NAME, BlockEntityType.Builder.of(BrickBlastFurnaceBlockEntity::new, Constants.BRICK_BLAST_FURNACE_BLOCK.get()));
    private static final BlockEntityType<? extends AbstractBrickFurnaceBlockEntity> BRICK_SMOKER_BLOCK_ENTITY_TYPE = register(Constants.BRICK_SMOKER_NAME, BlockEntityType.Builder.of(BrickSmokerBlockEntity::new, Constants.BRICK_SMOKER_BLOCK.get()));

    static {
        Constants.BRICK_FURNACE_BLOCK_ENTITY_TYPE = () -> BRICK_FURNACE_BLOCK_ENTITY_TYPE;
        Constants.BRICK_BLAST_FURNACE_BLOCK_ENTITY_TYPE = () -> BRICK_BLAST_FURNACE_BLOCK_ENTITY_TYPE;
        Constants.BRICK_SMOKER_BLOCK_ENTITY_TYPE = () -> BRICK_SMOKER_BLOCK_ENTITY_TYPE;
    }

    public static void init() {}

    private static BlockEntityType<? extends AbstractBrickFurnaceBlockEntity> register(String name, BlockEntityType.Builder<? extends AbstractBrickFurnaceBlockEntity> blockEntityTypeBuilder) {
        return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, CommonLoader.id(name), blockEntityTypeBuilder.build(null));
    }

}
