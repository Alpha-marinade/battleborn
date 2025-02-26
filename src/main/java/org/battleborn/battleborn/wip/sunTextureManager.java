package org.battleborn.battleborn.wip;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.Resource;

import static org.battleborn.battleborn.Battleborn.MODID;

public class sunTextureManager {
    private static ResourceLocation[] sunTextures;
    private static int frame= 0;
    private static int t=0;

    public static void init() {
        sunTextures = new ResourceLocation[] {
                new ResourceLocation(MODID, "textures/environment/sun1.png"),
                new ResourceLocation(MODID, "textures/environment/sun2.png"),
                new ResourceLocation(MODID, "textures/environment/sun3.png"),
                new ResourceLocation(MODID, "textures/environment/sun4.png"),
                new ResourceLocation(MODID, "textures/environment/sun5.png"),
                new ResourceLocation(MODID, "textures/environment/sun6.png"),
                new ResourceLocation(MODID, "textures/environment/sun7.png"),
                new ResourceLocation(MODID, "textures/environment/sun8.png")
        };

    }
    public static ResourceLocation getSunTexture() {
        return sunTextures[frame];
    }

    public static void sunAnimate(){
        RenderSystem.setShaderTexture(0,getSunTexture());
        if(t<Minecraft.getInstance().getFps()/2){
            t++;
        }
        else {
            if(frame<7){
                frame++;
            }
            else{
                frame=0;
            }
            t=0;
        }
    }
}




