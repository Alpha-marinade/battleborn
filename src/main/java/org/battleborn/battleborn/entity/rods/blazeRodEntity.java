package org.battleborn.battleborn.entity.rods;

import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import org.battleborn.battleborn.common.EntityReg;

import javax.annotation.Nullable;

public class blazeRodEntity  extends AbstractArrow {
    public blazeRodEntity(EntityType<? extends AbstractArrow> pEntityType, Level level) {
        super(pEntityType, level);
    }
    public blazeRodEntity(Level level,double x, double y, double z) {
        super(EntityReg.BLAZE_ROD.get(),level);
        this.setPos(x,y,z);
        this.setDeltaMovement(this.random.triangle(0.0D, 0.002297D), 0.05D, this.random.triangle(0.0D, 0.002297D));
    }
    public blazeRodEntity (Level level, @Nullable Entity owner, double x, double y, double z) {
        this(level,x, y, z);
        this.setOwner(owner);

    }
    @Override
    protected SoundEvent getDefaultHitGroundSoundEvent() {
        return SoundEvents.TRIDENT_HIT_GROUND;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(Items.BLAZE_POWDER);
    }

    @Override
    protected void onHitBlock(BlockHitResult hitResult) {
        super.onHitBlock(hitResult);
        Direction blockFace= hitResult.getDirection();


    }

    @Override
    public void tick() {
        super.tick();

    }
}
