package de.cech12.brickfurnace.integration;

import de.cech12.brickfurnace.BrickFurnaceMod;
import de.cech12.brickfurnace.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.gametest.GameTestHolder;

@SuppressWarnings("unused")
@GameTestHolder(BrickFurnaceMod.MOD_ID)
public class VillagerTests {

    private static final BlockPos FURNACE_POSITION = new BlockPos(1, 1, 1);
    private static final BlockPos VILLAGER_POSITION = new BlockPos(1, 2, 1);

    @GameTest(template = "villager_pit")
    public void testVillagerConvertsToArmorerWithVanillaBlastFurnace(GameTestHelper test) {
        test.setBlock(FURNACE_POSITION, Blocks.BLAST_FURNACE);
        test.spawn(EntityType.VILLAGER, VILLAGER_POSITION);
        test.succeedWhenEntityData(VILLAGER_POSITION, EntityType.VILLAGER,
                (villager) -> villager.getVillagerData().getProfession(), VillagerProfession.ARMORER);
    }

    @GameTest(template = "villager_pit")
    public void testVillagerConvertsToArmorerWithBrickBlastFurnace(GameTestHelper test) {
        test.setBlock(FURNACE_POSITION, ModBlocks.BRICK_BLAST_FURNACE.get());
        test.spawn(EntityType.VILLAGER, VILLAGER_POSITION);
        test.succeedWhenEntityData(VILLAGER_POSITION, EntityType.VILLAGER,
                (villager) -> villager.getVillagerData().getProfession(), VillagerProfession.ARMORER);
    }

    @GameTest(template = "villager_pit")
    public void testVillagerConvertsToButcherWithVanillaSmoker(GameTestHelper test) {
        test.setBlock(FURNACE_POSITION, Blocks.SMOKER);
        test.spawn(EntityType.VILLAGER, VILLAGER_POSITION);
        test.succeedWhenEntityData(VILLAGER_POSITION, EntityType.VILLAGER,
                (villager) -> villager.getVillagerData().getProfession(), VillagerProfession.BUTCHER);
    }

    @GameTest(template = "villager_pit")
    public void testVillagerConvertsToButcherWithBrickSmoker(GameTestHelper test) {
        test.setBlock(FURNACE_POSITION, ModBlocks.BRICK_SMOKER.get());
        test.spawn(EntityType.VILLAGER, VILLAGER_POSITION);
        test.succeedWhenEntityData(VILLAGER_POSITION, EntityType.VILLAGER,
                (villager) -> villager.getVillagerData().getProfession(), VillagerProfession.BUTCHER);
    }

}
