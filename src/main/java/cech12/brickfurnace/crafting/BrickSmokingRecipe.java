package cech12.brickfurnace.crafting;

import cech12.brickfurnace.init.ModBlocks;
import cech12.brickfurnace.init.ModRecipeTypes;
import cech12.brickfurnace.config.ServerConfig;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.SimpleCookingSerializer;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.SmokingRecipe;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;

public class BrickSmokingRecipe extends AbstractCookingRecipe {

    public static final SimpleCookingSerializer<BrickSmokingRecipe> SERIALIZER = new SimpleCookingSerializer<>(BrickSmokingRecipe::new, 100);

    public BrickSmokingRecipe(ResourceLocation p_i50031_1_, String p_i50031_2_, Ingredient p_i50031_3_, ItemStack p_i50031_4_, float p_i50031_5_, int p_i50031_6_) {
        super(ModRecipeTypes.SMOKING.get(), p_i50031_1_, p_i50031_2_, p_i50031_3_, p_i50031_4_, p_i50031_5_, p_i50031_6_);
    }

    public static BrickSmokingRecipe convert(@Nonnull SmokingRecipe recipe) {
        return new BrickSmokingRecipe(recipe.getId(), recipe.getGroup(), recipe.getIngredients().get(0), recipe.getResultItem(), recipe.getExperience(), (int) (recipe.getCookingTime() * ServerConfig.COOK_TIME_FACTOR.get()));
    }

    @Override
    @Nonnull
    public ItemStack getToastSymbol() {
        return new ItemStack(ModBlocks.BRICK_SMOKER.get());
    }

    @Override
    @Nonnull
    public RecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }

}
