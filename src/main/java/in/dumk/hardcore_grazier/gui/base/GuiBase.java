package in.dumk.hardcore_grazier.gui.base;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class GuiBase extends GuiContainer {
  public GuiBase(Container inventorySlotsIn) {
    super(inventorySlotsIn);
  }

  public int getCenterX() {
    return this.guiLeft + this.xSize / 2;
  }

  public int getCenterY() {
    return this.guiTop + this.ySize / 2;
  }

  public int getScaled(int from, int to, int max, int min) {
    return (from * max) / to + min;
  }

  public int getScaled(int from, int to, int max) {
    return this.getScaled(from, to, max, 0);
  }

  public int textWidth(String text) {
    return this.mc.fontRenderer.getStringWidth(text);
  }
}