package cech12.brickfurnace.blockentity;

import cech12.brickfurnace.api.crafting.RecipeTypes;
import cech12.brickfurnace.api.blockentity.BrickFurnaceBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.SmokerMenu;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nonnull;

public class BrickSmokerBlockEntity extends AbstractBrickFurnaceBlockEntity {

    public BrickSmokerBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(BrickFurnaceBlockEntities.BRICK_SMOKER, blockPos, blockState, RecipeTypes.SMOKING, RecipeType.SMOKING);
    }

    @Override
    @Nonnull
    protected Component getDefaultName() {
        return new TranslatableComponent("block.brickfurnace.brick_smoker");
    }

    @Override
    @Nonnull
    protected AbstractContainerMenu createMenu(int id, @Nonnull Inventory player) {
        return new SmokerMenu(id, player, this, this.dataAccess);
    }

}
