package cech12.brickfurnace.integration;

import cech12.brickfurnace.IntegrationTestUtils;
import cech12.brickfurnace.api.block.BrickFurnaceBlocks;
import com.alcatrazescapee.mcjunitlib.framework.IntegrationTest;
import com.alcatrazescapee.mcjunitlib.framework.IntegrationTestClass;
import com.alcatrazescapee.mcjunitlib.framework.IntegrationTestHelper;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

@IntegrationTestClass(value = "villager")
public class VillagerTests {

    private static final BlockPos FURNACE_POSITION = new BlockPos(1, 1, 1);
    private static final BlockPos VILLAGER_POSITION = new BlockPos(1, 2, 1);

    @IntegrationTest(value = "villager_pit")
    public void testVillagerConvertsToArmorerWithVanillaBlastFurnace(IntegrationTestHelper helper) {
        helper.placeBlock(FURNACE_POSITION, Direction.NORTH, Blocks.BLAST_FURNACE);
        VillagerEntity villager = (VillagerEntity) IntegrationTestUtils.placeEntity(helper, VILLAGER_POSITION, EntityType.VILLAGER);

        helper.assertTrue(() -> villager.getVillagerData().getProfession() == VillagerProfession.ARMORER, "Villager should change its profession to armorer");
    }

    @IntegrationTest(value = "villager_pit")
    public void testVillagerConvertsToArmorerWithBrickBlastFurnace(IntegrationTestHelper helper) {
        helper.placeBlock(FURNACE_POSITION, Direction.NORTH, BrickFurnaceBlocks.BRICK_BLAST_FURNACE);
        VillagerEntity villager = (VillagerEntity) IntegrationTestUtils.placeEntity(helper, VILLAGER_POSITION, EntityType.VILLAGER);

        helper.assertTrue(() -> villager.getVillagerData().getProfession() == VillagerProfession.ARMORER, "Villager should change its profession to armorer");
    }

    @IntegrationTest(value = "villager_pit")
    public void testVillagerConvertsToButcherWithVanillaSmoker(IntegrationTestHelper helper) {
        helper.placeBlock(FURNACE_POSITION, Direction.NORTH, Blocks.SMOKER);
        VillagerEntity villager = (VillagerEntity) IntegrationTestUtils.placeEntity(helper, VILLAGER_POSITION, EntityType.VILLAGER);

        helper.assertTrue(() -> villager.getVillagerData().getProfession() == VillagerProfession.BUTCHER, "Villager should change its profession to butcher");
    }

    @IntegrationTest(value = "villager_pit")
    public void testVillagerConvertsToButcherWithBrickSmoker(IntegrationTestHelper helper) {
        helper.placeBlock(FURNACE_POSITION, Direction.NORTH, BrickFurnaceBlocks.BRICK_SMOKER);
        VillagerEntity villager = (VillagerEntity) IntegrationTestUtils.placeEntity(helper, VILLAGER_POSITION, EntityType.VILLAGER);

        helper.assertTrue(() -> villager.getVillagerData().getProfession() == VillagerProfession.BUTCHER, "Villager should change its profession to butcher");
    }

}
