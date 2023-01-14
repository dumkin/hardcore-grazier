package in.dumk.hardcore_grazier.tile;

import in.dumk.hardcore_grazier.HardcoreGrazier;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileEntityEgg extends TileEntity implements ITickable {
  private int progress;

  public static final int duration = 20 * 5;

  public TileEntityEgg() {
    this.progress = 0;
  }

  public int getProgress() {
    return this.progress;
  }

  @Override
  public void update() {
    this.progress++;
    this.markDirty();

//    HardcoreGrazier.logger.info(this.getTileData().getDouble("movement_speed"));

    if (!this.world.isRemote && this.progress >= this.duration) {
      this.progress = 0;

      EntityHorse horse = new EntityHorse(this.world);
      horse.setPosition(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ());
      IAttribute JUMP_STRENGTH = (new RangedAttribute((IAttribute) null, "horse.jumpStrength", 0.7D, 0.0D, 2.0D)).setDescription("Jump Strength").setShouldWatch(true);

      horse.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100);
      horse.getEntityAttribute(JUMP_STRENGTH).setBaseValue(100);
      horse.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(1);

      this.world.destroyBlock(pos, false);
      this.world.spawnEntity(horse);
    }
  }

  @Override
  public void readFromNBT(NBTTagCompound compound) {
    progress = compound.getInteger("progress");

    super.readFromNBT(compound);
  }

  @Override
  public NBTTagCompound writeToNBT(NBTTagCompound compound) {
    compound.setInteger("progress", progress);

    return super.writeToNBT(compound);
  }
}