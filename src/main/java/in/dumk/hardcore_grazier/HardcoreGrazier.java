package in.dumk.hardcore_grazier;

import in.dumk.hardcore_grazier.event.RightClickHorse;
import in.dumk.hardcore_grazier.gui.GuiHandler;
import in.dumk.hardcore_grazier.proxy.IProxy;
import in.dumk.hardcore_grazier.tabs.TabMain;
import in.dumk.hardcore_grazier.tile.TileEntityAnalyzer;
import in.dumk.hardcore_grazier.tile.TileEntityEgg;
import in.dumk.hardcore_grazier.tile.TileEntityIncubator;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.Logger;

@Mod(modid = HardcoreGrazier.MODID, name = HardcoreGrazier.NAME, version = HardcoreGrazier.VERSION, useMetadata = HardcoreGrazier.USEMETADATA)
public class HardcoreGrazier {
  public static final String MODID = "hardcore_grazier";
  public static final String NAME = "Hardcore Grazier";
  public static final String VERSION = "1.0.0";
  public static final boolean USEMETADATA = true;

  public static final String CLIENT = "in.dumk.hardcore_grazier.proxy.ClientProxy";
  public static final String SERVER = "in.dumk.hardcore_grazier.proxy.ServerProxy";

  @Mod.Instance
  public static HardcoreGrazier INSTANCE;

  @SidedProxy(clientSide = CLIENT, serverSide = SERVER)
  public static IProxy proxy;

  public static Logger logger;

  public static final CreativeTabs Tab = new TabMain("main");

  @EventHandler
  public void preInit(FMLPreInitializationEvent event) {
    logger = event.getModLog();

    HardcoreGrazier.logger.info("\u001B[32m" + "[PRE-INITIALIZATION]" + "\u001B[0m");

    proxy.preInit(event);
  }

  @EventHandler
  public void init(FMLInitializationEvent event) {
    HardcoreGrazier.logger.info("\u001B[32m" + "[INITIALIZATION]" + "\u001B[0m");

    NetworkRegistry.INSTANCE.registerGuiHandler(HardcoreGrazier.INSTANCE, new GuiHandler());

    MinecraftForge.EVENT_BUS.register(new RightClickHorse());

    GameRegistry.registerTileEntity(TileEntityAnalyzer.class, new ResourceLocation(HardcoreGrazier.MODID, "analyzer"));
    GameRegistry.registerTileEntity(TileEntityIncubator.class, new ResourceLocation(HardcoreGrazier.MODID, "incubator"));
    GameRegistry.registerTileEntity(TileEntityEgg.class, new ResourceLocation(HardcoreGrazier.MODID, "egg"));

    proxy.init(event);
  }

  @EventHandler
  public void postInit(FMLPostInitializationEvent event) {
    HardcoreGrazier.logger.info("\u001B[32m" + "[POST-INITIALIZATION]" + "\u001B[0m");

    proxy.postInit(event);
  }
}