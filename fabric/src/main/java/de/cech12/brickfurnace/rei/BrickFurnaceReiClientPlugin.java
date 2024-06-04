package de.cech12.brickfurnace.rei;

import de.cech12.brickfurnace.Constants;
import de.cech12.brickfurnace.crafting.BrickBlastingRecipe;
import de.cech12.brickfurnace.crafting.BrickSmeltingRecipe;
import de.cech12.brickfurnace.crafting.BrickSmokingRecipe;
import de.cech12.brickfurnace.platform.Services;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.entry.type.VanillaEntryTypes;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import me.shedaniel.rei.plugin.client.categories.cooking.DefaultCookingCategory;
import me.shedaniel.rei.plugin.common.BuiltinPlugin;
import me.shedaniel.rei.plugin.common.displays.cooking.DefaultCookingDisplay;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;

import java.util.Collections;
import java.util.Objects;

@SuppressWarnings("unused")
public class BrickFurnaceReiClientPlugin implements REIClientPlugin {

    public static final CategoryIdentifier<DefaultCookingDisplay> SMELTING_ID = CategoryIdentifier.of(Constants.MOD_ID, Constants.SMELTING_NAME);
    public static final CategoryIdentifier<DefaultCookingDisplay> SMOKING_ID = CategoryIdentifier.of(Constants.MOD_ID, Constants.SMOKING_NAME);
    public static final CategoryIdentifier<DefaultCookingDisplay> BLASTING_ID = CategoryIdentifier.of(Constants.MOD_ID, Constants.BLASTING_NAME);

    @Override
    public void registerCategories(CategoryRegistry registry) {
        registerCategory(registry, SMELTING_ID, Constants.BRICK_FURNACE_BLOCK.get(), Constants.BRICK_FURNACE_NAME);
        registerCategory(registry, SMOKING_ID, Constants.BRICK_SMOKER_BLOCK.get(), Constants.BRICK_SMOKER_NAME);
        registerCategory(registry, BLASTING_ID, Constants.BRICK_BLAST_FURNACE_BLOCK.get(), Constants.BRICK_BLAST_FURNACE_NAME);
    }

    private void registerCategory(CategoryRegistry registry, CategoryIdentifier<DefaultCookingDisplay> id, Block block, String name) {
        registry.add(new DefaultCookingCategory(id, EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(block)), "block." + Constants.MOD_ID + "." + name));
        registry.addWorkstations(id, EntryStacks.of(block));
        registry.addWorkstations(BuiltinPlugin.FUEL, EntryStacks.of(block));
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerRecipeFiller(BrickSmeltingRecipe.class, Constants.SMELTING_RECIPE_TYPE.get(), BrickSmeltingDisplay::new);
        registry.registerRecipeFiller(BrickSmokingRecipe.class, Constants.SMOKING_RECIPE_TYPE.get(), BrickSmokingDisplay::new);
        registry.registerRecipeFiller(BrickBlastingRecipe.class, Constants.BLASTING_RECIPE_TYPE.get(), BrickBlastingDisplay::new);
        if (Services.CONFIG.areVanillaRecipesEnabled()) {
            registry.registerRecipeFiller(AbstractCookingRecipe.class, type -> Objects.equals(RecipeType.SMELTING, type),
                    recipeHolder -> Services.CONFIG.isRecipeAllowed(recipeHolder.id()), BrickSmeltingDisplay::new);
            registry.registerRecipeFiller(AbstractCookingRecipe.class, type -> Objects.equals(RecipeType.SMOKING, type),
                    recipeHolder -> Services.CONFIG.isRecipeAllowed(recipeHolder.id()), BrickSmokingDisplay::new);
            registry.registerRecipeFiller(AbstractCookingRecipe.class, type -> Objects.equals(RecipeType.BLASTING, type),
                    recipeHolder -> Services.CONFIG.isRecipeAllowed(recipeHolder.id()), BrickBlastingDisplay::new);
        }
    }

    static class BrickSmeltingDisplay extends DefaultCookingDisplay {

        public BrickSmeltingDisplay(RecipeHolder<? extends AbstractCookingRecipe> recipe) {
            super(EntryIngredients.ofIngredients(recipe.value().getIngredients()), Collections.singletonList(EntryIngredients.of(recipe.value().getResultItem(null))),
                    recipe, recipe.value().getExperience(), recipe.value() instanceof BrickSmeltingRecipe ? recipe.value().getCookingTime() : recipe.value().getCookingTime() * Services.CONFIG.getCookTimeFactor());
        }

        @Override
        public CategoryIdentifier<?> getCategoryIdentifier() {
            return SMELTING_ID;
        }
    }

    static class BrickSmokingDisplay extends DefaultCookingDisplay {

        public BrickSmokingDisplay(RecipeHolder<? extends AbstractCookingRecipe> recipe) {
            super(EntryIngredients.ofIngredients(recipe.value().getIngredients()), Collections.singletonList(EntryIngredients.of(recipe.value().getResultItem(null))),
                    recipe, recipe.value().getExperience(), recipe.value() instanceof BrickSmokingRecipe ? recipe.value().getCookingTime() : recipe.value().getCookingTime() * Services.CONFIG.getCookTimeFactor());
        }

        @Override
        public CategoryIdentifier<?> getCategoryIdentifier() {
            return SMOKING_ID;
        }
    }

    static class BrickBlastingDisplay extends DefaultCookingDisplay {

        public BrickBlastingDisplay(RecipeHolder<? extends AbstractCookingRecipe> recipe) {
            super(EntryIngredients.ofIngredients(recipe.value().getIngredients()), Collections.singletonList(EntryIngredients.of(recipe.value().getResultItem(null))),
                    recipe, recipe.value().getExperience(), recipe.value() instanceof BrickBlastingRecipe ? recipe.value().getCookingTime() : recipe.value().getCookingTime() * Services.CONFIG.getCookTimeFactor());
        }

        @Override
        public CategoryIdentifier<?> getCategoryIdentifier() {
            return BLASTING_ID;
        }
    }

}
