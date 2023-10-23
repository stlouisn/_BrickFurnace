package cech12.brickfurnace.jei;

import cech12.brickfurnace.init.ModBlocks;
import cech12.brickfurnace.init.ModRecipeTypes;
import cech12.brickfurnace.config.ServerConfig;
import cech12.brickfurnace.crafting.BrickSmeltingRecipe;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.library.plugins.vanilla.cooking.AbstractCookingCategory;
import net.minecraft.world.item.crafting.RecipeHolder;

import javax.annotation.Nonnull;

public class BrickSmeltingCategory extends AbstractCookingCategory<BrickSmeltingRecipe> {

    public BrickSmeltingCategory(IGuiHelper guiHelper) {
        super(guiHelper, ModBlocks.BRICK_FURNACE.get(), "gui.jei.category.smelting", (int) (200 * ServerConfig.COOK_TIME_FACTOR.get()));
    }

    @Override
    @Nonnull
    public RecipeType<RecipeHolder<BrickSmeltingRecipe>> getRecipeType() {
        Class<? extends RecipeHolder<BrickSmeltingRecipe>> holderClass = (Class<? extends RecipeHolder<BrickSmeltingRecipe>>) (Object) RecipeHolder.class;
        return new RecipeType<>(ModRecipeTypes.SMELTING.getId(), holderClass);
    }

}
