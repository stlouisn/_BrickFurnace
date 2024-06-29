package de.cech12.brickfurnace.platform;

import de.cech12.brickfurnace.platform.services.IPlatformHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

/**
 * The platform service implementation for Fabric.
 */
public class FabricPlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return !stack.isEmpty() && stack.getItem().hasCraftingRemainingItem();
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack stack) {
        Item remainingItem;
        if (!stack.isEmpty() && (remainingItem = stack.getItem().getCraftingRemainingItem()) != null) {
            return new ItemStack(remainingItem);
        }
        return ItemStack.EMPTY;
    }

}
