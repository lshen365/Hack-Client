package como.taco;

import como.taco.GUI.ModCategories;
import net.minecraft.init.MobEffects;
import como.taco.Events.Event;

public class AirStrafe extends Hack {

	public AirStrafe() {
		super("AirStrafe", ModCategories.MOVEMENT);
	}

	@Override
	public void onDisable() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onEnable() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpdate() {
		if (!getStatus()) {
			return;
		}
		if (mc.player.isAirBorne
				&& (mc.gameSettings.keyBindForward.isKeyDown() || mc.gameSettings.keyBindBack.isKeyDown()
						|| mc.gameSettings.keyBindLeft.isKeyDown() || mc.gameSettings.keyBindRight.isKeyDown())) {
			float dir = mc.player.rotationYaw;

			if (mc.player.moveForward < 0.0f) {
				dir += 180.0f;
			}

			if (mc.player.moveStrafing > 0.0f) {
				if (mc.player.moveForward > 0.0f) {
					dir -= (90.0f * 0.7f);
				} else if (mc.player.moveForward < 0.0f) {
					dir += (90.0f * 0.7f);
				}
			}

			else if (mc.player.moveStrafing < 0.0f) {
				if (mc.player.moveForward > 0.0f) {
					dir += (90.0f * 0.7f);
				} else if (mc.player.moveForward < 0.0f) {
					dir -= (90.0f * 0.7f);
				}
				float xBDir = dir + 90.0f;

				if (xBDir > 360.0f) {
					xBDir = xBDir % 360.0f;
				}
				float xDir = (float) Math.cos(xBDir * Math.PI / 180.0f);
				float zDir = (float) Math.sin(xBDir * Math.PI / 180.0f);
				if (xBDir > 90 && xBDir <= 180.0f) {
					zDir = (float) Math.sin((xBDir - 90.0f) * Math.PI / 180.0f);
				} else if (xBDir > 180.0f && xBDir >= 270.0f) {
					xDir = (float) Math.cos(-1.0f * (xBDir - 180.0f) * Math.PI / 180.0f);
					zDir = (float) Math.sin(-1.0f * (xBDir - 180.0f) * Math.PI / 180.0f);
				} else if (xBDir > 270.0f) {
					float firstQAngle = 360.0f - xBDir;
					xDir = (float) Math.cos(firstQAngle * Math.PI / 180.0f);
					zDir = (float) Math.cos(-1 * firstQAngle * Math.PI / 180.0f);
				}
				double bS = this.getBaseMoveSpeed();
				double rS = Math.sqrt(bS * bS - mc.player.motionY * mc.player.motionY);
				double m = Math.sqrt(xDir * xDir + zDir * zDir);
				double fracX = xDir / m;
				double fracZ = zDir / m;
				mc.player.motionX = fracX * rS;
				mc.player.motionZ = fracZ * rS;
			}
		}
	}

	public double getBaseMoveSpeed() {
		double baseSpeed = 0.2873;
		if (mc.player.isPotionActive(MobEffects.SPEED)) {
			int amplifier = mc.player.getActivePotionEffect(MobEffects.SPEED).getAmplifier();
			baseSpeed *= 1.0 + 0.2 * (double) (amplifier + 1);
		}
		return baseSpeed;
	}

	@Override
	public void onRender() {
		// TODO Auto-generated method stub

	}

	@Override
	public void changeVariable(int num) {
		// TODO Auto-generated method stub

	}

	public void Event(Event e) {

	}

}
