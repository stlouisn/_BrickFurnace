package de.cech12.brickfurnace.mixin;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.ai.village.poi.PoiTypes;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(PoiTypes.class)
public interface PoiTypesAccessor {

    @Accessor("TYPE_BY_STATE")
    static Map<BlockState, Holder<PoiType>> getBlockStateToPointOfInterestType() {
        throw new AssertionError();
    }

    @Mutable
    @Accessor("ARMORER")
    static void setArmorer(ResourceKey<PoiType> armorer) {
        throw new AssertionError();
    }

    @Mutable
    @Accessor("BUTCHER")
    static void setButcher(ResourceKey<PoiType> butcher) {
        throw new AssertionError();
    }

}
