package cech12.brickfurnace.crafting;

import cech12.brickfurnace.init.ModBlocks;
import cech12.brickfurnace.init.ModRecipeTypes;
import cech12.brickfurnace.config.ServerConfig;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.BlastingRecipe;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.SimpleCookingSerializer;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;

public class BrickBlastingRecipe extends AbstractCookingRecipe {

    public static final SimpleCookingSerializer<BrickBlastingRecipe> SERIALIZER = new SimpleCookingSerializer<>(BrickBlastingRecipe::new, 100);

    public BrickBlastingRecipe(ResourceLocation p_i50031_1_, String p_i50031_2_, CookingBookCategory category, Ingredient p_i50031_3_, ItemStack p_i50031_4_, float p_i50031_5_, int p_i50031_6_) {
        super(ModRecipeTypes.BLASTING.get(), p_i50031_1_, p_i50031_2_, category, p_i50031_3_, p_i50031_4_, p_i50031_5_, p_i50031_6_);
    }

    public static BrickBlastingRecipe convert(@Nonnull BlastingRecipe recipe) {
        return new BrickBlastingRecipe(recipe.getId(), recipe.getGroup(), recipe.category(), recipe.getIngredients().get(0), recipe.getResultItem(), recipe.getExperience(), (int) (recipe.getCookingTime() * ServerConfig.COOK_TIME_FACTOR.get()));
    }

    @Override
    @Nonnull
    public ItemStack getToastSymbol() {
        return new ItemStack(ModBlocks.BRICK_BLAST_FURNACE.get());
    }

    @Override
    @Nonnull
    public RecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }

}
