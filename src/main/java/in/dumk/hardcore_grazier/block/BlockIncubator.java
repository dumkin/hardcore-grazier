package in.dumk.hardcore_grazier.block;

import in.dumk.hardcore_grazier.HardcoreGrazier;
import in.dumk.hardcore_grazier.gui.GuiHandler;
import in.dumk.hardcore_grazier.tile.TileEntityIncubator;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class BlockIncubator extends Block {
  public BlockIncubator(String name) {
    super(Material.IRON);

    this.setSoundType(SoundType.METAL);
    this.setRegistryName(name);
    this.setUnlocalizedName(HardcoreGrazier.MODID + "." + name);

    setCreativeTab(HardcoreGrazier.Tab);
  }

  @Override
  public boolean hasTileEntity(IBlockState state) {
    return true;
  }

  @Override
  public TileEntity createTileEntity(World world, IBlockState state) {
    return new TileEntityIncubator();
  }

  @Override
  public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
    if (!worldIn.isRemote) {
      if (worldIn.getTileEntity(pos) instanceof TileEntityIncubator) {
        playerIn.openGui(HardcoreGrazier.INSTANCE, GuiHandler.BLOCK_INCUBATOR, worldIn, pos.getX(), pos.getY(), pos.getZ());
      }
    }
    return true;
  }

  @Override
  public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
    TileEntity tileentity = worldIn.getTileEntity(pos);

    if (tileentity instanceof TileEntityIncubator) {
      IItemHandler handler = tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

      for (int slot = 0; slot < handler.getSlots() - 1; slot++) {
        ItemStack stack = handler.getStackInSlot(slot);
        InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), stack);
      }
    }

    super.breakBlock(worldIn, pos, state);
  }
}