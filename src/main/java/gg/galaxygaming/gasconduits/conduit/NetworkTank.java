package gg.galaxygaming.gasconduits.conduit;

import com.enderio.core.common.util.DyeColor;
import crazypants.enderio.base.conduit.ConnectionMode;
import mekanism.api.gas.IGasHandler;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nonnull;

public class NetworkTank {

    final @Nonnull
    EnderGasConduit con;
    final @Nonnull
    EnumFacing conDir;
    final IGasHandler externalTank;
    final @Nonnull
    EnumFacing tankDir;
    final @Nonnull
    BlockPos conduitLoc;
    final boolean acceptsOuput;
    final DyeColor inputColor;
    final DyeColor outputColor;
    final int priority;
    final boolean roundRobin;
    final boolean selfFeed;

    public NetworkTank(@Nonnull EnderGasConduit con, @Nonnull EnumFacing conDir) {
        this.con = con;
        this.conDir = conDir;
        conduitLoc = con.getBundle().getLocation();
        tankDir = conDir.getOpposite();
        externalTank = AbstractGasConduit.getExternalGasHandler(con.getBundle().getBundleworld(), conduitLoc.offset(conDir), tankDir);
        acceptsOuput = con.getConnectionMode(conDir).acceptsOutput();
        inputColor = con.getOutputColor(conDir);
        outputColor = con.getInputColor(conDir);
        priority = con.getOutputPriority(conDir);
        roundRobin = con.isRoundRobinEnabled(conDir);
        selfFeed = con.isSelfFeedEnabled(conDir);
    }

    public boolean isValid() {
        return externalTank != null && con.getConnectionMode(conDir) != ConnectionMode.DISABLED;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + conDir.hashCode();
        result = prime * result + conduitLoc.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        NetworkTank other = (NetworkTank) obj;
        if (conDir != other.conDir) {
            return false;
        }
        if (!conduitLoc.equals(other.conduitLoc)) {
            return false;
        }
        return true;
    }

}
