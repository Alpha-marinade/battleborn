package org.battleborn.battleborn.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.resources.ResourceLocation;
import org.battleborn.battleborn.wip.sunTextureManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LevelRenderer.class)
public class LevelRendererMixin {
    @Redirect(method = "renderSky", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;setShaderTexture(ILnet/minecraft/resources/ResourceLocation;)V", ordinal = 0))
    private void celesteConfig$scaleSun(int p_157457_, ResourceLocation p_157458_) {
        RenderSystem.setShaderTexture(p_157457_,sunTextureManager.getSunTexture());


    }
}