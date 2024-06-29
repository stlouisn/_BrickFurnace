package de.cech12.brickfurnace;

import com.google.common.collect.ImmutableSet;
import de.cech12.brickfurnace.mixin.PoiTypeAccessor;
import de.cech12.brickfurnace.mixin.PoiTypesAccessor;
import de.cech12.brickfurnace.platform.Services;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.ai.village.poi.PoiTypes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

/**
 * A static class for all loaders which initializes everything which is used by all loaders.
 */
public class CommonLoader {

    /**
     * Initialize method that should be called by every loader mod in the constructor.
     */
    public static void init() {
        Services.CONFIG.init();
    }

    /**
     * Initialize method that should be called by every loader mod when the mod blocks are registered.
     */
    public static void initPoiStates(Function<ResourceKey<PoiType>, PoiType> poiTypeGetter, Function<ResourceKey<PoiType>, Holder<PoiType>> poiTypeHolderGetter) {
        replacePoiStates(poiTypeGetter, poiTypeHolderGetter, PoiTypes.ARMORER, Constants.BRICK_BLAST_FURNACE_BLOCK.get());
        replacePoiStates(poiTypeGetter, poiTypeHolderGetter, PoiTypes.BUTCHER, Constants.BRICK_SMOKER_BLOCK.get());
    }


    private static void replacePoiStates(Function<ResourceKey<PoiType>, PoiType> poiTypeGetter, Function<ResourceKey<PoiType>, Holder<PoiType>> poiTypeHolderGetter, ResourceKey<PoiType> poiTypeKey, Block addBlock) {
        PoiType poiType = poiTypeGetter.apply(poiTypeKey);
        Set<BlockState> addedStates = new HashSet<>(addBlock.getStateDefinition().getPossibleStates());
        Set<BlockState> newStates = new HashSet<>();
        newStates.addAll(((PoiTypeAccessor)(Object)poiType).getMatchingStates());
        newStates.addAll(addedStates);
        ((PoiTypeAccessor) (Object)poiType).setMatchingStates(ImmutableSet.copyOf(newStates));
        addedStates.forEach(state -> PoiTypesAccessor.getBlockStateToPointOfInterestType().put(state, poiTypeHolderGetter.apply(poiTypeKey)));
    }

    private CommonLoader() {}

}
