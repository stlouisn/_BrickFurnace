package cech12.brickfurnace.init;

import cech12.brickfurnace.BrickFurnaceMod;
import cech12.brickfurnace.blockentity.AbstractBrickFurnaceBlockEntity;
import cech12.brickfurnace.blockentity.BrickBlastFurnaceBlockEntity;
import cech12.brickfurnace.blockentity.BrickFurnaceBlockEntity;
import cech12.brickfurnace.blockentity.BrickSmokerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ModBlockEntityTypes {

    public static DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, BrickFurnaceMod.MOD_ID);

    public static RegistryObject<BlockEntityType<? extends AbstractBrickFurnaceBlockEntity>> BRICK_FURNACE = BLOCK_ENTITY_TYPES.register("brick_furnace", () -> BlockEntityType.Builder.of(BrickFurnaceBlockEntity::new, ModBlocks.BRICK_FURNACE.get()).build(null));
    public static RegistryObject<BlockEntityType<? extends AbstractBrickFurnaceBlockEntity>> BRICK_BLAST_FURNACE = BLOCK_ENTITY_TYPES.register("brick_blast_furnace", () -> BlockEntityType.Builder.of(BrickBlastFurnaceBlockEntity::new, ModBlocks.BRICK_BLAST_FURNACE.get()).build(null));
    public static RegistryObject<BlockEntityType<? extends AbstractBrickFurnaceBlockEntity>> BRICK_SMOKER = BLOCK_ENTITY_TYPES.register("brick_smoker", () -> BlockEntityType.Builder.of(BrickSmokerBlockEntity::new, ModBlocks.BRICK_SMOKER.get()).build(null));

}
