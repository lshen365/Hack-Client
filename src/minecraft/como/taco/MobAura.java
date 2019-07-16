package como.taco;

import java.util.ArrayList;

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
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.client.multiplayer.PlayerControllerMP;
public class MobAura extends Hack{
	private long Time0; //Keeps track of current Time
	private long Time1; //Secondary time used later for subtraction
	private EntityLivingBase target;
	private int delayTimer = 500; 
	private boolean targetPlayers = true, targetAnimals = true, targetMobs = true; //If false that means don't attack them
	private ArrayList <EntityLivingBase> mobs = new ArrayList <EntityLivingBase>();
	public MobAura() {
		super("KillAura", Keyboard.KEY_R,ModCategories.COMBAT);
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
			if(entity instanceof EntityLivingBase) {
				EntityLivingBase thing = (EntityLivingBase) entity;
				if(thing.isEntityAlive() && thing != mc.player) {
					double distanceToThing = mc.player.getDistanceToEntity(thing);
					if(distanceToThing <= range) {
						target = (EntityLivingBase)thing;
						if(!targetPlayers && !targetAnimals && !targetMobs)
							return null;
						
						if(targetPlayers && targetAnimals && targetMobs)
							return thing;
						
						if(targetPlayers) {
							if(targetMobs&&!targetAnimals) {
								if(!isAnimal(thing))
									return thing;
								mc.world.removeEntity(thing);
								return null;
							}else if(!targetMobs && targetAnimals) {
								if(!isMob(thing))
									return thing;
								mc.world.removeEntity(thing);
								return null;
							}else if(!targetMobs && !targetAnimals) {
								if(!isMob(thing) && !isAnimal(thing))
									return thing;
								mc.world.removeEntity(thing);
								return null;
							}
						}else if(!targetPlayers) {
							if(targetMobs && targetAnimals) {
								 if(!isPlayer(thing))
									 return thing;
								 mc.world.removeEntity(thing);
								 return null;
							}else if(targetMobs && !targetAnimals) {
								if(!isAnimal(thing) && !isPlayer(thing))
									return thing;
								mc.world.removeEntity(thing);
								
								return null;
							}else if(!targetMobs && targetAnimals) {
								if(!isPlayer(thing) && !isMob(thing))
									return thing;
								mc.world.removeEntity(thing);
								return null;
							}
						}
						
							
							
							return target;
						
						
					}
				}
				
			}
		}
		return null;
	}
	
	private boolean isPlayer(EntityLivingBase thing) {
		if(thing instanceof EntityPlayer) {
			return true;
		}
		return false;
	}

	public void setTargetPlayers(boolean targetPlayers) {
		this.targetPlayers = targetPlayers;
	}

	private boolean isAnimal(EntityLivingBase thing) {
		if(thing instanceof EntityAnimal) {
			return true;
		}
		return false;
	}

	public void setTargetAnimals(boolean targetAnimals) {
		this.targetAnimals = targetAnimals;
	}

	private boolean isMob(EntityLivingBase thing) {
		if(thing instanceof EntityMob) {
			return true;
		}
		return false;
	}

	public void setTargetMobs(boolean targetMobs) {
		this.targetMobs = targetMobs;
	}

	public void editDelay(int num) {
		delayTimer = 1000/num;


	}
	
	@Override
	public void onUpdate() {
		
		if(getStatus()) {
			Time0 = getCurrentTime();
			target = getClosestEntity(mc.playerController.getBlockReachDistance());
			if( target != null){
				if(Time0 - Time1 > delayTimer) { //100 = 10 Hits per sec
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

	@Override
	public void changeVariable(int num) {
		editDelay(num);

		
	}

	@Override
	public void onEnable() {
		onUpdate();
	}



}
