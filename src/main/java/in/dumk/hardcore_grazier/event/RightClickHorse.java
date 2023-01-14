package in.dumk.hardcore_grazier.event;

import in.dumk.hardcore_grazier.item.ItemSyringe;
import in.dumk.hardcore_grazier.util.HardcoreGrazierItems;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RightClickHorse {
  @SubscribeEvent
  public void onEntityRightClicked(PlayerInteractEvent.EntityInteract event) {
    if (event.getWorld().isRemote) {
      return;
    }

    ItemStack item = event.getEntityPlayer().inventory.getCurrentItem();

    if (item.getItem() instanceof ItemSyringe == false) {
      return;
    }
    if (event.getTarget() instanceof EntityHorse == false) {
      return;
    }

    EntityHorse horse = (EntityHorse) event.getTarget();

    horse.attackEntityFrom(new DamageSource("Syringe"), 2f);

    item.shrink(1);
    ItemStack dna = new ItemStack(HardcoreGrazierItems.SYRINGE_BLOOD, 1);

    NBTTagCompound nbt;
    if (dna.hasTagCompound()) {
      nbt = dna.getTagCompound();
    } else {
      nbt = new NBTTagCompound();
    }

    double movementSpeed = horse.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue();
    double jumpStrength = horse.getHorseJumpStrength();
    float maxHealth = horse.getMaxHealth();

    nbt.setDouble("movement_speed", movementSpeed);
    nbt.setDouble("jump_strength", jumpStrength);
    nbt.setFloat("max_health", maxHealth);

    double yVelocity = jumpStrength;
    double jumpHeight = 0;
    while (yVelocity > 0) {
      jumpHeight += yVelocity;
      yVelocity -= 0.08;
      yVelocity *= 0.98;
    }

    nbt.setString("movement_speed_f", String.format("%.2f", movementSpeed * 43));
    nbt.setString("jump_strength_f", String.format("%.2f", jumpHeight));
    nbt.setString("max_health_f", String.format("%.1f", maxHealth / 2));

    dna.setTagCompound(nbt);

    event.getEntityPlayer().inventory.addItemStackToInventory(dna);
  }
}