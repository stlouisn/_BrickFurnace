package cech12.brickfurnace.jei;

import cech12.brickfurnace.api.block.BrickFurnaceBlocks;
import cech12.brickfurnace.api.crafting.RecipeTypes;
import cech12.brickfurnace.config.ServerConfig;
import cech12.brickfurnace.crafting.BrickBlastingRecipe;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.plugins.vanilla.cooking.AbstractCookingCategory;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;

public class BrickBlastingCategory extends AbstractCookingCategory<BrickBlastingRecipe> {

    public BrickBlastingCategory(IGuiHelper guiHelper) {
        super(guiHelper, BrickFurnaceBlocks.BRICK_BLAST_FURNACE, "gui.jei.category.blasting", (int) (100 * ServerConfig.COOK_TIME_FACTOR.get()));
    }

    @Override
    @Nonnull
    public RecipeType<BrickBlastingRecipe> getRecipeType() {
        return new RecipeType<>(RecipeTypes.BLASTING_ID, BrickBlastingRecipe.class);
    }

    @Override
    @Nonnull
    @Deprecated //is deprecated since 9.5.0, getRecipeType() should be used instead
    public ResourceLocation getUid() {
        return RecipeTypes.BLASTING_ID;
    }

    @Override
    @Nonnull
    @Deprecated //is deprecated since 9.5.0, getRecipeType() should be used instead
    public Class<? extends BrickBlastingRecipe> getRecipeClass() {
        return BrickBlastingRecipe.class;
    }
}
