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
        entity.shoot((double) vector3f.x(), (double) vector3f.y(), (double) vector3f.z(), 1.6f, 1f);

    }

    private static Vec3 reflectVector(Vec3 incident, Vec3 normal) {
        double dot = incident.dot(normal);
        return incident.subtract(normal.scale(2 * dot));
    }
}