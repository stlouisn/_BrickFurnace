package de.cech12.brickfurnace.blockentity;

import de.cech12.brickfurnace.Constants;
import de.cech12.brickfurnace.platform.Services;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.BlastFurnaceMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nonnull;

public class BrickBlastFurnaceBlockEntity extends AbstractBrickFurnaceBlockEntity {

    public BrickBlastFurnaceBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(Constants.BRICK_BLAST_FURNACE_BLOCK_ENTITY_TYPE.get(), blockPos, blockState, Constants.BLASTING_RECIPE_TYPE.get(), RecipeType.BLASTING);
    }

    @Override
    @Nonnull
    protected Component getDefaultName() {
        return Component.translatable("block.brickfurnace.brick_blast_furnace");
    }

    @Override
    protected int getBurnDuration(@Nonnull ItemStack stack) {
        return (int) (super.getBurnDuration(stack) * (0.5D * Services.CONFIG.getCookTimeFactor()));
    }

    @Override
    @Nonnull
    protected AbstractContainerMenu createMenu(int id, @Nonnull Inventory player) {
        return new BlastFurnaceMenu(id, player, this, this.dataAccess);
    }

}
