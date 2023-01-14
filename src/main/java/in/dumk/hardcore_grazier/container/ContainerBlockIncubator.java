package in.dumk.hardcore_grazier.container;

import in.dumk.hardcore_grazier.container.base.ContainerBase;
import in.dumk.hardcore_grazier.container.slot.SlotBlocked;
import in.dumk.hardcore_grazier.container.slot.SlotWhiteList;
import in.dumk.hardcore_grazier.tile.TileEntityIncubator;
import in.dumk.hardcore_grazier.util.HardcoreGrazierItems;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class ContainerBlockIncubator extends ContainerBase {
  public ContainerBlockIncubator(IInventory playerInv, TileEntityIncubator tileEntity) {
    IItemHandler handler = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

    this.addSlotToContainer(new SlotWhiteList(HardcoreGrazierItems.DECODED_DNA, handler, 0, 27, 35));
    this.addSlotToContainer(new SlotWhiteList(Items.EGG, handler, 1, 76, 35));
    this.addSlotToContainer(new SlotBlocked(handler, 2, 134, 35));

    this.addInventoryToContainer(playerInv, 8, 84);
  }
}