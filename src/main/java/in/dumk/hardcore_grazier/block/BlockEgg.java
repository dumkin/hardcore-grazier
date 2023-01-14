package in.dumk.hardcore_grazier.block;

import in.dumk.hardcore_grazier.HardcoreGrazier;
import in.dumk.hardcore_grazier.tile.TileEntityEgg;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.Random;

public class BlockEgg extends Block {
  public BlockEgg(String name) {
    super(Material.CAKE);

    this.setSoundType(SoundType.CLOTH);
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
    return new TileEntityEgg();
  }

  @Override
  public int quantityDropped(Random random) {
    return 0;
  }

  @Override
  public boolean isOpaqueCube(IBlockState state) {
    return false;
  }
}