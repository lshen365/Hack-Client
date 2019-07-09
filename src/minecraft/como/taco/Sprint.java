package como.taco;

import org.lwjgl.input.Keyboard;

public class Sprint extends Modules{

	public Sprint() {
		super("Sprint", Keyboard.KEY_C);
		
	}
	
	public void onUpdate() {
		super.onUpdate();
		if((mc.player.movementInput.moveForward > 0) && getStatus() == true) {
			mc.player.setSprinting(true);
		}else {
			mc.player.setSprinting(false);

		}
	}
}
