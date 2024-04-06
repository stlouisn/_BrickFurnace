package de.cech12.brickfurnace.init;

import de.cech12.brickfurnace.Constants;
import de.cech12.brickfurnace.crafting.BrickBlastingRecipe;
import de.cech12.brickfurnace.crafting.BrickSmeltingRecipe;
import de.cech12.brickfurnace.crafting.BrickSmokingRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipeTypes {

    public static DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, Constants.MOD_ID);
    public static DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Constants.MOD_ID);

    public static RegistryObject<RecipeSerializer<?>> BLASTING_SERIALIZER = RECIPE_SERIALIZERS.register(Constants.BLASTING_NAME, () -> BrickBlastingRecipe.SERIALIZER);
    public static RegistryObject<RecipeSerializer<?>> SMELTING_SERIALIZER = RECIPE_SERIALIZERS.register(Constants.SMELTING_NAME, () -> BrickSmeltingRecipe.SERIALIZER);
    public static RegistryObject<RecipeSerializer<?>> SMOKING_SERIALIZER = RECIPE_SERIALIZERS.register(Constants.SMOKING_NAME, () -> BrickSmokingRecipe.SERIALIZER);

    static {
        Constants.BLASTING_RECIPE_TYPE = RECIPE_TYPES.register(Constants.BLASTING_NAME, () -> new RecipeType<>() {});
        Constants.SMELTING_RECIPE_TYPE = RECIPE_TYPES.register(Constants.SMELTING_NAME, () -> new RecipeType<>() {});
        Constants.SMOKING_RECIPE_TYPE = RECIPE_TYPES.register(Constants.SMOKING_NAME, () -> new RecipeType<>() {});
    }

}
