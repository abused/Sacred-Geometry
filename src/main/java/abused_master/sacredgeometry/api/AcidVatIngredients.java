package abused_master.sacredgeometry.api;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

import java.util.ArrayList;
import java.util.List;

public class AcidVatIngredients {

    private List<Block> input = new ArrayList<>();

    public AcidVatIngredients() {
        this.input.add(Blocks.STONE);
        this.input.add(Blocks.STONEBRICK);
        this.input.add(Blocks.END_STONE);
        this.input.add(Blocks.COBBLESTONE);
        this.input.add(Blocks.MOSSY_COBBLESTONE);
        this.input.add(Blocks.NETHERRACK);
        this.input.add(Blocks.NETHER_BRICK);
        this.input.add(Blocks.BRICK_BLOCK);
        this.input.add(Blocks.HARDENED_CLAY);
        this.input.add(Blocks.STAINED_HARDENED_CLAY);
    }

    public void addIngredient(Block block) {
        this.input.add(block);
    }

    public void removeIngredient(Block block) {
        this.input.remove(block);
    }

    public boolean containsIngredient(Block block) {
        return this.input.contains(block);
    }

    public List getInputList() {
        return this.input;
    }
}
