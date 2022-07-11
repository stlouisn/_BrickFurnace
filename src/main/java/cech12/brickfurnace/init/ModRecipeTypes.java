package cech12.brickfurnace.init;

import cech12.brickfurnace.BrickFurnaceMod;
import cech12.brickfurnace.crafting.BrickBlastingRecipe;
import cech12.brickfurnace.crafting.BrickSmeltingRecipe;
import cech12.brickfurnace.crafting.BrickSmokingRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipeTypes {

    public static DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, BrickFurnaceMod.MOD_ID);

    public static RegistryObject<RecipeType<BrickBlastingRecipe>> BLASTING = RECIPE_TYPES.register("blasting", () -> new RecipeType<>() {});
    public static RegistryObject<RecipeType<BrickSmeltingRecipe>> SMELTING = RECIPE_TYPES.register("smelting", () -> new RecipeType<>() {});
    public static RegistryObject<RecipeType<BrickSmokingRecipe>> SMOKING = RECIPE_TYPES.register("smoking", () -> new RecipeType<>() {});

    public static DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, BrickFurnaceMod.MOD_ID);

    public static RegistryObject<RecipeSerializer<?>> BLASTING_SERIALIZER = RECIPE_SERIALIZERS.register("blasting", () -> BrickBlastingRecipe.SERIALIZER);
    public static RegistryObject<RecipeSerializer<?>> SMELTING_SERIALIZER = RECIPE_SERIALIZERS.register("smelting", () -> BrickSmeltingRecipe.SERIALIZER);
    public static RegistryObject<RecipeSerializer<?>> SMOKING_SERIALIZER = RECIPE_SERIALIZERS.register("smoking", () -> BrickSmokingRecipe.SERIALIZER);

}
