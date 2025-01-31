package org.battleborn.battleborn.entity.rods;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class blazeRodEntity  extends AbstractArrow {
    protected blazeRodEntity(EntityType<? extends AbstractArrow> p_36721_, Level p_36722_) {
        super(p_36721_, p_36722_);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(Items.LIGHTNING_ROD);
    }
}
