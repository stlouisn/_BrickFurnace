package cech12.brickfurnace.jei;

import cech12.brickfurnace.api.block.BrickFurnaceBlocks;
import cech12.brickfurnace.api.crafting.RecipeTypes;
import cech12.brickfurnace.config.ServerConfig;
import cech12.brickfurnace.crafting.BrickSmokingRecipe;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.plugins.vanilla.cooking.AbstractCookingCategory;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;

public class BrickSmokingCategory extends AbstractCookingCategory<BrickSmokingRecipe> {

    public BrickSmokingCategory(IGuiHelper guiHelper) {
        super(guiHelper, BrickFurnaceBlocks.BRICK_SMOKER, "gui.jei.category.smoking", (int) (100 * ServerConfig.COOK_TIME_FACTOR.get()));
    }

    @Override
    @Nonnull
    public RecipeType<BrickSmokingRecipe> getRecipeType() {
        return new RecipeType<>(RecipeTypes.SMOKING_ID, BrickSmokingRecipe.class);
    }

    @Override
    @Nonnull
    @Deprecated //is deprecated since 9.5.0, getRecipeType() should be used instead
    public ResourceLocation getUid() {
        return RecipeTypes.SMOKING_ID;
    }

    @Override
    @Nonnull
    @Deprecated //is deprecated since 9.5.0, getRecipeType() should be used instead
    public Class<? extends BrickSmokingRecipe> getRecipeClass() {
        return BrickSmokingRecipe.class;
    }
}
