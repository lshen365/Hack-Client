package como.taco;

import org.lwjgl.input.Keyboard;

import como.taco.GUI.ModCategories;

public class Speed extends Hack {

	public Speed() {
		super("Speed", Keyboard.KEY_V,ModCategories.MOVEMENT);
	}

	public void onUpdate() {
		mc.player.setSprinting(true);
		if (mc.player.onGround) {
			mc.player.jump();
		} else {
			mc.player.motionY = -0.5;
		}
	}

	@Override
	public void onDisable() {
		mc.player.setSprinting(false);

	}

	@Override
	public void onEnable() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRender() {
		// TODO Auto-generated method stub

	}

	@Override
	public void changeVariable(int num) {
		// TODO Auto-generated method stub

	}

}