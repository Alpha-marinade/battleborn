package org.battleborn.battleborn.common;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.battleborn.battleborn.entity.rods.blazeRodEntity;
import org.battleborn.battleborn.entity.rods.lightningRodEntity;

import static org.battleborn.battleborn.Battleborn.MODID;

public class EntityReg {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES,MODID);

    public static final RegistryObject<EntityType<lightningRodEntity>> LIGHTNING_ROD =
            ENTITY_TYPES.register("lightning_rod", () -> EntityType.Builder.<lightningRodEntity>of(lightningRodEntity::new, MobCategory.MISC).fireImmune()
                    .sized(0.5f, 0.5f).build("lightning_rod"));

    public static final RegistryObject<EntityType<blazeRodEntity>> BLAZE_ROD =
            ENTITY_TYPES.register("blaze_rod", () -> EntityType.Builder.<blazeRodEntity>of(blazeRodEntity::new, MobCategory.MISC).fireImmune()
                    .sized(0.1f, 0.1f).build("blaze_rod"));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
