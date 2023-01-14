package in.dumk.hardcore_grazier.item;

import in.dumk.hardcore_grazier.HardcoreGrazier;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ItemDecodedDNA extends Item {
  public ItemDecodedDNA(String name) {
    this.setRegistryName(name);
    this.setUnlocalizedName(HardcoreGrazier.MODID + "." + name);

    this.maxStackSize = 1;

    setCreativeTab(HardcoreGrazier.Tab);
  }

  @SideOnly(Side.CLIENT)
  @Override
  public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
//    if (GuiScreen.isShiftKeyDown()) {
//    }

    if (stack.hasTagCompound() && stack.getTagCompound().hasKey("jump_strength_f")) {
      tooltip.add("Movement Speed: " + stack.getTagCompound().getString("movement_speed_f"));
      tooltip.add("Jump Strength: " + stack.getTagCompound().getString("jump_strength_f"));
      tooltip.add("Max Health: " + stack.getTagCompound().getString("max_health_f"));
    } else {
      tooltip.add("Error in analyze DNA");
    }
  }
}