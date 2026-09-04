package momo.dev.lollygag.common;

import com.aetherteam.aether.entity.passive.Moa;
import com.teamabnormals.incubation.common.entity.ai.goal.LayEggInNestGoal;
import momo.dev.lollygag.Lollygag;
import momo.dev.lollygag.common.entity.ai.MoaLayEggInNestGoal;
import net.minecraft.world.entity.Entity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;

@EventBusSubscriber(modid = Lollygag.MODID)
public class LEvents {

    @SubscribeEvent
    public static void onEntityJoinWorld(EntityJoinLevelEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Moa moa) {
            if (moa.goalSelector.getAvailableGoals().stream().noneMatch((goal) -> goal.getGoal() instanceof LayEggInNestGoal)) {
                moa.goalSelector.addGoal(7, new MoaLayEggInNestGoal(moa, 1.0D));
            }
        }
    }
}
