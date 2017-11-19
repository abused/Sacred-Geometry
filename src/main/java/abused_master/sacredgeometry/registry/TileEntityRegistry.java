package abused_master.sacredgeometry.registry;

import abused_master.sacredgeometry.client.render.RenderAcidVatt;
import abused_master.sacredgeometry.tileentity.TileEntityVatt;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityRegistry {

    public static void regTE() {
        GameRegistry.registerTileEntity(TileEntityVatt.class, "tile_vatt");
    }

    public static void regTESR() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityVatt.class, new RenderAcidVatt());
    }
}
