package in.dumk.hardcore_grazier.gui;

import in.dumk.hardcore_grazier.container.ContainerBlockAnalyzer;
import in.dumk.hardcore_grazier.container.ContainerBlockIncubator;
import in.dumk.hardcore_grazier.tile.TileEntityAnalyzer;
import in.dumk.hardcore_grazier.tile.TileEntityIncubator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

public class GuiHandler implements IGuiHandler {

  public static final int BLOCK_ANALYZER = 0;
  public static final int BLOCK_INCUBATOR = 1;

  @Nullable
  @Override
  public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
    switch (ID) {
      case BLOCK_ANALYZER:
        return new ContainerBlockAnalyzer(player.inventory, (TileEntityAnalyzer) world.getTileEntity(new BlockPos(x, y, z)));
      case BLOCK_INCUBATOR:
        return new ContainerBlockIncubator(player.inventory, (TileEntityIncubator) world.getTileEntity(new BlockPos(x, y, z)));
      default:
        return null;
    }
  }

  @Nullable
  @Override
  public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
    switch (ID) {
      case BLOCK_ANALYZER:
        return new GuiBlockAnalyzer(player.inventory, (TileEntityAnalyzer) world.getTileEntity(new BlockPos(x, y, z)));
      case BLOCK_INCUBATOR:
        return new GuiBlockIncubator(player.inventory, (TileEntityIncubator) world.getTileEntity(new BlockPos(x, y, z)));
      default:
        return null;
    }
  }
}