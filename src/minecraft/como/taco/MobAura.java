package como.taco;

import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import org.lwjgl.input.Keyboard;

import como.taco.GUI.ModCategories;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.network.play.client.CPacketEntityAction;



public class MobAura extends Hack {
	private long Time0; // Keeps track of current Time
	private long Time1; // Secondary time used later for subtraction
	private EntityLivingBase target;
	private int delayTimer = 500;
	private float [] rotations;
	private boolean targetPlayers = true, targetAnimals = true, targetMobs = false; // If false that means don't attack
																					// them

	public MobAura(){
		super("KillAura", Keyboard.KEY_R, ModCategories.COMBAT);
		setTimer();
		
	}

	public void setTimer() {
		try {
			String computerName = System.getProperty("user.name");
			String filePath = "C:\\Users\\" + computerName + "\\Appdata\\Roaming\\.minecraft\\GUISettings.txt";
			File readFile = new File(filePath);
			Scanner readInput = new Scanner(readFile);
			readInput.useDelimiter(" ");
			while (readInput.hasNextLine()) {
				readInput.next();
				delayTimer = (int) (1000.0f / Float.parseFloat(readInput.next()));
			}
		} catch (FileNotFoundException e) {
			System.out.println("Failed to find file");
		}
	}

	private void attack(Entity mob) {
		EntityUtil.faceEntityClient((EntityLivingBase)mob);
		if(Math.random() < 0.2D) {
			System.out.println("Missed");
		}else {
			mc.player.swingArm(EnumHand.MAIN_HAND);
			mc.playerController.attackEntity(mc.player,mob);
		}
		
		


	}
	
	private void missAttack() {
		mc.player.swingArm(EnumHand.MAIN_HAND);
	}

	private long getCurrentTime() {
		return System.currentTimeMillis();

	}

	public EntityLivingBase getClosestEntity(double range) {
		EntityLivingBase target = null;
		
		for (Object obj : mc.world.loadedEntityList) {
			Entity entity = (Entity) obj;
			if (entity instanceof EntityLivingBase) {
				EntityLivingBase thing = (EntityLivingBase) entity;

				double distanceToThing = mc.player.getDistanceToEntity(thing);
				
				if(thing.isEntityAlive() && thing != mc.player && isValidEntity(thing) && distanceToThing <= range && thing.getHealth() > 0 && !isInvisible(thing) && !EntityUtil.isFriend(thing.getName())) {
					if(target == null || mc.player.getDistanceToEntity(thing) < mc.player.getDistanceToEntity(target))
						target = thing;
				}
			}
		}
		return target;
		
	}

	private boolean isValidEntity(EntityLivingBase obj) {
		if (targetPlayers == false && isPlayer(obj)) {
			return false;
		}
		else if (targetAnimals == false && isAnimal(obj)) {
			return false;
		}
		else if (targetMobs == false && isMob(obj)) {
			return false;
		}
		return true;
	}

	private boolean isPlayer(EntityLivingBase thing) {
		if (thing instanceof EntityPlayer) {
			return true;
		}
		return false;
	}

	private boolean isAnimal(EntityLivingBase thing) {
		if (thing instanceof EntityAnimal) {
			return true;
		}
		return false;
	}

	private boolean isMob(EntityLivingBase thing) {
		if (thing instanceof EntityMob) {
			return true;
		}
		return false;
	}
	
	public boolean isInvisible(Entity thing) {
		return thing.isInvisibleToPlayer(mc.player);
	}

	public void changeAnimal() {
		targetAnimals = !targetAnimals;
	}

	public void changeMob() {
		targetMobs = !targetMobs;
	}

	public void changePlayer() {
		targetPlayers = !targetPlayers;
	}

	public boolean getAnimal() {
		return targetAnimals;
	}

	public boolean getMobs() {
		return targetMobs;
	}

	public boolean getPlayers() {
		return targetPlayers;
	}

	public void editDelay(int num) {
		delayTimer = 1000 / num;
	}
	
	private int speedRandomizer(int min) {

		int range = (1000/delayTimer) - min ;
		int random = (int)((Math.random() * range) + (min)); 
		if(random == 0) {
			return delayTimer;
		}
		return 1000/random;
	}

	public String getStatus(boolean name) {
		if (name) {
			return "Enabled";
		}
		return "Disabled";
	}

//	private boolean fovRange() {
//		if(mc.player.getFovModifier())
//	}
	
	@Override
	public void onUpdate() {

		if (getStatus()) {
			mc.gameSettings.viewBobbing = false;
			Time0 = getCurrentTime();
			target = getClosestEntity(mc.playerController.getBlockReachDistance());
			if (target != null && mc.player.canEntityBeSeen(target) && !target.isEntityInsideOpaqueBlock()) {
				int delay = speedRandomizer((1000/delayTimer)-2);
				if (Time0 - Time1 > delay) {
					attack(target);
					Time1 = getCurrentTime();
					
				}
			}
		}

	}
	
	public void faceEntity(Entity thing) {
//		double x = thing.posX - mc.player.posX;
//		double y = thing.posY + (thing.getEyeHeight()/1.4D) - mc.player.posY+ (mc.player.getEyeHeight() / 1.4D);
//		double z = thing.posZ - mc.player.posZ;
//		double calculations = MathHelper.sqrt_double(x*x + z*z);
//		float yaw = (float) (Math.atan2(z, x) * 180.0D / Math.PI) - 90.0F;
//		float pitch = (float) -(Math.atan2(y, calculations) * 180.0D / Math.PI);
//        if(z < 0 && x < 0) {
//             yaw =(float)(90D + Math.toDegrees(Math.atan(z / x)));
//        } else if(z < 0 && x > 0) {
//        	System.out.println("Run");
//             pitch =(float)(-90D + Math.toDegrees(Math.atan(z / x)));
//        }
//		return new float[]{
//			mc.player.rotationYaw + MathHelper.wrapDegrees(yaw - mc.player.rotationYaw),
//   		 mc.player.rotationPitch + MathHelper.wrapDegrees(pitch - mc.player.rotationPitch)
//		};
		double x = thing.posX - mc.player.posX;
		double z = thing.posZ - mc.player.posZ;
		double y = thing.posY + (thing.getEyeHeight()/1.4D) - mc.player.posY + (mc.player.getEyeHeight()/1.4D);
		double helper = MathHelper.sqrt_double(x * x + z * z);

		float newYaw = (float)((Math.toDegrees(-Math.atan(x / z))));
		float newPitch = (float)-Math.toDegrees(Math.atan(y / helper));

		if(z < 0 && x < 0) { newYaw = (float)(90D + Math.toDegrees(Math.atan(z / x))); }
		else if(z < 0 && x > 0) { newYaw = (float)(-90D + Math.toDegrees(Math.atan(z / x))); }

		mc.player.rotationYaw = newYaw; 
		mc.player.rotationPitch = newPitch;
		mc.player.rotationYawHead = newPitch;
	}

	@Override
	public void onRender() {

	}

	@Override
	public void onDisable() {
		mc.gameSettings.viewBobbing = true;
	}

	@Override
	public void changeVariable(float num) {
		editDelay((int) num);

	}

	@Override
	public void onEnable() {
		onUpdate();
	}

}
