package cech12.brickfurnace.jei;

import cech12.brickfurnace.init.ModBlocks;
import cech12.brickfurnace.init.ModRecipeTypes;
import cech12.brickfurnace.config.ServerConfig;
import cech12.brickfurnace.crafting.BrickSmokingRecipe;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.library.plugins.vanilla.cooking.AbstractCookingCategory;

import javax.annotation.Nonnull;

public class BrickSmokingCategory extends AbstractCookingCategory<BrickSmokingRecipe> {

    public BrickSmokingCategory(IGuiHelper guiHelper) {
        super(guiHelper, ModBlocks.BRICK_SMOKER.get(), "gui.jei.category.smoking", (int) (100 * ServerConfig.COOK_TIME_FACTOR.get()));
    }

    @Override
    @Nonnull
    public RecipeType<BrickSmokingRecipe> getRecipeType() {
        return new RecipeType<>(ModRecipeTypes.SMOKING.getId(), BrickSmokingRecipe.class);
    }

}
