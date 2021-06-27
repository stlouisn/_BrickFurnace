package cech12.brickfurnace.integration;

import cech12.brickfurnace.api.block.BrickFurnaceBlocks;
import cech12.brickfurnace.tileentity.AbstractBrickFurnaceTileEntity;
import com.alcatrazescapee.mcjunitlib.framework.IntegrationTest;
import com.alcatrazescapee.mcjunitlib.framework.IntegrationTestClass;
import com.alcatrazescapee.mcjunitlib.framework.IntegrationTestHelper;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.HopperTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

@IntegrationTestClass(value = "hopper")
public class HopperTests {

    private static final BlockPos FURNACE_POSITION = new BlockPos(1, 2, 1);

    private static final BlockPos MAIN_INPUT_HOPPER_POSITION = new BlockPos(1, 3, 1);
    private static final BlockPos SECONDARY_INPUT_HOPPER_1_POSITION = new BlockPos(0, 2, 1);
    private static final BlockPos SECONDARY_INPUT_HOPPER_2_POSITION = new BlockPos(1, 2, 0);
    private static final BlockPos SECONDARY_INPUT_HOPPER_3_POSITION = new BlockPos(2, 2, 1);
    private static final BlockPos SECONDARY_INPUT_HOPPER_4_POSITION = new BlockPos(1, 2, 2);
    private static final BlockPos OUTPUT_HOPPER_POSITION = new BlockPos(1, 1, 1);

    private AbstractBrickFurnaceTileEntity getFurnaceTileEntity(IntegrationTestHelper helper) {
        AbstractBrickFurnaceTileEntity furnaceTileEntity = (AbstractBrickFurnaceTileEntity) helper.getTileEntity(FURNACE_POSITION);
        if (furnaceTileEntity == null) {
            helper.fail("AbstractBrickFurnaceTileEntity could not be found at " + FURNACE_POSITION);
            return null;
        }
        return furnaceTileEntity;
    }

    private HopperTileEntity getHopperTileEntity(IntegrationTestHelper helper, BlockPos pos) {
        HopperTileEntity hopperTileEntity = (HopperTileEntity) helper.getTileEntity(pos);
        if (hopperTileEntity == null) {
            helper.fail("HopperTileEntity for main input could not be found at " + pos);
            return null;
        }
        return hopperTileEntity;
    }

    @IntegrationTest(value = "hoppers")
    public void testMainInputOfBrickFurnace(IntegrationTestHelper helper) {
        testMainInput(helper, BrickFurnaceBlocks.BRICK_FURNACE, Items.COBBLESTONE);
    }

    @IntegrationTest(value = "hoppers")
    public void testMainInputOfBrickBlastFurnace(IntegrationTestHelper helper) {
        testMainInput(helper, BrickFurnaceBlocks.BRICK_BLAST_FURNACE, Items.CRAFTING_TABLE);
    }

    @IntegrationTest(value = "hoppers")
    public void testMainInputOfBrickSmoker(IntegrationTestHelper helper) {
        testMainInput(helper, BrickFurnaceBlocks.BRICK_SMOKER, Items.COD);
    }

    private void testMainInput(IntegrationTestHelper helper, Block furnaceBlock, Item inputItem) {
        helper.placeBlock(FURNACE_POSITION, Direction.NORTH, furnaceBlock);
        AbstractBrickFurnaceTileEntity furnaceTileEntity = getFurnaceTileEntity(helper);
        HopperTileEntity hopperTileEntity = getHopperTileEntity(helper, MAIN_INPUT_HOPPER_POSITION);
        if (furnaceTileEntity == null || hopperTileEntity == null) {
            return;
        }
        ItemStack inputItemStack = new ItemStack(inputItem);
        hopperTileEntity.setItem(0, inputItemStack.copy());

        helper.assertTrue(() -> hopperTileEntity.getItem(0).isEmpty(), inputItemStack.getItem() + " should be removed from main input hopper");
        helper.assertTrue(() -> furnaceTileEntity.getItem(0).equals(inputItemStack, true), inputItemStack.getItem() + " should be inserted to main slot of " + furnaceBlock);
    }

    @IntegrationTest(value = "hoppers")
    public void testSecondaryInputOfBrickFurnaceAcceptsItem(IntegrationTestHelper helper) {
        testSecondaryInput(helper, BrickFurnaceBlocks.BRICK_FURNACE, Items.COAL, true);
    }

    @IntegrationTest(value = "hoppers")
    public void testSecondaryInputOfBrickFurnaceRejectsItem(IntegrationTestHelper helper) {
        testSecondaryInput(helper, BrickFurnaceBlocks.BRICK_FURNACE, Items.IRON_INGOT, false);
    }

    @IntegrationTest(value = "hoppers")
    public void testSecondaryInputOfBrickBlastFurnaceAcceptsItem(IntegrationTestHelper helper) {
        testSecondaryInput(helper, BrickFurnaceBlocks.BRICK_BLAST_FURNACE, Items.OAK_PLANKS, true);
    }

    @IntegrationTest(value = "hoppers")
    public void testSecondaryInputOfBrickBlastFurnaceRejectsItem(IntegrationTestHelper helper) {
        testSecondaryInput(helper, BrickFurnaceBlocks.BRICK_BLAST_FURNACE, Items.OBSIDIAN, false);
    }

    @IntegrationTest(value = "hoppers")
    public void testSecondaryInputOfBrickSmokerAcceptsItem(IntegrationTestHelper helper) {
        testSecondaryInput(helper, BrickFurnaceBlocks.BRICK_SMOKER, Items.COAL_BLOCK, true);
    }

