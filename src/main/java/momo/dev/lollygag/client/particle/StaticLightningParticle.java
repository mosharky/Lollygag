package momo.dev.lollygag.client.particle;

import com.github.alexthe666.citadel.client.render.LightningBoltData;
import com.github.alexthe666.citadel.client.render.LightningRender;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector4f;

// Ported from Cloud Storage. The particle's speed is used as the bolt's end point
public class StaticLightningParticle extends Particle {
    private final float toX;
    private final float toY;
    private final float toZ;
    private final LightningRender lightningRender = new LightningRender();

    private StaticLightningParticle(ClientLevel level, double x, double y, double z, float toX, float toY, float toZ) {
        super(level, x, y, z);
        this.setSize(1, 1);
        this.gravity = 0.0F;
        this.lifetime = 5 + this.random.nextInt(3);
        this.toX = toX;
        this.toY = toY;
        this.toZ = toZ;
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.CUSTOM;
    }

    @Override
    public void render(VertexConsumer vertexConsumer, Camera camera, float partialTick) {
        Vec3 cameraPos = camera.getPosition();
        PoseStack poseStack = new PoseStack();
        MultiBufferSource.BufferSource bufferSource = Minecraft.getInstance().renderBuffers().bufferSource();
        float f = (float) (Mth.lerp(partialTick, this.xo, this.x) - cameraPos.x());
        float f1 = (float) (Mth.lerp(partialTick, this.yo, this.y) - cameraPos.y());
        float f2 = (float) (Mth.lerp(partialTick, this.zo, this.z) - cameraPos.z());
        float ageProgress = (this.age + partialTick) / (float) this.lifetime;
        float scale = 1.85F;
        poseStack.pushPose();
        poseStack.translate(f, f1, f2);
        poseStack.scale(scale, scale, scale);
        LightningBoltData.BoltRenderInfo boltRenderInfo = new LightningBoltData.BoltRenderInfo(0.5F, 0.1F, 0.5F, 0.85F, new Vector4f(0.3F, 0.45F, 0.6F, (1.0F - ageProgress) * 0.8F), 0.1F);
        LightningBoltData bolt = new LightningBoltData(boltRenderInfo, Vec3.ZERO, new Vec3(toX, toY, toZ), 4)
                .size(0.05F)
                .lifespan(this.lifetime)
                .spawn(LightningBoltData.SpawnFunction.CONSECUTIVE);
        lightningRender.update(this, bolt, partialTick);
        lightningRender.render(partialTick, poseStack, bufferSource);

        bufferSource.endBatch();
        poseStack.popPose();
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new StaticLightningParticle(level, x, y, z, (float) xSpeed, (float) ySpeed, (float) zSpeed);
        }
    }
}
