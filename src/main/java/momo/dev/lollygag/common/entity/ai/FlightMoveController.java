package momo.dev.lollygag.common.entity.ai;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.phys.Vec3;

// Ported from Cloud Storage by Alexthe668. Licensed under GPL-3.0, see LICENSE
public class FlightMoveController extends MoveControl {
    private final Mob parentEntity;
    private final float maxTurnY;

    public FlightMoveController(Mob mob, float maxTurnY) {
        super(mob);
        this.parentEntity = mob;
        this.maxTurnY = maxTurnY;
    }

    @Override
    public void tick() {
        if (this.operation == MoveControl.Operation.STRAFE) {
            Vec3 vector3d = new Vec3(this.strafeRight, 0, -this.strafeForwards).yRot((float) (-parentEntity.getYRot() * (180F / Math.PI)));
            double d5 = vector3d.length();
            parentEntity.setDeltaMovement(parentEntity.getDeltaMovement().add(vector3d.scale(this.speedModifier * 0.5F / d5)));

            this.operation = MoveControl.Operation.WAIT;
        } else if (this.operation == MoveControl.Operation.MOVE_TO) {
            Vec3 vector3d = new Vec3(this.wantedX - parentEntity.getX(), this.wantedY - parentEntity.getY(), this.wantedZ - parentEntity.getZ());
            double d5 = vector3d.length();
            if (d5 < 0.3) {
                this.operation = MoveControl.Operation.WAIT;
                parentEntity.setDeltaMovement(parentEntity.getDeltaMovement().scale(0.5D));
            } else {
                parentEntity.setDeltaMovement(parentEntity.getDeltaMovement().add(vector3d.scale(this.speedModifier * 0.05D / d5)));
                float f = -((float) Mth.atan2(vector3d.x, vector3d.z)) * (180F / (float) Math.PI);
                this.mob.setYRot(this.rotlerp(this.mob.getYRot(), f, this.maxTurnY));
                parentEntity.yBodyRot = parentEntity.getYRot();
            }
        }
    }

    public void stop() {
        this.operation = MoveControl.Operation.WAIT;
    }
}
