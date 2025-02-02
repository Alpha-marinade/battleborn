package org.battleborn.battleborn.entity.rods;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import org.battleborn.battleborn.common.EntityReg;
import org.battleborn.battleborn.entity.client.ModEventBusClientEvents;
import javax.annotation.Nullable;

public class blazeRodEntity  extends rodEntity {

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
    protected ItemStack getPickupItem() {
        return new ItemStack(Items.BLAZE_POWDER);
    }

    @Override
    public void onFirstBlockHit(BlockHitResult hitResult){
        FireWork(hitResult.getBlockPos().above());
    }

    public boolean setFire(BlockPos blockpos){
        if (!this.level().getBlockState(blockpos.below()).isAir()){
            if (this.level().getBlockState(blockpos).isAir()){
                this.level().setBlockAndUpdate(blockpos,Blocks.FIRE.defaultBlockState());
            }
            return true;
        }
        return false;
    }

    public void FireWork(BlockPos center){
        BlockPos angle1=center.west(2).north(2);
        BlockPos angle2=center.east(2).south(2);
        for (int i=angle1.getX(); i<=angle2.getX()-1;i++){
            for(int k=angle1.getZ(); k<=angle2.getZ()-1;k++){
                BlockPos pos;
                boolean fire=false;
                int y=center.getY()+1;
                while (!fire){
                    pos = new BlockPos(i,y,k);
                    fire= setFire(pos);
                    y--;
                }
            }
        }

    }
}
