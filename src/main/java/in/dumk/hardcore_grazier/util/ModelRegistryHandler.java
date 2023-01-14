package in.dumk.hardcore_grazier.util;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(Side.CLIENT)
public class ModelRegistryHandler {

  @SubscribeEvent
  public static void registerModels(ModelRegistryEvent event) {
    registerModel(HardcoreGrazierItems.SYRINGE);
    registerModel(HardcoreGrazierItems.SYRINGE_BLOOD);
    registerModel(HardcoreGrazierItems.DECODED_DNA);

    registerModel(Item.getItemFromBlock(HardcoreGrazierBlocks.ANALYZER));
    registerModel(Item.getItemFromBlock(HardcoreGrazierBlocks.INCUBATOR));
    registerModel(Item.getItemFromBlock(HardcoreGrazierBlocks.EGG));
  }

  private static void registerModel(Item item) {
    ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
  }
}