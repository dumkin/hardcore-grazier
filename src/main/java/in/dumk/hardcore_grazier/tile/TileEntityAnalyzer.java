package in.dumk.hardcore_grazier.tile;

import in.dumk.hardcore_grazier.util.HardcoreGrazierItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileEntityAnalyzer extends TileEntity implements ITickable, ICapabilityProvider {
  private ItemStackHandler handler;
  private int progress;

  public static final int itemsCount = 3;
  public static final int duration = 20 * 5;

  public TileEntityAnalyzer() {
    this.progress = 0;
    this.handler = new ItemStackHandler(itemsCount);
  }

  public int getProgress() {
    return this.progress;
  }

  @Override
  public void update() {
    if (this.handler.getStackInSlot(0).isEmpty() ||
      this.handler.getStackInSlot(1).isEmpty() ||
      !this.handler.getStackInSlot(2).isEmpty()) {

      this.progress = 0;
      this.markDirty();
      return;
    }

    if (this.handler.getStackInSlot(0).getItem() == HardcoreGrazierItems.SYRINGE_BLOOD &&
      this.handler.getStackInSlot(1).getItem() == Items.PAPER &&
      this.handler.getStackInSlot(2).isEmpty()) {

      this.progress++;
      this.markDirty();
    }

    if (!this.world.isRemote && this.progress >= this.duration) {
      NBTTagCompound nbt;
      ItemStack dna = new ItemStack(HardcoreGrazierItems.DECODED_DNA, 1);

      if (this.handler.getStackInSlot(0).hasTagCompound()) {
        nbt = this.handler.getStackInSlot(0).getTagCompound();
        dna.setTagCompound(nbt);
      }

      this.handler.getStackInSlot(0).shrink(1);
      this.handler.getStackInSlot(1).shrink(1);
      this.handler.setStackInSlot(2, dna);
      this.markDirty();
    }
  }

  @Override
  public void readFromNBT(NBTTagCompound compound) {
    progress = compound.getInteger("progress");
    handler.deserializeNBT(compound.getCompoundTag("handler"));

    super.readFromNBT(compound);
  }

  @Override
  public NBTTagCompound writeToNBT(NBTTagCompound compound) {
    compound.setInteger("progress", progress);
    compound.setTag("handler", handler.serializeNBT());

    return super.writeToNBT(compound);
  }

  @Override
  public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
    if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
      return true;
    }

    return super.hasCapability(capability, facing);
  }

  @Override
  @Nullable
  public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
    if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
      return (T) handler;
    }

    return super.getCapability(capability, facing);
  }
}