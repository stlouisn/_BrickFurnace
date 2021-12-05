package cech12.brickfurnace.jei;

import cech12.brickfurnace.BrickFurnaceMod;
import cech12.brickfurnace.api.block.BrickFurnaceBlocks;
import cech12.brickfurnace.api.crafting.RecipeTypes;
import cech12.brickfurnace.config.ServerConfig;
import cech12.brickfurnace.crafting.BrickBlastingRecipe;
import cech12.brickfurnace.crafting.BrickSmeltingRecipe;
import cech12.brickfurnace.crafting.BrickSmokingRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaRecipeCategoryUid;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;
import java.util.stream.Collectors;

@JeiPlugin
public class BrickFurnaceJEIPlugin implements IModPlugin {

    @Override
    @Nonnull
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(BrickFurnaceMod.MOD_ID, "plugin_" + BrickFurnaceMod.MOD_ID);
    }

    @Override
    public void registerRecipes(@Nonnull IRecipeRegistration registration) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player != null) {
            RecipeManager manager = player.connection.getRecipeManager();
            registration.addRecipes(manager.getAllRecipesFor(RecipeTypes.SMELTING), RecipeTypes.SMELTING_ID);
            registration.addRecipes(manager.getAllRecipesFor(RecipeTypes.SMOKING), RecipeTypes.SMOKING_ID);
            registration.addRecipes(manager.getAllRecipesFor(RecipeTypes.BLASTING), RecipeTypes.BLASTING_ID);

            if (ServerConfig.VANILLA_RECIPES_ENABLED.get()) {
                registration.addRecipes(manager.getAllRecipesFor(RecipeType.SMELTING).stream()
                        .filter(recipe -> ServerConfig.isRecipeNotBlacklisted(recipe.getId()))
                        .map(BrickSmeltingRecipe::convert)
                        .collect(Collectors.toList()), RecipeTypes.SMELTING_ID);
                registration.addRecipes(manager.getAllRecipesFor(RecipeType.SMOKING).stream()
                        .filter(recipe -> ServerConfig.isRecipeNotBlacklisted(recipe.getId()))
                        .map(BrickSmokingRecipe::convert)
                        .collect(Collectors.toList()), RecipeTypes.SMOKING_ID);
                registration.addRecipes(manager.getAllRecipesFor(RecipeType.BLASTING).stream()
                        .filter(recipe -> ServerConfig.isRecipeNotBlacklisted(recipe.getId()))
                        .map(BrickBlastingRecipe::convert)
                        .collect(Collectors.toList()), RecipeTypes.BLASTING_ID);
            }
        }
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IGuiHelper guiHelper = registration.getJeiHelpers().getGuiHelper();
        registration.addRecipeCategories(
                new BrickSmeltingCategory(guiHelper),
                new BrickSmokingCategory(guiHelper),
                new BrickBlastingCategory(guiHelper));
    }

    @Override
    public void registerRecipeCatalysts(@Nonnull IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(BrickFurnaceBlocks.BRICK_FURNACE),
                RecipeTypes.SMELTING_ID, VanillaRecipeCategoryUid.FUEL);
        registration.addRecipeCatalyst(new ItemStack(BrickFurnaceBlocks.BRICK_SMOKER),
                RecipeTypes.SMOKING_ID, VanillaRecipeCategoryUid.FUEL);
        registration.addRecipeCatalyst(new ItemStack(BrickFurnaceBlocks.BRICK_BLAST_FURNACE),
                RecipeTypes.BLASTING_ID, VanillaRecipeCategoryUid.FUEL);
    }

}
