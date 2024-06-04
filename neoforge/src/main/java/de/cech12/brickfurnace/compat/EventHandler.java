package de.cech12.brickfurnace.compat;

import de.cech12.brickfurnace.Constants;
import de.cech12.brickfurnace.compat.immersiveengineering.ImmersiveEngineering;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD, modid= Constants.MOD_ID)
public class EventHandler {

    @SubscribeEvent
    public static void onCapabilitiesAttachBlockEntity(RegisterCapabilitiesEvent event) {
        if (ModList.get().isLoaded("immersiveengineering")) {
            ImmersiveEngineering.onCapabilitiesAttachBlockEntity(event);
        }
    }

}