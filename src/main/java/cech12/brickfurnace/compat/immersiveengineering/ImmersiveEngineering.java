package cech12.brickfurnace.compat.immersiveengineering;

import blusunrize.immersiveengineering.api.tool.ExternalHeaterHandler;
import blusunrize.immersiveengineering.api.utils.CapabilityUtils;
import cech12.brickfurnace.BrickFurnaceMod;
import cech12.brickfurnace.blockentity.BrickFurnaceBlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;
import java.util.function.Supplier;

public class ImmersiveEngineering {

    public static void onCapabilitiesAttachBlockEntity(AttachCapabilitiesEvent<BlockEntity> event) {
        if (event.getObject() instanceof BrickFurnaceBlockEntity furnace) {
            event.addCapability(new ResourceLocation(BrickFurnaceMod.MOD_ID, "brick_furnace_heater"),
                    new SimpleCapProvider<>(() -> ExternalHeaterHandler.CAPABILITY, new BrickFurnaceHeater(furnace))
            );
        }
    }

    public record SimpleCapProvider<T>(Supplier<Capability<T>> cap, LazyOptional<T> value) implements ICapabilityProvider {
        public SimpleCapProvider(Supplier<Capability<T>> cap, T value)
        {
            this(cap, CapabilityUtils.constantOptional(value));
        }

        @Nonnull
        @Override
        public <T2> LazyOptional<T2> getCapability(@Nonnull Capability<T2> cap, @Nullable Direction side)
        {
            return Objects.requireNonNull(this.cap.get()).orEmpty(cap, value);
        }
    }

}
