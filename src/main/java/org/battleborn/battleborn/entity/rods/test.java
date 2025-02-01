package org.battleborn.battleborn.entity.rods;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.Vec3;
import net.minecraft.core.Direction;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class test {

    public static void reflectEntityView(blazeRodEntity entity, Direction blockFace) {

        Vec3 lookVec = entity.getLookAngle();

        Vec3 normal = new Vec3(blockFace.getStepX(), blockFace.getStepY(), blockFace.getStepZ());

        Vec3 vector3f = reflectVector(lookVec, normal);
        Projectile projectile = new blazeRodEntity(entity.level(), entity.getOwner(), entity.getOwner().getX(), entity.getOwner().getEyeY() - (double) 0.15F, entity.getOwner().getZ());
        entity.remove(Entity.RemovalReason.KILLED);
        projectile.shoot((double) vector3f.x(), (double) vector3f.y(), (double) vector3f.z(), 3.15f, 1f);

        //entity.setYRot((float) Math.toDegrees(Math.atan2(reflectedVec.z, reflectedVec.x)) - 90.0F);
        // entity.setXRot((float) Math.toDegrees(Math.asin(reflectedVec.y)));
    }

    private static Vec3 reflectVector(Vec3 incident, Vec3 normal) {
        double dot = incident.dot(normal);
        return incident.subtract(normal.scale(2 * dot));
    }
}