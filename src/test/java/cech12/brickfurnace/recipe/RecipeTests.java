package cech12.brickfurnace.recipe;

import cech12.brickfurnace.api.block.BrickFurnaceBlocks;
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
    public void testAllVanillaRecipesForBrickBlastFurnaceWhenVanillaRecipesAreEnabled() {
        testAllVanillaRecipes((AbstractFurnaceBlock) BrickFurnaceBlocks.BRICK_BLAST_FURNACE, IRecipeType.BLASTING, true);
    }

    @Test
    public void testAllVanillaRecipesForBrickBlastFurnaceWhenVanillaRecipesAreDisabled() {
        testAllVanillaRecipes((AbstractFurnaceBlock) BrickFurnaceBlocks.BRICK_BLAST_FURNACE, IRecipeType.BLASTING, false);
    }

    @Test
    public void testAllVanillaRecipesForBrickSmokerWhenVanillaRecipesAreEnabled() {
        testAllVanillaRecipes((AbstractFurnaceBlock) BrickFurnaceBlocks.BRICK_SMOKER, IRecipeType.SMOKING, true);
    }

    @Test
    public void testAllVanillaRecipesForBrickSmokerWhenVanillaRecipesAreDisabled() {
        testAllVanillaRecipes((AbstractFurnaceBlock) BrickFurnaceBlocks.BRICK_SMOKER, IRecipeType.SMOKING, false);
    }

    private void testAllVanillaRecipes(AbstractFurnaceBlock block, IRecipeType<? extends AbstractCookingRecipe> recipeType, boolean vanillaRecipesEnabled) {
        ServerConfig.VANILLA_RECIPES_ENABLED.set(vanillaRecipesEnabled);
        ServerConfig.RECIPE_BLACKLIST.set("");
        double cookTimeFactor = 0.5;
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
            for (IRecipe<?> recipe : world.getRecipeManager().getRecipes()) {
                if (recipe.getType() != recipeType) continue;
                AbstractCookingRecipe vanillaRecipe = (AbstractCookingRecipe) recipe;
                ItemStack ingredient = vanillaRecipe.getIngredients().get(0).getItems()[0];
                tileEntity.setItem(0, ingredient.copy());
                AbstractCookingRecipe resultRecipe = (AbstractCookingRecipe) getRecipeMethod.invoke(tileEntity);
                if (vanillaRecipesEnabled) {
                    assertNotNull(resultRecipe, vanillaRecipe.getResultItem() + " should be made when put " + ingredient + " into " + block + ", but nothing will come out of it.");
                    assertTrue(vanillaRecipe.getResultItem().equals(resultRecipe.getResultItem(), true), vanillaRecipe.getResultItem() + " should be made when put " + ingredient + " into " + block + ", but it turns into " + resultRecipe.getResultItem());
                    assertEquals((int) (vanillaRecipe.getCookingTime() * cookTimeFactor), (int) getTotalCookTimeMethod.invoke(tileEntity), "The cooking time should be influenced by the config");
                } else {
                    assertNull(resultRecipe, "Nothing should be made when put " + ingredient + " into " + block);
                }
            }
        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException ex) {
            fail("Java reflection failed", ex);
        }
    }

}
