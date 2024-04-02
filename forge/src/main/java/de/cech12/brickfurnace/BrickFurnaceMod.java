package de.cech12.brickfurnace;

import de.cech12.brickfurnace.init.ModBlockEntityTypes;
import de.cech12.brickfurnace.init.ModBlocks;
import de.cech12.brickfurnace.init.ModItems;
import de.cech12.brickfurnace.init.ModPoiTypes;
import de.cech12.brickfurnace.init.ModRecipeTypes;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static de.cech12.brickfurnace.BrickFurnaceMod.MOD_ID;

@Mod(MOD_ID)
@Mod.EventBusSubscriber(modid= MOD_ID, bus= Mod.EventBusSubscriber.Bus.MOD)
public class BrickFurnaceMod {

    public static final String MOD_ID = "brickfurnace";

    public BrickFurnaceMod() {
        final IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModBlocks.BLOCKS.register(eventBus);
        ModBlockEntityTypes.BLOCK_ENTITY_TYPES.register(eventBus);
        ModItems.ITEMS.register(eventBus);
        ModRecipeTypes.RECIPE_TYPES.register(eventBus);
        ModRecipeTypes.RECIPE_SERIALIZERS.register(eventBus);
        ModPoiTypes.POI_TYPES.register(eventBus);
        //Config
        CommonLoader.init();
    }

    @SubscribeEvent
    public static void addItemsToTabs(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.accept(Constants.BRICK_FURNACE_ITEM);
            event.accept(Constants.BRICK_BLAST_FURNACE_ITEM);
            event.accept(Constants.BRICK_SMOKER_ITEM);
        }
    }

}
