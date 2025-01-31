package org.battleborn.battleborn.common;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.battleborn.battleborn.entity.rods.lightningRodEntity;

import static org.battleborn.battleborn.Battleborn.MODID;

public class EntityReg {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES,MODID);

    public static final RegistryObject<EntityType<lightningRodEntity>> LIGHTNING_ROD =
            ENTITY_TYPES.register("lightning_rod", () -> EntityType.Builder.<lightningRodEntity>of(lightningRodEntity::new, MobCategory.MISC)
                    .sized(1f, 0.5f).build("lightning_rod"));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
