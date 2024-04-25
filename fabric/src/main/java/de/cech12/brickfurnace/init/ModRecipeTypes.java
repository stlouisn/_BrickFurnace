package de.cech12.brickfurnace.init;

import de.cech12.brickfurnace.CommonLoader;
import de.cech12.brickfurnace.Constants;
import de.cech12.brickfurnace.crafting.BrickBlastingRecipe;
import de.cech12.brickfurnace.crafting.BrickSmeltingRecipe;
import de.cech12.brickfurnace.crafting.BrickSmokingRecipe;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

public class ModRecipeTypes {

    private static final RecipeType<?> BLASTING_RECIPE_TYPE = registerRecipe(Constants.BLASTING_NAME);
    private static final RecipeType<?> SMELTING_RECIPE_TYPE = registerRecipe(Constants.SMELTING_NAME);
    private static final RecipeType<?> SMOKING_RECIPE_TYPE = registerRecipe(Constants.SMOKING_NAME);

    private static final RecipeSerializer<?> BLASTING_RECIPE_SERIALIZER = registerSerializer(Constants.BLASTING_NAME, BrickBlastingRecipe.SERIALIZER);
    private static final RecipeSerializer<?> SMELTING_RECIPE_SERIALIZER = registerSerializer(Constants.SMELTING_NAME, BrickSmeltingRecipe.SERIALIZER);
    private static final RecipeSerializer<?> SMOKING_RECIPE_SERIALIZER = registerSerializer(Constants.SMOKING_NAME, BrickSmokingRecipe.SERIALIZER);

    static {
        Constants.BLASTING_RECIPE_TYPE = () -> (RecipeType<BrickBlastingRecipe>) BLASTING_RECIPE_TYPE;
        Constants.SMELTING_RECIPE_TYPE = () -> (RecipeType<BrickSmeltingRecipe>) SMELTING_RECIPE_TYPE;
        Constants.SMOKING_RECIPE_TYPE = () -> (RecipeType<BrickSmokingRecipe>) SMOKING_RECIPE_TYPE;
    }

    public static void init() {}

    private static <T extends AbstractCookingRecipe> RecipeType<T> registerRecipe(String name) {
        return Registry.register(BuiltInRegistries.RECIPE_TYPE, CommonLoader.id(name), new RecipeType<>() {});
    }

    private static <T extends AbstractCookingRecipe> RecipeSerializer<T> registerSerializer(String name, RecipeSerializer<T> serializer) {
        return Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, CommonLoader.id(name), serializer);
    }

}
