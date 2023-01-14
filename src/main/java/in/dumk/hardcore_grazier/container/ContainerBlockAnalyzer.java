package in.dumk.hardcore_grazier.container;

import in.dumk.hardcore_grazier.container.base.ContainerBase;
import in.dumk.hardcore_grazier.container.slot.SlotBlocked;
import in.dumk.hardcore_grazier.container.slot.SlotWhiteList;
import in.dumk.hardcore_grazier.tile.TileEntityAnalyzer;
import in.dumk.hardcore_grazier.util.HardcoreGrazierItems;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class ContainerBlockAnalyzer extends ContainerBase {
  public ContainerBlockAnalyzer(IInventory playerInv, TileEntityAnalyzer te) {
    IItemHandler handler = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

    this.addSlotToContainer(new SlotWhiteList(HardcoreGrazierItems.SYRINGE_BLOOD, handler, 0, 27, 35));
    this.addSlotToContainer(new SlotWhiteList(Items.PAPER, handler, 1, 76, 35));
    this.addSlotToContainer(new SlotBlocked(handler, 2, 134, 35));

    this.addInventoryToContainer(playerInv, 8, 84);
  }
}