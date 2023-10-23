package cech12.brickfurnace.jei;

import cech12.brickfurnace.init.ModBlocks;
import cech12.brickfurnace.init.ModRecipeTypes;
import cech12.brickfurnace.config.ServerConfig;
import cech12.brickfurnace.crafting.BrickSmokingRecipe;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.library.plugins.vanilla.cooking.AbstractCookingCategory;
import net.minecraft.world.item.crafting.RecipeHolder;

import javax.annotation.Nonnull;

public class BrickSmokingCategory extends AbstractCookingCategory<BrickSmokingRecipe> {

    public BrickSmokingCategory(IGuiHelper guiHelper) {
        super(guiHelper, ModBlocks.BRICK_SMOKER.get(), "gui.jei.category.smoking", (int) (100 * ServerConfig.COOK_TIME_FACTOR.get()));
    }

    @Override
    @Nonnull
    public RecipeType<RecipeHolder<BrickSmokingRecipe>> getRecipeType() {
        Class<? extends RecipeHolder<BrickSmokingRecipe>> holderClass = (Class<? extends RecipeHolder<BrickSmokingRecipe>>) (Object) RecipeHolder.class;
        return new RecipeType<>(ModRecipeTypes.SMOKING.getId(), holderClass);
    }

}
