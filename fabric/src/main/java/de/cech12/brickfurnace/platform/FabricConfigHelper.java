package de.cech12.brickfurnace.platform;

import de.cech12.brickfurnace.Constants;
import de.cech12.brickfurnace.platform.services.IConfigHelper;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;

/**
 * The config service implementation for Fabric.
 */
@Config(name = Constants.MOD_ID)
public class FabricConfigHelper implements ConfigData, IConfigHelper {

    @ConfigEntry.Gui.Tooltip(count = 4)
    public boolean VANILLA_RECIPES_ENABLED = VANILLA_RECIPES_ENABLED_DEFAULT;

    @ConfigEntry.Gui.Tooltip(count = 5)
    @ConfigEntry.BoundedDiscrete(min = (long) (COOK_TIME_FACTOR_MIN * 100), max = (long) (COOK_TIME_FACTOR_MAX * 100))
    public long COOK_TIME_FACTOR = (long) (COOK_TIME_FACTOR_DEFAULT * 100);

    @ConfigEntry.Gui.Tooltip(count = 5)
    public String RECIPE_BLOCKED_LIST = RECIPE_BLOCKED_LIST_DEFAULT;

    @Override
    public void init() {
        AutoConfig.register(FabricConfigHelper.class, Toml4jConfigSerializer::new);
    }

    private FabricConfigHelper getConfig() {
        return AutoConfig.getConfigHolder(FabricConfigHelper.class).getConfig();
    }

    @Override
    public boolean areVanillaRecipesEnabled() {
        return getConfig().VANILLA_RECIPES_ENABLED;
    }

    @Override
    public double getCookTimeFactor() {
        return getConfig().COOK_TIME_FACTOR / 100D;
    }

    @Override
    public String getRecipeBlockedList() {
        return getConfig().RECIPE_BLOCKED_LIST;
    }

}
