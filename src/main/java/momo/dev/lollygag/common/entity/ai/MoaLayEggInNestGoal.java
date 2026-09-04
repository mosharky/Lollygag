package momo.dev.lollygag.common.entity.ai;

import com.aetherteam.aether.client.AetherSoundEvents;
import com.aetherteam.aether.entity.passive.Moa;
import com.aetherteam.aether.event.AetherEventDispatch;
import com.aetherteam.aether.event.EggLayEvent;
import com.teamabnormals.incubation.common.block.BirdNestBlock;
import com.teamabnormals.incubation.common.block.EmptyNestBlock;
import momo.dev.lollygag.mixin.aether.MoaAccessor;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class MoaLayEggInNestGoal extends MoveToBlockGoal {
    private final Moa moa;
    private int eggCounter;

    public MoaLayEggInNestGoal(Moa moa, double speedIn) {
        super(moa, speedIn, 16);
        this.moa = moa;
    }

    public boolean canUse() {
        return this.canEggBeLaid() && super.canUse();
    }

    public boolean canContinueToUse() {
        return this.canEggBeLaid() && super.canContinueToUse();
    }

    protected int nextStartTick(PathfinderMob creatureIn) {
        return 40;
    }

    public void start() {
        super.start();
        this.eggCounter = this.adjustedTickDelay(30);
    }

    public void tick() {
        super.tick();
        if (this.isReachedTarget() && this.canEggBeLaid()) {
            this.eggCounter = Math.max(0, this.eggCounter - 1);
            if (this.eggCounter <= 0) {
                BlockPos blockpos = this.blockPos.above();
                BlockState blockstate = this.moa.level().getBlockState(blockpos);
                Block block = blockstate.getBlock();
                if (block instanceof EmptyNestBlock) {
                    this.moa.level().setBlock(blockpos, ((EmptyNestBlock)block).getNest(moa.getMoaType().egg().getItem()).defaultBlockState(), 3);
                    this.resetBird();
                } else if (block instanceof BirdNestBlock && ((BirdNestBlock)block).getEgg() == this.moa.getMoaType().egg().getItem()) {
                    int i = (Integer)blockstate.getValue(BirdNestBlock.EGGS);
                    if (i < 6) {
                        this.moa.level().setBlock(blockpos, (BlockState)blockstate.setValue(BirdNestBlock.EGGS, i + 1), 3);
                        this.resetBird();
                    }
                }
            }
        }
    }

    private boolean canEggBeLaid() {
        return !this.moa.isBaby() && !this.moa.hasPassenger() && ((MoaAccessor) this.moa).lollygag$getEggTime() < 400;
    }

    private void resetBird() {
        RandomSource random = this.moa.getRandom();
        EggLayEvent eggLayEvent = AetherEventDispatch.onLayEgg(this.moa, AetherSoundEvents.ENTITY_MOA_EGG.get(), 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, moa.getMoaType().egg());
        if (!eggLayEvent.isCanceled()) {
            if (eggLayEvent.getSound() != null) {
                this.moa.playSound(eggLayEvent.getSound(), eggLayEvent.getVolume(), eggLayEvent.getPitch());
            }
            ((MoaAccessor) this.moa).lollygag$setEggTime(this.moa.getEggTime());
        }
    }

    protected boolean isValidTarget(LevelReader worldIn, BlockPos pos) {
        BlockState blockstate = worldIn.getBlockState(pos.above());
        Block block = blockstate.getBlock();
        return block instanceof EmptyNestBlock || block instanceof BirdNestBlock && ((BirdNestBlock)block).getEgg() == this.moa.getMoaType().egg().getItem() && (Integer)blockstate.getValue(BirdNestBlock.EGGS) < 6;
    }
}
