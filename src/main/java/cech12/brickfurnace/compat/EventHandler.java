package cech12.brickfurnace.compat;

import cech12.brickfurnace.compat.immersiveengineering.ImmersiveEngineering;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

import static cech12.brickfurnace.BrickFurnaceMod.MOD_ID;

@Mod.EventBusSubscriber(modid= MOD_ID)
public class EventHandler {

    @SubscribeEvent
    public static void onCapabilitiesAttachBlockEntity(AttachCapabilitiesEvent<BlockEntity> event) {
        if (ModList.get().isLoaded("immersiveengineering")) {
            ImmersiveEngineering.onCapabilitiesAttachBlockEntity(event);
        }
    }

}
