package cech12.brickfurnace.api.crafting;

import cech12.brickfurnace.BrickFurnaceMod;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.resources.ResourceLocation;

public class RecipeTypes {

    public final static ResourceLocation BLASTING_ID = new ResourceLocation(BrickFurnaceMod.MOD_ID, "blasting");
    public final static ResourceLocation SMELTING_ID = new ResourceLocation(BrickFurnaceMod.MOD_ID, "smelting");
    public final static ResourceLocation SMOKING_ID = new ResourceLocation(BrickFurnaceMod.MOD_ID, "smoking");

    public static RecipeType<? extends AbstractCookingRecipe> BLASTING;
    public static RecipeType<? extends AbstractCookingRecipe> SMELTING;
    public static RecipeType<? extends AbstractCookingRecipe> SMOKING;

}
