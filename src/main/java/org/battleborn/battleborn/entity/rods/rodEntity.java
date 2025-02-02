package org.battleborn.battleborn.entity.rods;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;

public class rodEntity extends AbstractArrow {
    private int hit;
    protected rodEntity(EntityType<? extends AbstractArrow> p_36721_, Level p_36722_) {
        super(p_36721_, p_36722_);
        this.hit=0;
    }
    @Override
    protected ItemStack getPickupItem() {
        return null;
    }
    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
    }
    @Override
    protected SoundEvent getDefaultHitGroundSoundEvent() {
        return SoundEvents.TRIDENT_HIT_GROUND;
    }

    @Override
    protected void onHitBlock(BlockHitResult hitResult) {
        super.onHitBlock(hitResult);
        if(this.hit==0){
            onFirstBlockHit(hitResult);
            this.hit++;
        }
    }

    public void onFirstBlockHit(BlockHitResult hitResult){
    }

    @Override
    protected boolean canHitEntity(Entity p_36743_) {
        return false;
    }
    @Override
    public void tick() {
        super.tick();
        if(this.hit==0){
            BeforeFirstHit();
        }

    }
    public void BeforeFirstHit(){

    }
}
