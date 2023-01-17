package cech12.brickfurnace.jei;

import cech12.brickfurnace.init.ModBlocks;
import cech12.brickfurnace.init.ModRecipeTypes;
import cech12.brickfurnace.config.ServerConfig;
import cech12.brickfurnace.crafting.BrickBlastingRecipe;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.library.plugins.vanilla.cooking.AbstractCookingCategory;

import javax.annotation.Nonnull;

public class BrickBlastingCategory extends AbstractCookingCategory<BrickBlastingRecipe> {

    public BrickBlastingCategory(IGuiHelper guiHelper) {
        super(guiHelper, ModBlocks.BRICK_BLAST_FURNACE.get(), "gui.jei.category.blasting", (int) (100 * ServerConfig.COOK_TIME_FACTOR.get()));
    }

    @Override
    @Nonnull
    public RecipeType<BrickBlastingRecipe> getRecipeType() {
        return new RecipeType<>(ModRecipeTypes.BLASTING.getId(), BrickBlastingRecipe.class);
    }

}
