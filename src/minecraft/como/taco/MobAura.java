package como.taco;

import org.lwjgl.input.Keyboard;

import como.taco.GUI.ModCategories;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.EnumHand;
import net.minecraft.client.multiplayer.PlayerControllerMP;
public class MobAura extends Hack{
	private long Time0; //Keeps track of current Time
	private long Time1; //Secondary time used later for subtraction
	private EntityLivingBase target;
	
	//private float yaw,pitch;
	public MobAura() {
		super("MobAura", Keyboard.KEY_R,ModCategories.COMBAT);
	}
	 
	private void attack(Entity mob) {
		mc.player.swingArm(EnumHand.MAIN_HAND);
		mc.playerController.attackEntity(mc.player,mob);

	}
	
	private long getCurrentTime() {
		return System.currentTimeMillis();
	}
	

	public EntityLivingBase getClosestEntity(double range) {
		EntityLivingBase target = null;
		for(Object obj:mc.world.loadedEntityList) {
			Entity entity = (Entity)obj;
			if(entity instanceof EntityMob) {
				double distanceToMob = mc.player.getDistanceToEntity(entity);
				if(distanceToMob <= range) {
					target = (EntityLivingBase)entity;
					return target;
				}
			}
		}
		return null;
	}
	
	@Override
	public void onUpdate() {
		
		if(getStatus()) {
			Time0 = getCurrentTime();
			target = getClosestEntity(mc.playerController.getBlockReachDistance());
			if( target != null){
				if(Time0 - Time1 > 100) {
					attack(target);
					Time1 = getCurrentTime();
				}
			}
		}

		
	}

	@Override
	public void onRender() {
		
	}
	
	@Override
	public void onDisable() {

		
	}

}