    @IntegrationTest(value = "hoppers")
    public void testSecondaryInputOfBrickSmokerRejectsItem(IntegrationTestHelper helper) {
        testSecondaryInput(helper, BrickFurnaceBlocks.BRICK_SMOKER, Items.STONE_BRICKS, false);
    }

    private void testSecondaryInput(IntegrationTestHelper helper, Block furnaceBlock, Item inputItem, boolean shouldBePutInside) {
        helper.placeBlock(FURNACE_POSITION, Direction.NORTH, furnaceBlock);
        AbstractBrickFurnaceTileEntity furnaceTileEntity = getFurnaceTileEntity(helper);
        HopperTileEntity hopperTileEntity1 = getHopperTileEntity(helper, SECONDARY_INPUT_HOPPER_1_POSITION);
        HopperTileEntity hopperTileEntity2 = getHopperTileEntity(helper, SECONDARY_INPUT_HOPPER_2_POSITION);
        HopperTileEntity hopperTileEntity3 = getHopperTileEntity(helper, SECONDARY_INPUT_HOPPER_3_POSITION);
        HopperTileEntity hopperTileEntity4 = getHopperTileEntity(helper, SECONDARY_INPUT_HOPPER_4_POSITION);
        if (furnaceTileEntity == null || hopperTileEntity1 == null || hopperTileEntity2 == null || hopperTileEntity3 == null || hopperTileEntity4 == null) {
            return;
        }
        ItemStack inputItemStack = new ItemStack(inputItem);
        hopperTileEntity1.setItem(0, inputItemStack.copy());
        hopperTileEntity2.setItem(0, inputItemStack.copy());
        hopperTileEntity3.setItem(0, inputItemStack.copy());
        hopperTileEntity4.setItem(0, inputItemStack.copy());

        if (shouldBePutInside) {
            inputItemStack.setCount(4);
            helper.assertTrue(() -> hopperTileEntity1.getItem(0).isEmpty(), inputItemStack.getItem() + " should be removed from secondary input hopper 1");
            helper.assertTrue(() -> hopperTileEntity2.getItem(0).isEmpty(), inputItemStack.getItem() + " should be removed from secondary input hopper 2");
            helper.assertTrue(() -> hopperTileEntity3.getItem(0).isEmpty(), inputItemStack.getItem() + " should be removed from secondary input hopper 3");
            helper.assertTrue(() -> hopperTileEntity4.getItem(0).isEmpty(), inputItemStack.getItem() + " should be removed from secondary input hopper 4");
            helper.assertTrue(() -> furnaceTileEntity.getItem(1).equals(inputItemStack, true), inputItemStack + " should be inserted to secondary slot of " + furnaceBlock);
        } else {
            helper.assertTrue(() -> hopperTileEntity1.getItem(0).equals(inputItemStack, true), inputItemStack.getItem() + " should not be removed from secondary input hopper 1");
            helper.assertTrue(() -> hopperTileEntity2.getItem(0).equals(inputItemStack, true), inputItemStack.getItem() + " should not be removed from secondary input hopper 2");
            helper.assertTrue(() -> hopperTileEntity3.getItem(0).equals(inputItemStack, true), inputItemStack.getItem() + " should not be removed from secondary input hopper 3");
            helper.assertTrue(() -> hopperTileEntity4.getItem(0).equals(inputItemStack, true), inputItemStack.getItem() + " should not be removed from secondary input hopper 4");
            helper.assertTrue(() -> furnaceTileEntity.getItem(1).isEmpty(), "No item should be inserted to secondary slot of " + furnaceBlock);
        }
    }

    @IntegrationTest(value = "hoppers")
    public void testOutputOfBrickFurnace(IntegrationTestHelper helper) {
        testOutput(helper, BrickFurnaceBlocks.BRICK_FURNACE, Items.STONE);
    }

    @IntegrationTest(value = "hoppers")
    public void testOutputOfBrickBlastFurnace(IntegrationTestHelper helper) {
        testOutput(helper, BrickFurnaceBlocks.BRICK_BLAST_FURNACE, Items.IRON_INGOT);
    }

    @IntegrationTest(value = "hoppers")
    public void testOutputOfBrickSmoker(IntegrationTestHelper helper) {
        testOutput(helper, BrickFurnaceBlocks.BRICK_SMOKER, Items.CHARCOAL);
    }

    private void testOutput(IntegrationTestHelper helper, Block furnaceBlock, Item outputItem) {
        helper.placeBlock(FURNACE_POSITION, Direction.NORTH, furnaceBlock);
        AbstractBrickFurnaceTileEntity furnaceTileEntity = getFurnaceTileEntity(helper);
        HopperTileEntity hopperTileEntity = getHopperTileEntity(helper, OUTPUT_HOPPER_POSITION);
        if (furnaceTileEntity == null || hopperTileEntity == null) {
            return;
        }
        ItemStack outputItemStack = new ItemStack(outputItem);
        furnaceTileEntity.setItem(2, outputItemStack.copy());

        helper.assertTrue(() -> furnaceTileEntity.getItem(2).isEmpty(), outputItemStack.getItem() + " should be removed from output slot of" + furnaceBlock);
        helper.assertTrue(() -> hopperTileEntity.getItem(0).equals(outputItemStack, true), outputItemStack.getItem() + " should be inserted to output hopper");
    }

}
