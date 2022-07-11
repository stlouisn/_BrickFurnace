package cech12.brickfurnace.block;

import cech12.brickfurnace.blockentity.AbstractBrickFurnaceBlockEntity;
import cech12.brickfurnace.blockentity.BrickBlastFurnaceBlockEntity;
import cech12.brickfurnace.init.ModBlockEntities;
import net.minecraft.world.level.block.BlastFurnaceBlock;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.MenuProvider;
import net.minecraft.stats.Stats;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class BrickBlastFurnaceBlock extends BlastFurnaceBlock {

    public BrickBlastFurnaceBlock(BlockBehaviour.Properties builder) {
        super(builder);
    }

    @Override
    public BlockEntity newBlockEntity(@Nonnull BlockPos blockPos, @Nonnull BlockState blockState) {
        return new BrickBlastFurnaceBlockEntity(blockPos, blockState);
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@Nonnull Level level, @Nonnull BlockState state, @Nonnull BlockEntityType<T> entityType) {
        return createTickerHelper(entityType, (BlockEntityType<AbstractBrickFurnaceBlockEntity>) ModBlockEntities.BRICK_BLAST_FURNACE.get(), AbstractBrickFurnaceBlockEntity::tick);
    }

    /**
     * Interface for handling interaction with blocks that implement AbstractFurnaceBlock. Called in onBlockActivated
     * inside AbstractFurnaceBlock.
     */
    @Override
    protected void openContainer(Level worldIn, @Nonnull BlockPos pos, @Nonnull Player player) {
        BlockEntity blockEntity = worldIn.getBlockEntity(pos);
        if (blockEntity instanceof BrickBlastFurnaceBlockEntity) {
            player.openMenu((MenuProvider)blockEntity);
            player.awardStat(Stats.INTERACT_WITH_BLAST_FURNACE);
        }

    }
}
