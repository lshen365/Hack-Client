package como.taco;

import org.lwjgl.input.Keyboard;

import como.taco.Events.EventPreMotion;
import como.taco.GUI.ModCategories;

public class Flight extends Hack {

	public double startY;
	public float flightSpeed = 3f;
	public boolean isBypass = true;

	public Flight() {
		super("Flying", Keyboard.KEY_F, ModCategories.MOVEMENT);
	}

	public void onDisable() {
		mc.player.capabilities.isFlying = false;
	}

	@Override
	public void onUpdate() {
		if (!isBypass) {
			if (getStatus()) {
				mc.player.capabilities.isFlying = true;
			}
		} else {
			if (startY > mc.player.posY) {
				mc.player.motionY = 0.1;
			}
		}
	}

	public void Event(como.taco.Events.Event e) {
		if (e instanceof EventPreMotion && isBypass) {
			((EventPreMotion) e).onGround = true;
		}

	}

	@Override
	public void onRender() {
		// TODO Auto-generated method stub

	}

	@Override
	public void changeVariable(float num) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onEnable() {
		startY = mc.player.posY;

	}
}
