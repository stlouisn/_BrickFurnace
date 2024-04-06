package de.cech12.brickfurnace.init;

import de.cech12.brickfurnace.Constants;
import de.cech12.brickfurnace.crafting.BrickBlastingRecipe;
import de.cech12.brickfurnace.crafting.BrickSmeltingRecipe;
import de.cech12.brickfurnace.crafting.BrickSmokingRecipe;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModRecipeTypes {

    public static DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(BuiltInRegistries.RECIPE_TYPE, Constants.MOD_ID);
    public static DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, Constants.MOD_ID);

    public static DeferredHolder<RecipeSerializer<?>, RecipeSerializer<?>> BLASTING_SERIALIZER = RECIPE_SERIALIZERS.register(Constants.BLASTING_NAME, () -> BrickBlastingRecipe.SERIALIZER);
    public static DeferredHolder<RecipeSerializer<?>, RecipeSerializer<?>> SMELTING_SERIALIZER = RECIPE_SERIALIZERS.register(Constants.SMELTING_NAME, () -> BrickSmeltingRecipe.SERIALIZER);
    public static DeferredHolder<RecipeSerializer<?>, RecipeSerializer<?>> SMOKING_SERIALIZER = RECIPE_SERIALIZERS.register(Constants.SMOKING_NAME, () -> BrickSmokingRecipe.SERIALIZER);

    static {
        Constants.BLASTING_RECIPE_TYPE = RECIPE_TYPES.register(Constants.BLASTING_NAME, () -> new RecipeType<>() {});
        Constants.SMELTING_RECIPE_TYPE = RECIPE_TYPES.register(Constants.SMELTING_NAME, () -> new RecipeType<>() {});
        Constants.SMOKING_RECIPE_TYPE = RECIPE_TYPES.register(Constants.SMOKING_NAME, () -> new RecipeType<>() {});
    }

}
