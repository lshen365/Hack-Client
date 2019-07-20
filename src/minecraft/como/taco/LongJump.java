package como.taco;

import como.taco.Events.Event;

import org.lwjgl.input.Keyboard;

import como.taco.GUI.ModCategories;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;

public class LongJump extends Hack {

	public boolean speedTick;
	public boolean prevOnGround = true;

	public LongJump() {
		super("LongJump", Keyboard.KEY_B, ModCategories.MOVEMENT);

	}

	@Override
	public void onDisable() {
		mc.player.setSprinting(false);
	}

	@Override
	public void onEnable() {
		mc.player.setSprinting(true);
	}

	@Override
	public void onUpdate() {
		if (!this.getStatus()) {
			return;
		}
		if (prevOnGround && mc.player.onGround) {
			speedTick = !speedTick;
		}
		prevOnGround = mc.player.onGround;
		if (mc.gameSettings.keyBindForward.isKeyDown() || mc.gameSettings.keyBindBack.isKeyDown()
				|| mc.gameSettings.keyBindRight.isKeyDown()
				|| mc.gameSettings.keyBindLeft.isKeyDown() && mc.gameSettings.keyBindJump.isKeyDown()) {
			final float dir = mc.player.rotationYaw + ((mc.player.moveForward < 0.0f) ? 180 : 0)
					+ ((mc.player.moveStrafing > 0.0f) ? (-90.0f
							* ((mc.player.moveForward < 0.0f) ? -0.5f : ((mc.player.moveForward > 0.0f) ? 0.5f : 1.0f)))
							: 0.0f)
					- ((mc.player.moveStrafing < 0.0f) ? (-90.0f
							* ((mc.player.moveForward < 0.0f) ? -0.5f : ((mc.player.moveForward > 0.0f) ? 0.5f : 1.0f)))
							: 0.0f);
			final float xDir = (float) Math.cos((dir + 90.0f) * (float) Math.PI / 180.0f);
			final float zDir = (float) Math.sin((dir + 90.0f) * (float) Math.PI / 180.0f);
			if (mc.player.isCollidedVertically && (mc.gameSettings.keyBindForward.isKeyDown()
					|| mc.gameSettings.keyBindBack.isKeyDown() || mc.gameSettings.keyBindRight.isKeyDown()
					|| mc.gameSettings.keyBindLeft.isKeyDown() && mc.gameSettings.keyBindJump.isKeyDown())) {
				mc.player.motionX = xDir * 0.2873f;
				mc.player.motionZ = zDir * 0.2873f;

			}
			if ((mc.player.motionY == 0.33319999363422365)
					&& (mc.gameSettings.keyBindForward.isKeyDown() || mc.gameSettings.keyBindBack.isKeyDown()
							|| mc.gameSettings.keyBindRight.isKeyDown() || mc.gameSettings.keyBindLeft.isKeyDown())) {
				if (speedTick) {
					if (mc.player.isPotionActive(MobEffects.SPEED)) {
						mc.player.motionX = xDir * 1.34;
						mc.player.motionZ = zDir * 1.34;
					} else {
						System.out.println("Test SpeedTick Successful");
						mc.player.motionX = xDir * 1.261;
						mc.player.motionZ = zDir * 1.261;
					}
				} else if (mc.player.isPotionActive(MobEffects.SPEED)) {
					mc.player.motionX = xDir * 0.5;
					mc.player.motionZ = zDir * 0.5;
				} else {
					mc.player.motionX = xDir * 0.3;
					mc.player.motionZ = zDir * 0.3;
				}
			}
		}

	}

	@Override
	public void onRender() {
		// TODO Auto-generated method stub

	}

	public void Event(Event e) {

	}

	@Override
	public void changeVariable(float num) {
		// TODO Auto-generated method stub

	}
}
