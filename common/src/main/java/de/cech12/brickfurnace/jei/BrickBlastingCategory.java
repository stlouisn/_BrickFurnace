package de.cech12.brickfurnace.jei;

import de.cech12.brickfurnace.Constants;
import de.cech12.brickfurnace.crafting.BrickBlastingRecipe;
import de.cech12.brickfurnace.platform.Services;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.library.plugins.vanilla.cooking.AbstractCookingCategory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeHolder;

import javax.annotation.Nonnull;

public class BrickBlastingCategory extends AbstractCookingCategory<BrickBlastingRecipe> {

    public BrickBlastingCategory(IGuiHelper guiHelper) {
        super(guiHelper, Constants.BRICK_BLAST_FURNACE_BLOCK.get(), "gui.jei.category.blasting", (int) (100 * Services.CONFIG.getCookTimeFactor()));
    }

    @Override
    @Nonnull
    public RecipeType<RecipeHolder<BrickBlastingRecipe>> getRecipeType() {
        Class<? extends RecipeHolder<BrickBlastingRecipe>> holderClass = (Class<? extends RecipeHolder<BrickBlastingRecipe>>) (Object) RecipeHolder.class;
        return new RecipeType<>(new ResourceLocation(Constants.MOD_ID, Constants.BLASTING_NAME), holderClass);
    }

}
