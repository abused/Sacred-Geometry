package abused_master.sacredgeometry.client.render;

import abused_master.abusedlib.render.hud.HudRender;
import abused_master.abusedlib.utils.FluidUtils;
import abused_master.sacredgeometry.registry.ModResources;
import abused_master.sacredgeometry.tileentity.TileEntityVatt;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fluids.FluidTank;

public class RenderAcidVatt extends TileEntitySpecialRenderer<TileEntityVatt> {

    @Override
    public void render(TileEntityVatt te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        BlockPos pos = new BlockPos(x, y, z);

        GlStateManager.pushMatrix();
        {
            if (!te.inventory.isEmpty()) {
                if(te.inventory.getItem() == Item.getItemFromBlock(ModResources.blockOrb)) {
                    GlStateManager.translate(x + 0.5, y + 0.8, z + 0.5);
                    GlStateManager.scale(0.55, 0.55, 0.55);
                }else {
                    GlStateManager.translate(x + 0.5, y + 0.60, z + 0.5);
                }
                long angle = (System.currentTimeMillis() / 10) % 360;
                GlStateManager.rotate(angle, 0, 1, 0);
                RenderHelper.disableStandardItemLighting();
                GlStateManager.alphaFunc(516, 0.003921569F);
                Minecraft.getMinecraft().getRenderItem().renderItem(te.inventory, ItemCameraTransforms.TransformType.GROUND);
            }
        }
        GlStateManager.popMatrix();

        final FluidTank fluid = te.tank;

        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        if(fluid != null && fluid.getFluid() != null) {
            if (te.tank.getFluidAmount() > 0) {
                FluidUtils.translateAgainstPlayer(te.getPos(), false);
                FluidUtils.renderFluid(te.tank.getFluid(), te.getPos(), 0.06d, 0.5d, 0.06d, 0.01d, 0.0d, 0.01d, 0.87d, (double) fluid.getFluidAmount() / (double) fluid.getCapacity() * 0.55d, 0.87d);
            }
        }
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();

        HudRender.renderHud(te, x, y, z);

    }
}
