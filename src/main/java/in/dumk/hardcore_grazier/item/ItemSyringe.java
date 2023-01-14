package in.dumk.hardcore_grazier.item;

import in.dumk.hardcore_grazier.HardcoreGrazier;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ItemSyringe extends Item {
  public ItemSyringe(String name) {
    this.setRegistryName(name);
    this.setUnlocalizedName(HardcoreGrazier.MODID + "." + name);

    this.maxStackSize = 1;

    setCreativeTab(HardcoreGrazier.Tab);
  }

  @SideOnly(Side.CLIENT)
  @Override
  public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
    tooltip.add(I18n.format(this.getUnlocalizedName() + ".tooltip"));
  }
}