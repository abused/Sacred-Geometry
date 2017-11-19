package abused_master.sacredgeometry.registry;

import abused_master.abusedlib.blocks.fluid.FluidBlock;
import abused_master.sacredgeometry.blocks.BlockOrb;
import abused_master.sacredgeometry.blocks.BlockVatt;
import abused_master.sacredgeometry.blocks.fluids.FluidAcid;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ModResources {

    public static Block blockOrb = new BlockOrb();
    public static Block blockVatt = new BlockVatt();

    public static Fluid acid = new FluidAcid();
    public static FluidBlock acid_block;

    public static void regFluids() {
        FluidRegistry.registerFluid(acid);
        FluidRegistry.addBucketForFluid(acid);
    }

    public static void regResources() {
        register(blockOrb);
        register(blockVatt);

        acid_block = new FluidBlock(acid, Material.WATER);
        register(acid_block);
    }

    public static void regModels() {
        regBlock(blockOrb);
        regBlock(blockVatt);

        acid_block.regFluid();
    }

    public static void regBlock(Block block) {
        ModelResourceLocation res = new
                ModelResourceLocation(block.getRegistryName().toString(), "inventory");
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, res);
    }

    public static void regItem(Item item) {
        ModelResourceLocation res = new ModelResourceLocation(item.getRegistryName().toString(), "inventory");
        ModelLoader.setCustomModelResourceLocation(item, 0, res);
    }

    public static void register(Object object) {
        if(object instanceof Block) {
            ForgeRegistries.BLOCKS.register((Block) object);
            ForgeRegistries.ITEMS.register(new ItemBlock((Block) object).setRegistryName(((Block) object).getRegistryName()));
        }else if(object instanceof Item) {
            ForgeRegistries.ITEMS.register((Item) object);
        }
    }
}
