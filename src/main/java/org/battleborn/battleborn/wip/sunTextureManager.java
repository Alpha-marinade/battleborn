package org.battleborn.battleborn.wip;

import net.minecraft.resources.ResourceLocation;

import static org.battleborn.battleborn.Battleborn.MODID;

public class sunTextureManager {
    public static ResourceLocation sunTexture;
    private static int currentTextureIndex = 0;

    public static void init() {
        sunTexture = new ResourceLocation(MODID, "textures/environment/img.png");

    }

    public static ResourceLocation getSunTexture() {
        return sunTexture;
    }
}




