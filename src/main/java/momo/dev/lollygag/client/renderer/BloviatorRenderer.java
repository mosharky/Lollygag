package momo.dev.lollygag.client.renderer;

import com.github.alexthe666.citadel.client.render.LightningBoltData;
import com.github.alexthe666.citadel.client.render.LightningRender;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.math.Axis;
import momo.dev.lollygag.Lollygag;
import momo.dev.lollygag.client.model.BloviatorModel;
import momo.dev.lollygag.common.entity.BloviatorEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import org.joml.Vector4f;

import java.util.HashMap;
import java.util.Map;

// Ported from Cloud Storage by Alexthe668
public class BloviatorRenderer extends MobRenderer<BloviatorEntity, BloviatorModel> {
    private static final ResourceLocation TEXTURE = Lollygag.loc("textures/entity/bloviator/bloviator.png");
    private static final ResourceLocation BLOWING_TEXTURE = Lollygag.loc("textures/entity/bloviator/bloviator_blowing.png");
    private static final ResourceLocation THUNDER_TEXTURE = Lollygag.loc("textures/entity/bloviator/bloviator_thunder.png");
    private static final ResourceLocation STATIC_TEXTURE = Lollygag.loc("textures/entity/bloviator/bloviator_static.png");
    // entityTranslucent without depth writes, so the beam's faded end doesn't cut holes in the clouds rendered after it
    private static final RenderType BEAM = RenderType.create("lollygag_bloviator_beam", DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 1536, true, true,
            RenderType.CompositeState.builder()
                    .setShaderState(RenderStateShard.RENDERTYPE_ENTITY_TRANSLUCENT_SHADER)
                    .setTextureState(new RenderStateShard.TextureStateShard(Lollygag.loc("textures/entity/bloviator/bloviator_beam.png"), false, false))
                    .setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
                    .setCullState(RenderStateShard.NO_CULL)
                    .setLightmapState(RenderStateShard.LIGHTMAP)
                    .setOverlayState(RenderStateShard.OVERLAY)
                    .setWriteMaskState(RenderStateShard.COLOR_WRITE)
                    .createCompositeState(true));
    // The number of trailing clouds depends on the bloviator's size, so there's one model per cloud count
    private static final Map<Integer, BloviatorModel> CLOUD_COUNT_TO_MODEL = new HashMap<>();
    private final LightningRender lightningRender = new LightningRender();

    public BloviatorRenderer(EntityRendererProvider.Context context) {
        super(context, new BloviatorModel(5), 0.3F);
        this.addLayer(new ThunderLayer(this));
    }

    @Override
    protected void scale(BloviatorEntity entity, PoseStack poseStack, float partialTick) {
        float f = entity.getCloudScale();
        poseStack.scale(f, f, f);
    }

