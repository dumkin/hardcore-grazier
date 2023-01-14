package in.dumk.hardcore_grazier.gui;

import in.dumk.hardcore_grazier.HardcoreGrazier;
import in.dumk.hardcore_grazier.container.ContainerBlockIncubator;
import in.dumk.hardcore_grazier.gui.base.GuiBase;
import in.dumk.hardcore_grazier.tile.TileEntityIncubator;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBlockIncubator extends GuiBase {
  private TileEntityIncubator tileEntity;
  private IInventory playerInv;
  private ResourceLocation res;

  public GuiBlockIncubator(IInventory playerInv, TileEntityIncubator tileEntity) {
    super(new ContainerBlockIncubator(playerInv, tileEntity));

    this.xSize = 176;
    this.ySize = 166;

    this.playerInv = playerInv;
    this.tileEntity = tileEntity;

    this.res = new ResourceLocation(HardcoreGrazier.MODID, "textures/gui/container/incubator.png");
  }

  @Override
  protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
    GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);

    this.mc.getTextureManager().bindTexture(res);

    // Container
    this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

    // Arrow
    int scaled = this.getScaled(tileEntity.getProgress(), TileEntityIncubator.duration, 24) + 1;
    this.drawTexturedModalRect(this.guiLeft + 101, this.guiTop + 36, 176, 0, scaled, 16);

    // Analyzer
    String s = I18n.format("container." + HardcoreGrazier.MODID + ".incubator");
    this.mc.fontRenderer.drawString(s, this.getCenterX() - this.textWidth(s) / 2, this.guiTop + 6, 0x404040);

    // Inventory
    this.mc.fontRenderer.drawString(this.playerInv.getDisplayName().getFormattedText(), this.guiLeft + 8, this.guiTop + 72, 0x404040);
  }
}