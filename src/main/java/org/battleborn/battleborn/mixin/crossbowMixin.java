package org.battleborn.battleborn.mixin;

import com.google.common.collect.Lists;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.CrossbowAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.battleborn.battleborn.entity.rods.lightningRodEntity;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.function.Predicate;

import static net.minecraft.world.item.ProjectileWeaponItem.ARROW_OR_FIREWORK;

@Mixin(CrossbowItem.class)
public class crossbowMixin extends ProjectileWeaponItem implements Vanishable {

    public crossbowMixin(Properties properties) {
        super(properties);
    }


    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return ARROW_OR_FIREWORK.or(itemStack -> {
            return itemStack.is(Items.LIGHTNING_ROD);
        });
    }
    @Inject(method = "getShootingPower",at=@At("HEAD"), cancellable = true)
    private static void power(ItemStack stack, CallbackInfoReturnable<Float> cir){
       List<ItemStack> list = getChargedProjectiles(stack);
        ItemStack itemstack = list.get(0);
        if (itemstack.is(Items.LIGHTNING_ROD)){
            cir.setReturnValue(1F);
        }






    }

    @Override
    public Predicate<ItemStack> getSupportedHeldProjectiles() {
        return ARROW_OR_FIREWORK.or(itemStack -> {
            return itemStack.is(Items.LIGHTNING_ROD);
        });
    }


    @Override
    public int getDefaultProjectileRange() {
        return 8;
    }

@Inject(method = "shootProjectile",at=@At("HEAD") , cancellable = true)
    private static void shoot(Level p_40895_, LivingEntity p_40896_, InteractionHand p_40897_, ItemStack p_40898_, ItemStack p_40899_, float p_40900_, boolean p_40901_, float p_40902_, float p_40903_, float p_40904_, CallbackInfo ci) {
        ci.cancel();
        if (!p_40895_.isClientSide) {
            boolean flag = p_40899_.is(Items.FIREWORK_ROCKET);
            boolean flag2 = p_40899_.is(Items.LIGHTNING_ROD);
            Projectile projectile;
            if (flag) {
                projectile = new FireworkRocketEntity(p_40895_, p_40899_, p_40896_, p_40896_.getX(), p_40896_.getEyeY() - (double) 0.15F, p_40896_.getZ(), true);
            } else {
                if(flag2){
                    projectile = new lightningRodEntity(p_40895_, p_40896_, p_40896_.getX(), p_40896_.getEyeY() - (double) 0.15F, p_40896_.getZ());

                }
                else {
                    projectile = getArrow(p_40895_, p_40896_, p_40898_, p_40899_);
                    if (p_40901_ || p_40904_ != 0.0F) {
                        ((AbstractArrow) projectile).pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                    }
                }
            }

            if (p_40896_ instanceof CrossbowAttackMob) {
                CrossbowAttackMob crossbowattackmob = (CrossbowAttackMob) p_40896_;
                crossbowattackmob.shootCrossbowProjectile(crossbowattackmob.getTarget(), p_40898_, projectile, p_40904_);
            } else {
                Vec3 vec31 = p_40896_.getUpVector(1.0F);
                Quaternionf quaternionf = (new Quaternionf()).setAngleAxis((double) (p_40904_ * ((float) Math.PI / 180F)), vec31.x, vec31.y, vec31.z);
                Vec3 vec3 = p_40896_.getViewVector(1.0F);
                Vector3f vector3f = vec3.toVector3f().rotate(quaternionf);
                projectile.shoot((double) vector3f.x(), (double) vector3f.y(), (double) vector3f.z(), p_40902_, p_40903_);
            }

            p_40898_.hurtAndBreak(flag ? 3 : 1, p_40896_, (p_40858_) -> {
                p_40858_.broadcastBreakEvent(p_40897_);
            });
            p_40895_.addFreshEntity(projectile);
            p_40895_.playSound((Player) null, p_40896_.getX(), p_40896_.getY(), p_40896_.getZ(), SoundEvents.CROSSBOW_SHOOT, SoundSource.PLAYERS, 1.0F, p_40900_);
        }
    }

    private static AbstractArrow getArrow(Level p_40915_, LivingEntity p_40916_, ItemStack p_40917_, ItemStack p_40918_) {
        ArrowItem arrowitem = (ArrowItem) (p_40918_.getItem() instanceof ArrowItem ? p_40918_.getItem() : Items.ARROW);
        AbstractArrow abstractarrow = arrowitem.createArrow(p_40915_, p_40918_, p_40916_);
        if (p_40916_ instanceof Player) {
            abstractarrow.setCritArrow(true);
        }

        abstractarrow.setSoundEvent(SoundEvents.CROSSBOW_HIT);
        abstractarrow.setShotFromCrossbow(true);
        int i = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PIERCING, p_40917_);
        if (i > 0) {
            abstractarrow.setPierceLevel((byte) i);
        }
        return abstractarrow;
    }
    private static List<ItemStack> getChargedProjectiles(ItemStack p_40942_) {
        List<ItemStack> list = Lists.newArrayList();
        CompoundTag compoundtag = p_40942_.getTag();
        if (compoundtag != null && compoundtag.contains("ChargedProjectiles", 9)) {
            ListTag listtag = compoundtag.getList("ChargedProjectiles", 10);
            if (listtag != null) {
                for(int i = 0; i < listtag.size(); ++i) {
                    CompoundTag compoundtag1 = listtag.getCompound(i);
                    list.add(ItemStack.of(compoundtag1));
                }
            }
        }
        return list;
    }

}
