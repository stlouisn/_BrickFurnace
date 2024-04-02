package de.cech12.brickfurnace;

import de.cech12.brickfurnace.compat.TOPCompat;
import de.cech12.brickfurnace.init.ModBlockEntityTypes;
import de.cech12.brickfurnace.init.ModBlocks;
import de.cech12.brickfurnace.init.ModItems;
import de.cech12.brickfurnace.init.ModPoiTypes;
import de.cech12.brickfurnace.init.ModRecipeTypes;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

import static de.cech12.brickfurnace.BrickFurnaceMod.MOD_ID;

@Mod(MOD_ID)
@Mod.EventBusSubscriber(modid= MOD_ID, bus= Mod.EventBusSubscriber.Bus.MOD)
public class BrickFurnaceMod {

    public static final String MOD_ID = "brickfurnace";

    public BrickFurnaceMod(IEventBus eventBus) {
        ModBlocks.BLOCKS.register(eventBus);
        ModBlockEntityTypes.BLOCK_ENTITY_TYPES.register(eventBus);
        ModItems.ITEMS.register(eventBus);
        ModRecipeTypes.RECIPE_TYPES.register(eventBus);
        ModRecipeTypes.RECIPE_SERIALIZERS.register(eventBus);
        //ModPoiTypes.POI_TYPES.register(eventBus); //TODO POI registration does not work in Neoforge - find another way
        //Config
        CommonLoader.init();
        //The One Probe registration.
        if (ModList.get().isLoaded("theoneprobe") && !ModList.get().isLoaded("topaddons")) {
            TOPCompat.register();
        }
    }

    @SubscribeEvent
    public static void addItemsToTabs(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.accept(Constants.BRICK_FURNACE_ITEM.get());
            event.accept(Constants.BRICK_BLAST_FURNACE_ITEM.get());
            event.accept(Constants.BRICK_SMOKER_ITEM.get());
        }
    }

}
