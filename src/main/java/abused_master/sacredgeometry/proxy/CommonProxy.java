package abused_master.sacredgeometry.proxy;

import abused_master.sacredgeometry.registry.ModResources;
import abused_master.sacredgeometry.registry.TileEntityRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod.EventBusSubscriber
public class CommonProxy {

    public void preInit(FMLPreInitializationEvent e) {
        ModResources.regFluids();
        ModResources.regResources();
    }

    public void init(FMLInitializationEvent e) {
        TileEntityRegistry.regTE();
    }

    public void postInit(FMLPostInitializationEvent e) {
    }
}
