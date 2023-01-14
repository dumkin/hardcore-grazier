package in.dumk.hardcore_grazier.container.slot;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotBlocked extends SlotItemHandler {
  public SlotBlocked(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
    super(itemHandler, index, xPosition, yPosition);
  }

  @Override
  public boolean isItemValid(ItemStack stack) {
    return false;
  }
}