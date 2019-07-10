package como.taco;

import org.lwjgl.input.Keyboard;

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
public class MobAura extends Modules{
	private double total = 10000000;
	private double subtract;
	public MobAura() {
		super("MobAura", Keyboard.KEY_R);
	}
	 
	private void attack(Entity mob) {
		mc.player.swingArm(EnumHand.MAIN_HAND);
		mc.playerController.attackEntity(mc.player,mob);
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
	
	private void reset() {
		total = 10000000;
	}
	
	@Override
	public void onUpdate() {
		//mc.world.loadedEntityList;
		System.out.println();
		
		if(getStatus()) {
			while(total > 0) {
				total--;
			}
			if(getClosestEntity(mc.playerController.getBlockReachDistance()) != null)
				attack(getClosestEntity(mc.playerController.getBlockReachDistance()));
		}
		
		
	}

	@Override
	public void onRender() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		
	}

}
