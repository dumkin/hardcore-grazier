package in.dumk.hardcore_grazier.tile;

import in.dumk.hardcore_grazier.util.HardcoreGrazierBlocks;
import in.dumk.hardcore_grazier.util.HardcoreGrazierItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileEntityIncubator extends TileEntity implements ITickable, ICapabilityProvider {
  private ItemStackHandler handler;
  private int progress;

  public static final int itemsCount = 3;
  public static final int duration = 20 * 5;

  public TileEntityIncubator() {
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

    if (this.handler.getStackInSlot(0).getItem() == HardcoreGrazierItems.DECODED_DNA &&
      this.handler.getStackInSlot(1).getItem() == Items.EGG &&
      this.handler.getStackInSlot(2).isEmpty()) {

      this.progress++;
      this.markDirty();
    }

    if (!this.world.isRemote && this.progress >= this.duration) {
      ItemStack egg = new ItemStack(HardcoreGrazierBlocks.EGG, 1);

      if (this.handler.getStackInSlot(0).hasTagCompound()) {
        NBTTagCompound dna = this.handler.getStackInSlot(0).getTagCompound();
        egg.setTagCompound(dna);
      }

      this.handler.getStackInSlot(0).shrink(1);
      this.handler.getStackInSlot(1).shrink(1);
      this.handler.setStackInSlot(2, egg);
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