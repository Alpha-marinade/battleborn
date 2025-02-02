package org.battleborn.battleborn.entity.rods;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.common.Tags;
import org.battleborn.battleborn.common.EntityReg;

import javax.annotation.Nullable;
import java.util.OptionalInt;

public class lightningRodEntity extends rodEntity {
    public lightningRodEntity(EntityType<? extends AbstractArrow> pEntityType, Level level) {
        super(pEntityType, level);
    }
    public lightningRodEntity(Level level,double x, double y, double z) {
        super(EntityReg.LIGHTNING_ROD.get(),level);
        this.setPos(x,y,z);

        this.setDeltaMovement(this.random.triangle(0.0D, 0.002297D), 0.05D, this.random.triangle(0.0D, 0.002297D));
    }

    public lightningRodEntity(Level level, @Nullable Entity owner, double x, double y, double z) {
        this(level,x, y, z);
        this.setOwner(owner);

    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(Items.LIGHTNING_ROD);
    }
    @Override
    public void onFirstBlockHit(BlockHitResult hitResult) {
        super.onFirstBlockHit(hitResult);
        if(!this.level().isClientSide){
            if(this.level().isRaining()){
                Entity lightning = new LightningBolt(EntityType.LIGHTNING_BOLT, this.level());
                if(!this.level().getBlockState(new BlockPos(blockPosition().getX(),blockPosition().getY()-1,blockPosition().getZ())).is(Tags.Blocks.GLASS)){
                    lightning.setPos(blockPosition().getX(),blockPosition().getY(),blockPosition().getZ());
                }
                else {
                    lightning.setPos(blockPosition().getX(),blockPosition().getY()-1,blockPosition().getZ());
                }
                this.level().addFreshEntity(lightning);
            }
        }
    }
    @Override
    public void BeforeFirstHit() {
        super.BeforeFirstHit();
        if(this.level().isRaining()) {
            this.level().addParticle(ParticleTypes.ELECTRIC_SPARK,
                    this.level().getRandom().nextFloat() * (0.3f - (-0.3f)) + blockPosition().getX(),
                    this.level().getRandom().nextFloat() * (0.3f - (-0.3f)) + blockPosition().getY(),
                    this.level().getRandom().nextFloat() * (0.3f - (-0.3f)) + blockPosition().getZ(),
                    0.2f, 0.2f, 0.2f);
        }
    }
    @Override
    protected boolean canHitEntity(Entity entity) {
        return !(entity instanceof lightningRodEntity);
    }

    @Override
    protected void onHitEntity(EntityHitResult p_36757_) {
        this.setBaseDamage(10d);
        super.onHitEntity(p_36757_);
    }
}
