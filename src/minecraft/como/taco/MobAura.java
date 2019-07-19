package como.taco;

import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
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
import net.minecraft.client.multiplayer.PlayerControllerMP;

public class MobAura extends Hack {
	private long Time0; // Keeps track of current Time
	private long Time1; // Secondary time used later for subtraction
	private EntityLivingBase target;
	private int delayTimer = 500;

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
		mc.player.swingArm(EnumHand.MAIN_HAND);
		mc.playerController.attackEntity(mc.player, mob);

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
				if (thing.isEntityAlive() && thing != mc.player && isValidEntity(thing) && distanceToThing <= range) {
					return thing;
				}
			}
		}
		return null;
	}

	private boolean isValidEntity(EntityLivingBase obj) {
		if (targetPlayers == false && isPlayer(obj)) {
			return false;
		}
		if (targetAnimals == false && isAnimal(obj)) {
			return false;
		}
		if (targetMobs == false && isMob(obj)) {
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

	public String getStatus(boolean name) {
		if (name) {
			return "Enabled";
		}
		return "Disabled";
	}

	@Override
	public void onUpdate() {

		if (getStatus()) {
			Time0 = getCurrentTime();
			target = getClosestEntity(mc.playerController.getBlockReachDistance());
			if (target != null) {

				if (Time0 - Time1 > delayTimer) { // 100 = 10 Hits per sec
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
	public void changeVariable(float num) {
		editDelay((int) num);

	}

	@Override
	public void onEnable() {
		onUpdate();
	}

}
