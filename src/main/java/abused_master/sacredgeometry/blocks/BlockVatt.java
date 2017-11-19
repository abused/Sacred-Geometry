package abused_master.sacredgeometry.blocks;

import abused_master.abusedlib.blocks.BlockBase;
import abused_master.abusedlib.utils.FluidUtils;
import abused_master.sacredgeometry.SacredGeometry;
import abused_master.sacredgeometry.registry.ModResources;
import abused_master.sacredgeometry.tileentity.TileEntityVatt;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidActionResult;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

import static net.minecraftforge.fluids.capability.CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY;

public class BlockVatt extends BlockBase {

    public BlockVatt() {
        super(Material.ROCK, "acid_vatt", SacredGeometry.SacredTab);
        this.setHarvestLevel("pickaxe", 2);
        this.setHardness(2.2f);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack heldItem = player.getHeldItem(hand);
        final TileEntityVatt te = (TileEntityVatt) world.getTileEntity(pos);


        if(heldItem.getItem() == FluidUtil.getFilledBucket(new FluidStack(ModResources.acid, 1000)).getItem()) {
            IFluidHandler handler = te.getCapability(FLUID_HANDLER_CAPABILITY, facing);
            FluidActionResult res = FluidUtils.interactWithFluidHandler(heldItem, handler, player);
            if (res.isSuccess()) {
                player.setHeldItem(hand, res.getResult());
                return true;
            }
        }else if(SacredGeometry.vattIngredients.containsIngredient(Block.getBlockFromItem(heldItem.getItem())) && heldItem != FluidUtil.getFilledBucket(new FluidStack(ModResources.acid, 1000))) {
            if(te.inventory.isEmpty()) {
                te.setInventoryStack(new ItemStack(heldItem.getItem(), 1));

                if(player.getHeldItem(hand).getMaxStackSize() > 1) {
                    player.inventory.getCurrentItem().shrink(1);
                    player.openContainer.detectAndSendChanges();
                }else {
                    player.inventory.setInventorySlotContents(player.inventory.currentItem, ItemStack.EMPTY);
                    player.openContainer.detectAndSendChanges();
                }
                return true;
            }
        }else if(!te.inventory.isEmpty()){
            if(!world.isRemote) {
                EntityItem entityItem = new EntityItem(world, pos.getX(), pos.getY() + 1, pos.getZ(), te.inventory);
                world.spawnEntity(entityItem);
                te.setInventoryStack(ItemStack.EMPTY);
                return true;
            }
        }
        return true;
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityVatt();
    }
}
