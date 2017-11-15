package abused_master.sacredgeometry;

import abused_master.sacredgeometry.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Info.MODID, name = Info.NAME, version = Info.Version, acceptedMinecraftVersions = Info.ACCEPTED_MINECRAFT)
public class SacredGeometry {

    @Mod.Instance
    public static SacredGeometry instance;

    @SidedProxy(clientSide = "abused_master.sacredgeometry.proxy.ClientProxy", serverSide = "abused_master.sacredgeometry.proxy.CommonProxy")
    static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        proxy.preInit(e);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }

    public static CreativeTabs SacredTab = new CreativeTabs("sacredtab")
    {
        @Override
        public ItemStack getTabIconItem()
        {
            return new ItemStack(Items.DIAMOND);
        }

    };
}
