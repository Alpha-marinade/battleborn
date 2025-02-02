package org.battleborn.battleborn.entity.rods;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
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
import net.minecraft.world.phys.EntityHitResult;
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
        FireWork(hitResult.getBlockPos().above(),2);
    }

    @Override
    public void BeforeFirstHit() {
        super.BeforeFirstHit();
            this.level().addParticle(ParticleTypes.FLAME,
                    this.level().getRandom().nextFloat() * (0.3f - (-0.3f)) + blockPosition().getX(),
                    this.level().getRandom().nextFloat() * (0.3f - (-0.3f)) + blockPosition().getY(),
                    this.level().getRandom().nextFloat() * (0.3f - (-0.3f)) + blockPosition().getZ(),
                    0.2f, 0.2f, 0.2f);
            boolean fire=false;
            int y=0;
            while(!fire){
                int d = random.nextInt(3) - 1;
                BlockPos pos=new BlockPos(this.blockPosition().getX()+d,this.blockPosition().getY()-y,this.blockPosition().getZ()+d);
                fire=setFire(pos);
                y++;
                if(y-8==0){fire=true;}

            }
        }

    @Override
    protected boolean canHitEntity(Entity entity) {
        return !(entity instanceof blazeRodEntity);
    }

    @Override
    protected void onHitEntity(EntityHitResult hitResult) {
        super.onHitEntity(hitResult);
        hitResult.getEntity().setSecondsOnFire(8);
        FireWork(hitResult.getEntity().blockPosition().above(),1);

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

    public void FireWork(BlockPos center,int r){
        BlockPos angle1=center.west(r).north(r);
        BlockPos angle2=center.east(r).south(r);
        for (int i=angle1.getX(); i<=angle2.getX()-1;i++){
            for(int k=angle1.getZ(); k<=angle2.getZ()-1;k++){
                BlockPos pos;
                boolean fire=false;
                int y=center.getY();
                while (!fire){
                    pos = new BlockPos(i,y,k);
                    fire= setFire(pos);
                    y--;
                }
            }
        }

    }

}
