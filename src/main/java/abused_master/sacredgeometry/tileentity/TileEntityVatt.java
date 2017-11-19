package abused_master.sacredgeometry.tileentity;

import abused_master.abusedlib.render.hud.IHudSupport;
import abused_master.abusedlib.tileentity.TileEntityBase;
import abused_master.abusedlib.utils.AbusedUtils;
import abused_master.sacredgeometry.registry.ModResources;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class TileEntityVatt extends TileEntityBase implements IHudSupport {

    public FluidTank tank = new FluidTank(4000);
    public ItemStack inventory = ItemStack.EMPTY;
    public int workTime;
    public int totalWorkTime;

    public TileEntityVatt() {
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        if (nbt.hasKey("FluidData")) {
            this.tank.setFluid(FluidStack.loadFluidStackFromNBT(nbt.getCompoundTag("FluidData")));
        }

        if(tank != null && tank.getFluid() != null) {
            tank.readFromNBT(nbt);
        }

        if (this.tank != null) {
            this.tank.setTileEntity(this);
        }

        if (nbt.hasKey("item")) {
            inventory = new ItemStack(nbt.getCompoundTag("item"));
        } else {
            inventory = ItemStack.EMPTY;
        }
        workTime = nbt.getInteger("WorkTime");
        totalWorkTime = nbt.getInteger("TotalCookTime");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        if (this.tank != null && this.tank.getFluid() != null) {
            final NBTTagCompound tankTag = new NBTTagCompound();
            this.tank.getFluid().writeToNBT(tankTag);
            nbt.setTag("FluidData", tankTag);
            tank.writeToNBT(nbt);
        }

        if (!inventory.isEmpty()) {
            NBTTagCompound tagCompound = new NBTTagCompound();
            inventory.writeToNBT(tagCompound);
            nbt.setTag("item", tagCompound);
        }

        nbt.setInteger("WorkTime", (short) workTime);
        nbt.setInteger("TotalWorkTime", (short) totalWorkTime);
        return super.writeToNBT(nbt);
    }

    @Override
    public void update() {
        if(!world.isRemote) {
            IBlockState state = world.getBlockState(pos);
            world.notifyBlockUpdate(pos, state, state, 3);
        }
        this.totalWorkTime = 120;

        if(!inventory.isEmpty()) {
            if(inventory.getItem() != Item.getItemFromBlock(ModResources.blockOrb)) {
                if (tank.getFluidAmount() >= 500) {
                    workTime++;
                    if (workTime >= totalWorkTime) {
                        workTime = 0;
                        this.setInventoryStack(new ItemStack(ModResources.blockOrb, 1));
                        tank.drain(500, true);
                    }
                }
            }
        }else {
            if(workTime > 0) {
                workTime = 0;
            }
        }
    }

    public void setInventoryStack(ItemStack stack) {
        this.inventory = stack;
        markDirty();
        if (world != null) {
            IBlockState state = world.getBlockState(getPos());
            world.notifyBlockUpdate(getPos(), state, state, 3);
        }
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
        return (T) tank;
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public EnumFacing getBlockOrientation() {
        return AbusedUtils.getOrientationHoriz(getBlockMetadata());
    }

    @Override
    public boolean isBlockAboveAir() {
        return getWorld().isAirBlock(pos.up());
    }

    @Override
    public BlockPos getBlockPos() {
        return getPos();
    }

    @Override
    public String getDisplay() {
        return tank.getFluidAmount() + " / " + tank.getCapacity() + " MB";
    }
}
