package de.cech12.brickfurnace;

import de.cech12.brickfurnace.init.ModBlockEntityTypes;
import de.cech12.brickfurnace.init.ModBlocks;
import de.cech12.brickfurnace.init.ModItems;
import de.cech12.brickfurnace.init.ModRecipeTypes;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CreativeModeTabs;

public class BrickFurnaceMod implements ModInitializer {

    @Override
    public void onInitialize() {
        ModBlocks.init();
        ModBlockEntityTypes.init();
        ModItems.init();
        ModRecipeTypes.init();
        CommonLoader.init();
        //init POI types
        CommonLoader.initPoiStates(BuiltInRegistries.POINT_OF_INTEREST_TYPE::get, BuiltInRegistries.POINT_OF_INTEREST_TYPE::getHolderOrThrow);
        //Register item in the creative tab.
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(content -> {
            content.accept(Constants.BRICK_FURNACE_ITEM.get());
            content.accept(Constants.BRICK_BLAST_FURNACE_ITEM.get());
            content.accept(Constants.BRICK_SMOKER_ITEM.get());
        });
    }

}
