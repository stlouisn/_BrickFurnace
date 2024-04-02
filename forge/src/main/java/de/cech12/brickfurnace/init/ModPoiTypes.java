package de.cech12.brickfurnace.init;

import de.cech12.brickfurnace.Constants;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.ai.village.poi.PoiTypes;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashSet;

public class ModPoiTypes {

    public static DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, "minecraft");

    public static RegistryObject<PoiType> ARMORER = POI_TYPES.register("armorer", () -> {
        HashSet<BlockState> states = new HashSet<>(ForgeRegistries.POI_TYPES.getDelegateOrThrow(PoiTypes.ARMORER).get().matchingStates());
        states.addAll(Constants.BRICK_BLAST_FURNACE_BLOCK.get().getStateDefinition().getPossibleStates());
        return new PoiType(states, 1, 1);
    });
    public static RegistryObject<PoiType> BUTCHER = POI_TYPES.register("butcher", () ->{
        HashSet<BlockState> states = new HashSet<>(ForgeRegistries.POI_TYPES.getDelegateOrThrow(PoiTypes.BUTCHER).get().matchingStates());
        states.addAll(Constants.BRICK_SMOKER_BLOCK.get().getStateDefinition().getPossibleStates());
        return new PoiType(states, 1, 1);
    });

}
