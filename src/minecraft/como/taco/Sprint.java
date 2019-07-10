package como.taco;

import org.lwjgl.input.Keyboard;

import como.taco.GUI.ModCategories;

public class Sprint extends Hack {

	public Sprint() {
		super("Sprint", Keyboard.KEY_C, ModCategories.MOVEMENT);

	}

	public void onUpdate() {
		if ((mc.player.movementInput.moveForward > 0) && getStatus()) {
			mc.player.setSprinting(true);
		}
//Not Necessary			
//		}else {
//			mc.player.capabilities.setPlayerWalkSpeed(0f);
//
//		}
	}

	@Override
	public void onRender() {
		/**
		 * Nothing Rendered
		 */
	}

	@Override
	public void onDisable() {
		mc.player.setSprinting(false);

	}

}
