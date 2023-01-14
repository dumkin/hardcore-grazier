package in.dumk.hardcore_grazier.tabs;

import in.dumk.hardcore_grazier.HardcoreGrazier;
import in.dumk.hardcore_grazier.util.HardcoreGrazierItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TabMain extends CreativeTabs {
  public TabMain(String label) {
    super(HardcoreGrazier.MODID + "." + label);
  }

  @SideOnly(Side.CLIENT)
  @Override
  public ItemStack getTabIconItem() {
    return new ItemStack(HardcoreGrazierItems.SYRINGE);
  }
}