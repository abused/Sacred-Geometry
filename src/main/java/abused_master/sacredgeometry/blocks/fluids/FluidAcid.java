package abused_master.sacredgeometry.blocks.fluids;

import abused_master.abusedlib.blocks.fluid.FluidBase;
import abused_master.sacredgeometry.Info;

public class FluidAcid extends FluidBase {

    public FluidAcid() {
        super("fluid_acid", Info.MODID);
        this.setGaseous(false);
        this.setLuminosity(1);
        this.setViscosity(1200);
        this.setTemperature(600);
    }
}
