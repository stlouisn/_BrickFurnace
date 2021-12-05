package cech12.brickfurnace;

import cech12.brickfurnace.api.block.BrickFurnaceBlocks;
import cech12.brickfurnace.api.crafting.RecipeTypes;
import cech12.brickfurnace.config.ServerConfig;
import cech12.brickfurnace.crafting.BrickBlastingRecipe;
import cech12.brickfurnace.crafting.BrickSmeltingRecipe;
import cech12.brickfurnace.crafting.BrickSmokingRecipe;
import com.google.common.collect.ImmutableSet;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.loading.FMLConfig;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static cech12.brickfurnace.BrickFurnaceMod.MOD_ID;

@Mod(MOD_ID)
@Mod.EventBusSubscriber(modid= MOD_ID, bus= Mod.EventBusSubscriber.Bus.MOD)
public class BrickFurnaceMod {

    public static final String MOD_ID = "brickfurnace";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public BrickFurnaceMod() {
        //Config
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, ServerConfig.SERVER_CONFIG);
        ServerConfig.loadConfig(ServerConfig.SERVER_CONFIG, FMLPaths.GAMEDIR.get().resolve(FMLConfig.defaultConfigPath()).resolve(MOD_ID + "-server.toml"));
    }

    @SubscribeEvent
    public static void registerRecipeSerializers(RegistryEvent.Register<RecipeSerializer<?>> event) {
        RecipeTypes.BLASTING = Registry.register(Registry.RECIPE_TYPE,
                RecipeTypes.BLASTING_ID,
                new RecipeType<BrickBlastingRecipe>() {});
        ForgeRegistries.RECIPE_SERIALIZERS.register(BrickBlastingRecipe.SERIALIZER);

        RecipeTypes.SMELTING = Registry.register(Registry.RECIPE_TYPE,
                RecipeTypes.SMELTING_ID,
                new RecipeType<BrickSmeltingRecipe>() {});
        ForgeRegistries.RECIPE_SERIALIZERS.register(BrickSmeltingRecipe.SERIALIZER);

        RecipeTypes.SMOKING = Registry.register(Registry.RECIPE_TYPE,
                RecipeTypes.SMOKING_ID,
                new RecipeType<BrickSmokingRecipe>() {});
        ForgeRegistries.RECIPE_SERIALIZERS.register(BrickSmokingRecipe.SERIALIZER);
    }

    @SubscribeEvent
    public static void registerVillagerWorkstations(RegistryEvent.Register<PoiType> event) {
        addBlockStatesToPOIType(PoiType.ARMORER, BrickFurnaceBlocks.BRICK_BLAST_FURNACE);
        addBlockStatesToPOIType(PoiType.BUTCHER, BrickFurnaceBlocks.BRICK_SMOKER);
    }

    private static void addBlockStatesToPOIType(PoiType poiType, Block block) {
        Set<BlockState> poiTypeStates = new HashSet<>(poiType.getBlockStates());
        Set<BlockState> blockStates = new HashSet<>(block.getStateDefinition().getPossibleStates());
        poiTypeStates.addAll(blockStates);
        poiType.matchingStates = ImmutableSet.copyOf(poiTypeStates);
        try {
            //accesstransformer cannot change access of TYPE_BY_STATE - use java reflection
            Field typeByStateField = PoiType.class.getDeclaredField("TYPE_BY_STATE");
            typeByStateField.setAccessible(true);
            Map<BlockState, PoiType> typeByState = (Map<BlockState, PoiType>) typeByStateField.get(poiType);
            for (BlockState blockState : blockStates) {
                typeByState.put(blockState, poiType);
            }
        } catch (Throwable exception) {
            LOGGER.warn("PointOfInterestType (" + poiType + ") - reflection of TYPE_BY_STATE failed", exception);
        }
    }

}
