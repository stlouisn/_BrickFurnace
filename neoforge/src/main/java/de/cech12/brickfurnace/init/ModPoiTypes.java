package de.cech12.brickfurnace.init;

import de.cech12.brickfurnace.Constants;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.ai.village.poi.PoiTypes;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashSet;

public class ModPoiTypes {

    public static DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(BuiltInRegistries.POINT_OF_INTEREST_TYPE, "minecraft");

    public static DeferredHolder<PoiType, PoiType> ARMORER = POI_TYPES.register("armorer", () -> {
        HashSet<BlockState> states = new HashSet<>(BuiltInRegistries.POINT_OF_INTEREST_TYPE.get(PoiTypes.ARMORER).matchingStates());
        states.addAll(Constants.BRICK_BLAST_FURNACE_BLOCK.get().getStateDefinition().getPossibleStates());
        return new PoiType(states, 1, 1);
    });
    public static DeferredHolder<PoiType, PoiType> BUTCHER = POI_TYPES.register("butcher", () ->{
        HashSet<BlockState> states = new HashSet<>(BuiltInRegistries.POINT_OF_INTEREST_TYPE.get(PoiTypes.BUTCHER).matchingStates());
        states.addAll(Constants.BRICK_SMOKER_BLOCK.get().getStateDefinition().getPossibleStates());
        return new PoiType(states, 1, 1);
    });

}
