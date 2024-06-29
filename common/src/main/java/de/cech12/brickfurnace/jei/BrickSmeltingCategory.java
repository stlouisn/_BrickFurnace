package de.cech12.brickfurnace.jei;

import de.cech12.brickfurnace.Constants;
import de.cech12.brickfurnace.crafting.BrickSmeltingRecipe;
import de.cech12.brickfurnace.platform.Services;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.library.plugins.vanilla.cooking.AbstractCookingCategory;
import net.minecraft.world.item.crafting.RecipeHolder;

import javax.annotation.Nonnull;

public class BrickSmeltingCategory extends AbstractCookingCategory<BrickSmeltingRecipe> {

    public BrickSmeltingCategory(IGuiHelper guiHelper) {
        super(guiHelper, Constants.BRICK_FURNACE_BLOCK.get(), "gui.jei.category.smelting", (int) (200 * Services.CONFIG.getCookTimeFactor()));
    }

    @Override
    @Nonnull
    public RecipeType<RecipeHolder<BrickSmeltingRecipe>> getRecipeType() {
        Class<? extends RecipeHolder<BrickSmeltingRecipe>> holderClass = (Class<? extends RecipeHolder<BrickSmeltingRecipe>>) (Object) RecipeHolder.class;
        return new RecipeType<>(Constants.id(Constants.SMELTING_NAME), holderClass);
    }

}
