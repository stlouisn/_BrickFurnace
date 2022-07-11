package cech12.brickfurnace.jei;

import cech12.brickfurnace.BrickFurnaceMod;
import cech12.brickfurnace.init.ModBlocks;
import cech12.brickfurnace.init.ModRecipeTypes;
import cech12.brickfurnace.config.ServerConfig;
import cech12.brickfurnace.crafting.BrickBlastingRecipe;
import cech12.brickfurnace.crafting.BrickSmeltingRecipe;
import cech12.brickfurnace.crafting.BrickSmokingRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;

import javax.annotation.Nonnull;
import java.util.stream.Collectors;

@JeiPlugin
public class BrickFurnaceJEIPlugin implements IModPlugin {

    private static BrickSmeltingCategory smeltingRecipeType;
    private static BrickSmokingCategory smokingRecipeType;
    private static BrickBlastingCategory blastingRecipeType;

    @Override
    @Nonnull
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(BrickFurnaceMod.MOD_ID, "plugin_" + BrickFurnaceMod.MOD_ID);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IGuiHelper guiHelper = registration.getJeiHelpers().getGuiHelper();
        smeltingRecipeType = new BrickSmeltingCategory(guiHelper);
        smokingRecipeType = new BrickSmokingCategory(guiHelper);
        blastingRecipeType = new BrickBlastingCategory(guiHelper);
        registration.addRecipeCategories(
                smeltingRecipeType,
                smokingRecipeType,
                blastingRecipeType);
    }

    @Override
    public void registerRecipes(@Nonnull IRecipeRegistration registration) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player != null) {
            RecipeManager manager = player.connection.getRecipeManager();
            registration.addRecipes(smeltingRecipeType.getRecipeType(), manager.getAllRecipesFor(ModRecipeTypes.SMELTING.get()));
            registration.addRecipes(smokingRecipeType.getRecipeType(), manager.getAllRecipesFor(ModRecipeTypes.SMOKING.get()));
            registration.addRecipes(blastingRecipeType.getRecipeType(), manager.getAllRecipesFor(ModRecipeTypes.BLASTING.get()));

            if (ServerConfig.VANILLA_RECIPES_ENABLED.get()) {
                registration.addRecipes(smeltingRecipeType.getRecipeType(), manager.getAllRecipesFor(RecipeType.SMELTING).stream()
                        .filter(recipe -> ServerConfig.isRecipeNotBlacklisted(recipe.getId()))
                        .map(BrickSmeltingRecipe::convert)
                        .collect(Collectors.toList()));
                registration.addRecipes(smokingRecipeType.getRecipeType(), manager.getAllRecipesFor(RecipeType.SMOKING).stream()
                        .filter(recipe -> ServerConfig.isRecipeNotBlacklisted(recipe.getId()))
                        .map(BrickSmokingRecipe::convert)
                        .collect(Collectors.toList()));
                registration.addRecipes(blastingRecipeType.getRecipeType(), manager.getAllRecipesFor(RecipeType.BLASTING).stream()
                        .filter(recipe -> ServerConfig.isRecipeNotBlacklisted(recipe.getId()))
                        .map(BrickBlastingRecipe::convert)
                        .collect(Collectors.toList()));
            }
        }
    }

    @Override
    public void registerRecipeCatalysts(@Nonnull IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.BRICK_FURNACE.get()),
                smeltingRecipeType.getRecipeType(), mezz.jei.api.constants.RecipeTypes.FUELING);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.BRICK_SMOKER.get()),
                smokingRecipeType.getRecipeType(), mezz.jei.api.constants.RecipeTypes.FUELING);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.BRICK_BLAST_FURNACE.get()),
                blastingRecipeType.getRecipeType(), mezz.jei.api.constants.RecipeTypes.FUELING);
    }

}
