package abused_master.sacredgeometry.blocks;

import abused_master.abusedlib.blocks.BlockBase;
import abused_master.sacredgeometry.SacredGeometry;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;

public class BlockOrb extends BlockBase {

    public BlockOrb() {
        super(Material.ROCK, "block_orb", SacredGeometry.SacredTab);
        this.setHardness(1.6f);
        this.setHarvestLevel("pickaxe", 2);
        this.setLightOpacity(100);
        this.setLightLevel(0.5f);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isTranslucent(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }
}