    @Override
    public boolean shouldRender(BloviatorEntity entity, Frustum frustum, double x, double y, double z) {
        if (super.shouldRender(entity, frustum, x, y, z)) {
            return true;
        } else {
            Entity push = entity.getPushingEntity();
            Entity shock = entity.getShockingEntity();
            if (push != null && (frustum.isVisible(push.getBoundingBox()) || push == Minecraft.getInstance().player)) {
                return true;
            }
            if (shock != null && (frustum.isVisible(shock.getBoundingBox()) || shock == Minecraft.getInstance().player)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void render(BloviatorEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        this.model = CLOUD_COUNT_TO_MODEL.computeIfAbsent(entity.getCloudCount(), BloviatorModel::new);

        Entity blow = entity.getPushingEntity();
        Entity struck = entity.getShockingEntity();
        double x = Mth.lerp(partialTicks, entity.xOld, entity.getX());
        double y = Mth.lerp(partialTicks, entity.yOld, entity.getY());
        double z = Mth.lerp(partialTicks, entity.zOld, entity.getZ());
        if (struck != null) {
            poseStack.pushPose();
            Vec3 toVec = struck.position().subtract(x, y, z);
            float alpha = (entity.getShockTime()) / 5F;
            int segCount = 2 + entity.getCloudCount() * 2;
            LightningBoltData.BoltRenderInfo lightningBoltData = new LightningBoltData.BoltRenderInfo(0.15F, 0.1F, 0.5F, 0.85F, new Vector4f(0.3F, 0.45F, 0.6F, alpha * 0.5F), 0.1F);
            LightningBoltData bolt = new LightningBoltData(lightningBoltData, Vec3.ZERO, toVec, segCount)
                    .size(0.1F + 0.2F * entity.getCloudScale())
                    .lifespan(5)
                    .spawn(LightningBoltData.SpawnFunction.CONSECUTIVE);
            lightningRender.update(entity, bolt, partialTicks);
            lightningRender.render(partialTicks, poseStack, buffer);
            poseStack.popPose();
        }
        if (blow != null) {
            poseStack.pushPose();
            Vec3 vec3 = entity.getMouthVec(partialTicks);
            Vec3 vec31 = vec3.subtract(x, y, z);
            double d3 = Mth.lerp(partialTicks, blow.xOld, blow.getX());
            double d4 = Mth.lerp(partialTicks, blow.yOld, blow.getY()) + blow.getEyeHeight();
            double d5 = Mth.lerp(partialTicks, blow.zOld, blow.getZ());
            float f6 = (float) (vec3.x - d3);
            float f7 = (float) (vec3.y - d4);
            float f8 = (float) (vec3.z - d5);
            poseStack.translate(vec31.x, vec31.y, vec31.z);
            renderBeam(f6, f7, f8, partialTicks, entity.tickCount, poseStack, buffer, packedLight, entity.getCloudScale(), entity.getPushProgress(partialTicks));
            poseStack.popPose();
        }
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

    private static void renderBeam(float dx, float dy, float dz, float partialTicks, int tickCount, PoseStack poseStack, MultiBufferSource buffer, int packedLight, float intensity, float alpha) {
        float horizontalDist = Mth.sqrt(dx * dx + dz * dz);
        float dist = Mth.sqrt(dx * dx + dy * dy + dz * dz);
        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotation((float) (-Math.atan2(dz, dx)) + ((float) Math.PI / 2F)));
        poseStack.mulPose(Axis.XN.rotation((float) (-Math.atan2(horizontalDist, dy)) - ((float) Math.PI / 2F)));
        VertexConsumer consumer = buffer.getBuffer(BEAM);
        float v0 = ((float) tickCount + partialTicks) * -0.04F * intensity;
        float v1 = dist / 16.0F + ((float) tickCount + partialTicks) * -0.04F * intensity;
        float prevX = 0F;
        float prevY = 0.2F;
        float prevU = 0.0F;
        int startAlpha = (int) (alpha * 255);
        PoseStack.Pose pose = poseStack.last();
        Matrix4f matrix4f = pose.pose();
        for (int j = 1; j <= 8; ++j) {
            float x = Mth.cos((float) Math.PI + (float) j * ((float) Math.PI * 2F) / 8.0F) * 0.75F;
            float y = Mth.sin((float) Math.PI + (float) j * ((float) Math.PI * 2F) / 8.0F) * 0.75F;
            float u = (float) j / 4.0F;
            consumer.addVertex(matrix4f, prevX * 0.2F, prevY * 0.2F, 0.0F).setColor(255, 255, 255, startAlpha).setUv(prevU, v0).setOverlay(OverlayTexture.NO_OVERLAY).setLight(packedLight).setNormal(pose, 0.0F, -1.0F, 0.0F);
            consumer.addVertex(matrix4f, prevX, prevY * intensity, dist - 0.5F).setColor(255, 255, 255, 0).setUv(prevU, v1).setOverlay(OverlayTexture.NO_OVERLAY).setLight(packedLight).setNormal(pose, 0.0F, -1.0F, 0.0F);
            consumer.addVertex(matrix4f, x, y * intensity, dist - 0.5F).setColor(255, 255, 255, 0).setUv(u, v1).setOverlay(OverlayTexture.NO_OVERLAY).setLight(packedLight).setNormal(pose, 0.0F, -1.0F, 0.0F);
            consumer.addVertex(matrix4f, x * 0.2F, y * 0.2F, 0.0F).setColor(255, 255, 255, startAlpha).setUv(u, v0).setOverlay(OverlayTexture.NO_OVERLAY).setLight(packedLight).setNormal(pose, 0.0F, -1.0F, 0.0F);
            prevX = x;
            prevY = y;
            prevU = u;
        }
        poseStack.popPose();
    }

    @Override
    protected float getFlipDegrees(BloviatorEntity entity) {
        return 0.0F;
    }

    @Override
    public ResourceLocation getTextureLocation(BloviatorEntity entity) {
        return entity.isPushing() ? BLOWING_TEXTURE : TEXTURE;
    }

    private static class ThunderLayer extends RenderLayer<BloviatorEntity, BloviatorModel> {
        public ThunderLayer(BloviatorRenderer renderer) {
            super(renderer);
        }

        @Override
        public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, BloviatorEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
            int overlay = LivingEntityRenderer.getOverlayCoords(entity, 0.0F);
            VertexConsumer sheenConsumer = buffer.getBuffer(RenderType.entityTranslucent(THUNDER_TEXTURE));
            this.getParentModel().renderToBuffer(poseStack, sheenConsumer, packedLight, overlay, FastColor.ARGB32.colorFromFloat(entity.getTransformProgress(partialTicks), 1.0F, 1.0F, 1.0F));
            if (entity.isThundery()) {
                VertexConsumer staticConsumer = buffer.getBuffer(RenderType.entityTranslucent(STATIC_TEXTURE));
                float staticAlpha = entity.getChargeTimeLerp(partialTicks) / entity.getMaxChargeTime();
                this.getParentModel().renderToBuffer(poseStack, staticConsumer, 240, overlay, FastColor.ARGB32.colorFromFloat(staticAlpha, 1.0F, 1.0F, 1.0F));
            }
        }
    }
}
