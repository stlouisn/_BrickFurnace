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

@IntegrationTestClass(value = "furnace")
public class SmeltingTests {
/*
    private static final BlockPos FURNACE_POSITION = new BlockPos(0, 1, 0);

    private AbstractBrickFurnaceTileEntity getFurnaceTileEntity(IntegrationTestHelper helper) {
        AbstractBrickFurnaceTileEntity furnaceTileEntity = (AbstractBrickFurnaceTileEntity) helper.getTileEntity(FURNACE_POSITION);
        if (furnaceTileEntity == null) {
            helper.fail("AbstractBrickFurnaceTileEntity could not be found at " + FURNACE_POSITION);
            return null;
        }
        return furnaceTileEntity;
    }

    @IntegrationTest(value = "hoppers")
    public void testFurnaceRecipeInBrickFurnace(IntegrationTestHelper helper) {
        testMainInput(helper, BrickFurnaceBlocks.BRICK_FURNACE, Items.COBBLESTONE);
    }

    private void testRecipe(IntegrationTestHelper helper, Block furnaceBlock, Item inputItem) {
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

 */

}
