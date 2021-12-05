package cech12.brickfurnace.crafting;

import cech12.brickfurnace.api.block.BrickFurnaceBlocks;
import cech12.brickfurnace.api.crafting.RecipeTypes;
import cech12.brickfurnace.config.ServerConfig;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.SimpleCookingSerializer;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;

public class BrickSmeltingRecipe extends AbstractCookingRecipe {

    public static final SimpleCookingSerializer<BrickSmeltingRecipe> SERIALIZER = new SimpleCookingSerializer<>(BrickSmeltingRecipe::new, 200);
    static {
        SERIALIZER.setRegistryName(RecipeTypes.SMELTING_ID);
    }

    public BrickSmeltingRecipe(ResourceLocation p_i50031_1_, String p_i50031_2_, Ingredient p_i50031_3_, ItemStack p_i50031_4_, float p_i50031_5_, int p_i50031_6_) {
        super(RecipeTypes.SMELTING, p_i50031_1_, p_i50031_2_, p_i50031_3_, p_i50031_4_, p_i50031_5_, p_i50031_6_);
    }

    public static BrickSmeltingRecipe convert(@Nonnull SmeltingRecipe recipe) {
        return new BrickSmeltingRecipe(recipe.getId(), recipe.getGroup(), recipe.getIngredients().get(0), recipe.getResultItem(), recipe.getExperience(), (int) (recipe.getCookingTime() * ServerConfig.COOK_TIME_FACTOR.get()));
    }

    @Override
    @Nonnull
    public ItemStack getToastSymbol() {
        return new ItemStack(BrickFurnaceBlocks.BRICK_FURNACE);
    }

    @Override
    @Nonnull
    public RecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }

}
