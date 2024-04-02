package de.cech12.brickfurnace.recipe;
/*
import cech12.brickfurnace.BrickFurnaceMod;
import cech12.brickfurnace.api.block.BrickFurnaceBlocks;
import cech12.brickfurnace.api.crafting.RecipeTypes;
import cech12.brickfurnace.config.ServerConfig;
import cech12.brickfurnace.tileentity.AbstractBrickFurnaceTileEntity;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RecipeTests {

    @Test
    public void testAllVanillaRecipesForBrickFurnaceWhenVanillaRecipesAreEnabled() {
        testAllVanillaRecipes((AbstractFurnaceBlock) BrickFurnaceBlocks.BRICK_FURNACE, IRecipeType.SMELTING, true);
    }

    @Test
    public void testAllVanillaRecipesForBrickFurnaceWhenVanillaRecipesAreDisabled() {
        testAllVanillaRecipes((AbstractFurnaceBlock) BrickFurnaceBlocks.BRICK_FURNACE, IRecipeType.SMELTING, false);
    }

    @Test
    public void testAllSpecialRecipesForBrickFurnaceWhenVanillaRecipesAreEnabled() {
        testAllVanillaRecipes((AbstractFurnaceBlock) BrickFurnaceBlocks.BRICK_FURNACE, RecipeTypes.SMELTING, true);
    }

    @Test
    public void testAllSpecialRecipesForBrickFurnaceWhenVanillaRecipesAreDisabled() {
        testAllVanillaRecipes((AbstractFurnaceBlock) BrickFurnaceBlocks.BRICK_FURNACE, RecipeTypes.SMELTING, false);
    }

    @Test
    public void testAllVanillaRecipesForBrickBlastFurnaceWhenVanillaRecipesAreEnabled() {
        testAllVanillaRecipes((AbstractFurnaceBlock) BrickFurnaceBlocks.BRICK_BLAST_FURNACE, IRecipeType.BLASTING, true);
    }

    @Test
    public void testAllVanillaRecipesForBrickBlastFurnaceWhenVanillaRecipesAreDisabled() {
        testAllVanillaRecipes((AbstractFurnaceBlock) BrickFurnaceBlocks.BRICK_BLAST_FURNACE, IRecipeType.BLASTING, false);
    }

    @Test
    public void testAllSpecialRecipesForBrickBlastFurnaceWhenVanillaRecipesAreEnabled() {
        testAllVanillaRecipes((AbstractFurnaceBlock) BrickFurnaceBlocks.BRICK_BLAST_FURNACE, RecipeTypes.BLASTING, true);
    }

    @Test
    public void testAllSpecialRecipesForBrickBlastFurnaceWhenVanillaRecipesAreDisabled() {
        testAllVanillaRecipes((AbstractFurnaceBlock) BrickFurnaceBlocks.BRICK_BLAST_FURNACE, RecipeTypes.BLASTING, false);
    }

    @Test
    public void testAllVanillaRecipesForBrickSmokerWhenVanillaRecipesAreEnabled() {
        testAllVanillaRecipes((AbstractFurnaceBlock) BrickFurnaceBlocks.BRICK_SMOKER, IRecipeType.SMOKING, true);
    }

    @Test
    public void testAllVanillaRecipesForBrickSmokerWhenVanillaRecipesAreDisabled() {
        testAllVanillaRecipes((AbstractFurnaceBlock) BrickFurnaceBlocks.BRICK_SMOKER, IRecipeType.SMOKING, false);
    }

    @Test
    public void testAllSpecialRecipesForBrickSmokerWhenVanillaRecipesAreEnabled() {
        testAllSpecialRecipes((AbstractFurnaceBlock) BrickFurnaceBlocks.BRICK_SMOKER, RecipeTypes.SMOKING, true);
    }

    @Test
    public void testAllSpecialRecipesForBrickSmokerWhenVanillaRecipesAreDisabled() {
        testAllSpecialRecipes((AbstractFurnaceBlock) BrickFurnaceBlocks.BRICK_SMOKER, RecipeTypes.SMOKING, false);
    }

    private void testAllVanillaRecipes(AbstractFurnaceBlock block, IRecipeType<? extends AbstractCookingRecipe> recipeType, boolean vanillaRecipesEnabled) {
        testAllVanillaRecipes(block, recipeType, vanillaRecipesEnabled, "");
    }

    private void testAllVanillaRecipes(AbstractFurnaceBlock block, IRecipeType<? extends AbstractCookingRecipe> recipeType, boolean vanillaRecipesEnabled, String blacklistRecipe) {
        testAllRecipes(block, recipeType, vanillaRecipesEnabled, blacklistRecipe, false);
    }

    private void testAllSpecialRecipes(AbstractFurnaceBlock block, IRecipeType<? extends AbstractCookingRecipe> recipeType, boolean vanillaRecipesEnabled) {
        testAllSpecialRecipes(block, recipeType, vanillaRecipesEnabled, "");
    }

    private void testAllSpecialRecipes(AbstractFurnaceBlock block, IRecipeType<? extends AbstractCookingRecipe> recipeType, boolean vanillaRecipesEnabled, String blacklistRecipe) {
        testAllRecipes(block, recipeType, vanillaRecipesEnabled, blacklistRecipe, true);
    }

    private void testAllRecipes(AbstractFurnaceBlock block, IRecipeType<? extends AbstractCookingRecipe> recipeType, boolean vanillaRecipesEnabled, String blacklistRecipe, boolean testSpecialRecipe) {
        ServerConfig.VANILLA_RECIPES_ENABLED.set(vanillaRecipesEnabled);
        ServerConfig.RECIPE_BLACKLIST.set(blacklistRecipe);
        double cookTimeFactor = 2.0;
        ServerConfig.COOK_TIME_FACTOR.set(cookTimeFactor);
        final ServerWorld world = ServerLifecycleHooks.getCurrentServer().overworld();
        AbstractBrickFurnaceTileEntity tileEntity = (AbstractBrickFurnaceTileEntity) block.newBlockEntity(world);
        assertNotNull(tileEntity, "AbstractBrickFurnaceTileEntity could not be generated");
        tileEntity.setLevelAndPosition(world, new BlockPos(0, 0, 0));

        //use java reflection to enable access to getRecipe method
        try {
            Method getRecipeMethod = AbstractBrickFurnaceTileEntity.class.getDeclaredMethod("getRecipe");
            getRecipeMethod.setAccessible(true);
            Method getTotalCookTimeMethod = AbstractBrickFurnaceTileEntity.class.getDeclaredMethod("getTotalCookTime");
            getTotalCookTimeMethod.setAccessible(true);
            for (IRecipe<?> iRecipe : world.getRecipeManager().getRecipes()) {
                if (iRecipe.getType() != recipeType) continue;
                AbstractCookingRecipe recipe = (AbstractCookingRecipe) iRecipe;
                ItemStack ingredient = recipe.getIngredients().get(0).getItems()[0];
                ItemStack resultItem = recipe.getResultItem();
                BrickFurnaceMod.LOGGER.debug("test [" + ingredient + " -> " + resultItem + "]");
                tileEntity.setItem(0, ingredient.copy());
                AbstractCookingRecipe resultRecipe = (AbstractCookingRecipe) getRecipeMethod.invoke(tileEntity);
                if (testSpecialRecipe || vanillaRecipesEnabled) {
                    assertNotNull(resultRecipe, resultItem + " should be made when put " + ingredient + " into " + block + ", but nothing will come out of it.");
                    assertTrue(resultItem.equals(resultRecipe.getResultItem(), true), resultItem + " should be made when put " + ingredient + " into " + block + ", but it turns into " + resultRecipe.getResultItem());
                    int cookingTime = (int) getTotalCookTimeMethod.invoke(tileEntity);
                    if (!testSpecialRecipe) {
                        int expectedCookingTime = (int) (recipe.getCookingTime() * cookTimeFactor);
                        assertEquals(expectedCookingTime, cookingTime, "The cooking time should be influenced by the cookingTimeFactor of " + cookTimeFactor + " config. [" + ingredient + " -> " + resultItem + "]");
                    } else {
                        assertEquals(recipe.getCookingTime(), cookingTime, "The cooking time should not be influenced by the cookingTimeFactor. [" + ingredient + " -> " + resultItem + "]");
                    }
                } else {
                    assertNull(resultRecipe, "Nothing should be made when put " + ingredient + " into " + block);
                }
            }
        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException ex) {
            fail("Java reflection failed", ex);
        }
    }

}
 */
