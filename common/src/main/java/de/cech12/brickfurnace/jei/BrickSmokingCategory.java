package de.cech12.brickfurnace.jei;

import de.cech12.brickfurnace.Constants;
import de.cech12.brickfurnace.crafting.BrickSmokingRecipe;
import de.cech12.brickfurnace.platform.Services;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.library.plugins.vanilla.cooking.AbstractCookingCategory;
import net.minecraft.world.item.crafting.RecipeHolder;

import javax.annotation.Nonnull;

public class BrickSmokingCategory extends AbstractCookingCategory<BrickSmokingRecipe> {

    public BrickSmokingCategory(IGuiHelper guiHelper) {
        super(guiHelper, Constants.BRICK_SMOKER_BLOCK.get(), "gui.jei.category.smoking", (int) (100 * Services.CONFIG.getCookTimeFactor()));
    }

    @Override
    @Nonnull
    public RecipeType<RecipeHolder<BrickSmokingRecipe>> getRecipeType() {
        Class<? extends RecipeHolder<BrickSmokingRecipe>> holderClass = (Class<? extends RecipeHolder<BrickSmokingRecipe>>) (Object) RecipeHolder.class;
        return new RecipeType<>(Constants.id(Constants.SMOKING_NAME), holderClass);
    }

}
