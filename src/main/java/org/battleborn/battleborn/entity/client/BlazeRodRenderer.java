package org.battleborn.battleborn.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.battleborn.battleborn.entity.client.models.lightning_rod;
import org.battleborn.battleborn.entity.rods.blazeRodEntity;
import org.battleborn.battleborn.entity.rods.lightningRodEntity;

import static org.battleborn.battleborn.Battleborn.MODID;

public class BlazeRodRenderer extends EntityRenderer<blazeRodEntity> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(MODID,"textures/entity/lightning_rod.png");
    private final lightning_rod model;

    public BlazeRodRenderer(EntityRendererProvider.Context p_174420_) {
        super(p_174420_);
        this.model = new lightning_rod<blazeRodEntity>(p_174420_.bakeLayer(modelLayerReg.BLAZE_ROD_LAYER));
    }
    @Override
  public void render(blazeRodEntity p_114656_, float p_114657_, float p_114658_, PoseStack p_114659_, MultiBufferSource p_114660_, int p_114661_) {
        p_114659_.pushPose();
        p_114659_.mulPose(Axis.YP.rotationDegrees(Mth.lerp(p_114658_, p_114656_.yRotO, p_114656_.getYRot()) - 90.0F));
        p_114659_.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(p_114658_, p_114656_.xRotO, p_114656_.getXRot()) + 90.0F));
        VertexConsumer vertexconsumer = ItemRenderer.getFoilBufferDirect(p_114660_, this.model.renderType(this.getTextureLocation( p_114656_)), false, false);
        this.model.renderToBuffer(p_114659_, vertexconsumer, p_114661_, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        p_114659_.popPose();
      super.render(p_114656_, p_114657_, p_114658_, p_114659_, p_114660_, p_114661_);
  }

    @Override
    public ResourceLocation getTextureLocation(blazeRodEntity p_114482_) {
        return TEXTURE;
    }





}
