package cech12.brickfurnace.block;

import cech12.brickfurnace.tileentity.BrickFurnaceTileEntity;
import net.minecraft.block.FurnaceBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

import net.minecraft.block.AbstractBlock;

public class BrickFurnaceBlock extends FurnaceBlock {

    public BrickFurnaceBlock(AbstractBlock.Properties builder) {
        super(builder);
    }

    @Override
    public TileEntity newBlockEntity(@Nonnull IBlockReader worldIn) {
        return new BrickFurnaceTileEntity();
    }

    /**
     * Interface for handling interaction with blocks that implement AbstractFurnaceBlock. Called in onBlockActivated
     * inside AbstractFurnaceBlock.
     */
    @Override
    protected void openContainer(World worldIn, @Nonnull BlockPos pos, @Nonnull PlayerEntity player) {
        TileEntity tileentity = worldIn.getBlockEntity(pos);
        if (tileentity instanceof BrickFurnaceTileEntity) {
            player.openMenu((INamedContainerProvider)tileentity);
            player.awardStat(Stats.INTERACT_WITH_FURNACE);
        }

    }
}