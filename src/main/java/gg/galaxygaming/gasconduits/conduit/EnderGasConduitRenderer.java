package gg.galaxygaming.gasconduits.conduit;

import crazypants.enderio.base.conduit.IClientConduit;
import crazypants.enderio.base.conduit.IConduit;
import crazypants.enderio.base.conduit.IConduitBundle;
import crazypants.enderio.base.conduit.IConduitTexture;
import crazypants.enderio.base.conduit.geom.CollidableComponent;
import crazypants.enderio.conduits.render.ConduitInOutRenderer;
import crazypants.enderio.conduits.render.DefaultConduitRenderer;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;

import javax.annotation.Nonnull;
import java.util.List;

public class EnderGasConduitRenderer extends DefaultConduitRenderer {

    @Override
    public boolean isRendererForConduit(@Nonnull IConduit conduit) {
        if (conduit instanceof EnderGasConduit) {
            return true;
        }
        return false;
    }

    @Override
    public @Nonnull
    BlockRenderLayer getCoreLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    protected void addConduitQuads(@Nonnull IConduitBundle bundle, @Nonnull IClientConduit conduit, @Nonnull IConduitTexture tex,
                                   @Nonnull CollidableComponent component, float selfIllum, BlockRenderLayer layer, @Nonnull List<BakedQuad> quads) {
        super.addConduitQuads(bundle, conduit, tex, component, selfIllum, layer, quads);

        EnderGasConduit pc = (EnderGasConduit) conduit;
        EnumFacing dir = component.getDirection();
        ConduitInOutRenderer.renderIO(bundle, conduit, component, layer, quads, pc.getInputColor(dir), pc.getOutputColor(dir));
    }

}
