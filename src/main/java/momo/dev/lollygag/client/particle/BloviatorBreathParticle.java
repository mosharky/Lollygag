package momo.dev.lollygag.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;

// Ported from Cloud Storage by Alexthe668
public class BloviatorBreathParticle extends TextureSheetParticle {
    private static final int[] POSSIBLE_COLORS = {0xEDF4F6, 0xD0DBE2, 0xC0C5C7, 0xB1CEE0};

    private BloviatorBreathParticle(ClientLevel level, double x, double y, double z, double motionX, double motionY, double motionZ) {
        super(level, x, y, z, motionX, motionY, motionZ);
        int color = selectColor(this.random);
        setColor((float) (color >> 16 & 255) / 255.0F, (float) (color >> 8 & 255) / 255.0F, (float) (color & 255) / 255.0F);
        this.quadSize *= 0.4F + this.random.nextFloat() * 0.4F;
        this.lifetime = 25 + this.random.nextInt(6);
        this.xd *= 0.1F;
        this.yd *= 0.1F;
        this.zd *= 0.1F;
        this.xd += motionX;
        this.yd += motionY;
        this.zd += motionZ;
        this.setAlpha(1);
    }

    private static int selectColor(RandomSource random) {
        return POSSIBLE_COLORS[random.nextInt(POSSIBLE_COLORS.length - 1)];
    }

    @Override
    public void tick() {
        super.tick();
        float ageScale = age / (float) lifetime;
        this.setAlpha(1F - ageScale);
    }

    // Ignores block collision
    @Override
    public void move(double x, double y, double z) {
        this.setBoundingBox(this.getBoundingBox().move(x, y, z));
        this.setLocationFromBoundingbox();
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Provider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            BloviatorBreathParticle particle = new BloviatorBreathParticle(level, x, y, z, xSpeed, ySpeed, zSpeed);
            particle.pickSprite(spriteSet);
            return particle;
        }
    }
}
